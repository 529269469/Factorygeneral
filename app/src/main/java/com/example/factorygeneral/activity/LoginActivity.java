package com.example.factorygeneral.activity;

import android.Manifest;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.factorygeneral.R;
import com.example.factorygeneral.base.BaseActivity;
import com.example.factorygeneral.bean.FindFilesBean;
import com.example.factorygeneral.net.HttpUtils;
import com.example.factorygeneral.net.MyCallBack;
import com.example.factorygeneral.net.URLS;
import com.example.factorygeneral.utils.StringUtils;
import com.example.factorygeneral.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissionItem;
import okhttp3.Call;
import okhttp3.Response;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.iv_setup)
    ImageView ivSetup;

    @BindView(R.id.ll_new)
    LinearLayout llNew;
    @BindView(R.id.ll_file)
    LinearLayout llFile;
    @BindView(R.id.ll_checklist)
    LinearLayout llChecklist;

    @Override
    protected void initView() {
        ivSetup.setOnClickListener(this);
        llFile.setOnClickListener(this);
        llChecklist.setOnClickListener(this);
        llNew.setOnClickListener(this);

        List<PermissionItem> permissionItems1 = new ArrayList<PermissionItem>();
        permissionItems1.add(new PermissionItem(Manifest.permission.WRITE_EXTERNAL_STORAGE, "存储", R.drawable.permission_ic_storage));
        permissionItems1.add(new PermissionItem(Manifest.permission.CAMERA, "照相机", R.drawable.permission_ic_camera));
        HiPermission.create(this)
                .permissions(permissionItems1)
                .checkMutiPermission(new PermissionCallback() {
                    @Override
                    public void onClose() {
                    }

                    @Override
                    public void onFinish() {

                    }

                    @Override
                    public void onDeny(String permisson, int position) {
                        finish();
                    }

                    @Override
                    public void onGuarantee(String permisson, int position) {
                    }
                });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initDataAndEvent() {

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_setup:
                startActivity(SettingActivity.openIntent(this));
                break;
            case R.id.ll_checklist:
                startActivity(ChecklistActivity.openIntent(this));
                break;
            case R.id.ll_file:
                startActivity(ToActivity.openIntent(this));
                break;
            case R.id.ll_new:
                newPopup();
                break;

        }
    }

    private void newPopup() {
        View view = LayoutInflater.from(this).inflate(R.layout.new_name, null);
        PopupWindow popupWindow = new PopupWindow(view);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.7f;
        getWindow().setAttributes(lp);
        popupWindow.showAtLocation(llNew, Gravity.CENTER, 0, 0);
        popupWindow.setOnDismissListener(() -> {
            WindowManager.LayoutParams lp1 = getWindow().getAttributes();
            lp1.alpha = 1f;
            getWindow().setAttributes(lp1);
        });


        EditText tv_name = view.findViewById(R.id.tv_name);
        TextView tv_del = view.findViewById(R.id.tv_del);
        TextView tv_save = view.findViewById(R.id.tv_save);

        tv_del.setOnClickListener(view1 -> {
            popupWindow.dismiss();
        });

        tv_save.setOnClickListener(view12 -> {
            String name=tv_name.getText().toString().trim();
//            String name="ruohan";
            if (StringUtils.isBlank(name)){
                ToastUtils.getInstance().showTextToast(this,"请输入用户名");
                return;
            }
            Map<String,String> map=new HashMap<>();
            map.put("userName",name);
            HttpUtils.getInstance().post(URLS.USERNAME, map, new MyCallBack<FindFilesBean>() {
                @Override
                public void onSuccess(FindFilesBean findFilesBean) {
                    if (findFilesBean.getData()!=null&&!findFilesBean.getData().isEmpty()){
                        startActivity(NewActivity.openIntent(LoginActivity.this,name));
                        popupWindow.dismiss();
                    }else {
                        if (!StringUtils.isBlank(findFilesBean.getMessage())){
                            ToastUtils.getInstance().showTextToast(LoginActivity.this,findFilesBean.getMessage());
                        }else {
                            startActivity(NewActivity.openIntent(LoginActivity.this,name));
                            popupWindow.dismiss();
                        }
                    }

                }

                @Override
                public void onFaile(String msg) {

                }
            });


        });



    }


}
