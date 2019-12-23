package com.ma7moud.newsapp.newsPackage.detailsScreenPackage;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.ma7moud.newsapp.databinding.FragmentDetailsBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {

    public FragmentDetailsBinding mDetailsBinding ;
    private DetailsPresenter mDetailsPresenter ;
    public DetailsFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDetailsPresenter = new DetailsPresenter(this) ;
        mDetailsPresenter.getData(getArguments());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mDetailsBinding = FragmentDetailsBinding.inflate(inflater,container,false);
        mDetailsBinding.setActivity((AppCompatActivity) getActivity());
        mDetailsBinding.setDetailsViewPresenter(mDetailsPresenter);
        return mDetailsBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDetailsBinding.setArticlesItem(mDetailsPresenter.articlesItem);
    }
}
