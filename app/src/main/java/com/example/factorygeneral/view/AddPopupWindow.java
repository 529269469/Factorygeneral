//package com.example.factorygeneral.view;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.graphics.Color;
//import android.graphics.drawable.ColorDrawable;
//import android.view.Gravity;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.WindowManager;
//import android.widget.AdapterView;
//import android.widget.EditText;
//import android.widget.PopupWindow;
//import android.widget.Switch;
//import android.widget.TextView;
//
//import com.example.acceptance.R;
//import com.example.acceptance.adapter.File2Adapter;
//import com.example.acceptance.base.MyApplication;
//import com.example.acceptance.greendao.bean.DataPackageDBean;
//import com.example.acceptance.greendao.bean.DeliveryListBean;
//import com.example.acceptance.greendao.bean.DocumentBean;
//import com.example.acceptance.greendao.bean.FileBean;
//import com.example.acceptance.greendao.db.DataPackageDBeanDao;
//import com.example.acceptance.greendao.db.DeliveryListBeanDao;
//import com.example.acceptance.greendao.db.DocumentBeanDao;
//import com.example.acceptance.greendao.db.FileBeanDao;
//import com.example.acceptance.utils.FileUtils;
//import com.example.acceptance.utils.OpenFileUtil;
//import com.example.acceptance.utils.ToastUtils;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author :created by ${ WYW }
// * 时间：2019/10/24 15
// */
//public class AddPopupWindow extends PopupWindow {
//
//
//    private  View view;
//    private  PopupWindow popupWindow;
//    private String id;
//    private Activity context;
//    private List<DeliveryListBean> list = new ArrayList<>();
//    private File2Adapter fileAdapter;
//
//
//    public AddPopupWindow(Activity context,View tvAdd,String id) {
//        super(context);
//        this.id=id;
//        this.context=context;
//        view = context.getLayoutInflater().inflate(R.layout.popup_add3, null);
//        popupWindow = new PopupWindow(view);
//        popupWindow.setHeight(600);
//        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
//        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
//        popupWindow.setOutsideTouchable(true);
//        popupWindow.setFocusable(true);
//        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
//        lp.alpha = 0.7f;
//        context.getWindow().setAttributes(lp);
//        popupWindow.showAtLocation(tvAdd, Gravity.CENTER, 0, 0);
//        popupWindow.setOnDismissListener(() -> {
//            WindowManager.LayoutParams lp1 = context.getWindow().getAttributes();
//            lp1.alpha = 1f;
//            context.getWindow().setAttributes(lp1);
//        });
//
//
//    }
//
//    private List<FileBean> fileBeans = new ArrayList<>();
//    private String parentId;
//    private void addPopup2(boolean isAdd, int position) {
//        EditText tv_code = view.findViewById(R.id.tv_code);
//        EditText tv_name = view.findViewById(R.id.tv_name);
//        EditText tv_payClassify = view.findViewById(R.id.tv_payClassify);
//        EditText tv_secret = view.findViewById(R.id.tv_secret);
//        EditText tv_techStatus = view.findViewById(R.id.tv_techStatus);
//        EditText tv_approver = view.findViewById(R.id.tv_approver);
//        EditText tv_approvalDate = view.findViewById(R.id.tv_approvalDate);
//        Switch tv_issl = view.findViewById(R.id.tv_issl);
//        EditText tv_conclusion = view.findViewById(R.id.tv_conclusion);
//        TextView tv_file = view.findViewById(R.id.tv_file);
//        EditText tv_description = view.findViewById(R.id.tv_description);
//        TextView tv_popup_save = view.findViewById(R.id.tv_popup_save);
//        MyListView lv_file = view.findViewById(R.id.lv_file);
//
//        DocumentBeanDao documentBeanDao = MyApplication.getInstances().getDocumentDaoSession().getDocumentBeanDao();
//        List<DocumentBean> documentBeans = documentBeanDao.queryBuilder()
//                .where(DocumentBeanDao.Properties.DataPackageId.eq(id))
//                .where(DocumentBeanDao.Properties.PayClassify.eq(!list.isEmpty()?list.get(position).getId():"00000"))
//                .list();
//        tv_payClassify.setText(!list.isEmpty()?list.get(position).getProject():"");
//        fileBeans.clear();
//        if (isAdd && documentBeans != null && !documentBeans.isEmpty()) {
//            tv_payClassify.setText(list.get(position).getProject());
//            tv_code.setText(documentBeans.get(0).getCode());
//            tv_name.setText(documentBeans.get(0).getName());
//            tv_secret.setText(documentBeans.get(0).getSecret());
//            tv_techStatus.setText(documentBeans.get(0).getTechStatus());
//            tv_approver.setText(documentBeans.get(0).getApprover());
//            tv_approvalDate.setText(documentBeans.get(0).getApprovalDate());
//            if (documentBeans.get(0).getIssl().equals("true")) {
//                tv_issl.setChecked(true);
//            } else {
//                tv_issl.setChecked(false);
//            }
//            tv_conclusion.setText(documentBeans.get(0).getConclusion());
//            tv_description.setText(documentBeans.get(0).getDescription());
//
//            FileBeanDao fileBeanDao = MyApplication.getInstances().getFileDaoSession().getFileBeanDao();
//            List<FileBean> fileBeanList = fileBeanDao.queryBuilder()
//                    .where(FileBeanDao.Properties.DataPackageId.eq(id))
//                    .where(FileBeanDao.Properties.DocumentId.eq(documentBeans.get(0).getId()))
//                    .list();
//
//            fileBeans.addAll(fileBeanList);
//        }
//        fileAdapter = new File2Adapter(context, fileBeans);
//        lv_file.setAdapter(fileAdapter);
//        tv_popup_save.setVisibility(View.GONE);
//
//        lv_file.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                DataPackageDBeanDao dataPackageDBeanDao = MyApplication.getInstances().getDataPackageDaoSession().getDataPackageDBeanDao();
//                List<DataPackageDBean> dataPackageDBeans = dataPackageDBeanDao.queryBuilder()
//                        .where(DataPackageDBeanDao.Properties.Id.eq(id))
//                        .list();
//                File file = new File(dataPackageDBeans.get(0).getUpLoadFile() + "/" + fileBeans.get(i).getPath());
//                if (file.isFile() && file.exists()) {
//                    try {
//                        context.startActivity(OpenFileUtil.openFile(dataPackageDBeans.get(0).getUpLoadFile() + "/" + fileBeans.get(i).getPath()));
//                    } catch (Exception o) {
//                    }
//                } else {
//                    try {
//                        context.startActivity(OpenFileUtil.openFile(fileBeans.get(i).getPath()));
//                    } catch (Exception o) {
//                    }
//                }
//
//            }
//        });
//        tv_file.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//                intent.setType("*/*");//设置类型，我这里是任意类型，任意后缀的可以这样写。
//                intent.addCategory(Intent.CATEGORY_OPENABLE);
//
//                context.startActivityForResult(intent, 1);
//            }
//        });
//
//        tv_popup_save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (fileBeans.isEmpty()){
//                    ToastUtils.getInstance().showTextToast(context,"请添加文件");
//                    return;
//                }
//                DeliveryListBeanDao deliveryListBeanDao = MyApplication.getInstances().getDeliveryListDaoSession().getDeliveryListBeanDao();
//                String deliveryListParentId = System.currentTimeMillis() + "";
//                if (isAdd) {
//                    DeliveryListBean deliveryListBean = new DeliveryListBean(list.get(position).getUId(),
//                            id,
//                            list.get(position).getId(),
//                            false + "",
//                            tv_payClassify.getText().toString(),
//                            list.get(position).getParentId());
//                    deliveryListBeanDao.update(deliveryListBean);
//                } else {
//                    DeliveryListBean deliveryListBean = new DeliveryListBean(null,
//                            id,
//                            deliveryListParentId,
//                            false + "",
//                            tv_payClassify.getText().toString(),
//                            parentId);
//                    deliveryListBeanDao.insert(deliveryListBean);
//                }
//
//
//                DocumentBeanDao documentBeanDao = MyApplication.getInstances().getDocumentDaoSession().getDocumentBeanDao();
//                List<DocumentBean> documentBeans = documentBeanDao.queryBuilder()
//                        .where(DocumentBeanDao.Properties.DataPackageId.eq(list.get(position).getDataPackageId()))
//                        .where(DocumentBeanDao.Properties.PayClassify.eq(list.get(position).getId()))
//                        .list();
//                String documentId = System.currentTimeMillis() + "";
//                if (isAdd) {
//                    DocumentBean documentBean = new DocumentBean(documentBeans.get(0).getUId(),
//                            id,
//                            documentBeans.get(0).getId(),
//                            tv_code.getText().toString().trim(),
//                            tv_name.getText().toString().trim(),
//                            tv_secret.getText().toString().trim(),
//                            documentBeans.get(0).getPayClassify(),
//                            documentBeans.get(0).getPayClassifyName(),
//                            "",
//                            "",
//                            "",
//                            "",
//                            tv_techStatus.getText().toString().trim(),
//                            tv_approver.getText().toString().trim(),
//                            tv_approvalDate.getText().toString().trim(),
//                            tv_issl.isChecked() + "",
//                            tv_conclusion.getText().toString().trim(),
//                            tv_description.getText().toString().trim());
//                    documentBeanDao.update(documentBean);
//                } else {
//                    DocumentBean documentBean = new DocumentBean(null,
//                            id,
//                            documentId,
//                            tv_code.getText().toString().trim(),
//                            tv_name.getText().toString().trim(),
//                            tv_secret.getText().toString().trim(),
//                            deliveryListParentId,
//                            "",
//                            "",
//                            "",
//                            "",
//                            "",
//                            tv_techStatus.getText().toString().trim(),
//                            tv_approver.getText().toString().trim(),
//                            tv_approvalDate.getText().toString().trim(),
//                            tv_issl.isChecked() + "",
//                            tv_conclusion.getText().toString().trim(),
//                            tv_description.getText().toString().trim());
//                    documentBeanDao.insert(documentBean);
//                }
//
//
//                FileBeanDao fileBeanDao = MyApplication.getInstances().getFileDaoSession().getFileBeanDao();
//                List<FileBean> fileBeanList = fileBeanDao.queryBuilder()
//                        .where(FileBeanDao.Properties.DataPackageId.eq(id))
//                        .where(FileBeanDao.Properties.DocumentId.eq(documentBeans.get(0).getId()))
//                        .list();
//
//                for (int i = 0; i < fileBeans.size(); i++) {
//                    boolean isFile = false;
//                    for (int j = 0; j < fileBeanList.size(); j++) {
//                        if (fileBeans.get(i).getName().equals(fileBeanList.get(j).getName())) {
//                            isFile = true;
//                        }
//                    }
//                    if (!isFile) {
//                        FileBean fileBean = new FileBean(null,
//                                id,
//                                documentBeans.get(0).getId(),
//                                fileBeans.get(i).getName(),
//                                fileBeans.get(i).getName(),
//                                fileBeans.get(i).getType(),
//                                fileBeans.get(i).getSecret());
//                        fileBeanDao.insert(fileBean);
//                        DataPackageDBeanDao dataPackageDBeanDao = MyApplication.getInstances().getDataPackageDaoSession().getDataPackageDBeanDao();
//                        List<DataPackageDBean> dataPackageDBeans = dataPackageDBeanDao.queryBuilder()
//                                .where(DataPackageDBeanDao.Properties.Id.eq(id))
//                                .list();
//                        FileUtils.copyFile(fileBeans.get(i).getPath(), dataPackageDBeans.get(0).getUpLoadFile() + "/" + fileBeans.get(i).getName());
//                    }
//
//                }
//                DeliveryListBeanDao deliveryListBeanDao2 = MyApplication.getInstances().getDeliveryListDaoSession().getDeliveryListBeanDao();
//                List<DeliveryListBean> deliveryListBeans = deliveryListBeanDao2.queryBuilder()
//                        .where(DeliveryListBeanDao.Properties.DataPackageId.eq(id))
//                        .where(DeliveryListBeanDao.Properties.ParentId.notEq("null"))
//                        .whereOr(DeliveryListBeanDao.Properties.Project.eq("合同"),
//                                DeliveryListBeanDao.Properties.Project.eq("明细表"),
//                                DeliveryListBeanDao.Properties.Project.eq("任务书"))
//                        .list();
//                list.clear();
//                list.addAll(deliveryListBeans);
//                popupWindow.dismiss();
//                ToastUtils.getInstance().showTextToast(context, "保存成功");
//            }
//        });
//
//    }
//
//
//
//
//}
