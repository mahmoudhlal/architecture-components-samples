package com.ma7moud.newsapp.Remote;


import com.ma7moud.newsapp.models.newsResponse.NewsResponse;
import com.ma7moud.newsapp.models.SourcesResponse.SourcesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsService {

    @GET("top-headlines")
    Call<NewsResponse> getNews(
            @Query("apiKey") String apiKey,
            @Query("country") String country ,
            @Query("sources") String sources ,
            @Query("page") int page
    );

    @GET("sources")
    Call<SourcesResponse> getNewsSources(
            @Query("apiKey") String apiKey
    );


}
