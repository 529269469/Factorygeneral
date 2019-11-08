package com.example.factorygeneral.fragment.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.factorygeneral.R;
import com.example.factorygeneral.adapter.GridAdapter;
import com.example.factorygeneral.adapter.TextBoxAdapter;
import com.example.factorygeneral.base.MyApplication;
import com.example.factorygeneral.bean.TextLabelBean;
import com.example.factorygeneral.bean.TitleBean;
import com.example.factorygeneral.greendao.bean.UnitListBean;
import com.example.factorygeneral.greendao.db.UnitListBeanDao;
import com.example.factorygeneral.utils.SPUtils;
import com.example.factorygeneral.utils.StringUtils;
import com.example.factorygeneral.view.MyGridView;
import com.example.factorygeneral.view.OperationPopupWindow;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author :created by ${ WYW }
 * 时间：2019/11/5 15
 */
public class TextBoxLayout extends LinearLayout {
    private MyGridView gv_textBox;
    private MyGridView gv_file;
    private TextView tv_caozuo;
    private Activity context;
    private UnitListBean unitListBean;

    private List<TextLabelBean> labelBeans = new ArrayList<>();
    private List<TextLabelBean> gridList = new ArrayList<>();
    private TextBoxAdapter textBoxAdapter;
    private GridAdapter gridAdapter;


    public TextBoxLayout(Activity context, UnitListBean unitListBean) {
        super(context);
        this.context = context;
        this.unitListBean = unitListBean;
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.text_box, this);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        gv_textBox = view.findViewById(R.id.gv_textBox);
        gv_file = view.findViewById(R.id.gv_file);
        tv_caozuo = view.findViewById(R.id.tv_caozuo);

        tv_caozuo.setOnClickListener(view1 -> {
            if (textBox!=null){
                textBox.seTextBoxId(unitListBean.getId(),tv_caozuo);
            }
        });

        String[] labelArray = unitListBean.getLabel().split("%%&@");
        String[] textArray = unitListBean.getText().split("%%&@");

        for (int i = 0; i < labelArray.length; i++) {
            TextLabelBean textLabelBean = new TextLabelBean();
            try {
                textLabelBean.setLabel(labelArray[i]);
            } catch (Exception ex) {

            }
            try {
                textLabelBean.setText(textArray[i]);
            } catch (Exception ex) {

            }
            labelBeans.add(textLabelBean);
        }
        textBoxAdapter = new TextBoxAdapter(context, labelBeans, unitListBean.getId());
        gv_textBox.setAdapter(textBoxAdapter);

        if (!StringUtils.isBlank(unitListBean.getRelevantFile())){
            String[] relevantArray = unitListBean.getRelevantFile().split(",");
            for (int i = 0; i < relevantArray.length; i++) {
                String[] relevantArray2 = relevantArray[i].split("@%%%@");
                try {
                    TextLabelBean textLabelBean = new TextLabelBean();
                    try {
                        textLabelBean.setLabel(relevantArray2[0]);
                    } catch (Exception ex) {

                    }
                    try {
                        textLabelBean.setText(relevantArray2[1]);
                    } catch (Exception ex) {

                    }
                    gridList.add(textLabelBean);
                } catch (Exception ex) {

                }
            }
        }


        gridAdapter = new GridAdapter(gridList, context);
        gv_file.setAdapter(gridAdapter);

        gridAdapter.setOnDel(position -> {
            gridList.remove(position);
            UnitListBeanDao unitListBeanDao = MyApplication.getInstances().getUnitDaoSession().getUnitListBeanDao();

            StringBuffer relevantFileBuffer = new StringBuffer();
            for (int i = 0; i < gridList.size(); i++) {
                relevantFileBuffer.append(gridList.get(i).getLabel()).append("@%%%@").append(gridList.get(i).getText()).append(",");
            }
            String relevantFile = relevantFileBuffer.toString().substring(0, relevantFileBuffer.toString().length() - 1);
            UnitListBean unitListBean1 = new UnitListBean(unitListBean.getUId(),
                    unitListBean.getUuid(),
                    unitListBean.getAnswer(),
                    unitListBean.getContent(),
                    unitListBean.getContentFile(),
                    unitListBean.getId(),
                    unitListBean.getKeyUuid(),
                    unitListBean.getLabel(),
                    relevantFile,
                    unitListBean.getSx(),
                    unitListBean.getText(),
                    unitListBean.getType(),
                    unitListBean.getUserName());
            unitListBeanDao.update(unitListBean1);

            gridAdapter.notifyDataSetChanged();
        });

    }

    public interface ITextBox{
        void seTextBoxId(String unitListId,View operationView);
    }

    private ITextBox textBox;

    public void setTextBox(ITextBox textBox) {
        this.textBox = textBox;
    }
}
