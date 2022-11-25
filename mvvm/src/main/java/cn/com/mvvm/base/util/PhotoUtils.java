package cn.com.mvvm.base.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.provider.MediaStore;

import androidx.core.content.ContextCompat;

import com.luck.picture.lib.basic.PictureSelector;
import com.luck.picture.lib.config.SelectMimeType;
import com.luck.picture.lib.config.SelectModeConfig;
import com.luck.picture.lib.style.BottomNavBarStyle;
import com.luck.picture.lib.style.PictureSelectorStyle;
import com.luck.picture.lib.style.SelectMainStyle;
import com.luck.picture.lib.style.TitleBarStyle;

import java.io.ByteArrayOutputStream;
import java.io.File;

import cn.com.mvvm.R;
import cn.com.mvvm.base.interfaces.PhotoResultCallbackListener;
import cn.com.mvvm.base.listener.MyOnResultCallbackListener;
import cn.com.mvvm.base.util.ImageEngine.GlideEngine;
import cn.com.mvvm.base.util.ImageEngine.ImageCompressEngine;


public class PhotoUtils {
    public static PictureSelectorStyle pictureSelectorStyle;
    /**
     * 选择相册图片
     * @param context
     * @param call
     */
    public static void seletcPhoto(Context context, int maxSelectNum, PhotoResultCallbackListener call){
        PictureSelector.create(context)
                .openGallery(SelectMimeType.ofImage())
                .setSelectorUIStyle(getPictureSelectorStyle(context))
                .setImageEngine(GlideEngine.createGlideEngine())//图片加载引擎
                .setCompressEngine(new ImageCompressEngine())//图片压缩引擎
                .setSelectionMode(maxSelectNum==1?SelectModeConfig.SINGLE:SelectModeConfig.MULTIPLE)
                .isDisplayCamera(false)//不显示摄像头
                .setMaxSelectNum(maxSelectNum) //图片最大选择数量
                .setMinSelectNum(1) //图片最小选择数量
                .forResult(new MyOnResultCallbackListener(call));
    }

    /**
     * 打开照相机
     * @param context
     * @param call
     */
    public static void openCamera(Context context,  PhotoResultCallbackListener call){
        PictureSelector.create(context)
                .openCamera(SelectMimeType.ofImage())
                .setSelectorUIStyle(getPictureSelectorStyle(context))
                .setImageEngine(GlideEngine.createGlideEngine())//图片加载引擎
                .setCompressEngine(new ImageCompressEngine())//图片压缩引擎
                .isCameraRotateImage(true) //拍照是否纠正旋转图片
                .forResult(new MyOnResultCallbackListener(call));
    }

    /**
     * 选择相册图片
     * @param context
     * @param call
     */
    public static void seletcPhoto(Context context, int maxSelectNum,Class aClass, PhotoResultCallbackListener call){
        PictureSelector.create(context)
                .openGallery(SelectMimeType.ofImage())
                .setSelectorUIStyle(getPictureSelectorStyle(context))
                .setImageEngine(GlideEngine.createGlideEngine())//图片加载引擎
                .setCompressEngine(new ImageCompressEngine())//图片压缩引擎
                .setSelectionMode(maxSelectNum==1?SelectModeConfig.SINGLE:SelectModeConfig.MULTIPLE)
                .isDisplayCamera(false)//不显示摄像头
                .setMaxSelectNum(maxSelectNum) //图片最大选择数量
                .setMinSelectNum(1) //图片最小选择数量
                .forResult(new MyOnResultCallbackListener(call,aClass));
    }

    /**
     * 打开照相机
     * @param context
     * @param call
     */
    public static void openCamera(Context context,Class aClass,  PhotoResultCallbackListener call){
        PictureSelector.create(context)
                .openCamera(SelectMimeType.ofImage())
                .setSelectorUIStyle(getPictureSelectorStyle(context))
                .setImageEngine(GlideEngine.createGlideEngine())//图片加载引擎
                .setCompressEngine(new ImageCompressEngine())//图片压缩引擎
                .isCameraRotateImage(true) //拍照是否纠正旋转图片
                .forResult(new MyOnResultCallbackListener(call,aClass));
    }

    /**
     * 选择视频
     * @param context
     * @param call
     */
    public static void seletcVideo(Context context, int maxSelectNum, PhotoResultCallbackListener call){
        PictureSelector.create(context)
                .openGallery(SelectMimeType.ofVideo())
                .setSelectorUIStyle(getPictureSelectorStyle(context))
                .setImageEngine(GlideEngine.createGlideEngine())//图片加载引擎
                .setCompressEngine(new ImageCompressEngine())//图片压缩引擎
                .setSelectionMode(maxSelectNum==1?SelectModeConfig.SINGLE:SelectModeConfig.MULTIPLE)
                .isDisplayCamera(true)//不显示摄像头
                .setSelectMaxFileSize(512000)
                .setMaxSelectNum(maxSelectNum) //图片最大选择数量
                .setMinSelectNum(1) //图片最小选择数量
                .forResult(new MyOnResultCallbackListener(call));
    }


