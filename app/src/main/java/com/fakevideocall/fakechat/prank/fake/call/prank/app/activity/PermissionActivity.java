package com.fakevideocall.fakechat.prank.fake.call.prank.app.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import com.fakevideocall.fakechat.prank.fake.call.prank.app.R;
import com.fakevideocall.fakechat.prank.fake.call.prank.app.base.BaseActivity;
import com.fakevideocall.fakechat.prank.fake.call.prank.app.databinding.ActivityPermissionBinding;
import com.fakevideocall.fakechat.prank.fake.call.prank.app.utils.PermissionUtils;
import com.fakevideocall.fakechat.prank.fake.call.prank.app.utils.SharePreferenceUtils;
import com.fakevideocall.fakechat.prank.fake.call.prank.app.utils.SystemConfiguration;
import com.fakevideocall.fakechat.prank.fake.call.prank.app.utils.SystemUtil;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdView;
import com.mallegan.ads.callback.NativeCallback;
import com.mallegan.ads.util.Admob;

public class PermissionActivity extends BaseActivity {
    private ActivityPermissionBinding binding;

    @Override
    public void bind() {
        SystemUtil.setLocale(this);
        SystemConfiguration.setStatusBarColor(this, R.color.white, SystemConfiguration.IconColor.ICON_DARK);
        binding = ActivityPermissionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.txtGo.setOnClickListener(v -> {
            startActivity(new Intent(this, HomeActivity.class));

        });

        binding.onOffCamera.setOnCheckedChangeListener((compoundButton, z) -> {
            if(z){
                requestCamera();
            }
        });

        loadAds();
        if (!SharePreferenceUtils.isOrganic(this)) {
            Admob.getInstance().loadInlineBanner(this, getString(R.string.banner_inline_per), Admob.BANNER_INLINE_LARGE_STYLE);
        } else {
            binding.adCardView.setVisibility(View.GONE);
        }
    }


    private void requestCamera() {
        binding.onOffCamera.setChecked(true);
        requestPermissionCameraLauncher.launch(PermissionUtils.CAMERA_PERMISSION);
    }

    private final ActivityResultLauncher<String[]> requestPermissionCameraLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), result -> {
                if (PermissionUtils.cameraPermissionGrant(this)) {
                    binding.onOffCamera.setChecked(true);
                } else {
                    PermissionUtils.showAlertPermissionNotGrant(binding.getRoot(), this);
                    binding.onOffCamera.setChecked(false);
                }
            });



    private void loadAds() {

        if (!SharePreferenceUtils.isOrganic(PermissionActivity.this)) {
            Admob.getInstance().loadNativeAd(this, getString(R.string.native_permission), new NativeCallback() {
                @Override
                public void onNativeAdLoaded(NativeAd nativeAd) {
                    super.onNativeAdLoaded(nativeAd);
                    NativeAdView adView = (NativeAdView) LayoutInflater.from(PermissionActivity.this).inflate(R.layout.layout_native_language_non_organic, null);
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
        } else {
            binding.frAds.setVisibility(View.GONE);
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}
