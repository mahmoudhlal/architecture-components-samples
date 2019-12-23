package com.ma7moud.newsapp.newsPackage.listScreenPackage;

import android.app.AlertDialog;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.ma7moud.newsapp.models.CountryModel.Country;
import com.ma7moud.newsapp.models.newsResponse.ArticlesItem;
import com.ma7moud.newsapp.models.newsResponse.NewsResponse;
import com.ma7moud.newsapp.models.SourcesResponse.SourcesItem;
import com.ma7moud.newsapp.models.SourcesResponse.SourcesResponse;
import com.ma7moud.newsapp.R;
import com.ma7moud.newsapp.Remote.ApiUtils;
import com.ma7moud.newsapp.Remote.NewsService;
import com.ma7moud.newsapp.utils.Constants;
import com.ma7moud.newsapp.utils.SharedClass;
import com.ma7moud.newsapp.databinding.DialogFilterBinding;

import java.util.ArrayList;
import java.util.List;

import hari.bounceview.BounceView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsPresenter implements NewsViewPresenter {
    private NewsFragment context;
    private NewsAdapter mNewsAdapter ;
    private LinearLayoutManager layoutManager ;
    private boolean mLoading = false;
    private int breDy = 0;
    private int page = 1;
    private NewsService newsService ;
    private List<ArticlesItem> articlesItemList ;
    private String countryCode , countryName , sourceCode ,  sourceName ;
    private ArrayAdapter<Country> countryAdapter ;
    private ArrayAdapter<SourcesItem> sourcesItemAdapter ;
    private List<SourcesItem> sourcesItemList ;
    private NewsView view ;
    private DialogFilterBinding mFilterBinding ;
    private AlertDialog dialog ;


    NewsPresenter(NewsFragment context, NewsView view) {
        this.context = context;
        this.view=view;
    }

    /**
     * initView main method of this class
     */
    @Override
    public void initView() {
        newsService = ApiUtils.getNewsService();
        articlesItemList = new ArrayList<>() ;
        sourcesItemList = new ArrayList<>() ;

        layoutManager = new LinearLayoutManager(context.getContext());
        context.mBinding.recycleNews.setLayoutManager(layoutManager);
        context.mBinding.recycleNews.setHasFixedSize(true);
        mNewsAdapter = new NewsAdapter();
        context.mBinding.recycleNews.setAdapter(mNewsAdapter);
        mNewsAdapter.setAdapterClick(articlesItem -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("data" , articlesItem);
            context.navController.navigate(R.id.action_newsFragment_to_detailsFragment , bundle);
        });

        countryCode="US";
        sourceCode=null;
        callNews();
    }

    /**
     * get news article from api using Retrofit
     */
    @Override
    public void callNews() {
        view.onLoadMoreItems();
        Call<NewsResponse> call = newsService.getNews(Constants.getInstance().API_KEY , countryCode , sourceCode , page);
        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (response.isSuccessful()){
                    if (response.body().getStatus().equals("ok")){
                        if (page == 1) {
                            articlesItemList.clear();
                        }
                        page++;
                        mLoading = false;
                        articlesItemList.addAll(response.body().getArticles());
                        mNewsAdapter.setArticlesItemList(articlesItemList);

                        if (articlesItemList.size() == 0) {
                            view.onNoItemFounded();
                        } else {
                            view.onDataComing();
                        }

                    }else {
                        view.onError();
                        Toast.makeText(context.getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    view.onError();
                    Toast.makeText(context.getContext(), response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                view.onDataNotComing();
                SharedClass.getInstance(context.getContext()).handleException(t);
            }
        });
    }


    /**
     * build filter dialog to filter news article by country or news source
     */
    @Override
    public void openFilterDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context.getContext());
        final LayoutInflater inflater = LayoutInflater.from(context.getContext());
        mFilterBinding = DialogFilterBinding.inflate(inflater);
        dialogBuilder.setCancelable(true);
        //get view from data binding
        dialogBuilder.setView(mFilterBinding.getRoot());

        dialog = dialogBuilder.create();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);

        setSpinCountry();
        setSpinSources();
        callNewsSources();

        //data binding click
        mFilterBinding.setNewsViewPresenter(this);

        dialog.setOnCancelListener(dialog1 -> {
            countryCode="US" ;
            sourceCode=null ;
        });
        BounceView.addAnimTo(dialog);
        dialog.show();
    }



    @Override
    public void onBtnCancelClick() {
        dialog.cancel();
    }

    @Override
    public void onBtnFilterClick() {
        if (!mFilterBinding.radioCountry.isChecked() && !mFilterBinding.radioSource.isChecked()){
            Toast.makeText(context.getContext() , "Please Select Country Or News Source", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mFilterBinding.radioCountry.isChecked() && countryCode.equals("empty")){
            Toast.makeText(context.getContext(), "Select Country", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mFilterBinding.radioSource.isChecked() && sourceCode.equals("empty")){
            Toast.makeText(context.getContext(), "Select News Source", Toast.LENGTH_SHORT).show();
            return;
        }

        if (mFilterBinding.radioCountry.isChecked()) {
            sourceCode = null;
            context.mBinding.setTitle(countryName);
        }else {
            countryCode = null;
            context.mBinding.setTitle(sourceName);
        }

        page=1;
        callNews();
        dialog.dismiss();
    }

    /**
     * make pagination by scroll recyclerView
     */
    @Override
    public void makePaginate() {
        context.mBinding.recycleNews.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                int totalItemCount = layoutManager.getItemCount();
                int visibleItemCount = layoutManager.findLastVisibleItemPosition();
                if (!mLoading && visibleItemCount >= totalItemCount - 1 && page > 1) {
                    mLoading = true;
                    if (dy >= breDy) {
                        breDy = dy;
                    } else {
                        breDy = dy;
                    }
                    callNews();
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }


    /**
     * build spinner to country selection
     */
    @Override
    public void setSpinCountry() {
        countryAdapter = new ArrayAdapter<>
                (context.getContext(), R.layout.text_spinner, Constants.getInstance().COUNTRY_LIST);
        mFilterBinding.spinCountry.setAdapter(countryAdapter);
        mFilterBinding.spinCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                countryCode = Constants.getInstance().COUNTRY_LIST.get(position).getCode();
                countryName = Constants.getInstance().COUNTRY_LIST.get(position).getName();

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    /**
     * build spinner to news sources selection
     */
    @Override
    public void setSpinSources() {
        sourcesItemAdapter = new ArrayAdapter<>
                (context.getContext(), R.layout.text_spinner, sourcesItemList);
        mFilterBinding.spinSources.setAdapter(sourcesItemAdapter);
        mFilterBinding.spinSources.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sourceCode=sourcesItemList.get(position).getId();
                sourceName=sourcesItemList.get(position).getName();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * get  news sources from api by using Retrofit
     */
    @Override
    public void callNewsSources() {
        SharedClass.getInstance(context.getContext()).showWaiting();
        Call<SourcesResponse> call  = newsService.getNewsSources(Constants.getInstance().API_KEY) ;
        call.enqueue(new Callback<SourcesResponse>() {
            @Override
            public void onResponse(Call<SourcesResponse> call, Response<SourcesResponse> response) {
                SharedClass.getInstance(context.getContext()).dismissDialog();
                if (response.isSuccessful()){
                    if (response.body().getStatus().equals("ok")){
                        sourcesItemList.clear();
                        sourcesItemList.add(0 , new SourcesItem("Select News Source" , "empty")) ;
                        sourcesItemList.addAll(response.body().getSources()) ;
                        sourcesItemAdapter.notifyDataSetChanged();
                    }else {
                        Toast.makeText(context.getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(context.getContext(), response.message(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<SourcesResponse> call, Throwable t) {
                SharedClass.getInstance(context.getContext()).dismissDialog();
                SharedClass.getInstance(context.getContext()).handleException(t);
            }
        });
    }
}
