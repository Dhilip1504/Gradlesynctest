package com.example.gradlesynctest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager myViewPager;
    private TabLayout tabLayout;
    private TabAccessorAdapter tabAccessorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myViewPager = (ViewPager) findViewById(R.id.myViewPager);
        tabAccessorAdapter = new TabAccessorAdapter(getSupportFragmentManager(), 0);
        myViewPager.setAdapter(tabAccessorAdapter);

        tabLayout = (TabLayout) findViewById(R.id.myTabLayout);
        tabLayout.setupWithViewPager(myViewPager);

    }
}