package plugin.com.pluginpro;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class PluginManager {
    private static final PluginManager ourInstance = new PluginManager();
    public static PluginManager getInstance() {
        return ourInstance;
    }

    private PluginManager() {
    }

    Context context;
    DexClassLoader dexClassLoader;
    Resources resources;
    String entryActivityName;

    public void load(Context ctx,String path){
        context=ctx;
        File dexOutFile=context.getDir("dex",Context.MODE_PRIVATE);
        dexClassLoader=new DexClassLoader(path,dexOutFile.getAbsolutePath(),null,context.getClassLoader());

        PackageManager pm = context.getPackageManager();
        PackageInfo pkgInfo = pm.getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES);
        if (pkgInfo != null) {
            ActivityInfo[] infos= pkgInfo.activities;
            entryActivityName= infos[0].name;
//            ApplicationInfo appInfo = pkgInfo.applicationInfo;
//            String versionName = pkgInfo.versionName;//版本号
//            Drawable icon = pm.getApplicationIcon(appInfo);//图标
//            String appName = pm.getApplicationLabel(appInfo).toString();//app名称
//            String pkgName = appInfo.packageName;//包名
//            entryActivityName=pkgName
        }else{
            entryActivityName="plugin.com.taopiaopiao.MainActivity";
        }


        //实例化resource 对象
        try {
            AssetManager assetManager=AssetManager.class.newInstance();
            Method addAssetPath=AssetManager.class.getMethod("addAssetPath",String.class);
            addAssetPath.invoke(assetManager,path);
            resources=new Resources(assetManager,
                    context.getResources().getDisplayMetrics(),
                    context.getResources().getConfiguration());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }


    public DexClassLoader getDexClassLoader() {
        return dexClassLoader;
    }

    public Resources getResources() {
        return resources;
    }

    public String getEntryActivityName() {
        return entryActivityName;
    }

    public void setEntryActivityName(String entryActivityName) {
        this.entryActivityName = entryActivityName;
    }

    public void setContext(Context context) {
        this.context = context.getApplicationContext();
    }
}
