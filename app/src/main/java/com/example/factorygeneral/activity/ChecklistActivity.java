package com.example.factorygeneral.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.factorygeneral.R;
import com.example.factorygeneral.adapter.ChecklistAdapter;
import com.example.factorygeneral.base.BaseActivity;
import com.example.factorygeneral.base.MyApplication;
import com.example.factorygeneral.greendao.bean.PackageBean;
import com.example.factorygeneral.greendao.db.PackageBeanDao;
import com.example.factorygeneral.view.MyListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ChecklistActivity extends BaseActivity {

    @BindView(R.id.iv_genduo)
    ImageView ivGenduo;
    @BindView(R.id.tv_tuichu)
    TextView tvTuichu;
    @BindView(R.id.lv_checklist)
    MyListView lvChecklist;

    public static Intent openIntent(Context context) {
        Intent intent = new Intent(context, ChecklistActivity.class);
        return intent;
    }

    private List<PackageBean> list = new ArrayList<>();
    @Override
    protected void initView() {
        ivGenduo.setOnClickListener(view -> finish());
        PackageBeanDao packageBeanDao = MyApplication.getInstances().getPackageDaoSession().getPackageBeanDao();
        List<PackageBean> packageBeans = packageBeanDao.loadAll();

        list.addAll(packageBeans);

        ChecklistAdapter checklistAdapter=new ChecklistAdapter(this,list);
        lvChecklist.setAdapter(checklistAdapter);

        lvChecklist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(MainActivity.openIntent(ChecklistActivity.this,list.get(i).getUuid()));
                finish();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_checklist;
    }

    @Override
    protected void initDataAndEvent() {

    }
}
