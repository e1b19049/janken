package oit.is.z1204.first.janken.model;

public class Janken {

  public String whichWin(String userHand) {
    if (userHand.equals("gu")) {
      return "あいこ";
    }
    if (userHand.equals("choki")) {
      return "まけ";
    }
    if (userHand.equals("pa")) {
      return "かち";
    }
    return "エラー";
  }

  public String tranceHand(String hand) {
    if (hand.equals("gu")) {
      return "グー";
    }
    if (hand.equals("choki")) {
      return "チョキ";
    }
    if (hand.equals("pa")) {
      return "パー";
    }
    return "エラー";
  }
}
