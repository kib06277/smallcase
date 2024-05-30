package com.funshow.mpandroidchartdemo

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry

//使用 MPAndroidChartDemo 繪圖
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pieChart() //圓餅圖
        barchart() //條形圖
        linechart() //折線圖
    }

    //圓餅圖
    fun pieChart() {
        val pc = findViewById<PieChart>(R.id.piechart) //關聯
        val data = ArrayList<PieEntry>()   //存放 PieEntry 的容器
        data.add(PieEntry(36.7f, "有及格")) //添加數據
        data.add(PieEntry(13.3f, "沒及格")) //添加數據

        //設置顏色
        val color = ArrayList<Int>() //存放顏色的容器
        color.add(Color.parseColor("#1abc9c"))//添加數據
        color.add(Color.parseColor("#ffa502"))//添加數據
        val pieDataSet = PieDataSet(data, "") //存放數據和顏色的容器
        pieDataSet.colors = color

        val pieData = PieData(pieDataSet) //將數據傳給 PieData
        pc.data = pieData //將資料指定給圓餅圖
    }

    //條形圖
    fun barchart() {
        val barChart = findViewById<BarChart>(R.id.barchart) //關聯

        val barEntryList = ArrayList<BarEntry>() //存放 BarEntry 數值得容器
        barEntryList.add(BarEntry(1f, 11f)) //添加數據
        barEntryList.add(BarEntry(2f, 36f)) //添加數據
        barEntryList.add(BarEntry(3f, 23f)) //添加數據

        //設置顏色
        val colors = ArrayList<Int>() //存放顏色的容器
        colors.add(Color.parseColor("#FFBB86FC")) //添加數據
        colors.add(Color.parseColor("#FFFF8600")) //添加數據
        colors.add(Color.parseColor("#FF0086FC")) //添加數據

        val barDataSet = BarDataSet(barEntryList, "") //存放數據和顏色的容器
        barDataSet.colors = colors

        val barData = BarData(barDataSet) //將數據傳給 BarData
        barChart.data = barData //將資料指定給折線圖
    }

    //折線圖
    fun linechart() {
        val lc = findViewById<LineChart>(R.id.linechart) //關聯
        lc.setDrawBorders(true) //設置邊框

        //設置數據
        val entries = ArrayList<Entry>() //建立型別 Entry 容器
        entries.add(Entry(1f, 20f)) //添加數據
        entries.add(Entry(2f, 40f)) //添加數據
        entries.add(Entry(3f, 30f)) //添加數據
        entries.add(Entry(4f, 60f)) //添加數據
        entries.add(Entry(5f, 20f)) //添加數據

        val lineDataSet = LineDataSet(entries, "溫度") //將數據傳給變數
        lineDataSet.setColor(Color.parseColor("#FFBB86FC")) //設置折線圖 線的顏色

        val data = LineData(lineDataSet) //將數據傳給 LineData
        lc.data = data //將資料指定給折線圖
    }
}