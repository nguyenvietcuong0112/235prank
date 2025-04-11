package com.fakevideocall.fakechat.prank.fake.call.app.activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.fakevideocall.fakechat.prank.fake.call.app.activity.message.MessageActivity;
import com.fakevideocall.fakechat.prank.fake.call.app.activity.videocall.VideoCallActivity;
import com.fakevideocall.fakechat.prank.fake.call.app.base.BaseActivity;
import com.fakevideocall.fakechat.prank.fake.call.app.databinding.ActivityHomeBinding;
import com.fakevideocall.fakechat.prank.fake.call.app.databinding.ActivitySettingsBinding;
import com.mallegan.ads.util.AppOpenManager;

import java.util.Timer;
import java.util.TimerTask;

public class SettingActivity  extends BaseActivity {
    ActivitySettingsBinding binding;
    private boolean isBtnProcessing = false;

    @Override
    public void bind() {
        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.icback.setOnClickListener(v -> {
            onBackPressed();
        });

        binding.btnShare.setOnClickListener(v -> {
            if (isBtnProcessing) return;
            isBtnProcessing = true;

            Intent myIntent = new Intent(Intent.ACTION_SEND);
            myIntent.setType("text/plain");
            String body = "có link app thì điền vào";
            String sub = "Flash Alert App";
            myIntent.putExtra(Intent.EXTRA_SUBJECT, sub);
            myIntent.putExtra(Intent.EXTRA_TEXT, body);
            startActivity(Intent.createChooser(myIntent, "Share"));
            AppOpenManager.getInstance().disableAppResumeWithActivity(HomeActivity.class);
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    isBtnProcessing = false;
                }
            }, 1000);
        });

        binding.btnLanguage.setOnClickListener(v -> {
            Intent intent = new Intent(SettingActivity.this, LanguageActivity.class);
            intent.putExtra("from_settings", true);
            startActivity(intent);
        });

        binding.btnRateUs.setOnClickListener(v -> {
            Uri uri = Uri.parse("market://details?id=");
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
            goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                    Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
            try {
                this.startActivity(goToMarket);
            } catch (ActivityNotFoundException e) {
                this.startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=")));
            }
        });


        binding.btnPrivacyPolicy.setOnClickListener(v -> {
            Uri uri = Uri.parse("https://whyteabraham.netlify.app/policy");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });


    }
}
