package com.example.factorygeneral.fragment.main;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.factorygeneral.R;
import com.example.factorygeneral.adapter.GridAdapter;
import com.example.factorygeneral.adapter.RecycleAdapter;
import com.example.factorygeneral.adapter.TablePopupAdapter;
import com.example.factorygeneral.base.BaseFragment;
import com.example.factorygeneral.base.MyApplication;
import com.example.factorygeneral.bean.TextLabelBean;
import com.example.factorygeneral.fragment.view.AutographLayout;
import com.example.factorygeneral.fragment.view.CheckBoxLayout;
import com.example.factorygeneral.fragment.view.InputLayout;
import com.example.factorygeneral.fragment.view.TableLayout;
import com.example.factorygeneral.fragment.view.TextBoxLayout;
import com.example.factorygeneral.greendao.bean.UnitListBean;
import com.example.factorygeneral.greendao.db.UnitListBeanDao;
import com.example.factorygeneral.utils.FileUtils;
import com.example.factorygeneral.utils.SPUtils;
import com.example.factorygeneral.utils.ScreenUtils;
import com.example.factorygeneral.utils.StringUtils;
import com.example.factorygeneral.utils.ToastUtils;
import com.example.factorygeneral.view.MyGridView;
import com.example.factorygeneral.view.MyListView;
import com.example.factorygeneral.view.OperationPopupWindow;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;


public class ModelFragment extends BaseFragment implements TextBoxLayout.ITextBox, InputLayout.IInput, CheckBoxLayout.ICheckBox, TableLayout.ITable {


    @BindView(R.id.recycler_layout)
    LinearLayout recyclerLayout;
    private String uuId;
    private String keyUuid;
    private File cameraSavePath;
    private String[] textArray;
    private String[] contentArray;
    private List<TextLabelBean> gridList;
    private GridAdapter gridAdapter;

    @Override
    protected void initEventAndData() {
        uuId = getArguments().getString("uuId");
        keyUuid = getArguments().getString("keyUuid");

        initData();


    }

    private void initData() {
        UnitListBeanDao unitListBeanDao = MyApplication.getInstances().getUnitDaoSession().getUnitListBeanDao();
        List<UnitListBean> unitListBeans = unitListBeanDao.queryBuilder()
                .where(UnitListBeanDao.Properties.Uuid.eq(uuId))
                .where(UnitListBeanDao.Properties.KeyUuid.eq(keyUuid))
                .list();
        recyclerLayout.removeAllViews();

        List<UnitListBean> unitListBeanList = new ArrayList<>();
        for (int i = 0; i < unitListBeans.size(); i++) {
            for (int j = 0; j < unitListBeans.size() - 1 - i; j++) {
                if (Integer.parseInt(unitListBeans.get(j + 1).getSx()) < Integer.parseInt(unitListBeans.get(j).getSx())) {
                    unitListBeanList.clear();
                    unitListBeanList.add(unitListBeans.get(j));
                    unitListBeans.set(j, unitListBeans.get(j + 1));
                    unitListBeans.set(j + 1, unitListBeanList.get(0));
                }
            }

        }


        for (int i = 0; i < unitListBeans.size(); i++) {
            switch (unitListBeans.get(i).getType()) {
                case "textBox":
                    TextBoxLayout textBoxLayout = new TextBoxLayout(getActivity(), unitListBeans.get(i));
                    textBoxLayout.setTextBox(this);
                    recyclerLayout.addView(textBoxLayout);
                    break;
                case "table":
                    TableLayout tableLayout = new TableLayout(getActivity(), unitListBeans.get(i));
                    tableLayout.setiTable(this);
                    recyclerLayout.addView(tableLayout);
                    break;
                case "input":
                    InputLayout inputLayout = new InputLayout(getActivity(), unitListBeans.get(i));
                    inputLayout.setiInput(this);
                    recyclerLayout.addView(inputLayout);
                    break;
                case "checkBox":
                    CheckBoxLayout checkBoxLayout = new CheckBoxLayout(getActivity(), unitListBeans.get(i));
                    checkBoxLayout.setiCheckBox(this);
                    recyclerLayout.addView(checkBoxLayout);
                    break;
                case "autograph":
                    AutographLayout autographLayout = new AutographLayout(getActivity(), unitListBeans.get(i));
                    recyclerLayout.addView(autographLayout);
                    break;
            }


        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_model;
    }




    private String unitListId;
    private Uri uri;

    @Override
    public void seTextBoxId(String unitListId, View operationView) {
        this.unitListId = unitListId;
        popuView(operationView);
    }

    @Override
    public void setInputId(String unitListId, View operationView) {
        this.unitListId = unitListId;
        popuView(operationView);
    }

    @Override
    public void setCheckBoxId(String unitListId, View operationView) {
        this.unitListId = unitListId;
        popuView(operationView);
    }


    private void popuView(View operationView) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.operation_view, null);
        PopupWindow popupWindow = new PopupWindow(view);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.7f;
        getActivity().getWindow().setAttributes(lp);
        int windowPos[] = calculatePopWindowPos(operationView, view);


