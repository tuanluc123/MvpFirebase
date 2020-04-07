package com.maituanluc.basic1.ui.base;

public interface MvpPresenter<V extends MvpView> {
    void onAttach(V mvpView);
}
