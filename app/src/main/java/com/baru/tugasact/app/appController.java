package com.baru.tugasact.app;


import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;



public class appController extends Application {
    public static final String TAG = appController.class.getSimpleName();

    private RequestQueue mRequestQueue;
    private static appController mInstance;

    @Override
    public void onCreate()
    {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized appController getInstance(){return mInstance;}

    public RequestQueue getRequestQueue()
    {
        if(mRequestQueue == null)
        {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag)
    {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req)
    {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequest(Object tag)
    {
        if (mRequestQueue != null)
        {
            mRequestQueue.cancelAll(tag);
        }
    }
}

