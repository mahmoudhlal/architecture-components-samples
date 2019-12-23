package com.ma7moud.newsapp.newsPackage.listScreenPackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ma7moud.newsapp.databinding.ViewNewsBinding;
import com.ma7moud.newsapp.models.newsResponse.ArticlesItem;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> implements OnAdapterClick{


    private Context context;
    private List<ArticlesItem> articlesItemList ;
    private OnAdapterClick adapterClick ;
    NewsAdapter() {
        articlesItemList = new ArrayList<>() ;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        ViewNewsBinding mNewsBinding =
                ViewNewsBinding.inflate(LayoutInflater.from(context),viewGroup,false);
        return new ViewHolder(mNewsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.mNewsBinding.setArticlesItem(articlesItemList.get(i));
        viewHolder.mNewsBinding.setOnAdapterClick(this);
    }

    @Override
    public int getItemCount() {
        return articlesItemList.size();
    }

    /**
     * update list of adapter
     * @param articlesItemList updated List
     */
    public void setArticlesItemList(List<ArticlesItem> articlesItemList){
        this.articlesItemList = articlesItemList;
        notifyDataSetChanged();
    }

    @Override
    public void onItemClick(ArticlesItem articlesItem) {
        adapterClick.onItemClick(articlesItem);
/*
        Intent intent = new Intent(context , DetailsActivity.class);
        intent.putExtra("data" , articlesItem) ;
        context.startActivity(intent);
*/

    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ViewNewsBinding mNewsBinding ;
        ViewHolder(@NonNull ViewNewsBinding itemView) {
            super(itemView.getRoot());
            mNewsBinding = itemView ;
        }
    }

    public void setAdapterClick(OnAdapterClick onAdapterClick){
        this.adapterClick=onAdapterClick;
    }

    interface OnAdapterClick{
        void onItemClick(ArticlesItem articlesItem);
    }

}
