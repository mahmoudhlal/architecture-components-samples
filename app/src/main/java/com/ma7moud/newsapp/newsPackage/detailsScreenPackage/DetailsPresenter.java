package com.ma7moud.newsapp.newsPackage.detailsScreenPackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.ma7moud.newsapp.models.newsResponse.ArticlesItem;

public class DetailsPresenter implements DetailsViewPresenter {

    public ArticlesItem articlesItem ;
    private DetailsFragment activity ;

    DetailsPresenter(DetailsFragment activity) {
        this.activity = activity;

    }

    /**
     * get article data from bundle that passes in previous activity
     * @param bundle
     */
    @Override
    public void getData(Bundle bundle) {
        if (bundle != null){
            articlesItem = (ArticlesItem) bundle.get("data") ;
            //fill layout views by article data

        }
    }

    /**
     * open article on browser
     */
    @Override
    public void openArticleSource() {
        try {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(articlesItem.getUrl()));
            activity.startActivity(browserIntent);
        }catch (Exception e){
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
            activity.startActivity(browserIntent);
        }
    }
}
