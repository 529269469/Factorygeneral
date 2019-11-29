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
import com.example.factorygeneral.utils.ScreenUtils;
import com.example.factorygeneral.utils.StringUtils;
import com.example.factorygeneral.view.LinePathView;

import java.io.File;
import java.io.IOException;

/**
 * @author :created by ${ WYW }
 * 时间：2019/11/5 15
 */
public class AutographLayout extends LinearLayout {
    private Activity context;
    private UnitListBean unitListBean;
    public AutographLayout(Activity context, UnitListBean unitListBean) {
        super(context);
        this.context=context;
        this.unitListBean=unitListBean;
        initView();
    }

    private TextView tv_label;
    private ImageView iv_autograph;
    private TextView tv_caozuo;
    private void initView() {
        View view= LayoutInflater.from(context).inflate(R.layout.autograph_layout,this);
        LayoutParams layoutParams=new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        tv_label=view.findViewById(R.id.tv_label);
        iv_autograph=view.findViewById(R.id.iv_autograph);
        tv_caozuo = view.findViewById(R.id.tv_caozuo);
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

        iv_autograph.setOnClickListener(view12 -> pathPopu(iv_autograph));

        tv_caozuo.setOnClickListener(view12 ->
                popuView(tv_caozuo));

    }


    private void popuView(View operationView) {
        View view = context.getLayoutInflater().inflate(R.layout.table_popup_view, null);
        PopupWindow popupWindow = new PopupWindow(view);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = 0.7f;
        context.getWindow().setAttributes(lp);
        int windowPos[] = calculatePopWindowPos(operationView, view);

        popupWindow.showAtLocation(operationView, Gravity.TOP | Gravity.START, windowPos[0], windowPos[1]);
        popupWindow.setOnDismissListener(() -> {
            WindowManager.LayoutParams lp1 = context.getWindow().getAttributes();
            lp1.alpha = 1f;
            context.getWindow().setAttributes(lp1);
        });

        TextView tv_add = view.findViewById(R.id.tv_add);
        TextView tv_content = view.findViewById(R.id.tv_content);
        TextView tv_del = view.findViewById(R.id.tv_del);

        tv_add.setVisibility(GONE);

        tv_del.setOnClickListener(view15 -> {
            popupWindow.dismiss();
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("是否删除此模块");
            builder.setPositiveButton("是！！", (dialog, which) -> {
                if (iAutograph!=null){
                    iAutograph.setDelAutograph(unitListBean);
                }
            });
            builder.setNegativeButton("否", null);
            builder.show();
        });

        tv_content.setOnClickListener(view12 -> {
            popupWindow.dismiss();
            if (iAutograph!=null){
                iAutograph.setAutograph(unitListBean.getId());
            }
        });

    }


    /**
     * 计算出来的位置，y方向就在anchorView的上面和下面对齐显示，x方向就是与屏幕右边对齐显示
     * 如果anchorView的位置有变化，就可以适当自己额外加入偏移来修正
     *
     * @param anchorView  呼出window的view
     * @param contentView window的内容布局
     * @return window显示的左上角的xOff, yOff坐标
     */
    private static int[] calculatePopWindowPos(final View anchorView, final View contentView) {
        final int windowPos[] = new int[2];
        final int anchorLoc[] = new int[2];
        // 获取锚点View在屏幕上的左上角坐标位置
        anchorView.getLocationOnScreen(anchorLoc);
        final int anchorHeight = anchorView.getHeight();
        // 获取屏幕的高宽
        final int screenHeight = ScreenUtils.getScreenHeight(anchorView.getContext());
        final int screenWidth = ScreenUtils.getScreenWidth(anchorView.getContext());
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        // 计算contentView的高宽
        final int windowHeight = contentView.getMeasuredHeight();
        final int windowWidth = contentView.getMeasuredWidth();
        // 判断需要向上弹出还是向下弹出显示
        final boolean isNeedShowUp = (screenHeight - anchorLoc[1] - anchorHeight < windowHeight);
        if (isNeedShowUp) {
            windowPos[0] = screenWidth - windowWidth;
            windowPos[1] = anchorLoc[1] - windowHeight;
        } else {
            windowPos[0] = screenWidth - windowWidth;
            windowPos[1] = anchorLoc[1] + anchorHeight;
        }
        return windowPos;
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
