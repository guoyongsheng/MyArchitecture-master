package com.mycompany.myarchitecture.homepage;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TableLayout;

import com.mycompany.myarchitecture.R;
import com.mycompany.myarchitecture.base.BaseActivity;
import com.mycompany.myarchitecture.util.ActivityUtils;

public class HomePageActivity extends BaseActivity
{
    private HomePageFragment homePageFragment; //fragment
    private FragmentManager fragmentManager;


    @Override
    public void initVariables()
    {
        fragmentManager = getSupportFragmentManager();
    }

    @Override
    public void initView(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_home_page);
        homePageFragment = (HomePageFragment) fragmentManager.findFragmentById(R.id.fragment);
        if(homePageFragment == null)
        {
            homePageFragment = HomePageFragment.newInstance();
            ActivityUtils.addFragmentToActivity(fragmentManager, R.id.fragment, homePageFragment);
        }
    }

    @Override
    public void initPresenter()
    {
        new HomePagePresenter(homePageFragment,this);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }


    private int getHeapSize(){
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        return activityManager.getMemoryClass();
    }

}
