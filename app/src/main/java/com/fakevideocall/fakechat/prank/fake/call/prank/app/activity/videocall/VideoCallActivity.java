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
    private List<Video> allVideos = new ArrayList<>();
    private List<Video> filteredVideos = new ArrayList<>();
    private VideoCallAdapter adapter;
    private ActivityVideocallBinding binding;

    private static final String CATEGORY_STAR = "star";
    private static final String CATEGORY_FOOTBALLER = "footballer";
    private static final String CATEGORY_SPRANLKY = "spranlky";
    private String currentCategory = CATEGORY_STAR;

    @Override
    public void bind() {
        binding = ActivityVideocallBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupRecyclerView();
        setupTabBar();
        loadVideos();
        filterVideos(currentCategory);

        binding.icback.setOnClickListener(v -> {
            onBackPressed();
        });
    }

    private void setupTabBar() {
        // Tab Star
        binding.tabStar.setOnClickListener(v -> {
            currentCategory = CATEGORY_STAR;
            updateTabSelection();
            filterVideos(currentCategory);
        });

        binding.tabFootballer.setOnClickListener(v -> {
            currentCategory = CATEGORY_FOOTBALLER;
            updateTabSelection();
            filterVideos(currentCategory);
        });

        binding.tabSpranlky.setOnClickListener(v -> {
            currentCategory = CATEGORY_SPRANLKY;
            updateTabSelection();
            filterVideos(currentCategory);
        });

        updateTabSelection();
    }

    private void updateTabSelection() {
        binding.tabStar.setAlpha(0.3f);
        binding.tabFootballer.setAlpha(0.3f);
        binding.tabSpranlky.setAlpha(0.3f);


        switch (currentCategory) {
            case CATEGORY_STAR:
                binding.tabStar.setAlpha(1.0f);
                binding.tvStar.setTextColor(Color.parseColor("#7C30CB"));
                binding.tvFootballer.setTextColor(Color.parseColor("#C0B2CE"));
                binding.tvSpranlky.setTextColor(Color.parseColor("#C0B2CE"));
                break;
            case CATEGORY_FOOTBALLER:
                binding.tabFootballer.setAlpha(1.0f);
                binding.tvStar.setTextColor(Color.parseColor("#C0B2CE"));
                binding.tvFootballer.setTextColor(Color.parseColor("#7C30CB"));
                binding.tvSpranlky.setTextColor(Color.parseColor("#C0B2CE"));
                break;
            case CATEGORY_SPRANLKY:
                binding.tabSpranlky.setAlpha(1.0f);
                binding.tvStar.setTextColor(Color.parseColor("#C0B2CE"));
                binding.tvFootballer.setTextColor(Color.parseColor("#C0B2CE"));
                binding.tvSpranlky.setTextColor(Color.parseColor("#7C30CB"));
                break;
        }
    }

    private void setupRecyclerView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        binding.recyclerView.setLayoutManager(gridLayoutManager);

        adapter = new VideoCallAdapter(this, filteredVideos, this::showSetTimeDialog);
        binding.recyclerView.setAdapter(adapter);
    }

    private void loadVideos() {
        allVideos.add(new Video("V- BTS", R.drawable.v, R.raw.v, CATEGORY_STAR));
        allVideos.add(new Video("Jungkook", R.drawable.jungkook, R.raw.jungkook, CATEGORY_STAR));
        allVideos.add(new Video("Taylor Swift", R.drawable.taylorswift, R.raw.taylorswift, CATEGORY_STAR));
        allVideos.add(new Video("Jisoo", R.drawable.jisoo, R.raw.jisoo, CATEGORY_STAR));
        allVideos.add(new Video("Jennie", R.drawable.jennie, R.raw.jennie, CATEGORY_STAR));
        allVideos.add(new Video("Lisa", R.drawable.lisa, R.raw.lisa, CATEGORY_STAR));
        allVideos.add(new Video("Rose", R.drawable.rose, R.raw.rose, CATEGORY_STAR));
        allVideos.add(new Video("Adele", R.drawable.adele, R.raw.adele, CATEGORY_STAR));
        allVideos.add(new Video("Beonce'", R.drawable.beyonce, R.raw.beyonce, CATEGORY_STAR));
        allVideos.add(new Video("Tom Holland", R.drawable.tomholland, R.raw.tomholland, CATEGORY_STAR));
        allVideos.add(new Video("Cardi B", R.drawable.cardib, R.raw.cardib, CATEGORY_STAR));
        allVideos.add(new Video("Dwayne", R.drawable.dwayne, R.raw.dwayne, CATEGORY_STAR));
        allVideos.add(new Video("Johnny Depp", R.drawable.johnnydeep, R.raw.johnnydeep, CATEGORY_STAR));
        allVideos.add(new Video("Jennifer Lopez", R.drawable.jenniferlopez, R.raw.jenniferlopez, CATEGORY_STAR));

        allVideos.add(new Video("Messi", R.drawable.messi, R.raw.messi, CATEGORY_FOOTBALLER));
        allVideos.add(new Video("Neymar", R.drawable.neymar, R.raw.neymar, CATEGORY_FOOTBALLER));
        allVideos.add(new Video("Mbappe", R.drawable.mpabbe, R.raw.mpabbe, CATEGORY_FOOTBALLER));
        allVideos.add(new Video("Ronaldo", R.drawable.ronaldo, R.raw.ronaldo, CATEGORY_FOOTBALLER));

        allVideos.add(new Video("Spranlky 01", R.drawable.spranlky01, R.raw.spranlky01, CATEGORY_SPRANLKY));
        allVideos.add(new Video("Spranlky 02", R.drawable.spranlky02, R.raw.spranlky02, CATEGORY_SPRANLKY));
        allVideos.add(new Video("Spranlky 03", R.drawable.spranlky03, R.raw.spranlky03, CATEGORY_SPRANLKY));
        allVideos.add(new Video("Spranlky 04", R.drawable.spranlky04, R.raw.spranlky04, CATEGORY_SPRANLKY));
        allVideos.add(new Video("Spranlky 05", R.drawable.spranlky05, R.raw.spranlky05, CATEGORY_SPRANLKY));
        allVideos.add(new Video("Spranlky 06", R.drawable.spranlky06, R.raw.spranlky06, CATEGORY_SPRANLKY));
        allVideos.add(new Video("Spranlky 07", R.drawable.spranlky07, R.raw.spranlky07, CATEGORY_SPRANLKY));
        allVideos.add(new Video("Spranlky 08", R.drawable.spranlky08, R.raw.spranlky08, CATEGORY_SPRANLKY));
    }

    private void filterVideos(String category) {
        filteredVideos.clear();
        for (Video video : allVideos) {
            if (video.getCategory().equals(category)) {
                filteredVideos.add(video);
            }
        }
        adapter.notifyDataSetChanged();
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
                    child.setBackgroundColor(getResources().getColor(R.color.purple));
                } else {
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