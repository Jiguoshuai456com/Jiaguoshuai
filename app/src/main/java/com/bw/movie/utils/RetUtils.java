package com.bw.movie.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;


import com.bw.movie.base.App;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetUtils {
    public static final String BASE_URL = "http://mobile.bwstudent.com/movieApi/";
    private Apis apis;

    private RetUtils(){
        initHttp();
    }

    private static class AAA{
        private static final RetUtils RET_UTILS = new RetUtils();
    }

    public static RetUtils getInstance(){
        return AAA.RET_UTILS;
    }

    public boolean isNetWork(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo!=null){
            return true;
        }
        return false;
    }

    public void initHttp() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient build = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(new HandlerInt())
                .addNetworkInterceptor(httpLoggingInterceptor)
                .build();

        Retrofit build1 = new Retrofit.Builder()
                .client(build)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apis = build1.create(Apis.class);
    }
    public Apis getApis(){
        return apis;
    }

    public class HandlerInt implements Interceptor{
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            String userid = SPUtils.getString(App.getAppContext(),SPUtils.NAME,SPUtils.USERID);
            String sessionid = SPUtils.getString(App.getAppContext(),SPUtils.NAME,SPUtils.SESSIONID);

            if (TextUtils.isEmpty(userid) || TextUtils.isEmpty(sessionid)){
                return chain.proceed(request);
            }

            Request build = request.newBuilder()
                    .addHeader("userId", userid)
                    .addHeader("sessionId", sessionid)
                    .build();

            return chain.proceed(build);
        }
    }
}
