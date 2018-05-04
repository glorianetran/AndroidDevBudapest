package com.example.meda.weatherinfo;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.meda.weatherinfo.data.City;
import com.example.meda.weatherinfo.fragment.DetailsFragment;
import com.example.meda.weatherinfo.fragment.MainInfoFragment;


public class DetailsActivity extends AppCompatActivity {

    public static final String CITY_NAME = "KEY_CITY_NAME";

    //number of pages
    private static final int NUM_PAGES = 2;

    //pager widget, handles animations and swipes
    private ViewPager viewPager;

    //provides pages to the view pager
    private PagerAdapter viewPagerAdapter;

    private Intent intentDetails;

    public String cityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);

        intentDetails = getIntent();
        cityName = ((City)
                intentDetails.getSerializableExtra(CITY_NAME)).getCity();

        Toolbar mainToolbar = (Toolbar) findViewById(R.id.toolbar);
        mainToolbar.setTitle(cityName);
        setSupportActionBar(mainToolbar);

    }


    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }


    //pager adapter that represents 2 screen slide fragments
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter{
        public ScreenSlidePagerAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if(position == 0){
                return MainInfoFragment.newInstance(cityName);
            }
                return DetailsFragment.newInstance(cityName);
        }


        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

}
