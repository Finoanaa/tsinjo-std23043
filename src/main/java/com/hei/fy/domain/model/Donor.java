package com.hei.fy.domain.model;

public class Donor {
  private String email;
  private String fullName;

  public Donor() {}

  public Donor(String email, String fullName) {
    this.email = email;
    this.fullName = fullName;
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
