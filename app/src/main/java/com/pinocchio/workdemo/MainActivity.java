package com.pinocchio.workdemo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;

import androidx.annotation.Nullable;

import com.pinocchio.workdemo.barchart.BarChartActivity;
import com.pinocchio.workdemo.overlayavator.activity.OverlayAvatarActivity;
import com.pinocchio.workdemo.trapezoidview.TrapezoidDrawable;
import com.pinocchio.workdemo.trapezoidview.TrapezoidView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        initOverlayAvatarView();
        initBarChartView();
        initTrapezoidView();
    }

    private void initOverlayAvatarView() {
        findViewById(R.id.ll_overlay_entrance).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, OverlayAvatarActivity.class));
            }
        });
    }

    private void initBarChartView() {
        findViewById(R.id.ll_bar_chart_entrance).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BarChartActivity.class));
            }
        });
    }

    private void initTrapezoidView() {
        TrapezoidView trapezoidView = findViewById(R.id.trapezoid_view);
        View view = findViewById(R.id.view);
        view.setBackground(new TrapezoidDrawable());
    }
}
