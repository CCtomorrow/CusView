package yong.qing.com.qimingview;

import android.content.Context;

/**
 * <b>Project:</b> yong.qing.com.qimingview <br>
 * <b>Create Date:</b> 2016/5/30 <br>
 * <b>Author:</b> qingyong <br>
 * <b>Description:</b> App <br>
 */
public class Application extends android.app.Application {
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
    }

    public static Context getContext() {
        return sContext;
    }
}
