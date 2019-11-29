package com.example.factorygeneral.fragment.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.factorygeneral.R;
import com.example.factorygeneral.adapter.GridAdapter;
import com.example.factorygeneral.adapter.TableAdapter;
import com.example.factorygeneral.adapter.TablePopupAdapter;
import com.example.factorygeneral.base.MyApplication;
import com.example.factorygeneral.bean.TextLabelBean;
import com.example.factorygeneral.greendao.bean.UnitListBean;
import com.example.factorygeneral.greendao.db.UnitListBeanDao;
import com.example.factorygeneral.utils.FileUtils;
import com.example.factorygeneral.utils.SPUtils;
import com.example.factorygeneral.utils.ScreenUtils;
import com.example.factorygeneral.utils.StringUtils;
import com.example.factorygeneral.utils.ToastUtils;
import com.example.factorygeneral.view.LineGridView;
import com.example.factorygeneral.view.MyGridView;
import com.example.factorygeneral.view.MyListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author :created by ${ WYW }
 * 时间：2019/11/5 15
 */
public class TableLayout extends LinearLayout {
    private TextView tv_label;
    private TextView tv_caozuo;
    private LineGridView gv_label;

    private Activity context;
    private UnitListBean unitListBean;
    private List<String> list = new ArrayList<>();
    private String[] textArray;
    private String[] contentArray;
    private TableAdapter tableAdapter;

    public TableLayout(Activity context, UnitListBean unitListBean) {
        super(context);
        this.context = context;
        this.unitListBean = unitListBean;
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.table_layout, this);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        tv_label = view.findViewById(R.id.tv_label);
        gv_label = view.findViewById(R.id.gv_label);
        tv_caozuo = view.findViewById(R.id.tv_caozuo);
        tv_label.setText(StringUtils.isBlank(unitListBean.getLabel())?"":unitListBean.getLabel());

        textArray = unitListBean.getText().split("%%&@");
        for (int i = 0; i < textArray.length; i++) {
            try {
                list.add(textArray[i]);
            } catch (Exception ex) {
            }
        }

        if (!StringUtils.isBlank(unitListBean.getContent())) {
            contentArray = unitListBean.getContent().split("%%&@");
            for (int i = 0; i < contentArray.length; i++) {
                String[] contentArray2 = contentArray[i].split("★&&☆");
                for (int j = 0; j < contentArray2.length; j++) {
                    try {
                        list.add(contentArray2[j]);
                    } catch (Exception ex) {
                        list.add("");
                    }
                }

            }
        }


        gv_label.setNumColumns(textArray.length);
        tableAdapter = new TableAdapter(context, list);
        gv_label.setAdapter(tableAdapter);

        gv_label.setOnItemClickListener((adapterView, view1, position, l) -> {
            int num = position / textArray.length;
            if (num > 0) {
                if (iTable!=null){
                    iTable.setTable1(num-1,true,unitListBean);
                }
            }
        });

        tv_caozuo.setOnClickListener(view12 ->
                popuView(tv_caozuo));


    }


    public interface ITable{
        void setTable1(int position, boolean isLook,UnitListBean unitListBean);
        void setTable1(UnitListBean unitListBean);
        void setDelTable(UnitListBean unitListBean);
    }

    private ITable iTable;

    public void setiTable(ITable iTable) {
        this.iTable = iTable;
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

        tv_add.setOnClickListener(view1 -> {
            popupWindow.dismiss();
            if (iTable!=null){
                iTable.setTable1(0,false,unitListBean);
            }
        });

        tv_del.setOnClickListener(view15 -> {
            popupWindow.dismiss();
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("是否删除此模块");
            builder.setPositiveButton("是！！", (dialog, which) -> {
                if (iTable!=null){
                    iTable.setDelTable(unitListBean);
                }

            });
            builder.setNegativeButton("否", null);
            builder.show();
        });

        tv_content.setOnClickListener(view12 -> {
            popupWindow.dismiss();
            if (iTable!=null){
                iTable.setTable1(unitListBean);
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

}
