package com.example.factorygeneral.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.Map;

/**
 * Created by Administrator on 2016/8/2.
 * sharep- 工具类 控制读取sp文件
 */
public class SPUtils {
        /**
         * 保存在手机里面的文件名
         */
        public static final String SP_NAME = "config";
        public static final int SP_MODE = Context.MODE_PRIVATE;
        private static SharedPreferences sp;
        private static Editor editor;

        /**
         * 根据类型调用不同的保存方法
         *
         * @param context 上下文
         * @param key     添加的键
         * @param value   添加的值
         * @return 是否添加成功（可以使用apply提交）
         */
        public static boolean put(Context context, String key, Object value) {
            if (sp == null) {
                sp = context.getSharedPreferences(SP_NAME, SP_MODE);
            }
            editor = sp.edit();
            if (value == null) {
                editor.putString(key, null);
            } else if (value instanceof String) {
                editor.putString(key, (String) value);
            } else if (value instanceof Integer) {
                editor.putInt(key, (Integer) value);
            } else if (value instanceof Boolean) {
                editor.putBoolean(key, (Boolean) value);
            } else if (value instanceof Float) {
                editor.putFloat(key, (Float) value);
            } else if (value instanceof Long) {
                editor.putLong(key, (Long) value);
            } else {
                editor.putString(key, value.toString());
            }
            return editor.commit();
        }


    public static Object get(Context context, String key, Object defaultObject) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, SP_MODE);
        }

        if (defaultObject instanceof String)
        {
            return sp.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer)
        {
            return sp.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean)
        {
            return sp.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float)
        {
            return sp.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long)
        {
            return sp.getLong(key, (Long) defaultObject);
        }

        return null;
    }


        public static Map<String, ?> getAll(Context context) {
            if (sp == null) {
                sp = context.getSharedPreferences(SP_NAME, SP_MODE);
            }
            Map<String, ?> all = sp.getAll();
            return all;
        }

    /**
     * 清除数据
     *
     * @param context
     * @return 是否清除成功(如果不关注结果，可以使用apply)
     */
    public static boolean clear(Context context) {

        return false;
    }


    /**

     * 删除信息
     * @throws Exception
     */

    public static  void deleteAll(Context context) throws Exception {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, SP_MODE);
        }
        editor = sp.edit();
        editor.clear();
        editor.commit();
    }


}