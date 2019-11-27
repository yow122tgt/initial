package com.example.login1;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;

public class news extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_layout);


        InitialiComponent();
        final SharedPreferences sharedPreferences = getSharedPreferences("UserInfo",MODE_PRIVATE);
        String username = sharedPreferences.getString("username","");

        webview =findViewById(R.id.webview);
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        Log.d("erufhw","test");

        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl("http://104.41.183.184/web/index.html");
        setContentView(webview);

        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        //缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件



      //  Uri uri = Uri.parse("http://104.41.183.184/web/index.html"); // missing 'http://' will cause crashed
      //  Intent intent = new Intent(Intent.ACTION_VIEW, uri);
      //  startActivity(intent);

    }

    private void InitialiComponent() {


    }

    private WebView webview;
}
