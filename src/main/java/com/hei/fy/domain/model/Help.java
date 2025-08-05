package com.hei.fy.domain.model;

import java.util.Optional;

public class Help {
  private long id;
  private Beneficiary beneficiary;
  private Payment payment;
  private String description; // description de l’accident couvert

  public Help() {}

  public Help(long id, Beneficiary beneficiary, Payment payment, String description) {
    this.id = id;
    this.beneficiary = beneficiary;
    this.payment = payment;
    this.description = description;
  }

  // getters et setters

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Beneficiary getBeneficiary() {
    return beneficiary;
  }

  public void setBeneficiary(Optional<Beneficiary> beneficiary) {
    this.beneficiary = beneficiary;
  }

  public Payment getPayment() {
    return payment;
  }

  public void setPayment(Optional<Payment> payment) {
    this.payment = payment;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
