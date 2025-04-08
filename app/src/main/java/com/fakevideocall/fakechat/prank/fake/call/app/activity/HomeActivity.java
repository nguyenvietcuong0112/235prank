package com.fakevideocall.fakechat.prank.fake.call.app.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.fakevideocall.fakechat.prank.fake.call.app.R;
import com.fakevideocall.fakechat.prank.fake.call.app.activity.message.MessageActivity;
import com.fakevideocall.fakechat.prank.fake.call.app.activity.videocall.VideoCallActivity;
import com.fakevideocall.fakechat.prank.fake.call.app.base.BaseActivity;
import com.fakevideocall.fakechat.prank.fake.call.app.databinding.ActivityHomeBinding;
import com.fakevideocall.fakechat.prank.fake.call.app.utils.ActivityLoadNativeFull;
import com.fakevideocall.fakechat.prank.fake.call.app.utils.Constant;
import com.fakevideocall.fakechat.prank.fake.call.app.utils.SharePreferenceUtils;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdView;
import com.mallegan.ads.callback.InterCallback;
import com.mallegan.ads.callback.NativeCallback;
import com.mallegan.ads.util.Admob;

public class HomeActivity extends BaseActivity {

    ActivityHomeBinding binding;

    private SharePreferenceUtils sharePreferenceUtils;


    @Override
    public void bind() {
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharePreferenceUtils = new SharePreferenceUtils(this);
        sharePreferenceUtils.incrementCounter();

        binding.ivSettings.setOnClickListener(v -> {
            Intent intent = new Intent(this, SettingActivity.class);
            startActivity(intent);
        });

        binding.btnVideocall.setOnClickListener(v -> {
            if (Constant.interHome != null) {
                Admob.getInstance().showInterAds(HomeActivity.this, Constant.interHome, new InterCallback() {
                    @Override
                    public void onNextAction() {
                        super.onNextAction();
                        Intent intent = new Intent(HomeActivity.this, VideoCallActivity.class);
                        startActivity(intent);
                        loadInterHome();
                    }
                    @Override
                    public void onAdClosedByUser() {
                        super.onAdClosedByUser();
                        if (!SharePreferenceUtils.isOrganic(getApplicationContext())){
                            Intent intent = new Intent(HomeActivity.this, ActivityLoadNativeFull.class);
                            intent.putExtra(ActivityLoadNativeFull.EXTRA_NATIVE_AD_ID, getString(R.string.native_full_home));
                            startActivity(intent);
                        }
                    }
                });
            } else {
                Intent intent = new Intent(this, VideoCallActivity.class);
                startActivity(intent);
            }
        });

        binding.btnMessage.setOnClickListener(v -> {
            if (Constant.interHome != null) {
                Admob.getInstance().showInterAds(HomeActivity.this, Constant.interHome, new InterCallback() {
                    @Override
                    public void onNextAction() {
                        super.onNextAction();
                        Intent intent = new Intent(HomeActivity.this, MessageActivity.class);
                        startActivity(intent);
                        loadInterHome();
                    }
                    @Override
                    public void onAdClosedByUser() {
                        super.onAdClosedByUser();
                        if (!SharePreferenceUtils.isOrganic(getApplicationContext())){
                            Intent intent = new Intent(HomeActivity.this, ActivityLoadNativeFull.class);
                            intent.putExtra(ActivityLoadNativeFull.EXTRA_NATIVE_AD_ID, getString(R.string.native_full_home));
                            startActivity(intent);
                        }
                    }
                });
            } else {
                Intent intent = new Intent(this, MessageActivity.class);
                startActivity(intent);
            }
        });
//        loadBanner();
        loadInterHome();
        loadNative();

    }





    private void loadNative() {
        if (!SharePreferenceUtils.isOrganic(HomeActivity.this)) {
                loadAds();
        } else {
            binding.frAds.setVisibility(View.GONE);
        }
    }

    private void loadAds() {
        Admob.getInstance().loadNativeAd(this, getString(R.string.native_home), new NativeCallback() {
            @Override
            public void onNativeAdLoaded(NativeAd nativeAd) {
                super.onNativeAdLoaded(nativeAd);
                NativeAdView adView = (NativeAdView) LayoutInflater.from(HomeActivity.this).inflate(R.layout.layout_native_home, null);
                binding.frAds.setVisibility(View.VISIBLE);
                binding.frAds.removeAllViews();
                binding.frAds.addView(adView);
                Admob.getInstance().pushAdsToViewCustom(nativeAd, adView);
            }

            @Override
            public void onAdFailedToLoad() {
                super.onAdFailedToLoad();
                binding.frAds.setVisibility(View.GONE);
            }
        });
    }

    private void loadBanner() {
//        if (!SharePreferenceUtils.isOrganic(this)) {
            Admob.getInstance().loadCollapsibleBanner(
                    this,
                    getString(R.string.banner_collap),
                    "top"
            );
//        } else {
//            binding.llBanner.setVisibility(View.GONE);
//        }
    }

    private void loadInterHome() {
//        if(!SharePreferenceUtils.isOrganic(this)){
            Admob.getInstance().loadInterAds(this, getString(R.string.inter_home), new InterCallback() {
                @Override
                public void onInterstitialLoad(InterstitialAd interstitialAd) {
                    super.onInterstitialLoad(interstitialAd);
                    Constant.interHome = interstitialAd;
                }
            });
//    }

    }

}
