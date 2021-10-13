package oit.is.z1204.first.janken.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import oit.is.z1204.first.janken.model.Janken;

@Controller
@RequestMapping("/lec02")

public class Lec02Controller {
  @GetMapping
  public String lec02() {
    return "lec02.html";
  }

  @GetMapping("/result/{userHand}")
  public String syouhai(@PathVariable String userHand, ModelMap model) {
    Janken janken = new Janken();
    janken.setUserHand(userHand);

    model.addAttribute("userHand", userHand);
    model.addAttribute("CPUHand", janken.getCPUHand());
    model.addAttribute("result", janken.getResult());
    return "lec02.html";
  }
}
