package com.hei.fy.repository;

import com.hei.fy.domain.model.Donation;
import com.hei.fy.domain.model.Donor;
import com.hei.fy.domain.model.Payment;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class DonationDao {

  private final JdbcTemplate jdbcTemplate;
  private DonorDao donorDao = null;
  private PaymentDao paymentDao = null;

  public DonationDao(JdbcTemplate jdbcTemplate, DonorDao donorDao, PaymentDao paymentDao) {
    this.jdbcTemplate = jdbcTemplate;
    this.donorDao = donorDao;
    this.paymentDao = paymentDao;
  }

  private final RowMapper<Donation> donationRowMapper =
      (rs, rowNum) -> {
        Donation donation = new Donation();
        donation.setId(rs.getLong("id"));
        Donor donor = donorDao.findByEmail(rs.getString("donor_email"));
        Optional<Payment> paymentOpt = paymentDao.findById(rs.getString("payment_id"));
        donation.setDonor(donor);
        paymentOpt.ifPresent(donation::setPayment); // <- ici c'est paymentOpt, pas donationOpt
        return donation;
      };

  public List<Donation> findAllOrderedByDateDesc() {
    String sql =
        """
        SELECT d.id, donor.email as donor_email, d.payment_id
        FROM donation d
        JOIN donor donor ON d.donor_id = donor.email
        ORDER BY (SELECT payment_date FROM payment WHERE id = d.payment_id) DESC
        """;
    return jdbcTemplate.query(sql, donationRowMapper);
  }

  public void save(Donation donation) {
    // suppose donor and payment already saved
    String sql = "INSERT INTO donation(donor_id, payment_id) VALUES (?, ?)";
    jdbcTemplate.update(sql, donation.getDonor().getEmail(), donation.getPayment().getId());
  }
}
