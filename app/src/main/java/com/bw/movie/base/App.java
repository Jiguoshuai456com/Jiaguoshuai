package com.bw.movie.base;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * @ProjectName: wutenglongApp2
 * @Package: com.bawei.wutenglongapp.base
 * @ClassName: App
 * @Description: java类作用描述
 * @CreateDate: 2020/4/17 10:10
 * @UpdateUser: 武腾龙
 * @UpdateDate: 2020/4/17 10:10
 */
public class App extends Application {

    private static Context applicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = getApplicationContext();

        Fresco.initialize(this);
    }

    public static Context getAppContext(){
        return applicationContext;
    }
}
