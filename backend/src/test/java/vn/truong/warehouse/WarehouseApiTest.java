package vn.truong.warehouse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import java.util.UUID;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

/**
 * Kiểm thử API danh mục bằng quyền ADMIN giả lập trong môi trường test: tạo/sửa, phân trang, validation và giới hạn của API chỉ đọc.
 */
@org.springframework.security.test.context.support.WithMockUser(roles="ADMIN")
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class WarehouseApiTest {
    @Autowired MockMvc mvc;
    @Autowired JdbcTemplate jdbc;
    /**
     * Gọi API để kiểm tra tạo/sửa nhóm, báo lỗi trường thiếu và mã trùng mà không lộ câu SQL.
     */
    @Test void catalogCanBeCreatedEditedAndValidated() throws Exception {
        String code = "API-" + UUID.randomUUID();
        String response = mvc.perform(post("/api/categories").with(csrf()).contentType(MediaType.APPLICATION_JSON).content("{\"code\":\""+code+"\",\"name\":\"Test category\"}"))
            .andExpect(status().isCreated()).andExpect(jsonPath("$.code").value(code)).andReturn().getResponse().getContentAsString();
        long id = new com.fasterxml.jackson.databind.ObjectMapper().readTree(response).get("id").asLong();
        mvc.perform(put("/api/categories/"+id).with(csrf()).contentType(MediaType.APPLICATION_JSON).content("{\"code\":\""+code+"\",\"name\":\"Updated\"}"))
            .andExpect(status().isOk()).andExpect(jsonPath("$.name").value("Updated"));
        mvc.perform(post("/api/categories").with(csrf()).contentType(MediaType.APPLICATION_JSON).content("{}"))
            .andExpect(status().isBadRequest()).andExpect(jsonPath("$.fields.code").exists());
        mvc.perform(post("/api/categories").with(csrf()).contentType(MediaType.APPLICATION_JSON).content("{\"code\":\""+code+"\",\"name\":\"Duplicate\"}"))
            .andExpect(status().isConflict()).andExpect(content().string(not(containsString("INSERT"))));
    }
    /**
     * Kiểm tra giới hạn phân trang, ID không tồn tại, endpoint chỉ đọc và số liệu tổng quan.
     */
    @Test void pagingMissingResourcesAndReadonlyRoutesAreHandled() throws Exception {
        mvc.perform(get("/api/materials?size=201")).andExpect(status().isBadRequest());
        mvc.perform(get("/api/materials?page=-1")).andExpect(status().isBadRequest());
        mvc.perform(get("/api/materials/9223372036854775807")).andExpect(status().isNotFound());
        mvc.perform(get("/api/units")).andExpect(status().isOk()).andExpect(jsonPath("$.content",hasSize(greaterThanOrEqualTo(2))));
        mvc.perform(post("/api/documents").with(csrf()).contentType(MediaType.APPLICATION_JSON).content("{}"))
            .andExpect(status().isMethodNotAllowed());
        mvc.perform(post("/api/user-roles").with(csrf()).contentType(MediaType.APPLICATION_JSON).content("{}"))
            .andExpect(status().isMethodNotAllowed());
        mvc.perform(get("/api/dashboard")).andExpect(status().isOk()).andExpect(jsonPath("$.materials").isNumber());
    }
    /**
     * Chèn JSON audit có dữ liệu nhạy cảm giả lập, rồi kiểm tra API không trả dữ liệu đó.
     */
    @Test void auditApiDoesNotExposeJsonPayloads() throws Exception {
        jdbc.update("INSERT INTO audit_log(entity_type, entity_id, action, old_data, new_data) VALUES ('TEST',1,'UPDATE',JSON_OBJECT('password','secret-marker'),JSON_OBJECT('token','secret-marker'))");
        mvc.perform(get("/api/audit-logs?size=200")).andExpect(status().isOk()).andExpect(content().string(not(containsString("secret-marker"))));
    }
}
