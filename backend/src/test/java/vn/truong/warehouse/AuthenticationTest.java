package vn.truong.warehouse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.fasterxml.jackson.databind.ObjectMapper;
import vn.truong.warehouse.repository.AppUserRepository;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class AuthenticationTest {
    @Autowired MockMvc mvc;
    @Autowired JdbcTemplate jdbc;
    @Autowired AppUserRepository users;
    @Autowired PasswordEncoder encoder;
    @Autowired ObjectMapper json;
    static final String PASSWORD="Test-password-123!";
    String register() throws Exception {
        String username="auth-"+UUID.randomUUID();
        mvc.perform(post("/api/auth/register").with(csrf()).contentType(MediaType.APPLICATION_JSON)
            .content(json.writeValueAsString(java.util.Map.of("username",username,"fullName","Test Account","password",PASSWORD,"roles",java.util.List.of("ADMIN")))))
            .andExpect(status().isCreated()).andExpect(jsonPath("$.roles[0]").value("VIEWER"))
            .andExpect(content().string(not(containsString("password"))));
        return username;
    }
    MockHttpSession login(String username,String password) throws Exception {
        var token=mvc.perform(get("/api/auth/csrf")).andExpect(status().isOk()).andReturn();
        var session=(MockHttpSession)token.getRequest().getSession();
        var tree=json.readTree(token.getResponse().getContentAsString());
        String before=session.getId();
        var result=mvc.perform(post("/api/auth/login").session(session).header(tree.get("headerName").asText(),tree.get("token").asText())
            .contentType(MediaType.APPLICATION_JSON).content(json.writeValueAsString(java.util.Map.of("username",username,"password",password))))
            .andExpect(status().isOk()).andReturn();
        assertThat(session.getId()).isNotEqualTo(before);
        return (MockHttpSession)result.getRequest().getSession();
    }
    @Test void unauthenticatedRequestsAndMissingCsrfAreRejected() throws Exception {
        mvc.perform(get("/api/materials")).andExpect(status().isUnauthorized());
        mvc.perform(post("/api/auth/register").contentType(MediaType.APPLICATION_JSON).content("{}"))
            .andExpect(status().isForbidden());
        mvc.perform(post("/api/auth/register").with(csrf()).contentType(MediaType.APPLICATION_JSON).content("{\"username\":\"ab\",\"password\":\"short\",\"fullName\":\" \"}"))
            .andExpect(status().isBadRequest());
    }
    @Test void registrationHashesPasswordAndDoesNotAllowPrivilegeEscalation() throws Exception {
        var username=register();var user=users.findByUsername(username).orElseThrow();
        assertThat(user.getPasswordHash()).isNotEqualTo(PASSWORD);assertThat(encoder.matches(PASSWORD,user.getPasswordHash())).isTrue();
        mvc.perform(post("/api/auth/register").with(csrf()).contentType(MediaType.APPLICATION_JSON).content(json.writeValueAsString(java.util.Map.of("username",username,"fullName","Duplicate","password",PASSWORD))))
            .andExpect(status().isConflict());
        var session=login(username,PASSWORD);
        mvc.perform(get("/api/auth/me").session(session)).andExpect(status().isOk()).andExpect(jsonPath("$.username").value(username));
        mvc.perform(get("/api/users").session(session)).andExpect(status().isForbidden());
        mvc.perform(post("/api/categories").session(session).with(csrf()).contentType(MediaType.APPLICATION_JSON).content("{\"code\":\"DENIED\",\"name\":\"Denied\"}"))
            .andExpect(status().isForbidden());
        mvc.perform(post("/api/auth/login").with(csrf()).contentType(MediaType.APPLICATION_JSON).content(json.writeValueAsString(java.util.Map.of("username",username,"password","wrong-password"))))
            .andExpect(status().isUnauthorized());
    }
    @Test void profileCanOnlyChangeOwnNameAndRolesRefreshOnEachRequest() throws Exception {
        String username=register();var session=login(username,PASSWORD);
        mvc.perform(put("/api/auth/me").session(session).contentType(MediaType.APPLICATION_JSON).content("{\"fullName\":\"Changed\"}"))
            .andExpect(status().isForbidden());
        mvc.perform(put("/api/auth/me").session(session).with(csrf()).contentType(MediaType.APPLICATION_JSON).content("{\"fullName\":\"Changed\",\"isActive\":false,\"roles\":[\"ADMIN\"],\"username\":\"admin\"}"))
            .andExpect(status().isOk()).andExpect(jsonPath("$.fullName").value("Changed")).andExpect(jsonPath("$.username").value(username)).andExpect(jsonPath("$.roles[0]").value("VIEWER"));
        Long id=users.findByUsername(username).orElseThrow().getId();
        jdbc.update("INSERT INTO user_role(user_id,role_id) SELECT ?,id FROM app_role WHERE code='CLERK'",id);
        mvc.perform(post("/api/categories").session(session).with(csrf()).contentType(MediaType.APPLICATION_JSON).content("{\"code\":\"AUTH-"+UUID.randomUUID().toString().substring(0,20)+"\",\"name\":\"Permitted\"}"))
            .andExpect(status().isCreated());
        jdbc.update("DELETE FROM user_role WHERE user_id=?", id);
        mvc.perform(get("/api/materials").session(session)).andExpect(status().isForbidden());
        mvc.perform(get("/api/auth/me").session(session)).andExpect(status().isOk());
    }
    @Test void passwordChangeRevokesOtherSessionsAndOldCredentials() throws Exception {
        var username=register();var first=login(username,PASSWORD);var second=login(username,PASSWORD);
        mvc.perform(put("/api/auth/password").session(first).with(csrf()).contentType(MediaType.APPLICATION_JSON).content(json.writeValueAsString(java.util.Map.of("currentPassword","incorrect","newPassword","New-password-123!"))))
            .andExpect(status().isUnauthorized());
        mvc.perform(put("/api/auth/password").session(first).with(csrf()).contentType(MediaType.APPLICATION_JSON).content(json.writeValueAsString(java.util.Map.of("currentPassword",PASSWORD,"newPassword","New-password-123!"))))
            .andExpect(status().isOk());
        assertThat(first.isInvalid()).isTrue();
        mvc.perform(get("/api/auth/me").session(second)).andExpect(status().isUnauthorized());
        var current=login(username,"New-password-123!");
        var user=users.findByUsername(username).orElseThrow();user.setIsActive(false);users.saveAndFlush(user);
        mvc.perform(get("/api/auth/me").session(current)).andExpect(status().isUnauthorized());
    }
    @Test void logoutClearsSession() throws Exception {
        var username=register();var session=login(username,PASSWORD);
        mvc.perform(post("/api/auth/logout").session(session).with(csrf())).andExpect(status().isOk());
        assertThat(session.isInvalid()).isTrue();
    }
}
