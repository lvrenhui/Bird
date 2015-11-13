package com.ninegame.bird.app;

import android.annotation.TargetApi;
import android.app.Application;
import android.util.Log;

import com.ninegame.bird.framework.FrameworkFacade;

/**
 * Created by lvrh on 15/11/13.
 */
public class BirdApplication extends Application {
    private String TAG = "BirdApp";
    private static BirdApplication mApp;

    @TargetApi(9)
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "app oncreate");
        mApp = this;
        new InnerWrapper().onCreate(this);
    }

    class InnerWrapper {
        void onCreate(BirdApplication app) {
//            AppLaunchTimeCollector.init();
           /* //fix近期经常出现的java.lang.NoClassDefFoundError,类似http://mantis.ucweb.local/view.php?id=436944
            try {
                Class.forName("android.os.AsyncTask");
            } catch (ClassNotFoundException e) {
                //wa 此时未初始化，不能使用
                L.e(e);
                StackTraceElement[] stackTrace = e.getStackTrace();
                String ex = "stackTrace[0]:" + stackTrace[0];
                BusinessStat.getInstance().addStat("error_class_not_found", e.getMessage(), ex, "");
            }*/
            // NineGame_onCreate 简称为ngc
//            InitStat.start("ngc");

//            IModuleManifest[] moduleManifests = new IModuleManifest[]{new MainModuleManifest()};
//            FrameworkFacade.getInstance().start(getApplicationContext(), moduleManifests);

            FrameworkFacade.getInstance().start(getApplicationContext());

           /* MessageBroker.config().setContext(app).save();  // 尽快设置IM的context，避免后续IM流程出错

            if (ProcessManager.getInstance().isMainProcess() || ProcessManager.getInstance().isCoreProcess()) {
                // stat_init 简写为si 初始化状态开始
                InitStat.addNode("si");
                AppLaunchTimeCollector.getInstance().mApplicationExecDur = System.currentTimeMillis() - AppLaunchTimeCollector.getInstance().mLaunchTime;
                StateManager.getInstance().start();
            }*/
        }
    }

 /*   @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        if (ShellFeatureConfig.ENABLE_PRELOAD_DEX) {
            Log.i(TAG, "开启急速编译");
            if (!ShellFeatureConfig.isDevelopVersion()) {
                throw new Error("unsupport non-developer preload dex !!");
            }
            //Application作为ClassLoader最初类，加载dex
            Context context = base;
            for (EDEX_TYPE i : EDEX_TYPE.values()) {
                try {
                    DeveloperDexLoader.load(context, i);
                } catch (Exception e) {
                    Log.e("", "DexLoader load fail !", e);
                }
            }
        } else {
            DynamicLibLoader.getInstance().loadLib(base);
        }
    }*/

    public static BirdApplication getInstance() {
        return mApp;
    }
}
