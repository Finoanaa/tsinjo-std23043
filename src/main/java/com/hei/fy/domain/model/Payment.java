package com.hei.fy.domain.model;

import java.time.LocalDateTime;

public class Payment {
  private String id; // ID venant de Vola
  private LocalDateTime date;
  private int amount; // en Ar ou unité monétaire entière
  private String paymentMethod;
  private PaymentStatus status;

  public Payment() {}

  public Payment(
      String id, LocalDateTime date, int amount, String paymentMethod, PaymentStatus status) {
    this.id = id;
    this.date = date;
    this.amount = amount;
    this.paymentMethod = paymentMethod;
    this.status = status;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public String getPaymentMethod() {
    return paymentMethod;
  }

  public void setPaymentMethod(String paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  public PaymentStatus getStatus() {
    return status;
  }

  public void setStatus(PaymentStatus status) {
    this.status = status;
  }
}
