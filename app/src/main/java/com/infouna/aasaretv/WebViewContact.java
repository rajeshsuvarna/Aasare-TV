package com.infouna.aasaretv;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
/**
 * Created by rajesh on 6/22/2016.
 */
public class WebViewContact extends Activity {
    WebView wv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_contact);
        wv1 = (WebView) findViewById(R.id.webView);

        wv1.loadUrl("http://www.aasaretv.com/contact-us-app.php");
        wv1.setWebViewClient(new WebViewContact.MyBrowser());
        wv1.getSettings().setJavaScriptEnabled(true);
        wv1.getSettings().setLoadsImagesAutomatically(true);
        wv1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            if( url.startsWith("http:") || url.startsWith("https:") ) {
                return false;
            }
            // Otherwise allow the OS to handle it
            else if (url.startsWith("tel:")) {
                Intent tel = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
                startActivity(tel);
                return true;
            }
            else if (url.startsWith("mailto:")) {
                Intent mail = new Intent(Intent.ACTION_SEND, Uri.parse(url));
                mail.setType("message/rfc822");
                mail.putExtra(Intent.EXTRA_EMAIL, new String[] {"aasaretv2015@gmail.com"});
                startActivity(Intent.createChooser(mail, "Send email..."));
                return true;
            }
            else view.loadUrl(url);
            return true;
        }
    }
    public void openHomeActivity(View v) {
        Intent i = new Intent(WebViewContact.this, HomeActivity.class);
        startActivity(i);
    }
}