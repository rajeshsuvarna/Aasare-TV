package com.infouna.aasaretv;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout ln1;
    private static final int red = 0xfff44336;
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ln1 = (LinearLayout) findViewById(R.id.ln);
        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) { // connected to the internet

            new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

                @Override
                public void run() {
                    // This method will be executed once the timer is over
                    // Start your app main activity
                    Intent i = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(i);
                    // close this activity
                   finish();
                }
            }, SPLASH_TIME_OUT);
        } else {
            //  no inetrnet connectivity error message
            Snackbar snackbar = Snackbar.make(ln1, "Check your internet connection !!", Snackbar.LENGTH_INDEFINITE) . setAction("OK", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
                   finish();
                }
                });
                snackbar.setActionTextColor(Color.BLACK);
                View snackBarView = snackbar.getView();
                snackBarView.setBackgroundColor(red);
                snackbar.show();
        }
    }
}