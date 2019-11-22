package com.app.camera;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class scan_MedicineDetail extends Activity {
    private String showUri = "http://192.168.1.50/readMedicineDetailT.php";
    String result;
    com.android.volley.RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan_medicine_detail);

        MedicineDetailComponent();
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
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, showUri, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response.toString());
                        try {
                            mTxtResult.setText(response.toString());
                            JSONArray data = response.getJSONArray("data");
                            for (int i = 0; i < data.length(); i++) {
                                JSONObject jasondata = data.getJSONObject(i);
                                String MB_ID = jasondata.getString("MB_ID");
                                String MB_indication = jasondata.getString("MB_indication");
                                result += MB_ID + MB_indication;
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
    TextView mTxtResult;
    ImageView img藥袋外觀;
}