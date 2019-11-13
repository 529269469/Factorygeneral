package com.example.factorygeneral.fragment.view;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.factorygeneral.R;
import com.example.factorygeneral.adapter.GridAdapter;
import com.example.factorygeneral.base.MyApplication;
import com.example.factorygeneral.bean.TextLabelBean;
import com.example.factorygeneral.greendao.bean.UnitListBean;
import com.example.factorygeneral.greendao.db.UnitListBeanDao;
import com.example.factorygeneral.utils.SPUtils;
import com.example.factorygeneral.utils.StringUtils;
import com.example.factorygeneral.view.MyGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :created by ${ WYW }
 * 时间：2019/11/5 15
 */
public class InputLayout extends LinearLayout {
    private Context context;
    private UnitListBean unitListBean;
    private GridAdapter gridAdapter;
    private TextView tv_caozuo;

    public InputLayout(Context context, UnitListBean unitListBean) {
        super(context);
        this.context = context;
        this.unitListBean = unitListBean;
        initView();
    }

    private TextView tv_label;
    private EditText tv_text;
    private MyGridView gv_file;

    private List<TextLabelBean> gridList = new ArrayList<>();


    private void initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.input_layout, this);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        tv_label = view.findViewById(R.id.tv_label);
        tv_text = view.findViewById(R.id.tv_text);
        gv_file = view.findViewById(R.id.gv_file);
        tv_caozuo = view.findViewById(R.id.tv_caozuo);
        tv_text.addTextChangedListener(textWatcher);


        tv_label.setText(unitListBean.getLabel() + ":");
        tv_text.setText(StringUtils.isBlank(unitListBean.getText())?"":unitListBean.getText());

        tv_caozuo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (iInput != null) {
                    iInput.setInputId(unitListBean.getId(), tv_caozuo);
                }
            }
        });

        if (!StringUtils.isBlank(unitListBean.getRelevantFile())) {
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
            String relevantFile = "";
            if (!StringUtils.isBlank(relevantFileBuffer.toString())) {
                relevantFile = relevantFileBuffer.toString().substring(0, relevantFileBuffer.toString().length() - 1);
            }
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

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            String words = editable.toString();
            //首先内容进行非空判断，空内容（""和null）不处理
            UnitListBeanDao unitListBeanDao = MyApplication.getInstances().getUnitDaoSession().getUnitListBeanDao();
            UnitListBean unitListBean2 = new UnitListBean(unitListBean.getUId(),
                    unitListBean.getUuid(),
                    unitListBean.getAnswer(),
                    unitListBean.getContent(),
                    unitListBean.getContentFile(),
                    unitListBean.getId(),
                    unitListBean.getKeyUuid(),
                    unitListBean.getLabel(),
                    unitListBean.getRelevantFile(),
                    unitListBean.getSx(),
                    StringUtils.isBlank(words) ? "null" : words,
                    unitListBean.getType(),
                    unitListBean.getUserName());
            unitListBeanDao.update(unitListBean2);


        }
    };


    public interface IInput {
        void setInputId(String unitListId, View operationView);
    }

    private IInput iInput;

    public void setiInput(IInput iInput) {
        this.iInput = iInput;
    }
}
