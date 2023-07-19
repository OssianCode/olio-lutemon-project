package app.main.lutemon3033v2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

public class TabMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_main);

        System.out.println("TabMainActivity onCreate ");

        TabLayout tabLayout = findViewById(R.id.tabArea);
        ViewPager2 fragmentArea = findViewById(R.id.fragmentArea);
        TabPagerAdapter tabPagerAdapter = new TabPagerAdapter(this);
        fragmentArea.setAdapter(tabPagerAdapter);


        // Find if any data is passed from fragments
        String tabName = "other";
        int tabNbr = -1;
        Bundle tabSelected = getIntent().getExtras();
        if (tabSelected != null) {
            tabName = tabSelected.getString("tabName");
            tabNbr = tabSelected.getInt("tabNbr");

            System.out.println("TabMainActivity name nbr: " + tabName + " " + tabNbr);

            //Set wanted fragment
            fragmentArea.setCurrentItem(tabNbr);
            tabLayout.getTabAt(tabNbr).select();

        }



        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                fragmentArea.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        fragmentArea.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });

    }
}