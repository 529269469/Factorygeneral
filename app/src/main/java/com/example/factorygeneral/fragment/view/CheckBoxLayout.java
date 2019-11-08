package com.example.factorygeneral.fragment.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.factorygeneral.R;
import com.example.factorygeneral.adapter.GridAdapter;
import com.example.factorygeneral.adapter.OptionsAdapter;
import com.example.factorygeneral.base.MyApplication;
import com.example.factorygeneral.bean.TextLabelBean;
import com.example.factorygeneral.bean.TitleBean;
import com.example.factorygeneral.greendao.bean.UnitListBean;
import com.example.factorygeneral.greendao.db.UnitListBeanDao;
import com.example.factorygeneral.utils.StringUtils;
import com.example.factorygeneral.view.MyGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :created by ${ WYW }
 * 时间：2019/11/5 15
 */
public class CheckBoxLayout extends LinearLayout {
    private Context context;
    private UnitListBean unitListBean;

    public CheckBoxLayout(Context context, UnitListBean unitListBean) {
        super(context);
        this.context = context;
        this.unitListBean = unitListBean;
        initView();
    }

    private MyGridView gv_file;
    private TextView tv_label;
    private MyGridView gv_text;

    private List<TextLabelBean> gridList = new ArrayList<>();
    private List<TitleBean> textList = new ArrayList<>();
    private GridAdapter gridAdapter;
    private OptionsAdapter optionsAdapter;
    private TextView tv_caozuo;
    private void initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.check_box_layout, this);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);

        gv_file = view.findViewById(R.id.gv_file);
        tv_label = view.findViewById(R.id.tv_label);
        gv_text = view.findViewById(R.id.gv_text);
        tv_caozuo=view.findViewById(R.id.tv_caozuo);
        tv_label.setText(unitListBean.getLabel()+":");

        tv_caozuo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (iCheckBox!=null){
                    iCheckBox.setCheckBoxId(unitListBean.getId(),tv_caozuo);
                }
            }
        });

        String[] textArray = unitListBean.getText().split("&&");
        for (int i = 0; i < textArray.length; i++) {
            TitleBean titleBean=  new TitleBean(textArray[i]);
            if (textArray[i].equals(unitListBean.getAnswer())){
                titleBean.setCheck(true);
            }
            textList.add(titleBean);
        }
        optionsAdapter=new OptionsAdapter(context,textList);
        gv_text.setAdapter(optionsAdapter);

        gv_text.setOnItemClickListener((adapterView, view1, position, l) -> {
            for (int i = 0; i < textList.size(); i++) {
                textList.get(i).setCheck(false);
            }
            textList.get(position).setCheck(true);
            optionsAdapter.notifyDataSetChanged();

            UnitListBeanDao unitListBeanDao = MyApplication.getInstances().getUnitDaoSession().getUnitListBeanDao();
            UnitListBean unitListBean1 = new UnitListBean(unitListBean.getUId(),
                    unitListBean.getUuid(),
                    textList.get(position).getTitle(),
                    unitListBean.getContent(),
                    unitListBean.getContentFile(),
                    unitListBean.getId(),
                    unitListBean.getKeyUuid(),
                    unitListBean.getLabel(),
                    unitListBean.getRelevantFile(),
                    unitListBean.getSx(),
                    unitListBean.getText(),
                    unitListBean.getType(),
                    unitListBean.getUserName());
            unitListBeanDao.update(unitListBean1);


        });


        if (!StringUtils.isBlank(unitListBean.getRelevantFile())){
            String[] relevantArray=unitListBean.getRelevantFile().split(",");
            for (int i = 0; i < relevantArray.length; i++) {
                String[] relevantArray2=relevantArray[i].split("@%%%@");
                try {
                    TextLabelBean textLabelBean=new TextLabelBean();
                    try {
                        textLabelBean.setLabel(relevantArray2[0]);
                    }catch (Exception ex){

                    }
                    try {
                        textLabelBean.setText(relevantArray2[1]);
                    }catch (Exception ex){

                    }
                    gridList.add(textLabelBean);
                }catch (Exception ex){

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



    public interface ICheckBox{
        void setCheckBoxId(String unitListId,View operationView);
    }

    private ICheckBox iCheckBox;

    public void setiCheckBox(ICheckBox iCheckBox) {
        this.iCheckBox = iCheckBox;
    }
}
