package com.app.camera;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class scan_MedicineDetail extends Activity {
    private String showUri = "http://52.243.63.197/readMedicineDetailT.php";
    String result, nickname, compressedPicPath;
    com.android.volley.RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan_medicine_detail);

        MedicineDetailComponent();
        Intent intent = getIntent();
        Bundle bund = intent.getExtras();
        nickname = bund.getString("nickname");
        compressedPicPath = bund.getString("compressedPicPath");

        detailTitle.setText(nickname);

        Bitmap compressedBitmap = BitmapFactory.decodeFile(compressedPicPath);
        img藥袋外觀.setImageBitmap(compressedBitmap);

        requestQueue = Volley.newRequestQueue(scan_MedicineDetail.this);
        testJSON();
    }

    private void MedicineDetailComponent() {
        detailTitle=findViewById(R.id.detailTitle);
        txt調劑日期=findViewById(R.id.txt調劑日期);
        txt用法用量=findViewById(R.id.txt用法用量);
        txt用途=findViewById(R.id.txt用途);
        txt副作用=findViewById(R.id.txt副作用);
        txt藥品外觀=findViewById(R.id.txt藥品外觀);
        img藥袋外觀=findViewById(R.id.img藥袋外觀);
        txt藥品名稱=findViewById(R.id.txt藥品名稱);
        mTxtResult = findViewById(R.id.txtResult);
    }

    public void testJSON() {
        //Map<String, String> params = new HashMap<String, String>();
        //params.put("tag", "test");
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, showUri, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response.toString());
                        try {
                            JSONArray data = response.getJSONArray("data");
                            for (int i = 0; i < data.length(); i++) {
                                JSONObject jasondata = data.getJSONObject(i);
                                String MB_ID = "藥袋_ID:" + jasondata.getString("MB_ID") + "\r\n";

                                //String MB_indication = "藥袋_適應症狀:" + jasondata.getString("MB_indication") + "\r\n";
                                String MB_indication = jasondata.getString("MB_indication");

                                //String MB_sied_effect = "藥袋_副作用:" + jasondata.getString("MB_sied_effect") + "\r\n";
                                String MB_sied_effect = jasondata.getString("MB_sied_effect");

                                //String MB_nickname = "藥袋_自定義名稱:" + jasondata.getString("MB_nickname") + "\r\n";
                                String MB_image = "藥袋_圖片:" + jasondata.getString("MB_image") + "\r\n";
                                String MB_pharmacy = "藥袋_診所:" + jasondata.getString("MB_pharmacy") + "\r\n";

                                //String MB_dosage = "藥袋_用法:" + jasondata.getString("MB_dosage") + "\r\n";
                                String MB_dosage = jasondata.getString("MB_dosage");

                                String m_id = "會員_ID:" + jasondata.getString("m_id") + "\r\n";
                                String d_id = "藥品_ID:" + jasondata.getString("d_id") + "\r\n";

                                //String MB_date = "藥袋_日期:" + jasondata.getString("MB_date") + "\r\n";
                                String MB_date = jasondata.getString("MB_date");

                                String MB_days = "藥袋_天數:" + jasondata.getString("MB_days") + "\r\n";

                                //result += MB_ID + MB_indication + MB_sied_effect + MB_nickname + MB_image + MB_pharmacy + MB_dosage + m_id + d_id + MB_date + MB_days;
                                //mTxtResult.setText(result);
                                txt調劑日期.setText(MB_date);
                                txt用法用量.setText(MB_dosage);
                                //txt藥品名稱.setText("");
                                txt用途.setText(MB_indication);
                                txt副作用.setText(MB_sied_effect);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("de","nonResponse");
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.append(error.getMessage());
                    }
                });
        requestQueue.add(jsonObjectRequest);
    }
    TextView detailTitle;
    TextView txt調劑日期;
    TextView txt用法用量;
    TextView txt藥品名稱;
    TextView txt用途;
    TextView txt副作用;
    TextView txt藥品外觀;
    ImageView img藥袋外觀;

    TextView mTxtResult;
}