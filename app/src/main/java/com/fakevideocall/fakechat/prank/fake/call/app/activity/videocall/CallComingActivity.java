package com.fakevideocall.fakechat.prank.fake.call.app.activity.videocall;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.fakevideocall.fakechat.prank.fake.call.app.R;
import com.fakevideocall.fakechat.prank.fake.call.app.model.Video;

import de.hdodenhof.circleimageview.CircleImageView;


public class CallComingActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_coming);

        ImageView declineButton = findViewById(R.id.btn_decline);
        ImageView acceptButton = findViewById(R.id.btn_accept);
        RelativeLayout backgr = findViewById(R.id.backgr);
        CircleImageView imageViewAvatar = findViewById(R.id.imageViewAvatar);
        TextView tv_name = findViewById(R.id.tv_name);

        Video video = (Video) getIntent().getSerializableExtra("video");

        if(video!=null){
            imageViewAvatar.setImageResource(video.getImageResId());
            tv_name.setText(video.getName());
//            backgr.setBackgroundResource(video.getImageResId());
//            backgr.getBackground().setAlpha(64);

        }


        if (video == null) {
            Log.e("CallComingActivity", "Video object is null!");
            finish();
            return;
        }
        phatNhacChuong();

        declineButton.setOnClickListener(v -> {
            tatNhacChuong();
            finish();
        });

        acceptButton.setOnClickListener(v -> {
            tatNhacChuong();
            Intent intent = new Intent(this, VideoPlayerActivity.class);
            intent.putExtra("videoPath", video.getVideoResId());
            this.startActivity(intent);
        });
    }

    private void phatNhacChuong() {
        // Đặt nhạc chuông từ thư mục raw
        mediaPlayer = MediaPlayer.create(this, R.raw.call_coming);
        mediaPlayer.setLooping(true); // Lặp lại liên tục
        mediaPlayer.start();
    }

    private void tatNhacChuong() {
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        tatNhacChuong();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tatNhacChuong();
    }
}