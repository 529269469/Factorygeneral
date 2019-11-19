package com.example.factorygeneral.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.solver.GoalRow;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import com.example.factorygeneral.R;
import com.example.factorygeneral.adapter.TitleAdapter;
import com.example.factorygeneral.base.BaseActivity;
import com.example.factorygeneral.base.MyApplication;
import com.example.factorygeneral.bean.MenuBean;
import com.example.factorygeneral.bean.TitleBean;
import com.example.factorygeneral.fragment.main.HomeFragment;
import com.example.factorygeneral.greendao.bean.MenusListBean;
import com.example.factorygeneral.greendao.bean.ModuleListBean;
import com.example.factorygeneral.greendao.bean.PackageBean;
import com.example.factorygeneral.greendao.bean.ProjectModelBean;
import com.example.factorygeneral.greendao.bean.UnitListBean;
import com.example.factorygeneral.greendao.db.MenusListBeanDao;
import com.example.factorygeneral.greendao.db.ModuleListBeanDao;
import com.example.factorygeneral.greendao.db.PackageBeanDao;
import com.example.factorygeneral.greendao.db.ProjectModelBeanDao;
import com.example.factorygeneral.greendao.db.UnitListBeanDao;
import com.example.factorygeneral.utils.DataUtils;
import com.example.factorygeneral.utils.IOUtil;
import com.example.factorygeneral.utils.SPUtils;
import com.example.factorygeneral.utils.ToastUtils;
import com.example.factorygeneral.view.OperationPopupWindow;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.frame)
    FrameLayout frame;
    @BindView(R.id.tv_daochu)
    TextView tvDaochu;
    private String uuId;
    private HomeFragment homeFragment;

    public static Intent openIntent(Context context, String uuId) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("uuId", uuId);
        return intent;
    }

    @BindView(R.id.iv_genduo)
    ImageView ivGenduo;
    @BindView(R.id.tv_tuichu)
    TextView tvTuichu;
    @BindView(R.id.tv_operation)
    TextView tvOperation;
    @BindView(R.id.help_loading)
    RelativeLayout helpLoading;
    @BindView(R.id.gv_one)
    ListView gvOne;
    @BindView(R.id.ll_title)
    LinearLayout llTitle;
    @BindView(R.id.drawerlayout_drawer)
    DrawerLayout drawerlayoutDrawer;


    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    helpLoading.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    helpLoading.setVisibility(View.GONE);
                    ToastUtils.getInstance().showTextToast(MainActivity.this, "导出成功");
                    finish();
                    break;

            }

        }
    };


    private TitleAdapter titleAdapter;
    private List<MenusListBean> list = new ArrayList<>();

    private FragmentTransaction transaction;

    @Override
    protected void initView() {
        uuId = getIntent().getStringExtra("uuId");

        ivGenduo.setOnClickListener(this);
        tvOperation.setOnClickListener(this);
        tvDaochu.setOnClickListener(this);
        PackageBeanDao packageBeanDao = MyApplication.getInstances().getPackageDaoSession().getPackageBeanDao();
        List<PackageBean> packageBeans = packageBeanDao.queryBuilder()
                .where(PackageBeanDao.Properties.Uuid.eq(uuId))
                .list();
        SPUtils.put(this, "uuId", packageBeans.get(0).getUuid());
        SPUtils.put(this, "PackageName", packageBeans.get(0).getPackageName());
        SPUtils.put(this, "PackagePath", packageBeans.get(0).getPackagePath());
        MenusListBeanDao menusListBeanDao = MyApplication.getInstances().getMenusDaoSession().getMenusListBeanDao();
        List<MenusListBean> menusListBeans = menusListBeanDao.queryBuilder()
                .where(MenusListBeanDao.Properties.Uuid.eq(uuId))
                .list();

        SPUtils.put(this, "userName", menusListBeans.get(0).getUserName());

        list.addAll(menusListBeans);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setCheck(false);
        }
        list.get(0).setCheck(true);
        titleAdapter = new TitleAdapter(this, list);
        gvOne.setAdapter(titleAdapter);

        transaction = getSupportFragmentManager().beginTransaction();
        homeFragment = new HomeFragment(uuId, list.get(0).getKeyId());
        transaction.replace(R.id.frame, homeFragment);
        transaction.commit();
        try {
            tvTuichu.setText(list.get(0).getName());
        }catch (Exception ex){

        }
        gvOne.setOnItemClickListener((adapterView, view, position, l) -> {
            drawerlayoutDrawer.closeDrawers();
            tvTuichu.setText(list.get(position).getName());
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setCheck(false);
            }
            list.get(position).setCheck(true);

            transaction = getSupportFragmentManager().beginTransaction();

            homeFragment = new HomeFragment(uuId, list.get(position).getKeyId());
            transaction.replace(R.id.frame, homeFragment);
            transaction.commit();

            titleAdapter.notifyDataSetChanged();
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initDataAndEvent() {

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_genduo:
                //显示侧滑菜单
                drawerlayoutDrawer.openDrawer(GravityCompat.START);
                break;
            case R.id.tv_operation:
                setDaochu();
                break;
            case R.id.tv_daochu:
                startActivity(SettingActivity.openIntent(this));
                break;
        }
    }

    private void setDaochu() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("是否将数据导出");
        builder.setPositiveButton("是！！", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        handler.sendEmptyMessage(1);
                        daochuBuilder();
                    }
                }.start();


            }
        });
        builder.setNeutralButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


            }
        });
        builder.show();


    }

    private void daochuBuilder() {
        PackageBeanDao packageBeanDao = MyApplication.getInstances().getPackageDaoSession().getPackageBeanDao();
        List<PackageBean> packageBeans = packageBeanDao.queryBuilder()
                .where(PackageBeanDao.Properties.Uuid.eq(uuId))
                .list();

        MenuBean menuBean = new MenuBean();
        menuBean.setTime(packageBeans.get(0).getTime());
        menuBean.setUuid(packageBeans.get(0).getUuid());

        List<MenuBean.MenusListBean> menusList = new ArrayList<>();
        MenusListBeanDao menusListBeanDao = MyApplication.getInstances().getMenusDaoSession().getMenusListBeanDao();
        List<MenusListBean> menusListBeans = menusListBeanDao.queryBuilder()
                .where(MenusListBeanDao.Properties.Uuid.eq(uuId))
                .list();

        for (int i = 0; i < menusListBeans.size(); i++) {
            MenuBean.MenusListBean menusListBean = new MenuBean.MenusListBean();
            menusListBean.setUuid(menusListBeans.get(i).getUuid());
            menusListBean.setKeyId(menusListBeans.get(i).getKeyId());
            menusListBean.setUserName(menusListBeans.get(i).getUserName());
            menusListBean.setName(menusListBeans.get(i).getName());
            menusListBean.setId(menusListBeans.get(i).getId());
            menusList.add(menusListBean);
        }
        menuBean.setMenusList(menusList);

        MenuBean.ProjectModelBean projectModelBean = new MenuBean.ProjectModelBean();

        ProjectModelBeanDao projectModelBeanDao = MyApplication.getInstances().getProjectModelDaoSession().getProjectModelBeanDao();
        List<ProjectModelBean> projectModelBeans = projectModelBeanDao.queryBuilder()
                .where(ProjectModelBeanDao.Properties.Uuid.eq(uuId)).list();

        projectModelBean.setId(projectModelBeans.get(0).getId());
        projectModelBean.setName(projectModelBeans.get(0).getName());
        projectModelBean.setUserName(projectModelBeans.get(0).getUserName());
        projectModelBean.setUuid(projectModelBeans.get(0).getUuid());
        menuBean.setProjectModel(projectModelBean);

        List<MenuBean.ModuleListBean> moduleList = new ArrayList<>();
        ModuleListBeanDao moduleListBeanDao = MyApplication.getInstances().getModuleDaoSession().getModuleListBeanDao();
        List<ModuleListBean> moduleListBeans = moduleListBeanDao.queryBuilder()
                .where(ModuleListBeanDao.Properties.Uuid.eq(uuId)).list();

        for (int i = 0; i < moduleListBeans.size(); i++) {
            MenuBean.ModuleListBean moduleListBean = new MenuBean.ModuleListBean();
            moduleListBean.setId(moduleListBeans.get(i).getId());
            moduleListBean.setKeyId(moduleListBeans.get(i).getKeyId());
            moduleListBean.setKeyUuid(moduleListBeans.get(i).getKeyUuid());
            moduleListBean.setName(moduleListBeans.get(i).getName());
            moduleListBean.setUserName(moduleListBeans.get(i).getUserName());
            moduleList.add(moduleListBean);
        }
        menuBean.setModuleList(moduleList);

        List<MenuBean.UnitListBean> unitList = new ArrayList<>();
        UnitListBeanDao unitListBeanDao = MyApplication.getInstances().getUnitDaoSession().getUnitListBeanDao();
        List<UnitListBean> unitListBeanList = unitListBeanDao.queryBuilder()
                .where(UnitListBeanDao.Properties.Uuid.eq(uuId)).list();
        for (int i = 0; i < unitListBeanList.size(); i++) {
            MenuBean.UnitListBean unitListBean = new MenuBean.UnitListBean();
            unitListBean.setAnswer(unitListBeanList.get(i).getAnswer());
            unitListBean.setContent(unitListBeanList.get(i).getContent());
            unitListBean.setContentFile(unitListBeanList.get(i).getContentFile());
            unitListBean.setId(unitListBeanList.get(i).getId());
            unitListBean.setKeyUuid(unitListBeanList.get(i).getKeyUuid());
            unitListBean.setLabel(unitListBeanList.get(i).getLabel());
            unitListBean.setRelevantFile(unitListBeanList.get(i).getRelevantFile());
            unitListBean.setSx(unitListBeanList.get(i).getSx());
            unitListBean.setText(unitListBeanList.get(i).getText());
            unitListBean.setType(unitListBeanList.get(i).getType());
            unitListBean.setUserName(unitListBeanList.get(i).getUserName());
            unitList.add(unitListBean);
        }
        menuBean.setUnitList(unitList);


        Gson gson = new Gson();
        String string = gson.toJson(menuBean);


        try {
            writeToFile(string, packageBeans.get(0).getPackagePath() + "/" + "menu.json");
        } catch (IOException e) {
            e.printStackTrace();
        }

        File file = new File(packageBeans.get(0).getPackagePath());
        String fileName = packageBeans.get(0).getPackageName().substring(0, packageBeans.get(0).getPackageName().length() - 18);

        try {
            IOUtil.getZipFile(file.listFiles(), Environment.getExternalStorageDirectory() + "/factorygeneral" + "/" + fileName + "(" + DataUtils.getData2() + ").zip");
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*****************************删除本条数据***************************************/
        packageBeanDao.deleteByKey(packageBeans.get(0).getUId());
        projectModelBeanDao.deleteByKey(projectModelBeans.get(0).getUId());

        for (int i = 0; i < menusListBeans.size(); i++) {
            menusListBeanDao.deleteByKey(menusListBeans.get(i).getUId());
        }
        for (int i = 0; i < moduleListBeans.size(); i++) {
            moduleListBeanDao.deleteByKey(moduleListBeans.get(i).getUId());
        }
        for (int i = 0; i < unitListBeanList.size(); i++) {
            unitListBeanDao.deleteByKey(unitListBeanList.get(i).getUId());
        }

        //删除数据文件夹
        deleteFile(new File(packageBeans.get(0).getPackagePath()));
        /*****************************删除本条数据***************************************/


        handler.sendEmptyMessage(2);

    }

    /**
     * 写string到file文件中
     */
    public static void writeToFile(String content, String fPath) throws IOException {
        File txt = new File(fPath);
        if (!txt.exists() && !txt.isFile()) {
            txt.mkdirs();
        }

        FileOutputStream fos = new FileOutputStream(txt);
        fos.write(content.getBytes());
        fos.flush();
        fos.close();
    }

    //flie：要删除的文件夹的所在位置
    private static void deleteFile(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                File f = files[i];
                deleteFile(f);
            }
            file.delete();//如要保留文件夹，只删除文件，请注释这行
        } else if (file.exists()) {
            file.delete();
        }
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
