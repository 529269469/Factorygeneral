package com.example.factorygeneral.fragment.main;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.factorygeneral.R;
import com.example.factorygeneral.adapter.ModificationAdapter;
import com.example.factorygeneral.adapter.TbAdapter;
import com.example.factorygeneral.base.BaseFragment;
import com.example.factorygeneral.base.MyApplication;
import com.example.factorygeneral.bean.TextLabelBean;
import com.example.factorygeneral.greendao.bean.ModuleListBean;
import com.example.factorygeneral.greendao.bean.UnitListBean;
import com.example.factorygeneral.greendao.db.ModuleListBeanDao;
import com.example.factorygeneral.greendao.db.UnitListBeanDao;
import com.example.factorygeneral.utils.FileUtils;
import com.example.factorygeneral.utils.SPUtils;
import com.example.factorygeneral.utils.StringUtils;
import com.example.factorygeneral.view.ContentViewPager;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class HomeFragment extends BaseFragment implements View.OnClickListener {


    @BindView(R.id.tb)
    TabLayout tb;
    @BindView(R.id.iv_add)
    ImageView ivAdd;//添加
    @BindView(R.id.iv_up)
    ImageView ivUp;//修改
    @BindView(R.id.vp)
    ViewPager vp;
    private String uuId;
    private String keyId;

    private List<ModuleListBean> listTitle = new ArrayList<>();
    private List<Fragment> list = new ArrayList<>();
    private TbAdapter tbAdapter;

    public HomeFragment(String uuId, String keyId) {
        super();
        this.uuId = uuId;
        this.keyId = keyId;
    }

    @Override
    protected void initEventAndData() {
//        uuId = getArguments().getString("uuId");
//        keyId = getArguments().getString("keyId");

        ModuleListBeanDao moduleListBeanDao = MyApplication.getInstances().getModuleDaoSession().getModuleListBeanDao();
        List<ModuleListBean> moduleListBeans = moduleListBeanDao.queryBuilder()
                .where(ModuleListBeanDao.Properties.Uuid.eq(uuId))
                .where(ModuleListBeanDao.Properties.KeyId.eq(keyId))
                .list();
        for (int i = 0; i < moduleListBeans.size(); i++) {
            listTitle.add(moduleListBeans.get(i));
            ModelFragment modelFragment = new ModelFragment();
            Bundle bundle = new Bundle();
            bundle.putString("uuId", uuId);
            bundle.putString("keyUuid", listTitle.get(i).getKeyUuid());
            modelFragment.setArguments(bundle);
            list.add(modelFragment);
        }

        tbAdapter = new TbAdapter(getChildFragmentManager(), listTitle, list);
        vp.setAdapter(tbAdapter);
        tb.setTabMode(TabLayout.MODE_FIXED);
        tb.setupWithViewPager(vp);

        ivAdd.setOnClickListener(this);
        ivUp.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_add://添加
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                View inputView = LayoutInflater.from(getActivity()).inflate(R.layout.layout_dialog_report, null);
                EditText et_input = inputView.findViewById(R.id.et_input);
                builder.setTitle("请添加选项卡");
                builder.setMessage("请输入选项卡名称");
                builder.setView(inputView);
                builder.setNegativeButton("取消", null);
                builder.setPositiveButton("确定", (dialogInterface, i) -> {
                    String id = System.currentTimeMillis() + "";
                    ModuleListBeanDao moduleListBeanDao = MyApplication.getInstances().getModuleDaoSession().getModuleListBeanDao();
                    ModuleListBean moduleListBean = new ModuleListBean(null,
                            uuId,
                            id,
                            keyId,
                            id + "12",
                            et_input.getText().toString().trim(),
                            (String) SPUtils.get(getActivity(), "userName", "")
                    );
                    moduleListBeanDao.insert(moduleListBean);
                    listTitle.add(moduleListBean);
                    ModelFragment modelFragment = new ModelFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("uuId", uuId);
                    bundle.putString("keyUuid", moduleListBean.getKeyUuid());
                    modelFragment.setArguments(bundle);
                    list.add(modelFragment);
                    tbAdapter.notifyDataSetChanged();
                    dialogInterface.dismiss();
                });
                builder.show();

                break;
            case R.id.iv_up://修改/删除
                modificationPopup();
                break;
        }
    }

    /**
     * 修改/删除
     */
    private void modificationPopup() {
        View modificationView = getLayoutInflater().inflate(R.layout.modification_view, null);
        PopupWindow modificationWindow = new PopupWindow(modificationView);
        modificationWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        modificationWindow.setWidth(700);
        modificationWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        modificationWindow.setOutsideTouchable(true);
        modificationWindow.setFocusable(true);
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.7f;
        getActivity().getWindow().setAttributes(lp);
        modificationWindow.showAtLocation(ivUp, Gravity.CENTER, 0, 0);
        modificationWindow.setOnDismissListener(() -> {
            WindowManager.LayoutParams lp1 = getActivity().getWindow().getAttributes();
            lp1.alpha = 1f;
            getActivity().getWindow().setAttributes(lp1);
        });

        ListView lv_list = modificationView.findViewById(R.id.lv_list);
        TextView tv_cancel = modificationView.findViewById(R.id.tv_cancel);
        TextView tv_save = modificationView.findViewById(R.id.tv_save);

        ModificationAdapter modificationAdapter = new ModificationAdapter(listTitle, getActivity());
        lv_list.setAdapter(modificationAdapter);
        //取消
        tv_cancel.setOnClickListener(view -> modificationWindow.dismiss());
        //确定
        tv_save.setOnClickListener(view -> {
            ModuleListBeanDao moduleListBeanDao = MyApplication.getInstances().getModuleDaoSession().getModuleListBeanDao();
            listTitle = modificationAdapter.getList();

            for (int i = 0; i < listTitle.size(); i++) {
                ModuleListBean moduleListBean = new ModuleListBean(listTitle.get(i).getUId(),
                        listTitle.get(i).getUuid(),
                        listTitle.get(i).getId(),
                        listTitle.get(i).getKeyId(),
                        listTitle.get(i).getKeyUuid(),
                        listTitle.get(i).getName(),
                        listTitle.get(i).getUserName());
                moduleListBeanDao.update(moduleListBean);
            }

            tbAdapter.notifyDataSetChanged();
            modificationWindow.dismiss();
        });

        modificationAdapter.setModDel(position -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("是否将此选项卡删除，删除后不可恢复");
            builder.setNegativeButton("取消", null);
            builder.setPositiveButton("确定", (dialogInterface, posss) -> {

                ModuleListBeanDao moduleListBeanDao = MyApplication.getInstances().getModuleDaoSession().getModuleListBeanDao();
                moduleListBeanDao.deleteByKey(listTitle.get(position).getUId());
                UnitListBeanDao unitListBeanDao = MyApplication.getInstances().getUnitDaoSession().getUnitListBeanDao();
                List<UnitListBean> unitListBeanList = unitListBeanDao.queryBuilder()
                        .where(UnitListBeanDao.Properties.Uuid.eq(uuId))
                        .where(UnitListBeanDao.Properties.KeyUuid.eq(listTitle.get(position).getKeyUuid()))
                        .list();
                for (int i = 0; i < unitListBeanList.size(); i++) {
                    if (!StringUtils.isBlank(unitListBeanList.get(i).getRelevantFile())) {
                        String[] relevantArray = unitListBeanList.get(i).getRelevantFile().split(",");
                        for (int j = 0; j < relevantArray.length; j++) {
                            String[] relevantArray2 = relevantArray[j].split("@%%%@");
                            try {
                                FileUtils.delFile(relevantArray2[1]);
                            } catch (Exception ex) {

                            }
                        }
                    }
                    if (!StringUtils.isBlank(unitListBeanList.get(i).getContentFile())) {
                        String[] contentFileArray = unitListBeanList.get(i).getContentFile().split("%%&@");
                        if (contentFileArray != null && !StringUtils.isBlank(contentFileArray[position])) {
                            String[] contentFileArray2 = contentFileArray[position].split(",");
                            for (int j = 0; j < contentFileArray2.length; j++) {
                                String[] contentFileArray3 = contentFileArray2[j].split("@%%%@");
                                try {
                                    FileUtils.delFile(contentFileArray3[1]);
                                } catch (Exception ex) {

                                }

                            }
                        }

                    }

                    unitListBeanDao.deleteByKey(unitListBeanList.get(i).getUId());
                }

                list.remove(position);
                listTitle.remove(position);
                tbAdapter.notifyDataSetChanged();
                modificationWindow.dismiss();
                dialogInterface.dismiss();
            });
            builder.show();

        });

    }
}
