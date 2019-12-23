package com.ma7moud.newsapp.Remote;


public class ApiUtils {

    public static NewsService getNewsService() {
        return RetrofitClient.getClient().create(NewsService.class);
    }



}