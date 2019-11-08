package com.example.factorygeneral.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.factorygeneral.R;
import com.example.factorygeneral.greendao.bean.MenusListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author :created by ${ WYW }
 * 时间：2019/9/9 15
 */
public class PacketAdapter extends BaseAdapter {
    private Context context;
    private List<MenusListBean> list;
    public PacketAdapter(Context context, List<MenusListBean> list) {
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
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.packet_item, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.tvNo.setText(position + 1 + "");
        viewHolder.tvType2.setText(list.get(position).getName());

        return view;
    }

    static class ViewHolder {
        @BindView(R.id.tv_no)
        TextView tvNo;
        @BindView(R.id.tv_type2)
        TextView tvType2;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
