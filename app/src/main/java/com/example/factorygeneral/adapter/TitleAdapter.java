package com.example.factorygeneral.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.factorygeneral.R;
import com.example.factorygeneral.bean.TitleBean;
import com.example.factorygeneral.greendao.bean.MenusListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author :created by ${ WYW }
 * 时间：2019/9/9 11
 */
public class TitleAdapter extends BaseAdapter {
    private Context context;
    private List<MenusListBean> list;

    public TitleAdapter(Context context, List<MenusListBean> list) {
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
            view = LayoutInflater.from(context).inflate(R.layout.title, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tvTitle.setText(list.get(position).getName());
        if (list.get(position).isCheck()){
            viewHolder.tvTitle.setBackground(context.getResources().getDrawable(R.color.color_5398F7));
            viewHolder.tvTitle.setTextColor(context.getResources().getColor(R.color.color_FFFFFF));
        }else {
            viewHolder.tvTitle.setBackgroundColor(context.getResources().getColor(R.color.color_FFFFFF));
            viewHolder.tvTitle.setTextColor(context.getResources().getColor(R.color.color_333333));
        }
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
