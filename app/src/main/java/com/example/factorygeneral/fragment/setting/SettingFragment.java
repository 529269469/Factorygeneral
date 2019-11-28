package com.example.factorygeneral.fragment.setting;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.factorygeneral.R;
import com.example.factorygeneral.adapter.PacketAdapter;
import com.example.factorygeneral.base.BaseFragment;
import com.example.factorygeneral.base.MyApplication;
import com.example.factorygeneral.greendao.bean.MenusListBean;
import com.example.factorygeneral.greendao.bean.ModuleListBean;
import com.example.factorygeneral.greendao.bean.UnitListBean;
import com.example.factorygeneral.greendao.db.MenusListBeanDao;
import com.example.factorygeneral.greendao.db.ModuleListBeanDao;
import com.example.factorygeneral.greendao.db.UnitListBeanDao;
import com.example.factorygeneral.utils.FileUtils;
import com.example.factorygeneral.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class SettingFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.et_type)
    EditText etType;
    @BindView(R.id.tv_add)
    TextView tvAdd;
    @BindView(R.id.lv_packet)
    ListView lvPacket;
    @BindView(R.id.tv_type2)
    TextView tvType2;
    private PacketAdapter packetAdapter;
    private List<MenusListBean> list = new ArrayList<>();
    private String uuId;

    @Override
    protected void initEventAndData() {
        uuId= getArguments().getString("uuId");
        MenusListBeanDao menusListBeanDao= MyApplication.getInstances().getMenusDaoSession().getMenusListBeanDao();
        List<MenusListBean> modifyBeans=menusListBeanDao.queryBuilder()
                .where(MenusListBeanDao.Properties.Uuid.eq(uuId))
                .list();

        list.addAll(modifyBeans);
        packetAdapter = new PacketAdapter(getActivity(), list);
        lvPacket.setAdapter(packetAdapter);

        lvPacket.setOnItemClickListener((adapterView, view, position, l) -> {
            //修改
            modifyPopupWindow(position);
        });

        lvPacket.setOnItemLongClickListener((adapterView, view, position, l) -> {
            //删除
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("是否确定删除");
            builder.setPositiveButton("删除！！", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    MenusListBeanDao menusListBeanDao= MyApplication.getInstances().getMenusDaoSession().getMenusListBeanDao();

                    ModuleListBeanDao moduleListBeanDao=MyApplication.getInstances().getModuleDaoSession().getModuleListBeanDao();
                    List<ModuleListBean> moduleListBeans=moduleListBeanDao.queryBuilder()
                            .where(ModuleListBeanDao.Properties.Uuid.eq(uuId))
                            .where(ModuleListBeanDao.Properties.KeyId.eq(list.get(position).getKeyId()))
                            .list();
                    for (int p = 0; p <moduleListBeans.size() ; p++) {
                        UnitListBeanDao unitListBeanDao = MyApplication.getInstances().getUnitDaoSession().getUnitListBeanDao();
                        List<UnitListBean> unitListBeanList = unitListBeanDao.queryBuilder()
                                .where(UnitListBeanDao.Properties.Uuid.eq(uuId))
                                .where(UnitListBeanDao.Properties.KeyUuid.eq(moduleListBeans.get(p).getKeyUuid()))
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
                                for (int k = 0; k < contentFileArray.length; k++) {
                                    if (contentFileArray != null && !StringUtils.isBlank(contentFileArray[k])) {
                                        String[] contentFileArray2 = contentFileArray[k].split(",");
                                        for (int j = 0; j < contentFileArray2.length; j++) {
                                            String[] contentFileArray3 = contentFileArray2[j].split("@%%%@");
                                            try {
                                                FileUtils.delFile(contentFileArray3[1]);
                                            } catch (Exception ex) {

                                            }

                                        }
                                    }
                                }


                            }

                            unitListBeanDao.deleteByKey(unitListBeanList.get(i).getUId());
                        }




                    }

                    menusListBeanDao.deleteByKey(list.get(position).getUId());
                    list.remove(position);
                    packetAdapter.notifyDataSetChanged();
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            builder.show();
            return true;
        });
        tvAdd.setOnClickListener(this);

    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_setting;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_add:
                MenusListBeanDao menusListBeanDao= MyApplication.getInstances().getMenusDaoSession().getMenusListBeanDao();
                MenusListBean modifyBean=new MenusListBean(null,
                        false,
                        System.currentTimeMillis()+"",
                        System.currentTimeMillis()+"123",
                        etType.getText().toString().trim(),
                        list.get(0).getUserName(),
                        list.get(0).getUuid());
                menusListBeanDao.insert(modifyBean);
                list.add(modifyBean);
                etType.setText("");
                packetAdapter.notifyDataSetChanged();
                break;
        }
    }

    private void modifyPopupWindow(int position) {
        View poview = getLayoutInflater().inflate(R.layout.modify_view, null);
        PopupWindow popupWindow = new PopupWindow(poview);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.7f;
        getActivity().getWindow().setAttributes(lp);
        popupWindow.showAtLocation(lvPacket, Gravity.CENTER, 0, 0);

        popupWindow.setOnDismissListener(() -> {
            WindowManager.LayoutParams lp1 = getActivity().getWindow().getAttributes();
            lp1.alpha = 1f;
            getActivity().getWindow().setAttributes(lp1);
        });

        EditText et_modify = poview.findViewById(R.id.et_modify);
        TextView tv_modify_save = poview.findViewById(R.id.tv_modify_save);

        et_modify.setText(list.get(position).getName());

        tv_modify_save.setOnClickListener(view -> {
            popupWindow.dismiss();
            MenusListBeanDao menusListBeanDao= MyApplication.getInstances().getMenusDaoSession().getMenusListBeanDao();
            List<MenusListBean> modifyBeanList=menusListBeanDao.queryBuilder()
                    .where(MenusListBeanDao.Properties.Name.eq(list.get(position).getName()))
                    .list();
            MenusListBean modifyBean=new MenusListBean(modifyBeanList.get(0).getUId(),false,
                    modifyBeanList.get(0).getId(),
                    modifyBeanList.get(0).getKeyId(),
                    et_modify.getText().toString().trim(),
                    modifyBeanList.get(0).getUserName(),
                    modifyBeanList.get(0).getUuid());
            menusListBeanDao.update(modifyBean);


            MenusListBeanDao menusListBeanDao1= MyApplication.getInstances().getMenusDaoSession().getMenusListBeanDao();
            List<MenusListBean> modifyBeanList1=menusListBeanDao1.queryBuilder()
                    .where(MenusListBeanDao.Properties.Uuid.eq(uuId))
                    .list();
            list.clear();
            list.addAll(modifyBeanList1);
            packetAdapter.notifyDataSetChanged();
        });

    }
}
