package com.app.camera;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.document.FirebaseVisionCloudDocumentRecognizerOptions;
import com.google.firebase.ml.vision.document.FirebaseVisionDocumentText;
import com.google.firebase.ml.vision.document.FirebaseVisionDocumentTextRecognizer;
import com.google.firebase.ml.vision.text.RecognizedLanguage;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class scan2 extends AppCompatActivity {
    private String originalPicPath, compressedPicPath;
    private String resultText, blockText, paragraphText, wordText, symbolText;
    private String nameText, daysText, indicationText, dosageText, sideEffectText;
    String nickName;
    RequestQueue rQueue;
    JSONObject jsonObject;
    private String upload_URL = "http://104.41.183.184/uploadVolley.php"; //php放置位址
    Bitmap compressedBitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan2);

        final SharedPreferences sharedPreferences = getSharedPreferences("UserInfo",MODE_PRIVATE);
        String username = sharedPreferences.getString("username","");

        Intent intent = getIntent();
        Bundle bund = intent.getExtras();
        originalPicPath = bund.getString("originalPicPath");
        compressedPicPath = bund.getString("compressedPicPath");
        Toast.makeText(scan2.this, username,Toast.LENGTH_SHORT).show();

        InitialComponent();
    }

    public void InitialComponent() {
        mEdtName = findViewById(R.id.edtName);
        mEdtDays = findViewById(R.id.edtDays);
        mEdtDosage = findViewById(R.id.edtDosage);
        mEdtIndication = findViewById(R.id.edtIndication);
        mEdtSideEffect = findViewById(R.id.edtSideEffect);
        mImageCaptured = findViewById(R.id.imgCaptured);

        mTxtResult = findViewById(R.id.txtResult);

        mBtnApply = findViewById(R.id.btnApply);
        mBtnApply.setOnClickListener(new View.OnClickListener() {
            EditText input = new EditText(scan2.this);
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder dialog = new AlertDialog.Builder(scan2.this);
                dialog.setView(input);
                dialog.setTitle("請輸入藥袋名稱");
                dialog.setMessage("是否確定新增？");
                dialog.setCancelable(false);
                dialog.setNegativeButton("取消",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        // TODO Auto-generated method stub
                        Toast.makeText(scan2.this, "已取消",Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.setPositiveButton("確定新增",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        // TODO Auto-generated method stub
                        nickName = input.getText().toString();
                        if (nickName != null) {
                            uploadImage(compressedBitmap);
                            Toast.makeText(scan2.this, "新增藥單成功", Toast.LENGTH_SHORT).show();
                            Intent intent_detail = new Intent(scan2.this, scan_MedicineDetail.class);
                            Bundle bund_detail = new Bundle();
                            bund_detail.putString("nickname", nickName);
                            bund_detail.putString("compressedPicPath", compressedPicPath);
                            intent_detail.putExtras(bund_detail);
                            startActivity(intent_detail);
                            finish();
                        }
                    }
                });
                dialog.show();
            }
        });

        mBtnBack = findViewById(R.id.btnBack);
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(scan2.this, scan1.class));
            }
        });
        //runCloudTextRecognition();
        traceOn();
        setPicToImageView();
    }

    //文字辨識試作
    private void runCloudTextRecognition() {
        //將先前儲存的原始圖片解碼為bitmap
        Bitmap originalBitmap = BitmapFactory.decodeFile(originalPicPath);
        //確認圖片大小 by toast
        int picSizeW = originalBitmap.getWidth();
        int picSizeH = originalBitmap.getHeight();
        int picSizeByte = originalBitmap.getByteCount();
        String picSize = "Width:" + picSizeW + "Height:" + picSizeH + "Bytes:" + picSizeByte;
        Toast.makeText(scan2.this, picSize, Toast.LENGTH_LONG).show();

        //宣告一個FirebaseVisionImage物件image, 將compressedBitmap放入
        FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(originalBitmap);
        //提示辨識的主語言為中文/英文
        FirebaseVisionCloudDocumentRecognizerOptions options =
                new FirebaseVisionCloudDocumentRecognizerOptions.Builder()
                        .setLanguageHints(Arrays.asList("zh", "en"))
                        .build();
        //宣告一個FirebaseVisionDocumentTextRecognizer物件recognizer同時加入設定參數
        FirebaseVisionDocumentTextRecognizer recognizer = FirebaseVision.getInstance()
                .getCloudDocumentTextRecognizer(options);
        //將前面的FirebaseVisionImage物件image丟給recognizer的processImage方法以進行雲端文字辨識
        recognizer.processImage(image)
                .addOnSuccessListener(new OnSuccessListener<FirebaseVisionDocumentText>() {
                    @Override
                    public void onSuccess(FirebaseVisionDocumentText result) {
                        resultText = result.getText();
                        for (FirebaseVisionDocumentText.Block block: result.getBlocks()) {
                            blockText = block.getText();
                            Float blockConfidence = block.getConfidence();
                            List<RecognizedLanguage> blockRecognizedLanguages = block.getRecognizedLanguages();
                            Rect blockFrame = block.getBoundingBox();
                            for (FirebaseVisionDocumentText.Paragraph paragraph: block.getParagraphs()) {
                                paragraphText = paragraph.getText();
                                setRecognizedText(paragraphText);

                                Float paragraphConfidence = paragraph.getConfidence();
                                List<RecognizedLanguage> paragraphRecognizedLanguages = paragraph.getRecognizedLanguages();
                                Rect paragraphFrame = paragraph.getBoundingBox();
                                for (FirebaseVisionDocumentText.Word word: paragraph.getWords()) {
                                    wordText = word.getText();
                                    Float wordConfidence = word.getConfidence();
                                    List<RecognizedLanguage> wordRecognizedLanguages = word.getRecognizedLanguages();
                                    Rect wordFrame = word.getBoundingBox();
                                    for (FirebaseVisionDocumentText.Symbol symbol: word.getSymbols()) {
                                        symbolText = symbol.getText();
                                        Float symbolConfidence = symbol.getConfidence();
                                        List<RecognizedLanguage> symbolRecognizedLanguages = symbol.getRecognizedLanguages();
                                        Rect symbolFrame = symbol.getBoundingBox();
                                    }
                                }
                            }
                        }
                        mTxtResult.setText(resultText);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(scan2.this, "fail", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void uploadImage(Bitmap bitmap){//這個是透過volley上傳圖片，在build.gradle還有mainfest 都有加入一些套件  找不到的話跟我講
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        String encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
        try {
            jsonObject = new JSONObject();
            String MB_indication = mEdtIndication.getText().toString();//每個會辨識出來的欄位，盡量填滿  每個都有值 (適應症)
            String MB_sied_effect = mEdtSideEffect.getText().toString();//副作用
            String MB_nickname = nickName;//暱稱
            String MB_num = "1";//次數
            String MB_dosage = mEdtDosage.getText().toString();//總數量
            String m_id = "119";
            String d_id = "29";
            String MB_days = mEdtDays.getText().toString();//天數
            String MB_times = "3";

            String imgname = String.valueOf(Calendar.getInstance().getTimeInMillis());//取得日曆的時間當成檔案名字
            jsonObject.put("name", imgname);//將string裝到"name"，這樣php就能收到,
            //Log.e("Image name", etxtUpload.getText().toString().trim());
            jsonObject.put("image", encodedImage);//這樣名字不會重複
            //jsonObject.put("aa", "aa");
            jsonObject.put("MB_indication", MB_indication);
            jsonObject.put("MB_sied_effect", MB_sied_effect);
            jsonObject.put("MB_nickname", MB_nickname);
            jsonObject.put("MB_num", MB_num);
            jsonObject.put("MB_times", MB_times);
            jsonObject.put("m_id", m_id);
            jsonObject.put("d_id", d_id);
            jsonObject.put("MB_days", MB_days);
            jsonObject.put("MB_dosage", MB_dosage);

        } catch (JSONException e) {
            Log.e("JSONObject Here", e.toString());
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, upload_URL, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Log.e("aaaaaaa", jsonObject.toString());
                        rQueue.getCache().clear();
                        try {
                            Toast.makeText(getApplication(), jsonObject.getString("success"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("aaaaaaa", volleyError.toString());
            }
        });
        rQueue = Volley.newRequestQueue(scan2.this);
        rQueue.add(jsonObjectRequest);
    }

    private void requestMultiplePermissions(){
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            Toast.makeText(scan2.this, "All permissions are granted by user!", Toast.LENGTH_SHORT).show();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings
                        }
                    }
                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(scan2.this, "Some Error! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }

    public void setRecognizedText(String p) {
        if (p.indexOf("藥品名稱") != -1) {
            nameText = p.substring(p.indexOf("藥品名稱") + "藥物名稱".length(),
                    p.indexOf("數量天數"));
            mEdtName.setText(nameText);
        } else if (paragraphText.indexOf("天數") != -1) {
            String regEx = "[^0-9]";
            Pattern pattern = Pattern.compile(regEx);
            Matcher m = pattern.matcher(paragraphText);
            daysText = m.toString();
            mEdtDays.setText(daysText);
        } else if (p.indexOf("用法") != -1) {
            dosageText = p.substring(p.indexOf("用法") + "用法用量".length());
        } else if (p.indexOf("次") != -1) {
            dosageText += p.substring(p.indexOf("次")-1);
            mEdtDosage.setText(dosageText);
        } else if (p.indexOf("適應症") != -1) {
            indicationText = p.substring(p.indexOf("適應症") + "適應症".length());
            mEdtIndication.setText(indicationText);
        } else if (p.indexOf("副作用") != -1) {
            sideEffectText = p.substring(p.indexOf("副作用") + "副作用".length());
            mEdtSideEffect.setText(sideEffectText);
        }
    }

    public void setPicToImageView() {
        compressedBitmap = BitmapFactory.decodeFile(compressedPicPath);
        mImageCaptured.setImageBitmap(compressedBitmap);
    }

    EditText mEdtName, mEdtDays, mEdtIndication, mEdtDosage, mEdtSideEffect;
    ImageView mImageCaptured;
    TextView mTxtResult;
    Button mBtnApply, mBtnBack;

    public void traceOn() {
        mEdtName.setText("RIVOTRIL 0.5MG 福利全");
        mEdtDays.setText("28");
        mEdtIndication.setText("緩和焦慮相關症狀、控制癲癇發作");
        mEdtDosage.setText("口服\n" +
                "每一天兩次，遵照醫師指示，" +
                "早飯後1粒，午飯後1粒");
        mEdtSideEffect.setText("疲倦、肌肉無力等等");
    }
}