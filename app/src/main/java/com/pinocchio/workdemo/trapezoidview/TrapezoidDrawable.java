package com.pinocchio.workdemo.trapezoidview;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.pinocchio.workdemo.R;

public class TrapezoidDrawable extends Drawable {
    @Override
    public void draw(@NonNull Canvas canvas) {
        int width = getBounds().width();
        int height = getBounds().height();

        Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        path.lineTo(width, (float) height / 5);
        path.lineTo(width, (float) height * 4 / 5);
        path.lineTo(0.0f, height);
        path.lineTo(0.0f, 0.0f);
        path.close();

        Paint paint = new Paint();
        Shader shader = new LinearGradient(0, 0, width, 0, new int[]{R.color.teal_700, R.color.teal_200}, null, Shader.TileMode.CLAMP);
        paint.setAntiAlias(true);
        paint.setShader(shader);

        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
        canvas.drawPath(path, paint);
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSPARENT;
    }
}
