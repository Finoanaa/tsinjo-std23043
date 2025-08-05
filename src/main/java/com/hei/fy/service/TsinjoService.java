package com.hei.fy.service;

import com.hei.fy.domain.model.*;
import com.hei.fy.repository.*;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TsinjoService {

  private final DonorDao donorDao;
  private final DonationDao donationDao;
  private final HelpDao helpDao;
  private final PaymentDao paymentDao;

  public TsinjoService(
      DonorDao donorDao, DonationDao donationDao, HelpDao helpDao, PaymentDao paymentDao) {
    this.donorDao = donorDao;
    this.donationDao = donationDao;
    this.helpDao = helpDao;
    this.paymentDao = paymentDao;
  }

  public List<Donation> getAllDonationsOrdered() {
    return donationDao.findAllOrderedByDateDesc();
  }

  public List<Help> getAllHelpsOrdered() {
    return helpDao.findAllOrderedByDateDesc();
  }

  @Transactional
  public void createDonation(String email, String fullName, int amount, String paymentMethod) {
    // 1. Créer ou retrouver le Donor
    Donor donor = donorDao.findByEmail(email);
    if (donor == null) {
      donor = new Donor(email, fullName);
      donorDao.save(donor);
    }

    // 2. Créer un Payment avec status VERIFYING et date maintenant
    String paymentId = java.util.UUID.randomUUID().toString();
    Payment payment =
        new Payment(paymentId, LocalDateTime.now(), amount, paymentMethod, PaymentStatus.VERIFYING);
    paymentDao.save(payment);

    // 3. Créer Donation liée à Donor et Payment
    Donation donation = new Donation();
    donation.setDonor(donor);
    donation.setPayment(payment);
    donationDao.save(donation);

    // 4. TODO : appeler API Vola pour vérification asynchrone
  }
}
