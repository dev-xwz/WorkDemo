package com.pinocchio.workdemo.barchart;

import android.graphics.RectF;
import android.util.Log;

import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.mikephil.charting.renderer.BarChartRenderer;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;

public class CustomBarChartRenderer extends BarChartRenderer {

    private Transformer transformer;

    float groupSpace = 0.3f; //柱状图组之间的间距
    float barSpace = 0.1f;

    float compare = 0.5f;
    final float epsilon = 0.001f;

    public CustomBarChartRenderer(BarDataProvider chart, ChartAnimator animator, ViewPortHandler viewPortHandler) {
        super(chart, animator, viewPortHandler);
    }

    @Override
    protected void prepareBarHighlight(float x, float y1, float y2, float barWidthHalf,
                                       Transformer trans) {
        transformer = trans;
        //修改高亮背景的高度和宽度
        if (Math.abs(x - Math.floor(x) - compare) < epsilon) {
            super.prepareBarHighlight(x, mChart.getYChartMax(), y2, 3 * barWidthHalf + 2 * barSpace, trans);
        } else if (x - Math.floor(x) < compare) {
            super.prepareBarHighlight(x + 2 * barWidthHalf + barSpace, mChart.getYChartMax(), y2, 3 * barWidthHalf + 2 * barSpace, trans);
        } else {
            super.prepareBarHighlight(x - 2 * barWidthHalf - barSpace, mChart.getYChartMax(), y2, 3 * barWidthHalf + 2 * barSpace, trans);
        }
    }

    @Override
    protected void setHighlightDrawPos(Highlight high, RectF bar) {
        if (transformer != null) {
            //还原真实的点击数据
            float barWidthHalf = mChart.getBarData().getBarWidth() / 2f;
            float x = high.getX();
            RectF rectF = new RectF();
            rectF.set(x - barWidthHalf, high.getY(), x + barWidthHalf, 0f);

            transformer.rectToPixelPhase(rectF, mAnimator.getPhaseY());

            super.setHighlightDrawPos(high, rectF);

        } else {
            super.setHighlightDrawPos(high, bar);
        }
    }
}
