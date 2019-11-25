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
//        setContentView(webview);




      //  Uri uri = Uri.parse("http://104.41.183.184/web/index.html"); // missing 'http://' will cause crashed
      //  Intent intent = new Intent(Intent.ACTION_VIEW, uri);
      //  startActivity(intent);

    }

    private void InitialiComponent() {


    }

    private WebView webview;
}
