package com.mycompany.myarchitecture.homepage;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

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
