package vn.truong.warehouse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import vn.truong.warehouse.entity.*;
import vn.truong.warehouse.dto.*;
import vn.truong.warehouse.mapper.*;
import vn.truong.warehouse.repository.*;
import vn.truong.warehouse.service.*;
import java.math.BigDecimal;
import java.util.UUID;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class WarehouseIntegrationTest {
    @Autowired MaterialService materials;
    @Autowired InventoryBalanceRepository balances;
    @Autowired MaterialUnitService units;
    @Autowired MaterialUnitRepository materialUnits;
    @Autowired JdbcTemplate jdbc;
    @Autowired EntityManager em;
    @Autowired StockViewService views;
    @Autowired AppUserMapper users;
    @Autowired ObjectMapper json;

    private MaterialDto material() {
        Long base = jdbc.queryForObject("SELECT id FROM unit_of_measure WHERE code='CHIEC'", Long.class);
        return materials.create(new MaterialWriteDto("TEST-" + UUID.randomUUID(), "Test material", null, base,
            false, false, false, false, BigDecimal.ZERO, true));
    }

    @Test void materialCreatesZeroBalanceAndCompositeUnitCanBeRead() {
        var m = material();
        var balance = balances.findById(m.id()).orElseThrow();
        assertThat(balance.getQuantityOnHand()).isEqualByComparingTo("0");
        assertThat(balance.getVersion()).isNotNull();
        units.create(new MaterialUnitWriteDto(m.id(), m.baseUnitId(), BigDecimal.ONE, true, true));
        assertThat(materialUnits.findById(new MaterialUnitId(m.id(), m.baseUnitId()))).isPresent();
        assertThatThrownBy(() -> units.update(new MaterialUnitId(m.id(), m.baseUnitId()), new MaterialUnitWriteDto(m.id() + 1, m.baseUnitId(), BigDecimal.ONE, true, true))).isInstanceOf(IllegalArgumentException.class);
        assertThat(views.findMaterialStock(0, 200)).anyMatch(row -> row.materialId().equals(m.id()) && row.quantityOnHand().signum() == 0);
    }

    @Test void allTwentyEntityMappingsCanReadTheirTables() {
        for (var type : em.getMetamodel().getEntities()) {
            em.createQuery("select e from " + type.getName() + " e").setMaxResults(1).getResultList();
        }
        assertThat(em.getMetamodel().getEntities()).hasSize(20);
        assertThat(jdbc.queryForObject("SELECT count(*) FROM app_role", Long.class)).isEqualTo(4);
    }

    @Test void databaseGeneratesRoundedBaseQuantityAndChecksConstraints() {
        var m = material();
        units.create(new MaterialUnitWriteDto(m.id(), m.baseUnitId(), BigDecimal.ONE, true, true));
        jdbc.update("INSERT INTO app_user(username,password_hash,full_name) VALUES (?, ?, ?)", UUID.randomUUID().toString(), "hash-for-test-only", "Tester");
        Long user = jdbc.queryForObject("SELECT MAX(id) FROM app_user", Long.class);
        jdbc.update("INSERT INTO stock_document(document_no,document_type,document_date,created_by) VALUES (?, 'RECEIPT', CURRENT_DATE, ?)", UUID.randomUUID().toString(), user);
        Long document = jdbc.queryForObject("SELECT MAX(id) FROM stock_document", Long.class);
        jdbc.update("INSERT INTO stock_document_line(document_id,line_no,material_id,unit_id,quantity,conversion_factor) VALUES (?,1,?,?,2.500000,3.000000)", document, m.id(), m.baseUnitId());
        em.clear();
        var line = em.createQuery("select l from StockDocumentLine l where l.documentId = :id", StockDocumentLine.class).setParameter("id", document).getSingleResult();
        assertThat(line.getBaseQuantity()).isEqualByComparingTo("7.500000");
        assertThatThrownBy(() -> jdbc.update("UPDATE material SET min_stock=-1 WHERE id=?", m.id())).isInstanceOf(org.springframework.dao.DataAccessException.class)
            .satisfies(ex -> assertThat(((java.sql.SQLException) ex.getCause()).getErrorCode()).isEqualTo(3819));
    }

    @Test void passwordHashIsNotExposedInEntityOrResponseDto() throws Exception {
        var user = new AppUser(); user.setPasswordHash("do-not-expose");
        assertThat(json.writeValueAsString(user)).doesNotContain("do-not-expose", "passwordHash");
        assertThat(json.writeValueAsString(users.toDto(user))).doesNotContain("passwordHash");
    }

    @Test void dtoValidationAndImmutableCompositeKeysAreEnforced() {
        assertThatThrownBy(() -> materials.create(new MaterialWriteDto(null, "bad", null, 1L, false, false, false, false, BigDecimal.ZERO, true)))
            .isInstanceOf(jakarta.validation.ConstraintViolationException.class);
        assertThat(views.findTraceStock(Long.MAX_VALUE)).isEmpty();
        assertThat(materials.findAll(PageRequest.of(0, 10))).isNotNull();
    }
}
