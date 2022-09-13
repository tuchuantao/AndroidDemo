package com.kevin.demo.module.bitmap;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.ImageView;
import android.widget.Toast;

import com.kevin.demo.R;
import com.kevin.demo.base.BaseActivity;
import com.kevin.demo.databinding.ActivityBitmapBinding;
import com.kevin.demo.util.BitmapUtils;

import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by tuchuantao on 2021/8/23
 * Desc:
 */
public class BitmapActivity extends BaseActivity<ActivityBitmapBinding> {

    private static final String IMG_TYPE = "image/*";
    private static final int REQUEST_CODE_PICK_IMG = 1;

    private Uri mImgUri;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_bitmap;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        binding.btnSelectImg.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType(IMG_TYPE);
            startActivityForResult(intent, REQUEST_CODE_PICK_IMG);
        });

        binding.btnCompress.setOnClickListener(view -> {
            if (mImgUri == null) {
                Toast.makeText(this, "Please select some img!!!", Toast.LENGTH_SHORT).show();
                return;
            }
//            binding.img.setImageBitmap(compressBitmap(mImgUri));
        });

        binding.originColor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    int color = Color.parseColor("#" + editable.toString());
                    binding.originColor.setTextColor(color);
                } catch (Exception e) {
                    log(e.toString());
                }
            }
        });

        binding.newColor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    int color = Color.parseColor("#" + editable.toString());
                    binding.newColor.setTextColor(color);
                } catch (Exception e) {
                    log(e.toString());
                }
            }
        });

        binding.btnReplace.setOnClickListener(view -> {
            if (mImgUri == null) {
                Toast.makeText(this, "Please select some img!!!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(binding.originColor.getText()) || TextUtils.isEmpty(binding.newColor.getText())) {
                Toast.makeText(this, "Please input origin color and new color!!!", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                int originColor = Color.parseColor("#" + binding.originColor.getText());
                int newColor = Color.parseColor("#" + binding.newColor.getText());
                replaceBitmapColor(originColor, newColor, binding.img);
            } catch (Exception e) {
                log(e.toString());
            }
        });

        binding.btnReset.setOnClickListener(view -> {
            if (mImgUri == null) {
                Toast.makeText(this, "Please select some img!!!", Toast.LENGTH_SHORT).show();
                return;
            }
            binding.img.setImageBitmap(getBitmapAndHandleOrientation(mImgUri));
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @androidx.annotation.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_PICK_IMG) {
                if (data.getData() == null) {
                    return;
                }
                mImgUri = data.getData();
                binding.img.setImageBitmap(getBitmapAndHandleOrientation(mImgUri));
            }
        }
    }

    private Bitmap getBitmap(Uri imgUri) {
        log("imgUri=" + imgUri);
        Bitmap bitmap = null;
        try (InputStream inputStream = getContentResolver().openInputStream(imgUri)) {
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    // 处理旋转角度的问题
    private Bitmap getBitmapAndHandleOrientation(Uri imgUri) {
        log("imgUri=" + imgUri);
        Bitmap bitmap = null;
        Cursor cursor = getContentResolver().query(imgUri, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            // 获取图片路径
            String filePath = cursor.getString(cursor.getColumnIndex("_data"));
            // 获取旋转的角度
            String orientation = cursor.getString(cursor.getColumnIndex("orientation"));
            cursor.close();
            if (filePath != null) {
                // BitmapFactory.decodeFile(filePath) 会检查当前是否有读取权限
                //bitmap = BitmapFactory.decodeFile(filePath);
                InputStream inputStream = null;
                try {
                    inputStream = getContentResolver().openInputStream(imgUri);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inMutable = true; // Bitmap 是否可以更改
                bitmap = BitmapFactory.decodeStream(inputStream, null, options);


                int angle = 0;
                if (!TextUtils.isEmpty(orientation)) {
                    angle = Integer.parseInt(orientation);
                }
                if (angle != 0) {
                    Matrix m = new Matrix();
                    int width = bitmap.getWidth();
                    int height = bitmap.getHeight();
                    m.setRotate(angle); // 旋转度
                    // 重新生成图片
                    bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, m, true);
                }
            }
        }
        return bitmap;
    }


    private Bitmap compressBitmap(String photoPath) {
        log("photoPath=" + photoPath);
        BitmapFactory.Options options = new BitmapFactory.Options();
        // 不获取图片，不加载到内存中，只返回图片属性
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(photoPath, options);
        // 图片的宽高
        int outHeight = options.outHeight;
        int outWidth = options.outWidth;
        log("图片宽=" + outWidth + "  图片高=" + outHeight);
        // 图片格式压缩
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
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50,
                    new FileOutputStream(Environment.getExternalStorageDirectory().getPath() + File.separator +
                            "Download" + File.separator + "11.jpg"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    private void replaceBitmapColor(int originColor, int newColor, ImageView imageView) {
        new Thread(() -> {
            Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            // 保存所有像素
            int[] pixels = new int[width * height];
            bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
            for (int i = 0; i < pixels.length; i++) {
                int color = pixels[i];
                int red = (color & 0x00ff0000) >> 16; // 取高两位
                int green = (color & 0x0000ff00) >> 8; // 取中两位
                int blue = color & 0x000000ff; // 取低两位
                log("index=" + i + "  color=" + color + "  red=" + red + "  green=" + green + "  blue=" + blue);
//                if (color == originColor) {
//                    pixels[i] = newColor;
//                }

                // 所有的颜色并不是非黑即白，每个色块还有中间过度，具体情况具体分析
                pixels[i] = red > 220 ? Color.WHITE : newColor;
            }
            bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
            imageView.setImageBitmap(bitmap);
        }).start();
    }
}
