package com.ma7moud.newsapp.newsPackage.listScreenPackage;

public interface NewsView {
    void onDataComing();
    void onDataNotComing();
    void onNoItemFounded();
    void onLoadMoreItems();
    void onError();
    void onFilterClick();
}
