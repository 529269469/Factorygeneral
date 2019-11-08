package com.example.factorygeneral.fragment.main;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.factorygeneral.R;
import com.example.factorygeneral.adapter.TbAdapter;
import com.example.factorygeneral.base.BaseFragment;
import com.example.factorygeneral.base.MyApplication;
import com.example.factorygeneral.greendao.bean.ModuleListBean;
import com.example.factorygeneral.greendao.db.ModuleListBeanDao;
import com.example.factorygeneral.view.ContentViewPager;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class HomeFragment extends BaseFragment {


    @BindView(R.id.tb)
    TabLayout tb;
    @BindView(R.id.iv_add)
    ImageView ivAdd;
    @BindView(R.id.iv_up)
    ImageView ivUp;
    @BindView(R.id.iv_del)
    ImageView ivDel;
    @BindView(R.id.vp)
    ViewPager vp;
    private String uuId;
    private String keyId;

    private List<ModuleListBean> listTitle=new ArrayList<>();
    private List<Fragment> list=new ArrayList<>();

    public HomeFragment(String uuId, String keyId) {
        super();
        this.uuId = uuId;
        this.keyId = keyId;
    }

    @Override
    protected void initEventAndData() {
//        uuId = getArguments().getString("uuId");
//        keyId = getArguments().getString("keyId");

        ModuleListBeanDao moduleListBeanDao= MyApplication.getInstances().getModuleDaoSession().getModuleListBeanDao();
        List<ModuleListBean> moduleListBeans=moduleListBeanDao.queryBuilder()
                .where(ModuleListBeanDao.Properties.Uuid.eq(uuId))
                .where(ModuleListBeanDao.Properties.KeyId.eq(keyId))
                .list();
        for (int i = 0; i < moduleListBeans.size(); i++) {
            listTitle.add(moduleListBeans.get(i));
            ModelFragment modelFragment=new ModelFragment();
            Bundle bundle = new Bundle();
            bundle.putString("uuId", uuId);
            bundle.putString("keyUuid", listTitle.get(i).getKeyUuid());
            modelFragment.setArguments(bundle);
            list.add(modelFragment);
        }

        TbAdapter tbAdapter=new TbAdapter(getChildFragmentManager(),listTitle,list);
        vp.setAdapter(tbAdapter);
        tb.setTabMode(TabLayout.MODE_FIXED);
        tb.setupWithViewPager(vp);


    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }
}
