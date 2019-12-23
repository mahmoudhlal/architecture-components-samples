package com.ma7moud.newsapp.newsPackage.listScreenPackage;

public interface NewsViewPresenter {
    void initView();
    void callNews();
    void openFilterDialog();
    void makePaginate();
    void setSpinCountry();
    void setSpinSources();
    void callNewsSources();
    void onBtnCancelClick();
    void onBtnFilterClick();
}
