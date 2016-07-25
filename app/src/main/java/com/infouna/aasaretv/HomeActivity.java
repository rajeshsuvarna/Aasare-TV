package com.infouna.aasaretv;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.infouna.aasaretv.app.AppConfig;
import com.infouna.aasaretv.app.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.VideoView;

/**
 * Created by Rajesh
 */
public class HomeActivity extends Activity {

    public String path;
    private VideoView mVideoView;
    private Activity myActivity;
    private static ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        makeJsonObjectRequest(); // calling here to ensure that URL is recieved early

        Vitamio.isInitialized(getApplicationContext());
        setContentView(R.layout.activity_home);

        LinearLayout scl = (LinearLayout) findViewById(R.id.sc); //links to webview activity
        LinearLayout vdl = (LinearLayout) findViewById(R.id.vd);
        LinearLayout dol = (LinearLayout) findViewById(R.id.donate);
        LinearLayout conl = (LinearLayout) findViewById(R.id.contact);
        LinearLayout share = (LinearLayout) findViewById(R.id.share);
        this.myActivity = this;
        mVideoView = (VideoView) findViewById(R.id.vitamio_videoView);

        new Handler().postDelayed(new Runnable() {  //adding a delay to ensure the path variable has been set

            @Override
            public void run() {

                mVideoView.setVideoPath(path);
                mVideoView.requestFocus();
                mVideoView.setOnErrorListener(mOnErrorListener);
                progressDialog = ProgressDialog.show(HomeActivity.this, "", "Streaming...Touch video for fullscreen", true);

            }
        }, 5000);

        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                progressDialog.dismiss();
                mVideoView.start();
                mediaPlayer.setPlaybackSpeed(1.0f);
            }
        });

        mVideoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent i = new Intent(getApplicationContext(), FullScreen.class);
                startActivity(i);
                return true;
            }
        });


        scl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(myActivity.getApplicationContext(), WebViewActivitySchedule.class);
                startActivity(i);
            }
        });

        vdl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(myActivity.getApplicationContext(), WebViewVideos.class);
                startActivity(i);
            }
        });

        dol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(myActivity.getApplicationContext(), WebViewDonate.class);
                startActivity(i);
            }
        });

        conl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(myActivity.getApplicationContext(), WebViewContact.class);
                startActivity(i);
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "https://goo.gl/ZyBwAo";
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                share.putExtra(Intent.EXTRA_TEXT, message);
                startActivity(Intent.createChooser(share, "Share 'Aasare TV'  Via"));
            }
        });

    }

    private MediaPlayer.OnErrorListener mOnErrorListener = new MediaPlayer.OnErrorListener() {
        @Override
        public boolean onError(MediaPlayer mp, int what, int extra) {
            Toast.makeText(getApplicationContext(), "Sorry, Channel not broadcasting", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
            return true;
        }
    };

    @Override
    public void onBackPressed() {
        myActivity.finish();
    }

    private void makeJsonObjectRequest() {


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                AppConfig.URL_STREAM, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {


                try {
                    // Parsing json object response
                    // response will be a json object
                    path = response.getString("result");


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }

}