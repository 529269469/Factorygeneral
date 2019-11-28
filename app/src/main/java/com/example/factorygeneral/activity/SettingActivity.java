package com.example.factorygeneral.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;

import androidx.fragment.app.FragmentTransaction;

import com.example.factorygeneral.R;
import com.example.factorygeneral.adapter.TitleSetAdapter;
import com.example.factorygeneral.base.BaseActivity;
import com.example.factorygeneral.bean.TitleBean;
import com.example.factorygeneral.fragment.setting.SettingFragment;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SettingActivity extends BaseActivity {

    @BindView(R.id.lv_setting)
    ListView lvSetting;

    private String uuId;
    public static Intent openIntent(Context context, String uuId) {
        Intent intent = new Intent(context, SettingActivity.class);
        intent.putExtra("uuId",uuId);
        return intent;
    }

    private TitleSetAdapter titleAdapter;
    private List<TitleBean> list = new ArrayList<>();

    private FragmentTransaction transaction;
    private SettingFragment settingFragment;
    @Override
    protected void initView() {
        uuId=getIntent().getStringExtra("uuId");
        list.add(new TitleBean("频道设置"));
        list.get(0).setCheck(true);
        titleAdapter = new TitleSetAdapter(this, list);
        lvSetting.setAdapter(titleAdapter);

        transaction = getSupportFragmentManager().beginTransaction();
        settingFragment = new SettingFragment();
        Bundle bundle = new Bundle();
        bundle.putString("uuId", uuId);
        settingFragment.setArguments(bundle);
        transaction.replace(R.id.fl_setting, settingFragment);
        transaction.commit();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initDataAndEvent() {

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        hideInputWhenTouchOtherView(this, ev, null);
        return super.dispatchTouchEvent(ev);
    }


    public boolean isTouchView(View view, MotionEvent event) {
        if (view == null || event == null) {
            return false;
        }
        int[] leftTop = {0, 0};
        view.getLocationInWindow(leftTop);
        int left = leftTop[0];
        int top = leftTop[1];
        int bottom = top + view.getHeight();
        int right = left + view.getWidth();
        if (event.getRawX() > left && event.getRawX() < right
                && event.getRawY() > top && event.getRawY() < bottom) {
            return true;
        }
        return false;
    }

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            return !isTouchView(v, event);
        }
        return false;
    }

    public void hideInputWhenTouchOtherView(Activity activity, MotionEvent ev, List<View> excludeViews) {

        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            if (excludeViews != null && !excludeViews.isEmpty()) {
                for (int i = 0; i < excludeViews.size(); i++) {
                    if (isTouchView(excludeViews.get(i), ev)) {
                        return;
                    }
                }
            }
            View v = activity.getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
                InputMethodManager inputMethodManager = (InputMethodManager)
                        activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                if (inputMethodManager != null) {
                    inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }

        }
    }


}
