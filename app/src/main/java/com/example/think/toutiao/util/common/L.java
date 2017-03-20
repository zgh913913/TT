package com.example.think.toutiao.util.common;

import android.util.Log;

import com.example.think.toutiao.BuildConfig;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Log统一管理类
 *
 * @author way
 */
public class L {

    private static final String TAG = "railtool";
    public static boolean isDebug = BuildConfig.DEBUG;// 是否需要打印bug，可以在application的onCreate函数里面初始化

    private L() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    // 下面四个是默认tag的函数
    public static void i(String msg) {
        if (isDebug)
            Log.i(TAG, msg);
        writeToFile(TAG + " -- " + msg + " " + msg + "\n\n");
    }

    public static void d(String msg) {
        if (isDebug)
            Log.d(TAG, msg);
        writeToFile(TAG + " -- " + msg + " " + msg + "\n\n");
    }

    public static void e(String msg) {
        if (isDebug)
            Log.e
                    (TAG, msg);
        writeToFile(TAG + " -- " + msg + " " + msg + "\n\n");
    }

    public static void v(String msg) {
        if (isDebug)
            Log.v(TAG, msg);
        writeToFile(TAG + " -- " + msg + " " + msg + "\n\n");
    }

    // 下面是传入自定义tag的函数
    public static void i(String tag, String msg) {
        if (isDebug)
            Log.i(tag, msg);
        writeToFile(tag + " -- " + msg + " " + msg + "\n\n");
    }

    public static void d(String tag, String msg) {
        if (isDebug)
            Log.i(tag, msg);
        writeToFile(tag + " -- " + msg + " " + msg + "\n\n");
    }

    public static void e(String tag, String msg) {
        if (isDebug)
            Log.e(tag, msg);
        writeToFile(tag + " -- " + msg + " " + msg + "\n\n");
    }

    public static void v(String tag, String msg) {
        if (isDebug)
            Log.i(tag, msg);

        writeToFile(tag + " -- " + msg + " " + msg + "\n\n");
    }

    private static void writeToFile(String msg) {

//        String logdir = CrashHandler.getInstance().getCrashLogFilePath();
//        long timestamp = System.currentTimeMillis();
//        String time = formatter.format(new Date());
//        String fileName = "crash-" + time + "-" + timestamp + ".log";
//        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//            File dir = new File(logdir);
//            if (!dir.exists()) {
//                dir.mkdirs();
//            }
//            try {
//                FileOutputStream fos = new FileOutputStream(logdir + fileName);
//                fos.write(msg.getBytes());
//                fos.close();
//            } catch (IOException e) {
//                Log.e("crash handler", "load file failed...", e.getCause());
//            }
//        }
    }

    //用于格式化日期,作为日志文件名的一部分
    private static DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

}