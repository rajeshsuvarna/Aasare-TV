package com.infouna.aasaretv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
/**
 * Created by Rajesh
 */
public class WebViewActivitySchedule extends Activity {
    WebView wv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_schedule);
        wv1 = (WebView) findViewById(R.id.webView);
        wv1.loadUrl("http://www.aasaretv.com/app-schedule.php");
        wv1.setWebViewClient(new MyBrowser());
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
        Intent i = new Intent(WebViewActivitySchedule.this, HomeActivity.class);
        startActivity(i);
    }
}