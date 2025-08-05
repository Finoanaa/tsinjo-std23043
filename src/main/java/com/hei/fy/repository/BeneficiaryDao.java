package com.hei.fy.repository;

import com.hei.fy.domain.model.Beneficiary;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class BeneficiaryDao {

  private final JdbcTemplate jdbcTemplate;

  public BeneficiaryDao(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  private final RowMapper<Beneficiary> beneficiaryRowMapper =
      new RowMapper<>() {
        @Override
        public Beneficiary mapRow(ResultSet rs, int rowNum) throws SQLException {
          Beneficiary beneficiary = new Beneficiary();
          beneficiary.setId(rs.getLong("id"));
          beneficiary.setEmail(rs.getString("email"));
          beneficiary.setFullName(rs.getString("full_name"));
          return beneficiary;
        }
      };

  public List<Beneficiary> findAll() {
    String sql = "SELECT id, email, full_name FROM beneficiary ORDER BY id DESC";
    return jdbcTemplate.query(sql, beneficiaryRowMapper);
  }

  public Optional<Beneficiary> findById(long id) {
    String sql = "SELECT id, email, full_name FROM beneficiary WHERE id = ?";
    List<Beneficiary> list = jdbcTemplate.query(sql, beneficiaryRowMapper, id);
    if (list.isEmpty()) {
      return Optional.empty();
    }
    return Optional.of(list.get(0));
  }

  public int save(Beneficiary beneficiary) {
    String sql = "INSERT INTO beneficiary (email, full_name) VALUES (?, ?)";
    return jdbcTemplate.update(sql, beneficiary.getEmail(), beneficiary.getFullName());
  }

  public int update(Beneficiary beneficiary) {
    String sql = "UPDATE beneficiary SET email = ?, full_name = ? WHERE id = ?";
    return jdbcTemplate.update(
        sql, beneficiary.getEmail(), beneficiary.getFullName(), beneficiary.getId());
  }

  public int deleteById(long id) {
    String sql = "DELETE FROM beneficiary WHERE id = ?";
    return jdbcTemplate.update(sql, id);
  }
}
