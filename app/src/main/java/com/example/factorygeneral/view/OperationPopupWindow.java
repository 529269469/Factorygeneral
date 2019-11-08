package com.example.factorygeneral.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.factorygeneral.R;
import com.example.factorygeneral.utils.SPUtils;

import java.io.File;

/**
 * @author :created by ${ WYW }
 * 时间：2019/11/7 10
 */
public class OperationPopupWindow extends PopupWindow {

    private Activity context;

    private File cameraSavePath;
    private Uri uri;
    public OperationPopupWindow(Activity context,View operationView) {
        super(context);
        this.context = context;
        View view = context.getLayoutInflater().inflate(R.layout.operation_view, null);
        this.setContentView(view);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        this.setOutsideTouchable(true);
        this.setFocusable(true);
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = 0.7f;
        context.getWindow().setAttributes(lp);
        this.showAsDropDown(operationView);
        this.setOnDismissListener(() -> {
            WindowManager.LayoutParams lp1 = context.getWindow().getAttributes();
            lp1.alpha = 1f;
            context.getWindow().setAttributes(lp1);
        });

        TextView tv_img = view.findViewById(R.id.tv_img);
        TextView tv_video = view.findViewById(R.id.tv_video);
        TextView tv_file2 = view.findViewById(R.id.tv_file2);


        tv_file2.setOnClickListener(view14 -> {
            this.dismiss();
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("*/*");//设置类型，我这里是任意类型，任意后缀的可以这样写。
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            context.startActivityForResult(intent, 1);

        });

        tv_img.setOnClickListener(view13 -> {
            this.dismiss();
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            File cameraSavePath = new File(SPUtils.get(context, "PackagePath", "") + "/" + System.currentTimeMillis() + ".jpg");
            uri = Uri.fromFile(cameraSavePath);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            context.startActivityForResult(intent, 2);


        });

        tv_video.setOnClickListener(view1 -> {
            this.dismiss();
            Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            cameraSavePath = new File(SPUtils.get(context, "PackagePath", "") + "/" + System.currentTimeMillis() + ".mp4");
            uri = Uri.fromFile(cameraSavePath);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            context.startActivityForResult(intent, 3);

        });

    }
}
