package com.example.practical_3;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String Vid = "rabbid";
    private VideoView mVideoView;
    private static final String mp4 = "baby_laughing";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mVideoView = findViewById(R.id.videoView);


    }
    @Override
    protected void onStart() {
        super.onStart();

        initializePlayer();
        Uri myUri = getMedia(mp4); // initialize Uri here
        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(getApplicationContext(), myUri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.start();

    }
    @Override
    protected void onStop() {
        super.onStop();

        releasePlayer();
    }


    private void initializePlayer()
    {
        Uri videoUri = getMedia(Vid);
        mVideoView.setVideoURI(videoUri);
        mVideoView.start();
    }

    private Uri getMedia (String mediaName){
        return Uri.parse("android.resource://" + getPackageName() + "/raw/" + mediaName);
    }

    private void releasePlayer() {
        mVideoView.stopPlayback();
    }
}
