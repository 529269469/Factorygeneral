package com.example.factorygeneral.fragment.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

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
            }else if (num==0){
                if (iTable!=null){
                    iTable.setTable1(unitListBean);
                }
            }
        });


        tv_label.setOnClickListener(view12 -> {
            if (iTable!=null){
                iTable.setTable1(0,false,unitListBean);
            }
        });

    }


    public interface ITable{
        void setTable1(int position, boolean isLook,UnitListBean unitListBean);
        void setTable1(UnitListBean unitListBean);
    }

    private ITable iTable;

    public void setiTable(ITable iTable) {
        this.iTable = iTable;
    }
}
