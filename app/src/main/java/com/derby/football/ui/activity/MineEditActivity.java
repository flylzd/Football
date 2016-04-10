package com.derby.football.ui.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.derby.football.R;
import com.derby.football.api.ApiClient;
import com.derby.football.base.BaseActivity;
import com.derby.football.bean.UserBean;
import com.derby.football.config.AppConfig;
import com.derby.football.config.EventBusCode;
import com.derby.football.eventbus.EventCenter;
import com.derby.football.utils.ToastUtil;
import com.derby.football.utils.UIHelper;
import com.derby.football.widget.ActionSheetDialog;
import com.derby.football.widget.ActionSheetDialog.OnSheetItemClickListener;
import com.derby.football.widget.ActionSheetDialog.SheetItemColor;
import com.derby.football.widget.LoadingDialog;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;


public class MineEditActivity extends BaseActivity {

    @Bind(R.id.imAvatars)
    CircleImageView imAvatars;
    @Bind(R.id.etName)
    EditText etName;
    @Bind(R.id.etNickName)
    EditText etNickName;
    @Bind(R.id.tvDateOfBirth)
    TextView tvDateOfBirth;
    @Bind(R.id.tvSex)
    TextView tvSex;
    @Bind(R.id.tvArea)
    TextView tvArea;
    @Bind(R.id.btnOk)
    Button btnOk;

    /**
     * 头像名称
     */
    private static final String IMAGE_FILE_NAME = "avatar.png";

    /**
     * 请求码
     */
    private static final int REQUEST_CODE_TAKE = 0;
    private static final int REQUEST_CODE_PICK = 1;
    private static final int REQUEST_CODE_CUTTING = 2;

    private static final int REQUEST_CODE_AREA = 3;


    private LoadingDialog loadingDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_edit;
    }

    @Override
    protected int getToolbarTitle() {
        return R.string.mine_edit_title;
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        super.initViewsAndEvents(savedInstanceState);


        imAvatars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAvatar();
            }
        });

    }


    @OnClick({R.id.tvDateOfBirth, R.id.tvSex, R.id.tvArea, R.id.btnOk})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvDateOfBirth:
                showDateOfBirth();
                break;
            case R.id.tvSex:
                showSex();
                break;
            case R.id.tvArea:
                break;
            case R.id.btnOk:
                saveUser();
                break;
        }
    }

    private void saveUser() {

        loadingDialog = new LoadingDialog(this);
        String name = etName.getText().toString();
        String nickName = etNickName.getText().toString();
        String birthDate = tvDateOfBirth.getText().toString();
        String sex = tvSex.getText().toString();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(nickName) || TextUtils.isEmpty(birthDate) || TextUtils.isEmpty(sex)) {
            ToastUtil.showShortCenter("用户信息不能为空!");
            return;
        }

        UserBean.UserData userData = new UserBean.UserData();
        userData.name = name;
        userData.nickName = nickName;
        userData.birthday = birthDate.replace("-","");
        if (sex.equals(getString(R.string.common_male))){
            userData.sex = "M";
        } else {
            userData.sex = "F";
        }
        userData.areaID = "0";

        ApiClient.saveUser(this, TAG, userData);

    }

    private void showAvatar() {
        new ActionSheetDialog(MineEditActivity.this)
                .builder()
                .setCancelable(true)
                .setCanceledOnTouchOutside(true)
                .addSheetItem("用相机更换头像", SheetItemColor.Blue,
                        new OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                Intent takeIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                //下面这句指定调用相机拍照后的照片存储的路径
                                takeIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                                        Uri.fromFile(new File(Environment.getExternalStorageDirectory(), IMAGE_FILE_NAME)));
                                startActivityForResult(takeIntent, REQUEST_CODE_TAKE);
                            }
                        })
                .addSheetItem("去相册选择头像", SheetItemColor.Blue,
                        new OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {

                                Intent pickIntent = new Intent(Intent.ACTION_PICK, null);
                                // 如果朋友们要限制上传到服务器的图片类型时可以直接写如："image/jpeg 、 image/png等的类型"
                                pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                                startActivityForResult(pickIntent, REQUEST_CODE_PICK);
                            }
                        }).show();
    }

    private void showDateOfBirth() {
        Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String date = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                tvDateOfBirth.setText(date);
            }
        }, calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void showSex() {
        new ActionSheetDialog(MineEditActivity.this)
                .builder()
                .setCancelable(true)
                .setCanceledOnTouchOutside(true)
                .addSheetItem(getString(R.string.common_male), SheetItemColor.Blue,
                        new OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                tvSex.setText(R.string.common_male);
                            }
                        })
                .addSheetItem(getString(R.string.common_female), SheetItemColor.Blue,
                        new OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                tvSex.setText(R.string.common_female);
                            }
                        }).show();
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, REQUEST_CODE_CUTTING);
    }

    /**
     * 保存裁剪之后的图片数据
     *
     * @param picdata
     */
    private void setPicToView(Intent picdata) {
        Bundle extras = picdata.getExtras();
        if (extras != null) {
            // 取得SDCard图片路径做显示
            Bitmap photo = extras.getParcelable("data");
            Drawable drawable = new BitmapDrawable(null, photo);
            imAvatars.setImageDrawable(drawable);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case REQUEST_CODE_PICK:// 直接从相册获取
                try {
                    startPhotoZoom(data.getData());
                } catch (NullPointerException e) {
                    e.printStackTrace();// 用户点击取消操作
                }
                break;
            case REQUEST_CODE_TAKE:// 调用相机拍照
                File temp = new File(Environment.getExternalStorageDirectory() + "/" + IMAGE_FILE_NAME);
                startPhotoZoom(Uri.fromFile(temp));
                break;
            case REQUEST_CODE_CUTTING:// 取得裁剪后的图片
                if (data != null) {
                    setPicToView(data);
                }
                break;

            case REQUEST_CODE_AREA:
                if (data != null) {
//                    setPicToView(data);
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onEventBusHandler(EventCenter eventCenter) {

        loadingDialog.dismiss();
        switch (eventCenter.getEventCode()) {
            case EventBusCode.SUCCESS_MINE_EDIT:
                this.finish();
                AppConfig.STATUS = 2; //用户信息补充完整
//                UIHelper.showMainActivity(this);
//                Toast.makeText(MineEditActivity.this,"dddd",Toast.LENGTH_SHORT).show();
                break;
        }
    }

}

