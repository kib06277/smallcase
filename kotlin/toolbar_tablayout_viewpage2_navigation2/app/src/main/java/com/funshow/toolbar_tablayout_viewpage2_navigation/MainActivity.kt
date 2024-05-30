package com.funshow.toolbar_tablayout_viewpage2_navigation

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ImageSpan
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener


class MainActivity : AppCompatActivity() {
    private var toolbar: Toolbar? = null
    private var tabLayout: TabLayout? = null
    private var viewpager: ViewPager2? = null
    private var navigation: BottomNavigationView? = null
    private var adapter: FragmentViewPagerAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        try {
            init() //初始化
            setToolbar() //設定 toolbar
            setTabLayout() //設定 TabLayout
            setViewPage() //設定 viewpage 2
            setBottomNavigationView() // 設定底部導覽
        } catch (e: Exception) {
            Log.i("Error", "e = $e")
        }
    }

    //初始化
    private fun init() {
        toolbar = findViewById(R.id.toolbar)
        tabLayout = findViewById(R.id.tabLayout)
        viewpager = findViewById(R.id.viewpager)
        navigation = findViewById(R.id.navigation)
    }

    //設定 toolbar
    private fun setToolbar() {
        /**初始化Toolbar */
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        /**將Toolbar綁定到setSupportActionBar */
        setSupportActionBar(toolbar)
        /**設置大標題 */
        supportActionBar!!.title = "主標題"
        /**設置大標題字體顏色 */
        toolbar.setTitleTextColor(Color.WHITE)
        /**設置副標題 */
        toolbar.subtitle = "副標題"
        /**設置副標題字體顏色 */
        toolbar.setSubtitleTextColor(Color.WHITE)
        /**設置標題前方的Icon圖樣 */
        toolbar.navigationIcon = getDrawable(R.mipmap.right_arrow)
        /**設置前方Icon與Title之距離為0(預設的很遠...) */
        toolbar.contentInsetStartWithNavigation = 0
        /**設置Icon圖樣的點擊事件 */
        toolbar.setNavigationOnClickListener { v: View? ->
            Toast.makeText(
                this,
                "結束",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    /**使選項內Icon與文字並存 */
    private fun menuIconWithText(r: Drawable?, title: String): CharSequence {
        r!!.setBounds(0, 0, r.intrinsicWidth, r.intrinsicHeight)
        val sb = SpannableString("    $title")
        val imageSpan = ImageSpan(r, ImageSpan.ALIGN_BOTTOM)
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        return sb
    }

    /**程式中新增MenuItem選項 */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        /**itemId為稍後判斷點擊事件要用的 */
        menu.add(0, 0, 0, "第一選項")
        menu.add(0, 1, 1, "第二選項")
        menu.add(0, 2, 2, "第三選項")
        menu.add(0, 3, 3, menuIconWithText(getDrawable(R.mipmap.eye_oepn), "帶ICON選項"))
        /**setShowAsAction預設為NEVER
         * MenuItem.SHOW_AS_ACTION_IF_ROOM 為如果Toolbar內還有空間，便會將這個item放到Toolbar內 */
        menu.add(0, 4, 4, "外部選項").setShowAsAction(
            MenuItem.SHOW_AS_ACTION_IF_ROOM
        )
        return super.onCreateOptionsMenu(menu)
    }

    /**此處為設置點擊事件 */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        /**取得Item的ItemId，判斷點擊到哪個元件 */
        when (item.itemId) {
            0 -> Toast.makeText(this, "選一", Toast.LENGTH_SHORT).show()
            1 -> Toast.makeText(this, "選二", Toast.LENGTH_SHORT).show()
            2 -> Toast.makeText(this, "選三", Toast.LENGTH_SHORT).show()
            3 -> Toast.makeText(this, "選帶ICON的Item", Toast.LENGTH_SHORT).show()
            4 -> Toast.makeText(this, "選在外面的", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    //設定 viewpage 2
    private fun setViewPage() {
        //創建一個adapter
        adapter = FragmentViewPagerAdapter(this)

        //將 adapter 放入 ViewPager
        viewpager!!.adapter = adapter

        //指定一開始 ViewPager 顯示內容位置
        viewpager!!.currentItem = 0

        //ViewPage 換頁監聽
        viewpager!!.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                viewpager!!.currentItem = position
                tabLayout!!.getTabAt(position)!!.select()
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }
        })
    }

    //設定 TabLayout
    private fun setTabLayout() {
        tabLayout!!.addTab(tabLayout!!.newTab().setText(R.string.onepage))
        tabLayout!!.addTab(tabLayout!!.newTab().setText(R.string.twopage))
        tabLayout!!.addTab(tabLayout!!.newTab().setText(R.string.threepage))

        //tabLayout 查詢事件
        tabLayout!!.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewpager!!.currentItem = tab.position
                navigation!!.menu.getItem(tab.position).isChecked = true
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    // 設定底部導覽
    private fun setBottomNavigationView() {
        navigation!!.setOnItemSelectedListener { item ->
            try {
                val itemId = item.itemId
                if (itemId == R.id.onepage) {
                    viewpager!!.currentItem = 0
                    tabLayout!!.getTabAt(0)!!.select()
                } else if (itemId == R.id.twopage) {
                    viewpager!!.currentItem = 1
                    tabLayout!!.getTabAt(1)!!.select()
                } else if (itemId == R.id.threepage) {
                    viewpager!!.currentItem = 2
                    tabLayout!!.getTabAt(2)!!.select()
                }
            } catch (e: Exception) {
                Log.i("Error", "e = $e")
            }
            true
        }
    }
}