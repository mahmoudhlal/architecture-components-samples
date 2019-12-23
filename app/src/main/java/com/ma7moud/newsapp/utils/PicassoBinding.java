package com.ma7moud.newsapp.utils;

import androidx.databinding.BindingAdapter;
import android.widget.ImageView;

import com.ma7moud.newsapp.R;
import com.squareup.picasso.Picasso;

public class PicassoBinding {

    @BindingAdapter("imageUrl")
    public static void setImage(ImageView image,String url){
        Picasso.get().load(url).
                placeholder(image.getContext().getResources().getDrawable(R.drawable.ic_launcher_background)).
                into(image);
    }
}
