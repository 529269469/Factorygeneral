package com.example.factorygeneral.fragment.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.factorygeneral.R;
import com.example.factorygeneral.base.MyApplication;
import com.example.factorygeneral.greendao.bean.UnitListBean;
import com.example.factorygeneral.greendao.db.UnitListBeanDao;
import com.example.factorygeneral.utils.FileUtils;
import com.example.factorygeneral.utils.SPUtils;
import com.example.factorygeneral.utils.StringUtils;
import com.example.factorygeneral.view.LinePathView;

import java.io.File;
import java.io.IOException;

/**
 * @author :created by ${ WYW }
 * 时间：2019/11/5 15
 */
public class AutographLayout extends LinearLayout {
    private Context context;
    private UnitListBean unitListBean;
    public AutographLayout(Context context, UnitListBean unitListBean) {
        super(context);
        this.context=context;
        this.unitListBean=unitListBean;
        initView();
    }

    private TextView tv_label;
    private ImageView iv_autograph;
    private void initView() {
        View view= LayoutInflater.from(context).inflate(R.layout.autograph_layout,this);
        LayoutParams layoutParams=new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        tv_label=view.findViewById(R.id.tv_label);
        iv_autograph=view.findViewById(R.id.iv_autograph);

        tv_label.setText(unitListBean.getLabel());

        if (!StringUtils.isBlank(unitListBean.getRelevantFile())){
            String[] textArray=unitListBean.getRelevantFile().split("@%%%@");
            try {
                Glide.with(context)
                        .load(new File(SPUtils.get(context, "PackagePath", "") + File.separator + textArray[1]))
                        .into(iv_autograph);
            }catch (Exception ex){

            }

        }
//        iv_autograph.setImageResource(R.drawable.abc);

        iv_autograph.setOnClickListener(view12 -> pathPopu(iv_autograph));

        tv_label.setOnClickListener(view1 -> {
            if (iAutograph!=null){
                iAutograph.setAutograph(unitListBean.getId());
            }
        });

        tv_label.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("是否删除此模块");
                builder.setPositiveButton("是！！", (dialog, which) -> {
                    if (iAutograph!=null){
                        iAutograph.setDelAutograph(unitListBean);
                    }
                });
                builder.setNegativeButton("否", null);
                builder.show();
                return true;
            }
        });

    }


    /**
     * 签名
     */
    private void pathPopu(ImageView iv) {
        View poview = MyApplication.mContext.getLayoutInflater().inflate(R.layout.path_view, null);
        PopupWindow popupWindow = new PopupWindow(poview);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        WindowManager.LayoutParams lp = MyApplication.mContext.getWindow().getAttributes();
        lp.alpha = 0.7f;
        MyApplication.mContext.getWindow().setAttributes(lp);
        popupWindow.showAtLocation(iv, Gravity.CENTER, 0, 0);

        popupWindow.setOnDismissListener(() -> {
            WindowManager.LayoutParams lp1 = MyApplication.mContext.getWindow().getAttributes();
            lp1.alpha = 1f;
            MyApplication.mContext.getWindow().setAttributes(lp1);
        });

        LinePathView mPathView = poview.findViewById(R.id.path_view);
        TextView mBtnClear = poview.findViewById(R.id.m_btn_clear);
        TextView mBtnSave = poview.findViewById(R.id.m_btn_save);

        //修改背景、笔宽、颜色
        mPathView.setBackColor(Color.WHITE);
        mPathView.setPaintWidth(10);
        mPathView.setPenColor(Color.BLACK);
        //清除
        mBtnClear.setOnClickListener(v -> {
            mPathView.clear();
            mPathView.setBackColor(Color.WHITE);
            mPathView.setPaintWidth(10);
            mPathView.setPenColor(Color.BLACK);
        });
        //保存
        String path = System.currentTimeMillis() + ".jpg";
        mBtnSave.setOnClickListener(v -> {
            if (mPathView.getTouched()) {
                try {
                    mPathView.save(SPUtils.get(context, "PackagePath", "") + File.separator + path, true, 100);
                    Glide.with(context)
                            .load(new File(SPUtils.get(context, "PackagePath", "") + File.separator + path))
                            .skipMemoryCache(true)
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .into(iv);
                    UnitListBeanDao unitListBeanDao = MyApplication.getInstances().getUnitDaoSession().getUnitListBeanDao();

                    UnitListBean unitListBean1 = new UnitListBean(unitListBean.getUId(),
                            unitListBean.getUuid(),
                            unitListBean.getAnswer(),
                            unitListBean.getContent(),
                            unitListBean.getContentFile(),
                            unitListBean.getId(),
                            unitListBean.getKeyUuid(),
                            unitListBean.getLabel(),
                            path+"@%%%@"+path,
                            unitListBean.getSx(),
                            unitListBean.getText(),
                            unitListBean.getType(),
                            unitListBean.getUserName());
                    unitListBeanDao.update(unitListBean1);


                    Toast.makeText(context, "签名成功~", Toast.LENGTH_SHORT).show();
                    popupWindow.dismiss();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(context, "您没有签名~", Toast.LENGTH_SHORT).show();
            }
        });

    }


    public interface IAutograph {
        void setAutograph(String unitListId);
        void setDelAutograph(UnitListBean unitListBean);
    }

    private IAutograph iAutograph;

    public void setiAutograph(IAutograph iAutograph) {
        this.iAutograph = iAutograph;
    }
}
