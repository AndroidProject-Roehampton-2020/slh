package com.firebase.studentapp;
//Making necessary imports

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class social3 extends AppCompatActivity {
    //Declaring initial objects to be used inside this class
    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social3);
        //Pointing the objects to XML counterparts and set the WebView URL.
        webview = findViewById(R.id.mlink);
        webview.setWebViewClient(new WebViewClient());
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.setOverScrollMode(WebView.OVER_SCROLL_NEVER);
        webview.loadUrl("https://www.linkedin.com/");
    }//end of onCreate
}//end of Class
