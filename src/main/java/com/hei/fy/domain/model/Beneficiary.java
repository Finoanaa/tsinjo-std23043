package com.hei.fy.domain.model;

public class Beneficiary {
  private long id;
  private String email;
  private String fullName;

  public Beneficiary() {}

  public Beneficiary(long id, String email, String fullName) {
    this.id = id;
    this.email = email;
    this.fullName = fullName;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }
}
