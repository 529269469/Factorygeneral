package com.example.factorygeneral.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.factorygeneral.R;
import com.example.factorygeneral.adapter.ToAdapter;
import com.example.factorygeneral.base.BaseActivity;
import com.example.factorygeneral.base.MyApplication;
import com.example.factorygeneral.bean.MenuBean;
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
import com.example.factorygeneral.utils.ToastUtils;
import com.example.factorygeneral.utils.ZipUtils2;
import com.example.factorygeneral.view.MyListView;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;

public class ToActivity extends BaseActivity {
    public static Intent openIntent(Context context) {
        Intent intent = new Intent(context, ToActivity.class);
        return intent;
    }

    @BindView(R.id.iv_genduo)
    ImageView ivGenduo;
    @BindView(R.id.tv_tuichu)
    TextView tvTuichu;
    @BindView(R.id.lv_checklist)
    MyListView lvChecklist;
    @BindView(R.id.help_center_loading_prgbar)
    RelativeLayout helpCenterLoadingPrgbar;

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

    private List<PackageBean> list = new ArrayList<>();
    private boolean isPath;

    @Override
    protected void initView() {
        ivGenduo.setOnClickListener(view -> finish());

        File files = new File(Environment.getExternalStorageDirectory() + "/factorygeneral");
        if (!files.exists()) {
            files.mkdirs();
        }
        File[] subFile = files.listFiles();
        for (int i = 0; i < subFile.length; i++) {
            String filename = subFile[i].getName();
            File file = new File(filename);
            /* 取得扩展名 */
            String end = file.getName().substring(file.getName().lastIndexOf(".") + 1, file.getName().length()).toLowerCase(Locale.getDefault());
            if (end.equals("zip")) {
                list.add(new PackageBean(filename, subFile[i] + ""));
            }

        }
        ToAdapter toAdapter = new ToAdapter(this, list);
        lvChecklist.setAdapter(toAdapter);

        lvChecklist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (list.get(position).getPackageName() != null) {
                    PackageBeanDao packageBeanDao = MyApplication.getInstances().getPackageDaoSession().getPackageBeanDao();
                    List<PackageBean> packageBeans = packageBeanDao.queryBuilder()
                            .where(PackageBeanDao.Properties.PackageName.eq(list.get(position).getPackageName()))
                            .list();
                    if (packageBeans != null && !packageBeans.isEmpty()) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(ToActivity.this);
                        builder.setTitle("数据包已存在，是否覆盖");
                        builder.setPositiveButton("覆盖！！", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                isPath = true;
                                handler.sendEmptyMessage(1);
                                new Thread() {
                                    @Override
                                    public void run() {
                                        try {
                                            upData(position);
                                            //需要在子线程中处理的逻辑
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }.start();
                            }
                        });
                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                isPath = false;
                            }
                        });
                        builder.show();

                    } else {
                        isPath = false;
                        handler.sendEmptyMessage(1);
                        new Thread() {
                            @Override
                            public void run() {
                                try {
                                    upData(position);
                                    //需要在子线程中处理的逻辑
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }.start();

                    }
                }


            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_to;
    }

    @Override
    protected void initDataAndEvent() {

    }


    private void upData(int position) {
        File file = new File(list.get(position).getPackagePath());
        if (file.exists()) {
            String upLoadFilePath = file.toString();
            String upLoadFileName = file.getName();
            Log.e("TAG", "upLoadFilePath: " + upLoadFilePath);
            Log.e("TAG", "upLoadFileName: " + upLoadFileName);
            String upLoadFile = upLoadFilePath.substring(0, upLoadFilePath.length() - 4);
            Log.e("TAG", "upLoadFile: " + upLoadFile);
            try {
                ZipUtils2.UnZipFolder(upLoadFilePath, upLoadFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
            filePath(upLoadFile, upLoadFileName);

        }else {
            handler.sendEmptyMessage(2);
            ToastUtils.getInstance().showTextToast(this,"数据包错误");
        }

    }


    private void filePath(String upLoadFile, String upLoadFileName) {
        File files = new File(upLoadFile);
        File[] subFile = files.listFiles();
        for (int i = 0; i < subFile.length; i++) {
            String filename = subFile[i].getName();
            File file = new File(filename);
            /* 取得扩展名 */
            String end = file.getName().substring(file.getName().lastIndexOf(".") + 1, file.getName().length()).toLowerCase(Locale.getDefault());
            if (end.equals("json")) {
                input(subFile[i], upLoadFileName, upLoadFile);
                break;
            }

        }
    }

    private void input(File file, String upLoadFileName, String upLoadFile) {
        String content = "";
        try {
            InputStream instream = new FileInputStream(file);
            InputStreamReader inputreader = new InputStreamReader(instream);
            BufferedReader buffreader = new BufferedReader(inputreader);
            String line;
            //分行读取
            while ((line = buffreader.readLine()) != null) {
                content += line + "\n";
            }
            instream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        MenuBean menuBean = gson.fromJson(content, MenuBean.class);

        PackageBeanDao packageBeanDao = MyApplication.getInstances().getPackageDaoSession().getPackageBeanDao();
        ModuleListBeanDao moduleListBeanDao = MyApplication.getInstances().getModuleDaoSession().getModuleListBeanDao();
        MenusListBeanDao menusListBeanDao = MyApplication.getInstances().getMenusDaoSession().getMenusListBeanDao();
        ProjectModelBeanDao projectModelBeanDao = MyApplication.getInstances().getProjectModelDaoSession().getProjectModelBeanDao();
        UnitListBeanDao unitListBeanDao = MyApplication.getInstances().getUnitDaoSession().getUnitListBeanDao();

        List<PackageBean> packageBeans = packageBeanDao.queryBuilder()
                .where(PackageBeanDao.Properties.Uuid.eq(menuBean.getUuid()))
                .list();
        if (packageBeans != null && !packageBeans.isEmpty()) {
            isPath = true;
        }

        if (isPath) {
            for (int i = 0; i < packageBeans.size(); i++) {
                packageBeanDao.deleteByKey(packageBeans.get(i).getUId());
            }
            List<ModuleListBean> moduleListBeans = moduleListBeanDao.queryBuilder()
                    .where(ModuleListBeanDao.Properties.Uuid.eq(menuBean.getUuid()))
                    .list();
            for (int i = 0; i < moduleListBeans.size(); i++) {
                moduleListBeanDao.deleteByKey(moduleListBeans.get(i).getUId());
            }
            List<MenusListBean> menusListBeans = menusListBeanDao.queryBuilder()
                    .where(MenusListBeanDao.Properties.Uuid.eq(menuBean.getUuid()))
                    .list();
            for (int i = 0; i < menusListBeans.size(); i++) {
                menusListBeanDao.deleteByKey(menusListBeans.get(i).getUId());
            }

            List<ProjectModelBean> projectModelBeans = projectModelBeanDao.queryBuilder()
                    .where(ProjectModelBeanDao.Properties.Uuid.eq(menuBean.getUuid()))
                    .list();
            for (int i = 0; i < projectModelBeans.size(); i++) {
                projectModelBeanDao.deleteByKey(projectModelBeans.get(i).getUId());
            }

            List<UnitListBean> unitListBeans = unitListBeanDao.queryBuilder()
                    .where(UnitListBeanDao.Properties.Uuid.eq(menuBean.getUuid()))
                    .list();
            for (int i = 0; i < unitListBeans.size(); i++) {
                unitListBeanDao.deleteByKey(unitListBeans.get(i).getUId());
            }


        }

        try {
            PackageBean packageBean = new PackageBean(null,
                    upLoadFileName,
                    upLoadFile,
                    menuBean.getTime(),
                    menuBean.getUuid());
            packageBeanDao.insert(packageBean);
        } catch (Exception ex) {

        }


        try {
            ProjectModelBean projectModelBean = new ProjectModelBean(null,
                    menuBean.getProjectModel().getId(),
                    menuBean.getProjectModel().getName(),
                    menuBean.getProjectModel().getUserName(),
                    menuBean.getProjectModel().getUuid());
            projectModelBeanDao.insert(projectModelBean);
        } catch (Exception ex) {

        }


        for (int i = 0; i < menuBean.getMenusList().size(); i++) {
            try {
                MenusListBean menusListBean = new MenusListBean(null,false,
                        menuBean.getMenusList().get(i).getId(),
                        menuBean.getMenusList().get(i).getKeyId(),
                        menuBean.getMenusList().get(i).getName(),
                        menuBean.getMenusList().get(i).getUserName(),
                        menuBean.getMenusList().get(i).getUuid());
                menusListBeanDao.insert(menusListBean);
            } catch (Exception ex) {

            }

        }

        for (int i = 0; i < menuBean.getModuleList().size(); i++) {
            try {
                ModuleListBean moduleListBean = new ModuleListBean(null,
                        menuBean.getUuid(),
                        menuBean.getModuleList().get(i).getId(),
                        menuBean.getModuleList().get(i).getKeyId(),
                        menuBean.getModuleList().get(i).getKeyUuid(),
                        menuBean.getModuleList().get(i).getName(),
                        menuBean.getModuleList().get(i).getUserName());
                moduleListBeanDao.insert(moduleListBean);
            } catch (Exception ex) {

            }

        }

        for (int i = 0; i < menuBean.getUnitList().size(); i++) {
            try {
                UnitListBean unitListBean = new UnitListBean(null,
                        menuBean.getUuid(),
                        menuBean.getUnitList().get(i).getAnswer(),
                        menuBean.getUnitList().get(i).getContent(),
                        menuBean.getUnitList().get(i).getContentFile(),
                        menuBean.getUnitList().get(i).getId(),
                        menuBean.getUnitList().get(i).getKeyUuid(),
                        menuBean.getUnitList().get(i).getLabel(),
                        menuBean.getUnitList().get(i).getRelevantFile(),
                        menuBean.getUnitList().get(i).getSx(),
                        menuBean.getUnitList().get(i).getText(),
                        menuBean.getUnitList().get(i).getType(),
                        menuBean.getUnitList().get(i).getUserName());
                unitListBeanDao.insert(unitListBean);
            } catch (Exception ex) {

            }

        }

        handler.sendEmptyMessage(2);
        startActivity(MainActivity.openIntent(ToActivity.this,menuBean.getUuid()));
        finish();

    }


}
