package com.kevin.demo.module.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.os.Bundle;
import android.os.Environment;


import com.kevin.demo.R;
import com.kevin.demo.base.BaseActivity;
import com.kevin.demo.databinding.ActivityBitmapBinding;
import com.kevin.demo.util.BitmapUtils;

import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by tuchuantao on 2021/8/23
 * Desc:
 */
public class BitmapActivity extends BaseActivity<ActivityBitmapBinding> {

    @Override
    public int getLayoutResId() {
        return R.layout.activity_bitmap;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
//        try {
//            Bitmap bitmap = BitmapFactory.decodeStream(getAssets().open("imgs/BannerLayout.png"));
//            log(" 宽度=" + bitmap.getWidth() + "  高度=" + bitmap.getHeight());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        tt(Environment.getExternalStorageDirectory().getPath() + File.separator + "Download" + File.separator + "11.jpg");
    }

    private void tt(String photoPath) {
        log("photoPath=" + photoPath);
        BitmapFactory.Options options = new BitmapFactory.Options();
        // 不获取图片，不加载到内存中，只返回图片属性
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(photoPath, options);
        // 图片的宽高
        int outHeight = options.outHeight;
        int outWidth = options.outWidth;
        log("图片宽=" + outWidth + "  图片高=" + outHeight);
        // 设置 Bitmap 像素保存格式
//        options.inPreferredConfig = Bitmap.Config.ARGB_4444;
        options.inJustDecodeBounds = false;
        options.inSampleSize = 2;
//        options.inMutable = true;
//        options.inBitmap = oldBitmap;
        Bitmap bitmap = BitmapFactory.decodeFile(photoPath, options);
        int bitmapSize = BitmapUtils.getBitmapSize(bitmap);
        log("压缩后：图片占内存大小=" + bitmapSize + " / 宽度=" + bitmap.getWidth() + "高度=" + bitmap.getHeight());

//        // 支持传入图片的路径，流和图片修饰符等
//        BitmapRegionDecoder mDecoder = BitmapRegionDecoder.newInstance(photoPath, false);
//        // 需要显示的区域就有由rect控制，options来控制图片的属性
//        Bitmap bitmap = mDecoder.decodeRegion(mRect, options);

        try {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, new FileOutputStream(Environment.getExternalStorageDirectory().getPath() + File.separator + "Download" + File.separator + "11.jpg"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
