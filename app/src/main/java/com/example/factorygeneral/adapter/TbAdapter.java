package com.example.factorygeneral.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.factorygeneral.greendao.bean.ModuleListBean;

import java.util.List;

/**
 * @author :created by ${ WYW }
 * 时间：2019/9/27 10
 */
public class TbAdapter extends FragmentPagerAdapter {
    private List<ModuleListBean> listTitle;
    private List<Fragment> list;

    public TbAdapter(@NonNull FragmentManager fm, List<ModuleListBean> listTitle, List<Fragment> list) {
        super(fm);
        this.listTitle=listTitle;
        this.list=list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listTitle.get(position).getName();
    }

    @Override
    public int getItemPosition(Object object) {
        // 最简单解决 notifyDataSetChanged() 页面不刷新问题的方法
        return POSITION_NONE;
    }

}
