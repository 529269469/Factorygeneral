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
import com.example.factorygeneral.greendao.bean.ModuleListBean;
import com.example.factorygeneral.greendao.db.UnitListBeanDao;
import com.example.factorygeneral.utils.SPUtils;
import com.example.factorygeneral.utils.StringUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author :created by ${ WYW }
 * 时间：2019/11/12 10
 */
public class ModificationAdapter extends BaseAdapter {
    private List<ModuleListBean> list;
    private Context context;

    public ModificationAdapter(List<ModuleListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public List<ModuleListBean> getList() {
        return list;
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
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.modification_item, viewGroup, false);
            viewHolder = new ViewHolder(view);

            viewHolder.etModification.setTag(position);

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
                    if (!StringUtils.isBlank(words)) {
                        int pos = (Integer) mHolder.etModification.getTag();
                        ModuleListBean moduleListBean=new ModuleListBean();
                        moduleListBean.setId(list.get(pos).getId());
                        moduleListBean.setKeyId(list.get(pos).getKeyId());
                        moduleListBean.setKeyUuid(list.get(pos).getKeyUuid());
                        moduleListBean.setName(words);
                        moduleListBean.setUId(list.get(pos).getUId());
                        moduleListBean.setUserName(list.get(pos).getUserName());
                        moduleListBean.setUuid(list.get(pos).getUuid());
                        list.set(pos,moduleListBean);

                    }
                }
            }
            viewHolder.etModification.addTextChangedListener(new MyTextWatcher(viewHolder));
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
            viewHolder.etModification.setTag(position);
        }
        viewHolder.etModification.setText(StringUtils.isBlank(list.get(position).getName()) ? "" : list.get(position).getName());

        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.et_modification)
        EditText etModification;
        @BindView(R.id.tv_del)
        TextView tvDel;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
