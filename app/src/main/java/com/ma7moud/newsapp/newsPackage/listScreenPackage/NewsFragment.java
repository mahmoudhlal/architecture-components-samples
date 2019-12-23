package com.ma7moud.newsapp.newsPackage.listScreenPackage;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ma7moud.newsapp.R;
import com.ma7moud.newsapp.databinding.FragmentNewsBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment implements NewsView {

    FragmentNewsBinding mBinding ;
    private NewsPresenter mNewsPresenter;
    public NavController navController ;

    public NewsFragment(){}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentNewsBinding.inflate(inflater,container,false);
        mNewsPresenter = new NewsPresenter(this,this);
        mNewsPresenter.initView();
        mNewsPresenter.makePaginate();
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        mBinding.setTitle(getResources().getString(R.string.app_name));
        mBinding.setIsEmpty(false);
        mBinding.setHomeView(this);
    }

    @Override
    public void onDataComing() {
        mBinding.setIsEmpty(false);
        mBinding.setIsLoading(false);
    }

    @Override
    public void onDataNotComing() {
        mBinding.setIsLoading(false);
    }

    @Override
    public void onNoItemFounded() {
        mBinding.setIsEmpty(true);
        mBinding.setIsLoading(false);
    }

    @Override
    public void onLoadMoreItems() {
        mBinding.setIsLoading(true);
    }

    @Override
    public void onError() {
        mBinding.setIsLoading(false);
    }

    @Override
    public void onFilterClick() {
        mNewsPresenter.openFilterDialog();
    }



}
