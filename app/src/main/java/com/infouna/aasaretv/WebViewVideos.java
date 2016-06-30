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
public class WebViewVideos extends Activity{
    WebView wv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_videos);
        wv1 = (WebView) findViewById(R.id.webView);
        wv1.loadUrl("http://www.aasaretv.com/video-gallery-app.php");
        wv1.setWebViewClient(new WebViewVideos.MyBrowser());
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
        Intent i = new Intent(WebViewVideos.this, HomeActivity.class);
        startActivity(i);
    }
}