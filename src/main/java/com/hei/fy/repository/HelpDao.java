package com.hei.fy.repository;

import com.hei.fy.domain.model.Beneficiary;
import com.hei.fy.domain.model.Help;
import com.hei.fy.domain.model.Payment;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class HelpDao {

  private final JdbcTemplate jdbcTemplate;
  private BeneficiaryDao beneficiaryDao = null;
  private PaymentDao paymentDao = null;

  public HelpDao(JdbcTemplate jdbcTemplate, BeneficiaryDao beneficiaryDao, PaymentDao paymentDao) {
    this.jdbcTemplate = jdbcTemplate;
    this.beneficiaryDao = beneficiaryDao;
    this.paymentDao = paymentDao;
  }

  private final RowMapper<Help> helpRowMapper =
      (rs, rowNum) -> {
        Help help = new Help();
        help.setId(rs.getLong("id"));
        Optional<Beneficiary> beneficiary = beneficiaryDao.findById(rs.getLong("beneficiary_id"));
        Optional<Payment> payment = paymentDao.findById(rs.getString("payment_id"));
        help.setBeneficiary(beneficiary);
        help.setPayment(payment);
        help.setDescription(rs.getString("description"));
        return help;
      };

  public List<Help> findAllOrderedByDateDesc() {
    String sql =
        """
        SELECT h.id, h.beneficiary_id, h.payment_id, h.description
        FROM help h
        ORDER BY (SELECT payment_date FROM payment WHERE id = h.payment_id) DESC
        """;
    return jdbcTemplate.query(sql, helpRowMapper);
  }

  public void save(Help help) {
    String sql = "INSERT INTO help(beneficiary_id, payment_id, description) VALUES (?, ?, ?)";
    jdbcTemplate.update(
        sql, help.getBeneficiary().getId(), help.getPayment().getId(), help.getDescription());
  }
}
