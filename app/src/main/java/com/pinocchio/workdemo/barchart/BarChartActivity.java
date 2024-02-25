package com.pinocchio.workdemo.barchart;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import androidx.annotation.ColorRes;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.pinocchio.workdemo.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

public class BarChartActivity extends Activity {

    private BarChart barChart;
    private YAxis leftAxis;             //左侧Y轴
    private YAxis rightAxis;            //右侧Y轴
    private XAxis xAxis;                //X轴
    private Legend legend;              //图例
    private LimitLine limitLine;        //限制线

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);

        barChart = findViewById(R.id.bar_chart);
        initBarChart(barChart);

        //处理数据是 记得判断每条柱状图对应的数据集合 长度是否一致
        LinkedHashMap<String, List<Float>> chartDataMap = new LinkedHashMap<>();
        List<String> xValues = new ArrayList<>();
        List<Float> yValue1 = new ArrayList<>();
        List<Float> yValue2 = new ArrayList<>();
        List<Float> yValue3 = new ArrayList<>();
        List<Integer> colors = Arrays.asList(
                ContextCompat.getColor(this, R.color.purple_200), ContextCompat.getColor(this, R.color.teal_200),
                ContextCompat.getColor(this, R.color.purple)
        );

        //换为网络请求数据
        xValues.add("2/19");
        xValues.add("2/20");
        xValues.add("2/21");
        xValues.add("2/22");
        xValues.add("2/23");
        xValues.add("2/24");
        xValues.add("2/25");


        yValue1.add((float) 44);
        yValue2.add((float) 19);
        yValue3.add((float) 40);

        yValue1.add((float) 64);
        yValue2.add((float) 9);
        yValue3.add((float) 40);

        yValue1.add((float) 84);
        yValue2.add((float) 49);
        yValue3.add((float) 40);

        yValue1.add((float) 44);
        yValue2.add((float) 19);
        yValue3.add((float) 40);

        yValue1.add((float) 64);
        yValue2.add((float) 9);
        yValue3.add((float) 40);

        yValue1.add((float) 84);
        yValue2.add((float) 49);
        yValue3.add((float) 40);

        yValue1.add((float) 44);
        yValue2.add((float) 19);
        yValue3.add((float) 64);

        chartDataMap.put("Legend1", yValue1);
        chartDataMap.put("Legend2", yValue2);
        chartDataMap.put("Legend3", yValue3);

        showBarChart(xValues, chartDataMap, colors);
        //请求数据变化时刷新
        barChart.invalidate();

        findViewById(R.id.ll_bar_chart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                barChart.highlightValues(null);
            }
        });
    }

    /**
     * 初始化BarChart图表
     */
    private void initBarChart(BarChart barChart) {
        //背景颜色
        barChart.setBackgroundColor(Color.WHITE);
        //不显示图表网格
        barChart.setDrawGridBackground(false);
        //背景阴影
        barChart.setRenderer(new CustomBarChartRenderer(barChart, barChart.getAnimator(), barChart.getViewPortHandler()));

        barChart.setDoubleTapToZoomEnabled(false);
        //支持左右滑动
        barChart.setDragEnabled(true);
        //X轴或Y轴禁止缩放
        barChart.setScaleXEnabled(false);
        barChart.setScaleYEnabled(false);
        barChart.setScaleEnabled(false);
        //不显示边框
        barChart.setDrawBorders(false);

        //不显示右下角描述内容
        Description description = new Description();
        description.setEnabled(false);
        barChart.setDescription(description);

        //XY轴的设置
        //X轴设置显示位置在底部
        xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);

        leftAxis = barChart.getAxisLeft();
        rightAxis = barChart.getAxisRight();
        //不绘制 Y轴线条
        rightAxis.setDrawAxisLine(false);
        //不显示X轴网格线
        xAxis.setDrawGridLines(false);
        leftAxis.setDrawGridLines(false);
        rightAxis.setDrawGridLines(false);


        //图例设置
        legend = barChart.getLegend();
        legend.setForm(Legend.LegendForm.SQUARE);
        legend.setTextSize(11f);
        //显示位置
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        //是否绘制在图表里面
        legend.setDrawInside(false);
    }


    /**
     * 柱状图始化设置 一个BarDataSet 代表一列柱状图
     *
     * @param barDataSet 柱状图
     * @param color      柱状图颜色
     */
    private void initBarDataSet(BarDataSet barDataSet, int color) {
        barDataSet.setColor(color);
        barDataSet.setFormLineWidth(1f);
        barDataSet.setFormSize(15.f);

    }

    /**
     * @param xValues   X轴的值
     * @param dataLists LinkedHashMap<String, List<Float>>
     *                  key对应柱状图名字  List<Float> 对应每类柱状图的Y值
     */
    public void showBarChart(final List<String> xValues, LinkedHashMap<String, List<Float>> dataLists,
                             @ColorRes List<Integer> colors) {

        List<IBarDataSet> dataSets = new ArrayList<>();
        int currentPosition = 0;//用于柱状图颜色集合的index

        for (LinkedHashMap.Entry<String, List<Float>> entry : dataLists.entrySet()) {
            String name = entry.getKey();
            List<Float> yValueList = entry.getValue();

            List<BarEntry> entries = new ArrayList<>();

            for (int i = 0; i < yValueList.size(); i++) {
                entries.add(new BarEntry(i, yValueList.get(i)));
            }
            // 每一个BarDataSet代表一类柱状图
            BarDataSet barDataSet = new BarDataSet(entries, name);
            initBarDataSet(barDataSet, colors.get(currentPosition));
            dataSets.add(barDataSet);

            currentPosition++;
        }

        //X轴自定义值
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return xValues.get((int) Math.abs(value) % xValues.size());
            }
        });
        xAxis.setLabelRotationAngle(-60);//x轴文字斜体显示
        rightAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return "";
            }
        });

        BarData data = new BarData(dataSets);
        int barAmount = dataLists.size(); //需要显示柱状图的类别 数量
        //计算公式：图组间隔+(柱状图间隔*柱状图个数)+子柱状图宽度*每组柱状图个数=1f
        float groupSpace = 0.3f; //柱状图组之间的间距
        float barSpace = 0.1f;
        float barWidth = (1f - groupSpace - 3 * barSpace) / barAmount;

        //设置柱状图宽度
        data.setBarWidth(barWidth);
        //(起始点、柱状图组间距、柱状图之间间距)
        data.groupBars(0f, groupSpace, barSpace);
        barChart.setData(data);
        //设置柱状图一页显示的范围(0-5)
        barChart.setVisibleXRange(0, 4.5f);
        //解决x轴标签斜体显示不全的问题
        barChart.notifyDataSetChanged();

        xAxis.setAxisMinimum(0f);
        xAxis.setAxisMaximum(xValues.size());
        //将X轴的值显示在中央
        xAxis.setCenterAxisLabels(true);

        CustomMarkerView customMarkerView = new CustomMarkerView(this, dataSets);
        //必须设置，否则MarkerView会超出图表
        customMarkerView.setChartView(barChart);
        barChart.setMarker(customMarkerView);
    }

    private void showPopupWindow(View view) {
        // 弹窗需要的视图对象,利用布局逆向生成视图
        View v = LayoutInflater.from(this).inflate(R.layout.popup_layout, null);
        // 1. 实例化对象
        // 参数1：用在弹窗中的View;
        // 参数2、3:弹窗的宽高
        // 参数4：focusable 能否获取焦点
        PopupWindow window = new PopupWindow(v, 300, 300, true);
        // 2. 设置(背景,动画)
        // 设备背景
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置能响应外部的点击事件
        window.setOutsideTouchable(true);
        // 设置弹窗能响应点击事件
        window.setTouchable(true);
        // 3. 显示
        // 参数1：anchor锚点
        // 参数2、3：相对于锚在x,y上的偏移量
        window.showAsDropDown(view, view.getWidth() / 2, -(view.getHeight() / 2));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        barChart.highlightValues(null);
    }
}
