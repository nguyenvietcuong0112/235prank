package com.fakevideocall.fakechat.prank.fake.call.prank.app.activity.videocall;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;

import com.fakevideocall.fakechat.prank.fake.call.prank.app.R;
import com.fakevideocall.fakechat.prank.fake.call.prank.app.adapter.VideoCallAdapter;
import com.fakevideocall.fakechat.prank.fake.call.prank.app.base.BaseActivity;
import com.fakevideocall.fakechat.prank.fake.call.prank.app.databinding.ActivityVideocallBinding;
import com.fakevideocall.fakechat.prank.fake.call.prank.app.model.Video;

import java.util.ArrayList;
import java.util.List;

public class VideoCallActivity extends BaseActivity {
    @Override
    public void bind() {
        ActivityVideocallBinding binding = ActivityVideocallBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position == 3 ? 3 : 1;
            }
        });
        binding.recyclerView.setLayoutManager(gridLayoutManager);

        List<Video> videos = new ArrayList<>();
        videos.add(new Video("V", R.drawable.v, R.raw.v));
        videos.add(new Video("Jungkook", R.drawable.jungkook, R.raw.jungkook));
        videos.add(new Video("Taylor Swift", R.drawable.taylorswift, R.raw.taylorswift));
        videos.add(new Video("Jisoo", R.drawable.jisoo, R.raw.jisoo));
        videos.add(new Video("Jennie", R.drawable.jennie, R.raw.jennie));
        videos.add(new Video("Lisa", R.drawable.lisa, R.raw.lisa));
        videos.add(new Video("Rose", R.drawable.rose, R.raw.rose));
        videos.add(new Video("Adele", R.drawable.adele, R.raw.adele));
        videos.add(new Video("Messi", R.drawable.messi, R.raw.messi));
        videos.add(new Video("Neymar", R.drawable.neymar, R.raw.neymar));
        videos.add(new Video("Mbappe", R.drawable.mpabbe, R.raw.mpabbe));
        videos.add(new Video("Beonce'", R.drawable.beyonce, R.raw.beyonce));
        videos.add(new Video("Tom Holland", R.drawable.tomholland, R.raw.tomholland));
        videos.add(new Video("Ronaldo", R.drawable.ronaldo, R.raw.ronaldo));
        videos.add(new Video("Cardi B", R.drawable.cardib, R.raw.cardib));
        videos.add(new Video("Dwayne", R.drawable.dwayne, R.raw.dwayne));
        videos.add(new Video("Johnny Depp", R.drawable.johnnydeep, R.raw.johnnydeep));
        videos.add(new Video("Jennifer Lopez", R.drawable.jenniferlopez, R.raw.jenniferlopez));
//        videos.add(new Video("Santa Claus", R.drawable.santa, R.raw.santa));
//        videos.add(new Video("Princess", R.drawable.princess, R.raw.princess));

        videos.add(new Video("Spranlky 01", R.drawable.spranlky01, R.raw.spranlky01));
        videos.add(new Video("Spranlky 02", R.drawable.spranlky02, R.raw.spranlky02));
        videos.add(new Video("Spranlky 03", R.drawable.spranlky03, R.raw.spranlky03));
        videos.add(new Video("Spranlky 04", R.drawable.spranlky04, R.raw.spranlky04));
        videos.add(new Video("Spranlky 05", R.drawable.spranlky05, R.raw.spranlky05));
        videos.add(new Video("Spranlky 06", R.drawable.spranlky06, R.raw.spranlky06));
        videos.add(new Video("Spranlky 07", R.drawable.spranlky07, R.raw.spranlky07));
        videos.add(new Video("Spranlky 08", R.drawable.spranlky08, R.raw.spranlky08));


        VideoCallAdapter adapter = new VideoCallAdapter(this, videos, this::showSetTimeDialog);
        binding.recyclerView.setAdapter(adapter);

        binding.icback.setOnClickListener(v -> {
            onBackPressed();
        });
    }


    private void showSetTimeDialog(Video video) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_set_time);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(true);

        TextView title = dialog.findViewById(R.id.dialog_title);
        title.setText("Set time after");


        ListView listView = dialog.findViewById(R.id.time_list);
        String[] times = {"Off", "5s", "10s", "15s","30s","1m"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item_checked,R.id.checked_text, times);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        listView.setItemChecked(0, true);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            for (int i = 0; i < listView.getChildCount(); i++) {
                View child = listView.getChildAt(i);
                if (i == position) {
                    child.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
                } else {
                    // Đặt màu trắng cho các mục còn lại
                    child.setBackgroundColor(getResources().getColor(android.R.color.white));
                }
            }

        });

        Button okButton = dialog.findViewById(R.id.ok_button);

        okButton.setOnClickListener(v -> {
            int selectedPosition = listView.getCheckedItemPosition();
            int delay = 0;

            switch (selectedPosition) {
                case 1:
                    delay = 5000;
                    break;
                case 2:
                    delay = 10000;
                    break;
                case 3:
                    delay = 15000;
                    break;
                case 4:
                    delay = 30000;
                    break;
                case 5:
                    delay = 60000;
                    break;
            }

            dialog.dismiss();

            Intent intent = new Intent(this, WaitingActivity.class);
            intent.putExtra("video", video);
            intent.putExtra("delay", delay);
            startActivity(intent);
        });

        dialog.show();
    }
}
