package com.app.camera;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import androidx.core.content.FileProvider;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class scan1 extends Activity {
    private String originalPicPath, compressedPicPath;
    private Button mBtnCaptureImage;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan1);

        InitialComponent();
    }

    public void InitialComponent() {
        //調用其他服務拍照按鈕
        mBtnCaptureImage = findViewById(R.id.btnCaptureImage);
        mBtnCaptureImage.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                //使用Intent調用其他服務幫忙拍照
                takePicForRecognition();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //拍照後傳送圖片
        if (resultCode == RESULT_OK) {
            /*
            //取出拍照後回傳資料
            Bundle extras = data.getExtras();
            //將資料轉換為圖像格式
            bmp = extras.getParcelable("data");
            grayBmp = getGrayBitmap(bmp);
             */
            setPicSize();

            Intent intent = new Intent(scan1.this, scan2.class);
            Bundle bund = new Bundle();
            bund.putString("originalPicPath",originalPicPath);
            bund.putString("compressedPicPath",compressedPicPath);
            intent.putExtras(bund);
            startActivity(intent);
        }
        //覆蓋原來的Activity
        //super.onActivityResult(requestCode, resultCode, data);
    }

    public void takePicForRecognition() {
        File tmpFileForCamera = new File(getExternalCacheDir(), "image_temp.jpg");
        Uri outputFileUri = FileProvider.getUriForFile(this, getPackageName() + ".fileprovider", tmpFileForCamera);
        originalPicPath = tmpFileForCamera.getAbsolutePath();
        //呼叫拍照app
        Intent intent_camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //給予intent讀取uri的權限
        intent_camera.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        //將檔案路徑指定給intent
        intent_camera.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        if (intent_camera.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent_camera, 0);
        }
    }
    //調整圖片大小
    private void setPicSize() {
        //設定用於辨識的圖片 寬720, 長1280(此尺寸為Google建議)
        int targetW = 720;
        int targetH = 1280;
        //獲取原圖片大小
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(originalPicPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;
        //計算縮小比例
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);
        //重新已設定好的比例解析圖片
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        Bitmap tmpBitmap = BitmapFactory.decodeFile(originalPicPath, bmOptions);
        //壓縮已裁切尺寸後的圖片品質並另存為compressed_image.jpg
        File tmpFileForCompress  = new File(getExternalCacheDir(), "image_compressed.jpg");
        try {
            FileOutputStream fos = new FileOutputStream(tmpFileForCompress);
            tmpBitmap.compress(Bitmap.CompressFormat.JPEG, 80, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //儲存壓縮品質後的圖片路徑
        compressedPicPath = tmpFileForCompress.getAbsolutePath();
    }

    //灰階處理(未使用)
    public static Bitmap getGrayBitmap(Bitmap bmp) {
        Bitmap GBitmap = null;
        //獲取圖片的長與寬
        int width = bmp.getWidth();
        int height = bmp.getHeight();
        //建立灰階圖片
        GBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        //建立畫布
        Canvas canvas = new Canvas(GBitmap);
        //建立畫筆
        Paint paint = new Paint();
        //建立顏色矩陣
        ColorMatrix matrix = new ColorMatrix();
        //設置顏色矩陣的飽和度:0代表灰色, 1代表原圖
        matrix.setSaturation(0);
        //顏色過濾器
        ColorMatrixColorFilter cmcf = new ColorMatrixColorFilter(matrix);
        //設置畫筆顏色過濾器
        paint.setColorFilter(cmcf);
        //畫圖
        canvas.drawBitmap(bmp, 0, 0, paint);
        return GBitmap;
    }


}