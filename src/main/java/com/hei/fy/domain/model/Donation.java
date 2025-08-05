package com.hei.fy.domain.model;

public class Donation {
  private long id; // auto-increment en base
  private Donor donor;
  private Payment payment;

  public Donation() {}

  public Donation(long id, Donor donor, Payment payment) {
    this.id = id;
    this.donor = donor;
    this.payment = payment;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Donor getDonor() {
    return donor;
  }

  public void setDonor(Donor donor) {
    this.donor = donor;
  }

  public Payment getPayment() {
    return payment;
  }

  public void setPayment(Payment payment) {
    this.payment = payment;
  }
}
