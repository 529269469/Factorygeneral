package com.example.factorygeneral.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.factorygeneral.R;
import com.example.factorygeneral.base.MyApplication;
import com.example.factorygeneral.bean.TextLabelBean;
import com.example.factorygeneral.utils.OpenFileUtil;
import com.example.factorygeneral.utils.SPUtils;
import com.example.factorygeneral.utils.ToastUtils;
import com.example.factorygeneral.utils.ToastUtils2;


import java.io.File;
import java.util.List;
import java.util.Locale;

public class GridAdapter extends BaseAdapter {
    private List<TextLabelBean> list;
    private Context context;

    public GridAdapter(List<TextLabelBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolde viewHolde = null;
        if (convertView == null) {
            viewHolde = new ViewHolde();
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_item, parent, false);
            viewHolde.iv_grid = convertView.findViewById(R.id.iv_grid);
            viewHolde.iv_grid2 = convertView.findViewById(R.id.iv_grid2);
            viewHolde.iv_del = convertView.findViewById(R.id.iv_del);

            convertView.setTag(viewHolde);
        } else {
            viewHolde = (ViewHolde) convertView.getTag();
        }


        /* 取得扩展名 */
        String end = list.get(position).getText().substring(list.get(position).getText().lastIndexOf(".") + 1, list.get(position).getText().length()).toLowerCase(Locale.getDefault());
        if (end.equals("m4a") ||
                end.equals("mp3") ||
                end.equals("mid") ||
                end.equals("xmf") ||
                end.equals("ogg") ||
                end.equals("wav") ||
                end.equals("3gp") ||
                end.equals("mp4") ||
                end.equals("wmv")) {
            viewHolde.iv_grid2.setVisibility(View.VISIBLE);
        } else {
            viewHolde.iv_grid2.setVisibility(View.GONE);
        }


        if (end.equals("doc") || end.equals("docx")) {
            viewHolde.iv_grid.setImageResource(R.drawable.doc);
        } else if (end.equals("xls") || end.equals("xlsx")) {
            viewHolde.iv_grid.setImageResource(R.drawable.excel3);
        } else if (end.equals("pdf")) {
            viewHolde.iv_grid.setImageResource(R.drawable.ptf);
        }else {
            Log.e("TAG", "getView: "+list.get(position).getText() );
            if (list.get(position).getText().indexOf("/storage/emulated")!=-1){
                Glide.with(context)
                        .load(new File(list.get(position).getText()))
                        .into(viewHolde.iv_grid);
            }else {
                Glide.with(context)
                        .load(new File((String)SPUtils.get(context, "PackagePath", "") + File.separator + list.get(position).getText()))
                        .into(viewHolde.iv_grid);
            }

        }


        viewHolde.iv_grid.setOnClickListener(v -> {
                try {
                    if (list.get(position).getText().indexOf("/storage/emulated")!=-1){
                        context.startActivity(OpenFileUtil.openFile( list.get(position).getText()));
                    }else {
                        context.startActivity(OpenFileUtil.openFile(SPUtils.get(context, "PackagePath", "") + File.separator + list.get(position).getText()));
                    }
                } catch (Exception e) {
                    Toast.makeText(context, "打开失败，原因：文件已经被移动或者删除", Toast.LENGTH_SHORT).show();
                }


        });

        viewHolde.iv_grid.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                ToastUtils.getInstance().showTextToast(context,list.get(position).getLabel());
                return true;
            }
        });


        viewHolde.iv_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDel.onDel(position);
            }
        });

        return convertView;
    }

    public static class ViewHolde {
        private ImageView iv_grid;
        private ImageView iv_grid2;
        private ImageView iv_del;
    }

    public interface OnDel {
        public void onDel(int position);
    }

    private OnDel onDel;

    public void setOnDel(OnDel onDel) {
        this.onDel = onDel;
    }
}
