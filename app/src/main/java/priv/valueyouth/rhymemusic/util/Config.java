package priv.valueyouth.rhymemusic.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Environment;
import android.telephony.TelephonyManager;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

public class Config {
    //SSM
    public static String APP_KEY = "aed1b937fe9b";
    public static String APP_SECRET = "05614f77862163370a22d2fd01fd37f0";

    public static Properties props;
    /**
     * log等级 off info debug error all
     */
    public static int LOG_LEVEL;
    /**
     * 语言
     */
    public static String LANGUAGE = "zh";
    /**
     * app版本
     */
    public static String APP_VERSION;
    /**
     * 设备唯一标识
     */
    public static String PHONE_ANDROIDID;
    /**
     * 总缓存路径
     */
    public static String PATH_SYSTEM_CACHE;
    /**
     * icon缓存路径
     */
    public static String PATH_CACHE_ICON;
    /**
     * 用户保存的图片路径
     */
    public static String PATH_USER_SAVE_IMAGE;
    /**
     * 图片缓存路径
     */
    public static String PATH_CACHE_IMAGES;
    /**
     * 图片临时
     */
    public static String PATH_IMAGE_TEMP_PATH;
    /**
     * 产品详情页图片缓存路径
     */
    public static String PATH_IMAGE_PRODUCT_PATH;
    /**
     * 临时下载文件
     */
    public static String PATH_DOWN_FILE;
    /**
     * 缓存下载的音频文件
     */
    public static String PATH_DOWN_AUDIO;
    /**
     * 以下储存Object类型
     */
    public static String PATH_OBJ_SAVA_CACHE;
    /**
     * 缓存对象路径（以Obj为例）
     */
    public static String PATH_TARGET;
    /**
     * 缓存Map路径
     */
    public static String PATH_MAP;


    public static void init(Context context) throws IOException {
        props = new Properties();
        //设置语言
        setLocale(context);
        APP_VERSION = getVersion(context);
        PHONE_ANDROIDID = getAndroidId(context);
        //SD卡不存在就缓存到手机内存
        try {
            //  /storage/emulated/0/Android/data/com.cb.weibo/cache/system
            Config.PATH_SYSTEM_CACHE = context.getExternalCacheDir().getPath() + "/system" + "/master";
            MyLogger.CLog().e("Use cache directory in sd card cache dir " + PATH_SYSTEM_CACHE);
        } catch (Exception e) {
            PATH_SYSTEM_CACHE = context.getCacheDir().getPath() + "/system" + "/master";
            MyLogger.CLog().e("Use cache directory in app cache dir " + PATH_SYSTEM_CACHE);
        }

        //  /storage/emulated/0/SinaWeibo/images    //此路径相册可以查看到
        PATH_USER_SAVE_IMAGE = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/master" + "/images";
        File cacheImage = new File(PATH_USER_SAVE_IMAGE);
        cacheImage.mkdirs();

        PATH_DOWN_AUDIO = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/master" + "/audio";
        File audio = new File(PATH_USER_SAVE_IMAGE);
        audio.mkdirs();

        //以下均为缓存目录
        PATH_IMAGE_TEMP_PATH = PATH_SYSTEM_CACHE + "/imageTemp/";
        File imageTemp = new File(PATH_IMAGE_TEMP_PATH);
        imageTemp.mkdirs();

        PATH_IMAGE_PRODUCT_PATH = PATH_SYSTEM_CACHE + "/productImageTemp/";
        File productImageTemp = new File(PATH_IMAGE_PRODUCT_PATH);
        productImageTemp.mkdirs();

        PATH_CACHE_IMAGES = PATH_SYSTEM_CACHE + "/images/";
        File images = new File(PATH_CACHE_IMAGES);
        images.mkdirs();

        PATH_CACHE_ICON = PATH_SYSTEM_CACHE + "/icon/";
        File icon = new File(PATH_CACHE_ICON);
        icon.mkdirs();

        PATH_DOWN_FILE = PATH_SYSTEM_CACHE + "/downFile/";
        File down = new File(PATH_DOWN_FILE);
        down.mkdirs();

        PATH_OBJ_SAVA_CACHE = PATH_SYSTEM_CACHE + "/object/";
        File object = new File(PATH_OBJ_SAVA_CACHE);
        object.mkdirs();

        PATH_TARGET = PATH_OBJ_SAVA_CACHE + "/obj/";
        File target = new File(PATH_TARGET);
        target.mkdirs();

        PATH_MAP = PATH_OBJ_SAVA_CACHE + "/map/";
        File map = new File(PATH_MAP);
        map.mkdirs();

    }


    private static void setLocale(Context context) {
        Locale locale = Locale.SIMPLIFIED_CHINESE;
        String localeName = Locale.TRADITIONAL_CHINESE.getLanguage() + "_" + Locale.TRADITIONAL_CHINESE.getCountry();
        if (LANGUAGE.equals(localeName)) {
            locale = Locale.TRADITIONAL_CHINESE;
        }
        // 更新默认语言
        Locale.setDefault(locale);
        Configuration config = context.getResources()
                .getConfiguration();
        config.locale = locale;
        context.getResources().updateConfiguration(config,
                context.getResources().getDisplayMetrics());
    }

    /**
     * 获取APP版本号
     *
     * @param context
     * @return
     */
    public static String getVersion(Context context) {
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            return pi.versionName;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取手机设备唯一标识
     *
     * @param context
     * @return
     */
    public static String getAndroidId(Context context) {
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            return tm.getDeviceId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}


