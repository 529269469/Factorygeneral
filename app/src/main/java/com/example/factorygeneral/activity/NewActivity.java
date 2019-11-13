package com.example.factorygeneral.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.example.factorygeneral.R;
import com.example.factorygeneral.adapter.ChecklistAdapter;
import com.example.factorygeneral.adapter.DownloadAdapter;
import com.example.factorygeneral.base.BaseActivity;
import com.example.factorygeneral.bean.FindFilesBean;
import com.example.factorygeneral.greendao.bean.PackageBean;
import com.example.factorygeneral.net.HttpUtils;
import com.example.factorygeneral.net.MyCallBack;
import com.example.factorygeneral.net.URLS;
import com.example.factorygeneral.utils.FileUtils;
import com.example.factorygeneral.utils.StringUtils;
import com.example.factorygeneral.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class NewActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.iv_genduo)
    ImageView ivGenduo;
    @BindView(R.id.tv_uploading)
    TextView tvUploading;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_download)
    TextView tvDownload;
    @BindView(R.id.lv_checklist)
    ListView lvChecklist;
    @BindView(R.id.help_center_loading_prgbar)
    RelativeLayout helpCenterLoadingPrgbar;

    private String name;
    private DownloadAdapter downloadAdapter;

    public static Intent openIntent(Context context, String name) {
        Intent intent = new Intent(context, NewActivity.class);
        intent.putExtra("name", name);
        return intent;
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    helpCenterLoadingPrgbar.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    helpCenterLoadingPrgbar.setVisibility(View.GONE);
                    break;

            }

        }
    };

    private List<FindFilesBean.DataBean> list = new ArrayList<>();

    @Override
    protected void initView() {
        name = getIntent().getStringExtra("name");
        ivGenduo.setOnClickListener(view -> finish());

        downloadAdapter = new DownloadAdapter(this, list);
        lvChecklist.setAdapter(downloadAdapter);


        tvUploading.setOnClickListener(this);
        tvDownload.setOnClickListener(this);


        lvChecklist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (isDownload) {
                    //是下载
                    handler.sendEmptyMessage(1);
                    downloadMethod(list.get(i));

                } else {
                    //是上传
                    handler.sendEmptyMessage(1);
                    uploadingMethod(list.get(i));

                }
            }
        });

        lvChecklist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (!isDownload) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(NewActivity.this);
                    builder.setTitle("是否将数据包删除");
                    builder.setPositiveButton("是！！", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            FileUtils.delFile(Environment.getExternalStorageDirectory() + "/factorygeneral/"+list.get(i).getName());
                            ToastUtils.getInstance().showTextToast(NewActivity.this,"删除成功");
                            list.remove(i);
                            downloadAdapter.notifyDataSetChanged();
                        }
                    });
                    builder.setNeutralButton("否", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                        }
                    });
                    builder.show();

                }
                return true;
            }
        });

    }

    private void uploadingMethod(FindFilesBean.DataBean dataBean) {
        OkHttpUtils.post()
                .url(URLS.APPUPFILE)
                .addParams("userName",name)
                .addFile("file",dataBean.getName(),new File(Environment.getExternalStorageDirectory() + "/factorygeneral/"+dataBean.getName()))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtils.getInstance().showTextToast(NewActivity.this,"上传失败");
                        handler.sendEmptyMessage(2);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        ToastUtils.getInstance().showTextToast(NewActivity.this,"上传成功");
                        handler.sendEmptyMessage(2);
                    }
                });


    }

    /**
     *  下载方法
     * @param dataBean
     */
    private void downloadMethod(FindFilesBean.DataBean dataBean) {
        OkHttpUtils.get()
                .url(URLS.LISTDOWN+"?userName="+name+"&fileName="+dataBean.getName())
                .build()
                .execute(new FileCallBack(Environment.getExternalStorageDirectory() + "/factorygeneral",dataBean.getName()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtils.getInstance().showTextToast(NewActivity.this,"下载失败");
                        handler.sendEmptyMessage(2);
                    }

                    @Override
                    public void onResponse(File response, int id) {
                        ToastUtils.getInstance().showTextToast(NewActivity.this,"下载成功");
                        handler.sendEmptyMessage(2);
                    }
                });


    }



    @Override
    protected int getLayoutId() {
        return R.layout.activity_new;
    }

    @Override
    protected void initDataAndEvent() {
        tv_title.setText("下载");
        Map<String, String> map = new HashMap<>();
        map.put("userName", name);
        HttpUtils.getInstance().post(URLS.USERNAME, map, new MyCallBack<FindFilesBean>() {
            @Override
            public void onSuccess(FindFilesBean findFilesBean) {
                list.clear();
                if (findFilesBean.getData() != null && !findFilesBean.getData().isEmpty()) {
                    list.addAll(findFilesBean.getData());
                }
                downloadAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFaile(String msg) {

            }
        });


    }

    boolean isDownload = true;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_download:
                isDownload = true;
                tvDownload.setTextColor(getResources().getColor(R.color.color_FFFFFF));
                tvDownload.setBackgroundColor(getResources().getColor(R.color.color_5398F7));
                tvUploading.setTextColor(getResources().getColor(R.color.color_5398F7));
                tvUploading.setBackground(getResources().getDrawable(R.drawable.shape_text));
                tv_title.setText("下载");
                Map<String, String> map = new HashMap<>();
                map.put("userName", name);
                HttpUtils.getInstance().post(URLS.USERNAME, map, new MyCallBack<FindFilesBean>() {
                    @Override
                    public void onSuccess(FindFilesBean findFilesBean) {
                        if (findFilesBean.getData() != null && !findFilesBean.getData().isEmpty()) {
                            list.clear();
                            list.addAll(findFilesBean.getData());
                            downloadAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFaile(String msg) {

                    }
                });
                break;
            case R.id.tv_uploading:
                isDownload = false;
                tvUploading.setTextColor(getResources().getColor(R.color.color_FFFFFF));
                tvUploading.setBackgroundColor(getResources().getColor(R.color.color_5398F7));
                tvDownload.setTextColor(getResources().getColor(R.color.color_5398F7));
                tvDownload.setBackground(getResources().getDrawable(R.drawable.shape_text));


                tv_title.setText("上传");
                File files = new File(Environment.getExternalStorageDirectory() + "/factorygeneral");
                if (!files.exists()) {
                    files.mkdirs();
                }
                File[] subFile = files.listFiles();
                list.clear();
                for (int i = 0; i < subFile.length; i++) {
                    String filename = subFile[i].getName();
                    File file = new File(filename);
                    /* 取得扩展名 */
                    String end = file.getName().substring(file.getName().lastIndexOf(".") + 1, file.getName().length()).toLowerCase(Locale.getDefault());
                    if (end.equals("zip")) {
                        FindFilesBean.DataBean dataBean = new FindFilesBean.DataBean();
                        dataBean.setName(filename);
                        dataBean.setUserName(name);
                        list.add(dataBean);
                    }

                }
                downloadAdapter.notifyDataSetChanged();

                break;
        }
    }
}
