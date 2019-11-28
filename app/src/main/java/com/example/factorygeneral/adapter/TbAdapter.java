package com.example.factorygeneral.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;

import com.example.factorygeneral.greendao.bean.ModuleListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :created by ${ WYW }
 * 时间：2019/9/27 10
 */
public class TbAdapter extends FragmentPagerAdapter {
    private List<ModuleListBean> listTitle;
    private List<Fragment> list;
    private FragmentManager fm;
    public TbAdapter(@NonNull FragmentManager fm, List<ModuleListBean> listTitle, List<Fragment> list) {
        super(fm);
        this.listTitle=listTitle;
        this.list=list;
        this.fm = fm;
    }

    private List<String> myTag = new ArrayList<>();

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //将生成的key保存起来
        myTag .add(makeFragmentName(container.getId(), getItemId(position)));
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        this.fm.beginTransaction().show(fragment).commitAllowingStateLoss();
        return fragment;
    }

    private String makeFragmentName(int viewId, long id) {
        return "android:switcher:" + viewId + ":" + id;
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

    //改变的时候调用   将所有的fragment缓存全部删除
    public void reMoveAll() {
        if (this.myTag != null) {
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            for (int i = 0; i < myTag.size(); i++) {
                Fragment fragmentO = fm.findFragmentByTag(myTag.get(i));
                fragmentTransaction.remove(fragmentO);
            }
            fragmentTransaction.commitAllowingStateLoss();
            fm.executePendingTransactions();
            myTag.clear();
        }
        notifyDataSetChanged();
    }



}
