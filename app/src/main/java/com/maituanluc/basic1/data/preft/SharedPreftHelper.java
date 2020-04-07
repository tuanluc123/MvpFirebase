package com.maituanluc.basic1.data.preft;

public interface SharedPreftHelper {
    void clear();
    void putEmail(String email);
    String getEmail();
    boolean getLoggedInMode();
    void setLoggedInMode(boolean loggedIn);
}
