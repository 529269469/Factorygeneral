package com.example.factorygeneral.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.factorygeneral.R;
import com.example.factorygeneral.base.MyApplication;
import com.example.factorygeneral.bean.TextLabelBean;
import com.example.factorygeneral.greendao.bean.UnitListBean;
import com.example.factorygeneral.greendao.db.UnitListBeanDao;
import com.example.factorygeneral.utils.SPUtils;
import com.example.factorygeneral.utils.StringUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author :created by ${ WYW }
 * 时间：2019/11/5 18
 */
public class TextBoxAdapter extends BaseAdapter {
    private Context context;
    private List<TextLabelBean> list;
    private String unitListId;

    public TextBoxAdapter(Context context, List<TextLabelBean> list, String unitListId) {
        this.context = context;
        this.list = list;
        this.unitListId = unitListId;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.text_box_item, viewGroup, false);
            viewHolder = new ViewHolder(view);
            viewHolder.etText.setTag(i);

            class MyTextWatcher implements TextWatcher {

                public MyTextWatcher(ViewHolder holder) {
                    mHolder = holder;
                }

                private ViewHolder mHolder;

                @Override
                public void onTextChanged(CharSequence s, int start,
                                          int before, int count) {
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start,
                                              int count, int after) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    String words = s.toString();
                    //首先内容进行非空判断，空内容（""和null）不处理
                    int pos = (Integer) mHolder.etText.getTag();
                    TextLabelBean textLabelBean = new TextLabelBean();
                    textLabelBean.setLabel(list.get(i).getLabel());
                    textLabelBean.setText(StringUtils.isBlank(words) ? "null" : words);
                    list.set(pos, textLabelBean);

                    StringBuffer labelBuffer = new StringBuffer();
                    StringBuffer textBuffer = new StringBuffer();
                    for (int j = 0; j < list.size(); j++) {
                        labelBuffer.append(list.get(j).getLabel()).append("%%&@");
                        textBuffer.append(StringUtils.isBlank(list.get(j).getText()) ? "null" : list.get(j).getText()).append("%%&@");
                    }
                    String label = labelBuffer.toString().substring(0, labelBuffer.toString().length() - 4);
                    String text = textBuffer.toString().substring(0, textBuffer.toString().length() - 4);

                    UnitListBeanDao unitListBeanDao = MyApplication.getInstances().getUnitDaoSession().getUnitListBeanDao();
                    List<UnitListBean> unitListBeans = unitListBeanDao.queryBuilder()
                            .where(UnitListBeanDao.Properties.Uuid.eq((String) SPUtils.get(context, "uuId", "")))
                            .where(UnitListBeanDao.Properties.Id.eq(unitListId))
                            .list();

                    UnitListBean unitListBean = new UnitListBean(unitListBeans.get(0).getUId(),
                            unitListBeans.get(0).getUuid(),
                            unitListBeans.get(0).getAnswer(),
                            unitListBeans.get(0).getContent(),
                            unitListBeans.get(0).getContentFile(),
                            unitListBeans.get(0).getId(),
                            unitListBeans.get(0).getKeyUuid(),
                            label,
                            unitListBeans.get(0).getRelevantFile(),
                            unitListBeans.get(0).getSx(),
                            text,
                            unitListBeans.get(0).getType(),
                            unitListBeans.get(0).getUserName());
                    unitListBeanDao.update(unitListBean);


                }
            }
            viewHolder.etText.addTextChangedListener(new MyTextWatcher(viewHolder));

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
            viewHolder.etText.setTag(i);
        }

        viewHolder.tvText.setText(list.get(i).getLabel() + ":");
        viewHolder.etText.setText(StringUtils.isBlank(list.get(i).getText()) ? "" : list.get(i).getText());

        return view;
    }

    static
    class ViewHolder {
        @BindView(R.id.et_text)
        EditText etText;
        @BindView(R.id.tv_text)
        TextView tvText;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
