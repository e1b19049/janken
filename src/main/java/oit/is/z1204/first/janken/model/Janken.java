package oit.is.z1204.first.janken.model;

public class Janken {
  String userHand;
  String CPUHand = "gu";
  String result;

  public String getResult() {
    if (this.userHand.equals("gu")) {
      return this.result = "draw";
    }
    if (this.userHand.equals("choki")) {
      return this.result = "win";
    }
    if (this.userHand.equals("pa")) {
      return this.result = "lose";
    }
    return "error";
  }

  public String getUserHand() {
    return userHand;
  }

  public void setUserHand(String userHand) {
    this.userHand = userHand;
  }

  public String getCPUHand() {
    return CPUHand;
  }

  public void setCPUHand(String cPUHand) {
    CPUHand = cPUHand;
  }

  public void setResult(String result) {
    this.result = result;
  }
}
