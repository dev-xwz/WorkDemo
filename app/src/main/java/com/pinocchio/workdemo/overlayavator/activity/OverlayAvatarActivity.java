package com.pinocchio.workdemo.overlayavator.activity;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pinocchio.workdemo.R;
import com.pinocchio.workdemo.overlayavator.adapter.OverlayAvatarAdapter;

/**
 * 实现头像叠加效果
 */
public class OverlayAvatarActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overlay_avator);
        initOverlayAvatarRecycleView();
    }

    private void initOverlayAvatarRecycleView() {
        RecyclerView overlayAvatarRecycleView = findViewById(R.id.rcv_overlay_avatar);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        linearLayoutManager.setStackFromEnd(false); //列表在底部开始展示
        linearLayoutManager.setReverseLayout(false); //列表反转
        overlayAvatarRecycleView.setLayoutManager(linearLayoutManager);

        OverlayAvatarAdapter overlayAvatarAdapter = new OverlayAvatarAdapter();
        overlayAvatarRecycleView.setAdapter(overlayAvatarAdapter);

        overlayAvatarRecycleView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                if (parent.getChildLayoutPosition(view) > 0) {
                    outRect.left = dp2px(-30);
                }
            }
        });
    }

    private int dp2px(int dps) {
        return Math.round(getResources().getDisplayMetrics().density * dps);
    }
}
