package com.hei.fy.repository;

import com.hei.fy.domain.model.Payment;
import com.hei.fy.domain.model.PaymentStatus;
import java.sql.Timestamp;
import java.util.Optional;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentDao {

  private final JdbcTemplate jdbcTemplate;

  public PaymentDao(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  private final RowMapper<Payment> paymentRowMapper =
      (rs, rowNum) -> {
        Payment p = new Payment();
        p.setId(rs.getString("id"));
        p.setDate(rs.getTimestamp("payment_date").toLocalDateTime());
        p.setAmount(rs.getInt("amount"));
        p.setPaymentMethod(rs.getString("payment_method"));
        p.setStatus(PaymentStatus.valueOf(rs.getString("status")));
        return p;
      };

  public Optional<Payment> findById(String id) {
    String sql = "SELECT * FROM payment WHERE id = ?";
    try {
      Payment payment = jdbcTemplate.queryForObject(sql, paymentRowMapper, id);
      return Optional.of(payment);
    } catch (EmptyResultDataAccessException e) {
      return Optional.empty();
    }
  }

  public void save(Payment payment) {
    String sql =
        "INSERT INTO payment(id, payment_date, amount, payment_method, status) VALUES (?, ?, ?, ?,"
            + " ?)";
    jdbcTemplate.update(
        sql,
        payment.getId(),
        Timestamp.valueOf(payment.getDate()),
        payment.getAmount(),
        payment.getPaymentMethod(),
        payment.getStatus().name());
  }
}
