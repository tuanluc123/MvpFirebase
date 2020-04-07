package com.maituanluc.basic1.ui.user;

import com.maituanluc.basic1.ui.base.MvpView;

import java.util.List;

public interface UserMvpView extends MvpView {
    void openMainActivity();
    void listUser(List list);
}
