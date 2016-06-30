package com.infouna.aasaretv;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.VideoView;
/**
 * Created by rajesh on 6/27/2016.
 */
public class FullScreen extends Activity {
    private String path;
    private VideoView mVideoView;
    private static ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Vitamio.isInitialized(getApplicationContext());
        setContentView(R.layout.activity_fullscreen);
        mVideoView = (VideoView) findViewById(R.id.vitamio_videoView);
        path = "rtmp://103.230.222.250:1935/livestream/livestream/livestream";
        mVideoView.setVideoPath(path);
        mVideoView.requestFocus();
        progressDialog = ProgressDialog.show(this, "", "Streaming...", true);
        mVideoView.setVideoLayout(VideoView.VIDEO_LAYOUT_STRETCH, 0);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        mVideoView.setOnErrorListener(mOnErrorListener);

        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                progressDialog.dismiss();
                mVideoView.start();
                mediaPlayer.setPlaybackSpeed(1.0f);
            }
        });
    }

    private MediaPlayer.OnErrorListener mOnErrorListener = new MediaPlayer.OnErrorListener() {
        @Override
        public boolean onError(MediaPlayer mp, int what, int extra) {
            Toast.makeText(getApplicationContext(),"Sorry, Channel not broadcasting",Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
            return true;
        }
    };
}