    /**
     * 选择视频
     * @param context
     * @param call
     */
    public static void seletcVideo(Context context, int maxSelectNum,Class cass, PhotoResultCallbackListener call){
        PictureSelector.create(context)
                .openGallery(SelectMimeType.ofVideo())
                .setSelectorUIStyle(getPictureSelectorStyle(context))
                .setImageEngine(GlideEngine.createGlideEngine())//图片加载引擎
                .setCompressEngine(new ImageCompressEngine())//图片压缩引擎
                .setSelectionMode(maxSelectNum==1?SelectModeConfig.SINGLE:SelectModeConfig.MULTIPLE)
                .setSelectMaxFileSize(512000)
                .isDisplayCamera(true)//不显示摄像头
                .setMaxSelectNum(maxSelectNum) //图片最大选择数量
                .setMinSelectNum(1) //图片最小选择数量
                .forResult(new MyOnResultCallbackListener(call,cass));
    }

    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
//        ByteArrayOutputStream output = new ByteArrayOutputStream();
//        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
//        if (needRecycle) {
//            bmp.recycle();
//        }
//
//        byte[] result = output.toByteArray();
//        try {
//            output.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        // 首先进行一次大范围的压缩
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, output);
        float zoom = (float)Math.sqrt(32 * 1024 / (float)output.toByteArray().length); //获取缩放比例
        // 设置矩阵数据
        Matrix matrix = new Matrix();
        matrix.setScale(zoom, zoom);
        // 根据矩阵数据进行新bitmap的创建
        Bitmap resultBitmap = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
        output.reset();
        resultBitmap.compress(Bitmap.CompressFormat.JPEG, 100, output);
        // 如果进行了上面的压缩后，依旧大于32K，就进行小范围的微调压缩
        while(output.toByteArray().length > 32 * 1024){
            matrix.setScale(0.9f, 0.9f);//每次缩小 1/10
            resultBitmap = Bitmap.createBitmap(
                    resultBitmap, 0, 0,
                    resultBitmap.getWidth(), resultBitmap.getHeight(), matrix,true);
            output.reset();
            resultBitmap.compress(Bitmap.CompressFormat.JPEG, 100, output);
        }
        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 设置菜单选择样式
     * @param context
     * @return
     */
    @SuppressLint("UseCompatLoadingForDrawables")
    private static PictureSelectorStyle getPictureSelectorStyle(Context context){
        if (pictureSelectorStyle==null){
            pictureSelectorStyle = new PictureSelectorStyle();
            TitleBarStyle whiteTitleBarStyle = new TitleBarStyle();
            whiteTitleBarStyle.setTitleBackgroundColor(ContextCompat.getColor(context, R.color.white));
            whiteTitleBarStyle.setTitleTextColor(ContextCompat.getColor(context, R.color.black));
            whiteTitleBarStyle.setTitleCancelTextColor(ContextCompat.getColor(context, com.luck.picture.lib.R.color.ps_color_53575e));
            whiteTitleBarStyle.setTitleLeftBackResource(com.luck.picture.lib.R.drawable.ps_ic_back);

            BottomNavBarStyle whiteBottomNavBarStyle = new BottomNavBarStyle();
            whiteBottomNavBarStyle.setBottomNarBarBackgroundColor(context.getResources().getColor(R.color.white));
            whiteBottomNavBarStyle.setBottomPreviewSelectTextColor(ContextCompat.getColor(context, com.luck.picture.lib.R.color.ps_color_53575e));

            whiteBottomNavBarStyle.setBottomPreviewNormalTextColor(ContextCompat.getColor(context, com.luck.picture.lib.R.color.ps_color_9b));
            whiteBottomNavBarStyle.setBottomPreviewSelectTextColor(ContextCompat.getColor(context, com.luck.picture.lib.R.color.ps_color_fa632d));
            whiteBottomNavBarStyle.setCompleteCountTips(false);
            whiteBottomNavBarStyle.setBottomEditorTextColor(ContextCompat.getColor(context, com.luck.picture.lib.R.color.ps_color_53575e));
            whiteBottomNavBarStyle.setBottomOriginalTextColor(ContextCompat.getColor(context, com.luck.picture.lib.R.color.ps_color_53575e));

            SelectMainStyle selectMainStyle = new SelectMainStyle();
            selectMainStyle.setStatusBarColor(ContextCompat.getColor(context, com.luck.picture.lib.R.color.ps_color_white));
            selectMainStyle.setDarkStatusBarBlack(true);
            selectMainStyle.setSelectNormalTextColor(ContextCompat.getColor(context, com.luck.picture.lib.R.color.ps_color_9b));
            selectMainStyle.setSelectTextColor(ContextCompat.getColor(context, com.luck.picture.lib.R.color.ps_color_fa632d));
            selectMainStyle.setMainListBackgroundColor(ContextCompat.getColor(context, com.luck.picture.lib.R.color.ps_color_white));
            selectMainStyle.setNavigationBarColor(context.getResources().getColor(R.color.white));

            pictureSelectorStyle.setTitleBarStyle(whiteTitleBarStyle);
            pictureSelectorStyle.setBottomBarStyle(whiteBottomNavBarStyle);
            pictureSelectorStyle.setSelectMainStyle(selectMainStyle);
        }
        return pictureSelectorStyle;
    }


    //url转绝对地址
    public static File getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri,  proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return new File(cursor.getString(column_index));
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}
