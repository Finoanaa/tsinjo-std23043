package com.hei.fy.controller;

import com.hei.fy.domain.model.Donation;
import com.hei.fy.domain.model.Help;
import com.hei.fy.service.TsinjoService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TsinjoController {

  private final TsinjoService tsinjoService;

  public TsinjoController(TsinjoService tsinjoService) {
    this.tsinjoService = tsinjoService;
  }

  @GetMapping("/")
  public String index(Model model) {
    List<Donation> donations = tsinjoService.getAllDonationsOrdered();
    List<Help> helps = tsinjoService.getAllHelpsOrdered();

    model.addAttribute("donations", donations);
    model.addAttribute("helps", helps);

    return "index";
  }

  @PostMapping("/donate")
  public String submitDonation(
      @RequestParam String donorEmail,
      @RequestParam String donorFullName,
      @RequestParam int amount,
      @RequestParam String paymentMethod) {
    tsinjoService.createDonation(donorEmail, donorFullName, amount, paymentMethod);
    return "redirect:/";
  }
}
