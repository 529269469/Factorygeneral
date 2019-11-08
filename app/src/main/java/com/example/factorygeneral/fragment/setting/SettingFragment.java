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
import com.example.factorygeneral.greendao.db.MenusListBeanDao;

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

    @Override
    protected void initEventAndData() {

        MenusListBeanDao menusListBeanDao= MyApplication.getInstances().getMenusDaoSession().getMenusListBeanDao();
        List<MenusListBean> modifyBeans=menusListBeanDao.loadAll();

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
                MenusListBean modifyBean=new MenusListBean(null,false,
                        list.get(0).getId(),
                        list.get(0).getKeyId(),
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
            List<MenusListBean> modifyBeanList1=menusListBeanDao1.loadAll();
            list.clear();
            list.addAll(modifyBeanList1);
            packetAdapter.notifyDataSetChanged();
        });

    }
}
