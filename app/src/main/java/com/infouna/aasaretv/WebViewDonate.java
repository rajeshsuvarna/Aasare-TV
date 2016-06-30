package com.infouna.aasaretv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
/**
 * Created by rajesh on 6/22/2016.
 */
public class WebViewDonate extends Activity {
    WebView wv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_donate);
        wv1 = (WebView) findViewById(R.id.webView);
        wv1.loadUrl("http://www.aasaretv.com/donate-app.php");
        wv1.setWebViewClient(new WebViewDonate.MyBrowser());
        wv1.getSettings().setJavaScriptEnabled(true);
        wv1.getSettings().setLoadsImagesAutomatically(true);
        wv1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    public void openHomeActivity(View v) {
        Intent i = new Intent(WebViewDonate.this, HomeActivity.class);
        startActivity(i);
    }
}