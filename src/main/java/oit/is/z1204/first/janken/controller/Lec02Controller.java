package oit.is.z1204.first.janken.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.z1204.first.janken.model.Janken;

@Controller
@RequestMapping("/lec02")

public class Lec02Controller {
  @PostMapping
  public String lec02(@RequestParam String name, ModelMap model) {
    model.addAttribute("name", name);
    return "lec02.html";
  }

  @GetMapping
  public String lec02Direct() {
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
