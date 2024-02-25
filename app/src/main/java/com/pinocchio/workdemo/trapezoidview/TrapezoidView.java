package com.pinocchio.workdemo.trapezoidview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.pinocchio.workdemo.R;

public class TrapezoidView extends View {

    private ShapeDrawable mTrapezoid;

    public TrapezoidView(Context context) {
        super(context);
    }

    public TrapezoidView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        path.lineTo(100.0f, 20.0f);
        path.lineTo(100.0f, 80.0f);
        path.lineTo(0.0f, 100.0f);
        path.lineTo(0.0f, 0.0f);
        path.close();
        mTrapezoid = new ShapeDrawable(new PathShape(path, 100.0f, 100.0f));

        mTrapezoid.getPaint().setStyle(Paint.Style.FILL);
        mTrapezoid.getPaint().setAntiAlias(true);
        mTrapezoid.getPaint().setDither(true);
        mTrapezoid.getPaint().setFilterBitmap(true);
        Shader shader = new LinearGradient(0, 0, mTrapezoid.getBounds().width(), 0, new int[]{R.color.purple, R.color.purple_200}, null, Shader.TileMode.CLAMP);
        mTrapezoid.getPaint().setShader(shader);
    }

    public TrapezoidView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mTrapezoid.setBounds(0, 0, w, h);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
        mTrapezoid.draw(canvas);
    }
}
