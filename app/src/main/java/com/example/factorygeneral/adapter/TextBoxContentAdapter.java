package com.example.factorygeneral.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.factorygeneral.R;
import com.example.factorygeneral.base.MyApplication;
import com.example.factorygeneral.bean.TextLabelBean;
import com.example.factorygeneral.greendao.db.UnitListBeanDao;
import com.example.factorygeneral.utils.SPUtils;
import com.example.factorygeneral.utils.StringUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author :created by ${ WYW }
 * 时间：2019/11/18 10
 */
public class TextBoxContentAdapter extends BaseAdapter {
    private Context context;
    private List<TextLabelBean> list;

    public List<TextLabelBean> getList() {
        return list;
    }

    public TextBoxContentAdapter(Context context, List<TextLabelBean> list) {
        this.context = context;
        this.list = list;
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
        ViewHolder viewHolder=null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.text_box_content_item, null);
            viewHolder=new ViewHolder(view);

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
                    textLabelBean.setLabel(StringUtils.isBlank(words) ? "null" : words);
                    list.set(pos, textLabelBean);
                }
            }
            viewHolder.etText.addTextChangedListener(new MyTextWatcher(viewHolder));
            view.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) view.getTag();
            viewHolder.etText.setTag(i);
        }
        TextLabelBean textLabelBean = list.get(i);
        viewHolder.etText.setText(StringUtils.isBlank(textLabelBean.getLabel()) ? "" : textLabelBean.getLabel());

        viewHolder.tvDel.setOnClickListener(view1 -> {
            list.remove(i);
            notifyDataSetChanged();
        });
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.et_text)
        EditText etText;
        @BindView(R.id.tv_del)
        TextView tvDel;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
