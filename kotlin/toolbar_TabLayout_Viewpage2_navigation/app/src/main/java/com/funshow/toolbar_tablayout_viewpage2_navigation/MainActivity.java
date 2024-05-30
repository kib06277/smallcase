package com.funshow.toolbar_tablayout_viewpage2_navigation;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager2 viewpager;
    private BottomNavigationView navigation;
    private FragmentViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            init(); //初始化
            setToolbar(); //設定 toolbar
            setTabLayout(); //設定 TabLayout
            setViewPage(); //設定 viewpage 2
            setBottomNavigationView(); // 設定底部導覽
        } catch (Exception e) {
            Log.i("Error" , "e = " + e );
        }
    }

    //初始化
    private void init() {
        toolbar = findViewById(R.id.toolbar);
        tabLayout = findViewById(R.id.tabLayout);
        viewpager = findViewById(R.id.viewpager);
        navigation = findViewById(R.id.navigation);
    }

    //設定 toolbar
    private void setToolbar() {
        /**初始化Toolbar*/
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        /**將Toolbar綁定到setSupportActionBar*/
        setSupportActionBar(toolbar);
        /**設置大標題*/
        getSupportActionBar().setTitle("主標題");
        /**設置大標題字體顏色*/
        toolbar.setTitleTextColor(Color.WHITE);
        /**設置副標題*/
        toolbar.setSubtitle("副標題");
        /**設置副標題字體顏色*/
        toolbar.setSubtitleTextColor(Color.WHITE);
        /**設置標題前方的Icon圖樣*/
        toolbar.setNavigationIcon(getDrawable(R.mipmap.right_arrow));
        /**設置前方Icon與Title之距離為0(預設的很遠...)*/
        toolbar.setContentInsetStartWithNavigation(0);

        /**設置Icon圖樣的點擊事件*/
        toolbar.setNavigationOnClickListener(v->{
            Toast.makeText(this, "結束", Toast.LENGTH_SHORT).show();
        });
    }

    /**使選項內Icon與文字並存*/
    private CharSequence menuIconWithText(Drawable r, String title) {
        r.setBounds(0, 0, r.getIntrinsicWidth(), r.getIntrinsicHeight());
        SpannableString sb = new SpannableString("    " + title);
        ImageSpan imageSpan = new ImageSpan(r, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;
    }

    /**程式中新增MenuItem選項*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /**itemId為稍後判斷點擊事件要用的*/
        menu.add(0,0,0,"第一選項");
        menu.add(0,1,1,"第二選項");
        menu.add(0,2,2,"第三選項");
        menu.add(0,3,3,menuIconWithText(getDrawable(R.mipmap.eye_oepn),"帶ICON選項"));
        /**setShowAsAction預設為NEVER
         *MenuItem.SHOW_AS_ACTION_IF_ROOM 為如果Toolbar內還有空間，便會將這個item放到Toolbar內*/
        menu.add(0,4,4,"外部選項").setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        return super.onCreateOptionsMenu(menu);
    }

    /**此處為設置點擊事件*/
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        /**取得Item的ItemId，判斷點擊到哪個元件*/
        switch (item.getItemId()){
            case 0:
                Toast.makeText(this, "選一", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(this, "選二", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(this, "選三", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(this, "選帶ICON的Item", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(this, "選在外面的", Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    //設定 viewpage 2
    private void setViewPage() {
        //創建一個adapter
        adapter = new FragmentViewPagerAdapter(this);

        //將 adapter 放入 ViewPager
        viewpager.setAdapter(adapter);

        //指定一開始 ViewPager 顯示內容位置
        viewpager.setCurrentItem(0);

        //ViewPage 換頁監聽
        viewpager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                viewpager.setCurrentItem(position);
                tabLayout.getTabAt(position).select();
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

    //設定 TabLayout
    private void setTabLayout() {
        tabLayout.addTab(tabLayout.newTab().setText(R.string.onepage));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.twopage));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.threepage));

        //tabLayout 查詢事件
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager.setCurrentItem(tab.getPosition());
                navigation.getMenu().getItem(tab.getPosition()).setChecked(true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    // 設定底部導覽
    private void setBottomNavigationView() {
        navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                try {
                    int itemId = item.getItemId();
                    if (itemId == R.id.onepage) {
                        viewpager.setCurrentItem(0);
                        tabLayout.getTabAt(0).select();
                    } else if (itemId == R.id.twopage) {
                        viewpager.setCurrentItem(1);
                        tabLayout.getTabAt(1).select();
                    } else if (itemId == R.id.threepage) {
                        viewpager.setCurrentItem(2);
                        tabLayout.getTabAt(2).select();
                    }
                } catch (Exception e) {
                    Log.i("Error" , "e = " + e );
                }
                return true;
            }
        });
    }
}