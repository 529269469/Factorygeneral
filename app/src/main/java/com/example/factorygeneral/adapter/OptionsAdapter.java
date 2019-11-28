package com.example.factorygeneral.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.factorygeneral.R;
import com.example.factorygeneral.bean.TitleBean;
import com.example.factorygeneral.utils.StringUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author :created by ${ WYW }
 * 时间：2019/10/9 16
 */
public class OptionsAdapter extends BaseAdapter {
    private Context context;
    private List<TitleBean> list;

    public OptionsAdapter(Context context, List<TitleBean> list) {
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
            view = LayoutInflater.from(context).inflate(R.layout.lv_options_item, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.tvDanxuan.setText(StringUtils.isBlank(list.get(i).getTitle())?"":list.get(i).getTitle());

        if (list.get(i).isCheck()){
            viewHolder.ivDanxuan.setImageResource(R.drawable.danxuan2);
        }else {
            viewHolder.ivDanxuan.setImageResource(R.drawable.danxuan);
        }

        return view;
    }



    static class ViewHolder {
        @BindView(R.id.iv_danxuan)
        ImageView ivDanxuan;
        @BindView(R.id.tv_danxuan)
        TextView tvDanxuan;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


}
