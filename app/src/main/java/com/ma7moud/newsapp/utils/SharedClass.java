package com.ma7moud.newsapp.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.widget.Toast;

import com.ma7moud.newsapp.R;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class SharedClass {

    private static Context mContext;
    private static SharedClass mInstance ;
    private Dialog progressDialog ;



    public static synchronized SharedClass getInstance(Context context) {
        mContext = context ;
        if (mInstance == null) {
            mInstance = new SharedClass();
        }
        return mInstance;
    }

    public void handleException(Throwable t) {
        if (t instanceof SocketTimeoutException)
            makeToast("Internet error");
        else if (t instanceof UnknownHostException)
            makeToast("connection error");
        else if (t instanceof ConnectException)
            makeToast("connection error");
        else
            makeToast(t.getLocalizedMessage());

    }


    public void makeToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    public  void showWaiting() {
        progressDialog = new Dialog(mContext);
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progressDialog.setContentView(R.layout.view_waiting);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public  void dismissDialog(){
        progressDialog.dismiss();
    }


}
