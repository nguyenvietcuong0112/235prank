package com.fakevideocall.fakechat.prank.fake.call.prank.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fakevideocall.fakechat.prank.fake.call.prank.app.R;
import com.fakevideocall.fakechat.prank.fake.call.prank.app.activity.PermissionActivity;
import com.fakevideocall.fakechat.prank.fake.call.prank.app.activity.message.ChatActivity;
import com.fakevideocall.fakechat.prank.fake.call.prank.app.model.Message;
import com.fakevideocall.fakechat.prank.fake.call.prank.app.model.Video;
import com.fakevideocall.fakechat.prank.fake.call.prank.app.utils.SharePreferenceUtils;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdView;
import com.mallegan.ads.callback.NativeCallback;
import com.mallegan.ads.util.Admob;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEW_TYPE_VIDEO = 0;
    private static final int VIEW_TYPE_AD = 1;

    private final List<Message> messages;
    private final Context context;

    public MessageAdapter(Context context, List<Message> message) {
        this.context = context;
        this.messages = message;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == VIEW_TYPE_AD) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.ads_shimmer_video_call, parent, false);
            return new AdViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_video_call, parent, false);
            return new MessageViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder.getItemViewType() == VIEW_TYPE_VIDEO) {
            Message message = messages.get(getActualPosition(position));
            MessageViewHolder videoHolder = (MessageViewHolder) holder;

            videoHolder.imageView.setImageResource(message.getImageResId());
            videoHolder.textView.setText(message.getName());

            holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, ChatActivity.class);
                intent.putExtra("name", message.getName());
                intent.putExtra("image", message.getImageResId());
                context.startActivity(intent);
            });
        } else {
            AdViewHolder otherHolder = (AdViewHolder) holder;

            if (!SharePreferenceUtils.isOrganic(context)) {
                Admob.getInstance().loadNativeAd(context, context.getString(R.string.native_message), new NativeCallback() {
                    @Override
                    public void onNativeAdLoaded(NativeAd nativeAd) {
                        super.onNativeAdLoaded(nativeAd);
                        NativeAdView adView = (NativeAdView) LayoutInflater.from(context).inflate(R.layout.layout_native_item_video, null);
                        otherHolder.nativeAdView.setVisibility(View.VISIBLE);
                        otherHolder.nativeAdView.removeAllViews();
                        otherHolder.nativeAdView.addView(adView);
                        Admob.getInstance().pushAdsToViewCustom(nativeAd, adView);
                    }

                    @Override
                    public void onAdFailedToLoad() {
                        super.onAdFailedToLoad();
                        otherHolder.nativeAdView.setVisibility(View.GONE);
                    }
                });
            } else {
                otherHolder.nativeAdView.setVisibility(View.GONE);
                otherHolder.nativeAdView.removeAllViews();
            }

        }


    }

    @Override
    public int getItemCount() {
        return messages.size() + 1;
    }

    private int getActualPosition(int position) {
        if (position > 3) {
            return position - 1;
        }
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 3) {
            return VIEW_TYPE_AD;
        }
        return VIEW_TYPE_VIDEO;
    }


    static class MessageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        MessageViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_image);
            textView = itemView.findViewById(R.id.item_name);
        }
    }

    static class AdViewHolder extends RecyclerView.ViewHolder {
        FrameLayout nativeAdView;
        AdViewHolder(View itemView) {
            super(itemView);
            nativeAdView=itemView.findViewById(R.id.ad_unit_content);
        }

    }

}