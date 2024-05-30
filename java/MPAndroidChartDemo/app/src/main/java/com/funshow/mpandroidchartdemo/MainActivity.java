package com.funshow.mpandroidchartdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

//使用 MPAndroidChartDemo 繪圖
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pieChart();//圓餅圖
        barchart(); //條形圖
        linechart(); //折線圖
    }

    // 圓餅圖
    private void pieChart() {
        PieChart pc = findViewById(R.id.piechart); // 關聯
        ArrayList<PieEntry> data = new ArrayList<>(); // 存放 PieEntry 的容器
        data.add(new PieEntry(36.7f, "有及格")); // 添加數據
        data.add(new PieEntry(13.3f, "沒及格")); // 添加數據

        // 設置顏色
        ArrayList<Integer> colors = new ArrayList<>(); // 存放顏色的容器
        colors.add(Color.parseColor("#1abc9c")); // 添加顏色
        colors.add(Color.parseColor("#ffa502")); // 添加顏色

        PieDataSet pieDataSet = new PieDataSet(data, ""); // 存放數據和顏色的容器
        pieDataSet.setColors(colors);

        PieData pieData = new PieData(pieDataSet); // 將數據傳給 PieData
        pc.setData(pieData); // 將資料指定給圓餅圖
        pc.invalidate(); // 刷新圖表
    }

    // 條形圖
    private void barchart() {
        BarChart barChart = findViewById(R.id.barchart); // 關聯

        ArrayList<BarEntry> barEntryList = new ArrayList<>(); // 存放 BarEntry 數值得容器
        barEntryList.add(new BarEntry(1f, 11f)); // 添加數據
        barEntryList.add(new BarEntry(2f, 36f)); // 添加數據
        barEntryList.add(new BarEntry(3f, 23f)); // 添加數據

        // 設置顏色
        ArrayList<Integer> colors = new ArrayList<>(); // 存放顏色的容器
        colors.add(Color.parseColor("#FFBB86FC")); // 添加顏色
        colors.add(Color.parseColor("#FFFF8600")); // 添加顏色
        colors.add(Color.parseColor("#FF0086FC")); // 添加顏色

        BarDataSet barDataSet = new BarDataSet(barEntryList, ""); // 存放數據和顏色的容器
        barDataSet.setColors(colors);

        BarData barData = new BarData(barDataSet); // 將數據傳給 BarData
        barChart.setData(barData); // 將資料指定給條形圖
        barChart.invalidate(); // 刷新圖表
    }

    // 折線圖
    private void linechart() {
        LineChart lc = findViewById(R.id.linechart); // 關聯
        lc.setDrawBorders(true); // 設置邊框

        // 設置數據
        ArrayList<Entry> entries = new ArrayList<>(); // 建立型別 Entry 容器
        entries.add(new Entry(1f, 20f)); // 添加數據
        entries.add(new Entry(2f, 40f)); // 添加數據
        entries.add(new Entry(3f, 30f)); // 添加數據
        entries.add(new Entry(4f, 60f)); // 添加數據
        entries.add(new Entry(5f, 20f)); // 添加數據

        LineDataSet lineDataSet = new LineDataSet(entries, "溫度"); // 將數據傳給變數
        lineDataSet.setColor(Color.parseColor("#FFBB86FC")); // 設置折線圖 線的顏色

        LineData data = new LineData(lineDataSet); // 將數據傳給 LineData
        lc.setData(data); // 將資料指定給折線圖
        lc.invalidate(); // 刷新圖表
    }
}