package com.hei.fy.repository;

import com.hei.fy.domain.model.Donor;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class DonorDao {

  private final JdbcTemplate jdbcTemplate;

  public DonorDao(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  private final RowMapper<Donor> donorRowMapper =
      (rs, rowNum) -> {
        Donor d = new Donor();
        d.setEmail(rs.getString("email"));
        d.setFullName(rs.getString("full_name"));
        return d;
      };

  public List<Donor> findAll() {
    return jdbcTemplate.query("SELECT email, full_name FROM donor", donorRowMapper);
  }

  public Donor findByEmail(String email) {
    String sql = "SELECT email, full_name FROM donor WHERE email = ?";
    return jdbcTemplate.queryForObject(sql, donorRowMapper, email);
  }

  public void save(Donor donor) {
    jdbcTemplate.update(
        "INSERT INTO donor(email, full_name) VALUES (?, ?)", donor.getEmail(), donor.getFullName());
  }
}
