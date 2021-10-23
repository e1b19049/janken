package oit.is.z1204.first.janken.controller;

import java.util.ArrayList;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import oit.is.z1204.first.janken.model.Janken;
import oit.is.z1204.first.janken.model.Entry;
import oit.is.z1204.first.janken.model.User;
import oit.is.z1204.first.janken.model.UserMapper;

@Controller
@RequestMapping("/lec02")

public class Lec02Controller {
  @Autowired
  private Entry entry;

  @Autowired
  UserMapper userMapper;

  @GetMapping
  public String lec02(Principal prin, ModelMap model) {
    String name = prin.getName();
    this.entry.addUser(name);
    model.addAttribute("room", this.entry);
    model.addAttribute("num", this.entry.getNumOfUsers());

    ArrayList<User> users = userMapper.selectAllUsers();
    model.addAttribute("users", users);
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
