package com.lyf.po;

import java.io.Serializable;

/**
 * @Author lyf
 * @Date 2018/10/31 0031 17:22
 */
public class User implements Serializable {

  private static final long serialVersionUID = -6087778283313187571L;
  private String username;
  private String password;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "User{" +
        "username='" + username + '\'' +
        ", password='" + password + '\'' +
        '}';
  }
}
