package oit.is.z1204.first.janken.model;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class Entry {
  ArrayList<String> users = new ArrayList<>();

  public void addUser(String name) {
    for (String user : this.users) {
      if (user.equals(name)) {
        return;
      }
    }
    this.users.add(name);
  }

  public ArrayList<String> getUsers() {
    return this.users;
  }

  public void setUsers(ArrayList<String> users) {
    this.users = users;
  }

}
