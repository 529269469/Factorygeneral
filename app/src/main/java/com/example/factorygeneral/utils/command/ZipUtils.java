package com.example.factorygeneral.utils.command;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;

/**
 * @author :created by ${ WYW }
 * 时间：2019/9/11 10
 */
public class ZipUtils {
    private static final String TAG = "压缩";



    /**
     * 压缩
     * 7zr a  [输出文件] [待压缩文件/目录] -mx=9
     * 7zr a /sdcard/7-Zip.7z /sdcard/7-Zip -mx=9
     *
     * @param
     */
    public static void pack(Context context,String srcString,String outString) {
        File src = new File(Environment.getExternalStorageDirectory(), srcString);
        File out = new File(Environment.getExternalStorageDirectory(), outString);
        ZipHelper.execute(context, "7zr a " + out.getAbsolutePath() + " "
                + src.getAbsolutePath() + " -mx=9", new ZipHelper.OnResultListener() {
            @Override
            public void onSuccess(String msg) {
                Log.e(TAG, "执行成功");
            }

            @Override
            public void onFailure(int errorno, String msg) {
                Log.e(TAG, "执行失败");
                Log.e(TAG, "错误码：" + errorno);
                Log.e(TAG, "错误信息：" + msg);
            }

            @Override
            public void onProgress(String msg) {
                Log.e(TAG, "正在执行:" + msg);
            }
        });
    }

    /**
     * 解压
     * 7zr x [压缩文件]  -o[输出目录]
     *
     * @param
     */
    public static void unpack(Context context,String srcString,String outString) {
        File src = new File(Environment.getExternalStorageDirectory(), srcString);
        File out = new File(Environment.getExternalStorageDirectory(), outString);
        ZipHelper.execute(context, "7zr x " + src.getAbsolutePath() + " -o"
                + out.getAbsolutePath(), new ZipHelper.OnResultListener() {
            @Override
            public void onSuccess(String msg) {
                Log.e(TAG, "执行成功");
            }

            @Override
            public void onFailure(int errorno, String msg) {
                Log.e(TAG, "执行失败");
                Log.e(TAG, "错误码：" + errorno);
                Log.e(TAG, "错误信息：" + msg);
            }

            @Override
            public void onProgress(String msg) {
                Log.e(TAG, "正在执行:" + msg);
            }
        });
    }
}
