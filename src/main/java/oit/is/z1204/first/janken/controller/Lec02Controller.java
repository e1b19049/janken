package oit.is.z1204.first.janken.controller;

import java.util.ArrayList;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import oit.is.z1204.first.janken.model.Janken;
import oit.is.z1204.first.janken.model.Entry;
import oit.is.z1204.first.janken.model.User;
import oit.is.z1204.first.janken.model.UserMapper;
import oit.is.z1204.first.janken.service.AsyncKekka;
import oit.is.z1204.first.janken.model.Match;
import oit.is.z1204.first.janken.model.MatchMapper;
import oit.is.z1204.first.janken.model.MatchInfo;
import oit.is.z1204.first.janken.model.MatchInfoMapper;

@Controller
@RequestMapping("/lec02")

public class Lec02Controller {
  @Autowired
  private Entry entry;

  @Autowired
  UserMapper userMapper;

  @Autowired
  MatchMapper matchMapper;

  @Autowired
  MatchInfoMapper matchInfoMapper;

  @Autowired
  private AsyncKekka asyncKekka;

  @GetMapping
  @Transactional
  public String lec02(Principal prin, ModelMap model) {
    String name = prin.getName();
    this.entry.addUser(name);
    model.addAttribute("room", this.entry);
    model.addAttribute("num", this.entry.getNumOfUsers());

    // DBに同じ名前が登録されないように工夫
    int flag = 0;
    ArrayList<User> userList = userMapper.selectAllUsers();
    for (User user : userList) {
      if (user.getName().equals(name)) {
        flag = 1;
        break;
      }
    }
    if (flag == 0) {
      User user = new User();
      user.setName(name);
      userMapper.insertUser(user);
    }

    ArrayList<User> users = userMapper.selectAllUsers();
    model.addAttribute("users", users);

    ArrayList<Match> matches = matchMapper.selectAllMatches();
    model.addAttribute("matches", matches);

    ArrayList<MatchInfo> matchInfos = matchInfoMapper.selectAllMatchInfo();
    model.addAttribute("matchInfos", matchInfos);

    return "lec02.html";
  }

  @GetMapping("/waiting")
  @Transactional
  public String wating(@RequestParam String userHand, @RequestParam String user2Name, Principal prin) {
    String user1Name = prin.getName();
    User user1 = userMapper.selectByName(user1Name);
    User user2 = userMapper.selectByName(user2Name);

    // 自分と指定した対戦相手との試合がすでに相手によって作成されていた時
    ArrayList<MatchInfo> matchInfos = matchInfoMapper.selectAllMatchInfo();
    for (MatchInfo matchInfo : matchInfos) {
      if (matchInfo.getUser2() == user1.getId()) {
        Match match = new Match();
        match.setUser1(user1.getId());
        match.setUser2(matchInfo.getUser1());
        match.setUser1Hand(userHand);
        match.setUser2Hand(matchInfo.getUser1Hand());
        match.setActive(true);
        match.setMatchinfo(matchInfo.getId());
        matchMapper.insertMatch(match);
        return "wait.html";
      }
    }

    // 作成されていなかったとき
    MatchInfo matchinfo = new MatchInfo();
    matchinfo.setUser1(user1.getId());
    matchinfo.setUser2(user2.getId());
    matchinfo.setUser1Hand(userHand);
    matchinfo.setActive(true);
    matchInfoMapper.insertMatchInfo(matchinfo);
    return "wait.html";
  }

  @GetMapping("/kekka")
  public SseEmitter kekka() {
    final SseEmitter sseEmitter = new SseEmitter();
    this.asyncKekka.kekka(sseEmitter);
    return sseEmitter;
  }

  @GetMapping("/result/{userHand}")
  @Transactional
  public String syouhai(@PathVariable String userHand, Principal prin, ModelMap model) {
    Janken janken = new Janken();
    janken.setUserHand(userHand);

    Match match = new Match();
    User user1 = new User();
    User user2 = new User();
    user1 = userMapper.selectByName(prin.getName());
    user2 = userMapper.selectByName("CPU");
    match.setUser1(user1.getId());
    match.setUser2(user2.getId());
    match.setUser1Hand(janken.getUserHand());
    match.setUser2Hand(janken.getCPUHand());
    matchMapper.insertMatch(match);

    model.addAttribute("userHand", userHand);
    model.addAttribute("CPUHand", janken.getCPUHand());
    model.addAttribute("result", janken.getResult());
    return "match.html";
  }

  @GetMapping("/match")
  public String match(@RequestParam String name, ModelMap model) {
    model.addAttribute("name", name);
    return "match.html";
  }
}