        popupWindow.showAtLocation(operationView, Gravity.TOP | Gravity.START, windowPos[0], windowPos[1]);
        popupWindow.setOnDismissListener(() -> {
            WindowManager.LayoutParams lp1 = getActivity().getWindow().getAttributes();
            lp1.alpha = 1f;
            getActivity().getWindow().setAttributes(lp1);
        });

        TextView tv_img = view.findViewById(R.id.tv_img);
        TextView tv_video = view.findViewById(R.id.tv_video);
        TextView tv_file2 = view.findViewById(R.id.tv_file2);


        tv_file2.setOnClickListener(view14 -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("*/*");//设置类型，我这里是任意类型，任意后缀的可以这样写。
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            startActivityForResult(intent, 11);
            popupWindow.dismiss();
        });

        tv_img.setOnClickListener(view13 -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            cameraSavePath = new File(SPUtils.get(getActivity(), "PackagePath", "") + "/" + System.currentTimeMillis() + ".jpg");
            uri = Uri.fromFile(cameraSavePath);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivityForResult(intent, 22);
            popupWindow.dismiss();
        });

        tv_video.setOnClickListener(view1 -> {
            Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            cameraSavePath = new File(SPUtils.get(getActivity(), "PackagePath", "") + "/" + System.currentTimeMillis() + ".mp4");
            uri = Uri.fromFile(cameraSavePath);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivityForResult(intent, 22);
            popupWindow.dismiss();
        });

    }


    @Override
    public void setTable1(int position, boolean isLook, UnitListBean unitListBean) {
        View table_view = getLayoutInflater().inflate(R.layout.table_view, null);
        PopupWindow popupWindow = new PopupWindow(table_view);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.7f;
        getActivity().getWindow().setAttributes(lp);
        popupWindow.showAtLocation(recyclerLayout, Gravity.CENTER, 0, 0);
        popupWindow.setOnDismissListener(() -> {
            WindowManager.LayoutParams lp1 = getActivity().getWindow().getAttributes();
            lp1.alpha = 1f;
            getActivity().getWindow().setAttributes(lp1);
        });

        MyListView lv_table = table_view.findViewById(R.id.lv_table);
        MyGridView gv_table = table_view.findViewById(R.id.gv_table);
        TextView tv_cancel = table_view.findViewById(R.id.tv_cancel);
        TextView tv_save = table_view.findViewById(R.id.tv_save);
        TextView tv_file = table_view.findViewById(R.id.tv_file);
        TextView tv_photo = table_view.findViewById(R.id.tv_photo);
        TextView tv_video = table_view.findViewById(R.id.tv_video);

        tv_file.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("*/*");//设置类型，我这里是任意类型，任意后缀的可以这样写。
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            startActivityForResult(intent, 111);
        });
        tv_photo.setOnClickListener(view -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            cameraSavePath = new File(SPUtils.get(getActivity(), "PackagePath", "") + "/" + System.currentTimeMillis() + ".jpg");
            uri = Uri.fromFile(cameraSavePath);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivityForResult(intent, 222);
        });
        tv_video.setOnClickListener(view -> {
            Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            cameraSavePath = new File(SPUtils.get(getActivity(), "PackagePath", "") + "/" + System.currentTimeMillis() + ".mp4");
            uri = Uri.fromFile(cameraSavePath);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivityForResult(intent, 222);
        });

        List<TextLabelBean> textLabelBeans1 = new ArrayList<>();
        textLabelBeans1.clear();
        gridList = new ArrayList<>();
        gridList.clear();
        textArray=null;
        contentArray=null;
        textArray = unitListBean.getText().split("%%&@");
        contentArray = unitListBean.getContent().split("%%&@");


        if (isLook) {
            String[] strrr = contentArray[position].split("★&&☆");
            for (int i = 0; i < textArray.length; i++) {
                TextLabelBean textLabelBean = new TextLabelBean();
                textLabelBean.setLabel(textArray[i]);
                textLabelBean.setText(strrr[i]);
                textLabelBeans1.add(textLabelBean);
            }
            if (!StringUtils.isBlank(unitListBean.getContentFile())){
                String[] contentFileArray = unitListBean.getContentFile().split("%%&@");
                String[] contentFileArray2 = contentFileArray[position].split(",");
                for (int i = 0; i < contentFileArray2.length; i++) {
                    String[] contentFileArray3 = contentFileArray2[i].split("@%%%@");
                    TextLabelBean textLabelBean = new TextLabelBean();
                    textLabelBean.setLabel(contentFileArray3[0]);
                    textLabelBean.setText(contentFileArray3[1]);
                    gridList.add(textLabelBean);

                }
            }
        } else {
            for (int i = 0; i < textArray.length; i++) {
                TextLabelBean textLabelBean = new TextLabelBean();
                textLabelBean.setLabel(textArray[i]);
                textLabelBeans1.add(textLabelBean);
            }
        }

        TablePopupAdapter tablePopupAdapter = new TablePopupAdapter(getActivity(), textLabelBeans1);
        lv_table.setAdapter(tablePopupAdapter);
        gridAdapter = new GridAdapter(gridList, getActivity());
        gv_table.setAdapter(gridAdapter);

        /**
         * 删除文件，不清理，保存时再删除文件
         * */
        gridAdapter.setOnDel(position1 -> {
            gridList.remove(position1);
            gridAdapter.notifyDataSetChanged();
        });


        tv_cancel.setOnClickListener(view -> {
            popupWindow.dismiss();
        });
        tv_save.setOnClickListener(view -> {
            List<TextLabelBean> textLabelBeans = tablePopupAdapter.getList();

            StringBuffer textBuffer = new StringBuffer();
            for (int j = 0; j < textLabelBeans.size(); j++) {
                textBuffer.append(StringUtils.isBlank(textLabelBeans.get(j).getText()) ? "null" : textLabelBeans.get(j).getText()).append("★&&☆");
            }

            String text = textBuffer.toString().substring(0, textBuffer.toString().length() - 4);
            contentArray[position] = text;

            StringBuffer content = new StringBuffer();
            for (int i = 0; i < contentArray.length; i++) {
                content.append(contentArray[i]).append("%%&@");
            }
            text = content.toString().substring(0, content.toString().length() - 4);


            if (!StringUtils.isBlank(unitListBean.getContentFile())){
                String[] contentFiledel = unitListBean.getContentFile().split("%%&@");
                String[] contentFiledel2 = contentFiledel[position].split(",");

                boolean isDel = false;
                for (int i = 0; i < contentFiledel2.length; i++) {
                    String[] contentFileArray3 = contentFiledel2[i].split("@%%%@");
                    for (int j = 0; j < gridList.size(); j++) {
                        if (gridList.get(j).getText().equals(contentFileArray3[1])) {
                            isDel = true;
                        }
                    }
                    if (!isDel) {
                        FileUtils.delFile((String) SPUtils.get(getActivity(), "PackagePath", "") + File.separator + contentFileArray3[1]);
                    }

                }
            }



            StringBuffer relevantFileBuffer = new StringBuffer();
            for (int i = 0; i < gridList.size(); i++) {
                if (gridList.get(i).getText().indexOf("/storage/emulated")!=-1){
                    String end = gridList.get(i).getText().substring(gridList.get(i).getText().lastIndexOf(".") + 1
                            , gridList.get(i).getText().length()).toLowerCase(Locale.getDefault());
                    String upLoadFileName = System.currentTimeMillis()+"."+end;

                    FileUtils.copyFile(gridList.get(i).getText(),
                            (String) SPUtils.get(getActivity(), "PackagePath", "")+File.separator+upLoadFileName);
                    relevantFileBuffer
                            .append(gridList.get(i).getLabel()).append("@%%%@")
                            .append(upLoadFileName)
                            .append(",");
                }else {
                    relevantFileBuffer
                            .append(gridList.get(i).getLabel()).append("@%%%@")
                            .append(gridList.get(i).getText())
                            .append(",");
                }


            }
            String relevantFile="";
            if (!StringUtils.isBlank(relevantFileBuffer.toString())){
                relevantFile = relevantFileBuffer.toString().substring(0, relevantFileBuffer.toString().length() - 1);
            }

            UnitListBeanDao unitListBeanDao = MyApplication.getInstances().getUnitDaoSession().getUnitListBeanDao();
            UnitListBean unitListBean2 = new UnitListBean(unitListBean.getUId(),
                    unitListBean.getUuid(),
                    unitListBean.getAnswer(),
                    text,
                    relevantFile,
                    unitListBean.getId(),
                    unitListBean.getKeyUuid(),
                    unitListBean.getLabel(),
                    unitListBean.getRelevantFile(),
                    unitListBean.getSx(),
                    unitListBean.getText(),
                    unitListBean.getType(),
                    unitListBean.getUserName());
            unitListBeanDao.update(unitListBean2);

            initData();
            ToastUtils.getInstance().showTextToast(getActivity(), "保存成功");
            popupWindow.dismiss();
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) {
            if (requestCode == 11) {
                Uri uri = data.getData();
                if (uri != null) {
                    String path = FileUtils.getPath(getActivity(), uri);
                    if (path != null) {
                        File file = new File(path);
                        if (file.exists()) {
                            String end = file.getName().substring(file.getName().lastIndexOf(".") + 1, file.getName().length()).toLowerCase(Locale.getDefault());
                            String upLoadFileName = System.currentTimeMillis()+"."+end;
                            FileUtils.copyFile(file.getPath(),
                                    (String) SPUtils.get(getActivity(), "PackagePath", "")+File.separator+upLoadFileName);

                            UnitListBeanDao unitListBeanDao = MyApplication.getInstances().getUnitDaoSession().getUnitListBeanDao();
                            List<UnitListBean> unitListBeanList = unitListBeanDao.queryBuilder()
                                    .where(UnitListBeanDao.Properties.Uuid.eq((String) SPUtils.get(getActivity(), "uuId", "")))
                                    .where(UnitListBeanDao.Properties.Id.eq(unitListId))
                                    .list();
                            String files = "";
                            if (StringUtils.isBlank(unitListBeanList.get(0).getRelevantFile())) {
                                files = file.getName() + "@%%%@" + upLoadFileName;
                            } else {
                                files = unitListBeanList.get(0).getRelevantFile() + "," + file.getName() + "@%%%@" + upLoadFileName;
                            }
                            UnitListBean unitListBean1 = new UnitListBean(unitListBeanList.get(0).getUId(),
                                    unitListBeanList.get(0).getUuid(),
                                    unitListBeanList.get(0).getAnswer(),
                                    unitListBeanList.get(0).getContent(),
                                    unitListBeanList.get(0).getContentFile(),
                                    unitListBeanList.get(0).getId(),
                                    unitListBeanList.get(0).getKeyUuid(),
                                    unitListBeanList.get(0).getLabel(),
                                    files,
                                    unitListBeanList.get(0).getSx(),
                                    unitListBeanList.get(0).getText(),
                                    unitListBeanList.get(0).getType(),
                                    unitListBeanList.get(0).getUserName());
                            unitListBeanDao.update(unitListBean1);


                        }
                    }
                }
                initData();
            } else if (requestCode == 22) {
                String photoPath = String.valueOf(cameraSavePath);
                File file = new File(photoPath);
                Log.e("拍照返回图片路径:", photoPath);

                String upLoadFileName = file.getName();
                UnitListBeanDao unitListBeanDao = MyApplication.getInstances().getUnitDaoSession().getUnitListBeanDao();
                List<UnitListBean> unitListBeanList = unitListBeanDao.queryBuilder()
                        .where(UnitListBeanDao.Properties.Uuid.eq((String) SPUtils.get(getActivity(), "uuId", "")))
                        .where(UnitListBeanDao.Properties.Id.eq(unitListId))
                        .list();
                String files = "";
                if (StringUtils.isBlank(unitListBeanList.get(0).getRelevantFile())) {
                    files = upLoadFileName + "@%%%@" + upLoadFileName;
                } else {
                    files = unitListBeanList.get(0).getRelevantFile() + "," + upLoadFileName + "@%%%@" + upLoadFileName;
                }
                UnitListBean unitListBean1 = new UnitListBean(unitListBeanList.get(0).getUId(),
                        unitListBeanList.get(0).getUuid(),
                        unitListBeanList.get(0).getAnswer(),
                        unitListBeanList.get(0).getContent(),
                        unitListBeanList.get(0).getContentFile(),
                        unitListBeanList.get(0).getId(),
                        unitListBeanList.get(0).getKeyUuid(),
                        unitListBeanList.get(0).getLabel(),
                        files,
                        unitListBeanList.get(0).getSx(),
                        unitListBeanList.get(0).getText(),
                        unitListBeanList.get(0).getType(),
                        unitListBeanList.get(0).getUserName());
                unitListBeanDao.update(unitListBean1);
                initData();
            }else if (requestCode == 111){
                Uri uri = data.getData();
                if (uri != null) {
                    String path = FileUtils.getPath(getActivity(), uri);
                    if (path != null) {
                        File file = new File(path);
                        if (file.exists()) {
                            TextLabelBean textLabelBean=new TextLabelBean();
                            textLabelBean.setText(file.getPath());
                            textLabelBean.setLabel(file.getName());
                            gridList.add(textLabelBean);
                            gridAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }else if (requestCode == 222){
                String photoPath = String.valueOf(cameraSavePath);
                File file = new File(photoPath);
                String upLoadFileName = file.getName();
                TextLabelBean textLabelBean=new TextLabelBean();
                textLabelBean.setText(upLoadFileName);
                textLabelBean.setLabel(upLoadFileName);
                gridList.add(textLabelBean);
                gridAdapter.notifyDataSetChanged();

            }


        }

    }

    /**
     * 计算出来的位置，y方向就在anchorView的上面和下面对齐显示，x方向就是与屏幕右边对齐显示
     * 如果anchorView的位置有变化，就可以适当自己额外加入偏移来修正
     * @param anchorView  呼出window的view
     * @param contentView   window的内容布局
     * @return window显示的左上角的xOff,yOff坐标
     */
    private static int[] calculatePopWindowPos(final View anchorView, final View contentView) {
        final int windowPos[] = new int[2];
        final int anchorLoc[] = new int[2];
        // 获取锚点View在屏幕上的左上角坐标位置
        anchorView.getLocationOnScreen(anchorLoc);
        final int anchorHeight = anchorView.getHeight();
        // 获取屏幕的高宽
        final int screenHeight = ScreenUtils.getScreenHeight(anchorView.getContext());
        final int screenWidth = ScreenUtils.getScreenWidth(anchorView.getContext());
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        // 计算contentView的高宽
        final int windowHeight = contentView.getMeasuredHeight();
        final int windowWidth = contentView.getMeasuredWidth();
        // 判断需要向上弹出还是向下弹出显示
        final boolean isNeedShowUp = (screenHeight - anchorLoc[1] - anchorHeight < windowHeight);
        if (isNeedShowUp) {
            windowPos[0] = screenWidth - windowWidth;
            windowPos[1] = anchorLoc[1] - windowHeight;
        } else {
            windowPos[0] = screenWidth - windowWidth;
            windowPos[1] = anchorLoc[1] + anchorHeight;
        }
        return windowPos;
    }

}
