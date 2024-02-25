package com.pinocchio.workdemo.barchart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.MPPointF;
import com.pinocchio.workdemo.R;

import java.util.List;
import java.util.Locale;

@SuppressLint("ViewConstructor")
public class CustomMarkerView extends MarkerView {

    private TextView tvOne;
    private TextView tvTwo;
    private TextView tvThree;
    private final MPPointF mOffset2 = new MPPointF();
    private final List<IBarDataSet> mDataSets;


    /**
     * Constructor. Sets up the MarkerView with a custom layout resource.
     */
    public CustomMarkerView(Context context, List<IBarDataSet> dataSets) {
        super(context, R.layout.popup_layout);
        initView();
        mDataSets = dataSets;
    }

    private void initView() {
        tvOne = findViewById(R.id.tv_one);
        tvTwo = findViewById(R.id.tv_two);
        tvThree = findViewById(R.id.tv_three);
    }

    @Override
    public MPPointF getOffsetForDrawingAtPoint(float posX, float posY) {
        MPPointF offset = getOffset();
        mOffset2.x = offset.x;
        mOffset2.y = offset.y;
        //获取图表对象，如果没有调用setChartView方法，该chart就为空
        Chart chart = getChartView();
        //获取 MarkerView 的宽和高
        float width = getWidth();
        float height = getHeight();

        //计算最终位置
        if (posX + mOffset2.x < 0) {
            mOffset2.x = -posX;
        } else if (chart != null && posX + width + mOffset2.x > chart.getWidth()) {
            mOffset2.x = chart.getWidth() - posX - width;
        } else {
            mOffset2.x += width/2;
        }

        if (posY + mOffset2.y < 0) {
            mOffset2.y = -posY;
        } else if (chart != null && posY + height + mOffset2.y > chart.getHeight()) {
            mOffset2.y = chart.getHeight() - posY - height;
        }

        return mOffset2;
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        //更新显示数据，由于是柱状图，所以可以强制转换为BarEntry
        int index = (int) Math.floor(e.getX());
        BarEntry value1 = mDataSets.get(0).getEntryForIndex(index);
        BarEntry value2 = mDataSets.get(1).getEntryForIndex(index);
        BarEntry value3 = mDataSets.get(2).getEntryForIndex(index);
        tvOne.setText(String.format(Locale.US, "Legend1: %.2f", value1.getY()));
        tvTwo.setText(String.format(Locale.US, "Legend2: %.2f", value2.getY()));
        tvThree.setText(String.format(Locale.US, "Legend3: %.2f", value3.getY()));
        super.refreshContent(e, highlight);
    }

    private MPPointF mOffset;

    @Override
    public MPPointF getOffset() {
        //设置MarkerView的偏移量，就是提示框显示的位置
        if (mOffset == null) {
            // center the marker horizontally and vertically
            mOffset = new MPPointF(-((float) getWidth() / 2), -getHeight());
        }

        return mOffset;
    }
}
