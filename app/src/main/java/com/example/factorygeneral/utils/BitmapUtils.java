package com.example.factorygeneral.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class BitmapUtils {
    /**
     * 质量压缩
     * @param bmp 要压缩的图片
     * @param targetSize 最终要压缩的大小（KB）
     * @return
     */
    public static Bitmap qualityCompress(Bitmap bmp, int targetSize) {

        //压缩后的图片输出到baos中
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        // 压缩程度，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > targetSize) {
            // 循环判断如果压缩后图片是否大于target,大于继续压缩
            baos.reset();// 重置baos即清空baos
            bmp.compress(Bitmap.CompressFormat.JPEG, options, baos);
            // 逐渐减少压缩率，把压缩后的数据存放到baos中
            options -= 10;// 每次都减少10
            if (options <= 0) {
                break;
            }
        }
//        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        byte[] bytes = baos.toByteArray();
        // 得到最终质量压缩后的Bitmap
        Bitmap targetBmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

//        //输出相关信息
//        StringBuilder sb = new StringBuilder();
//        sb.append("压缩后图片以bitmap形式存在的内存大小：").append(targetBmp.getByteCount() / 1024)
//                .append("KB\n")
//                .append("像素为：").append(targetBmp.getWidth() + "*" + targetBmp.getHeight())
//                .append("\n在SD卡中的文件大小为：").append(bytes.length/1024).append("KB");
//
//
//        tv_compress.setText(sb);
//        iv.setImageBitmap(targetBmp);

        return targetBmp;
    }

    /**
     * 将图片转换成Base64编码的字符串
     */
    public static String imageToBase64(String path){
        if(TextUtils.isEmpty(path)){
            return null;
        }
        InputStream is = null;
        byte[] data = null;
        String result = null;
        try{
            is = new FileInputStream(path);
            //创建一个字符流大小的数组。
            data = new byte[is.available()];
            //写入数组
            is.read(data);
            //用默认的编码格式进行编码
            result = Base64.encodeToString(data, Base64.DEFAULT);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(null !=is){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return result;
    }
}
