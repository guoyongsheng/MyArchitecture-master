package com.mycompany.myarchitecture.homepage;

import android.content.Context;
import android.widget.Toast;

import com.mycompany.myarchitecture.CallBackListener;
import com.mycompany.myarchitecture.base.BaseApplication;
import com.mycompany.myarchitecture.entity.ImageInfo;
import com.mycompany.myarchitecture.entity.UserInfo;
import com.mycompany.myarchitecture.modleimpl.HomePageModleImpl;
import com.mycompany.myarchitecture.util.MD5Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2016/8/19.
 *
 * p层的实现
 * p层同时持有view层(fragment)和modle的引用
 */
public class HomePagePresenter implements HomePageContract.Presenter
{
    private final static int PAGE_SIZE = 10;
    private HomePageFragment fragment; //fragment
    private HomePageModleImpl modle; //modle
    private Context context; //上下文对象

    public HomePagePresenter(HomePageFragment fragment, Context context)
    {
        this.context = context;
        this.fragment = fragment;  //presenter层持有view层的引用
        modle = new HomePageModleImpl(); //presenter层持有modle层的引用

        //让view层持有presenter层的引用
        fragment.setPresenter(this);
    }

    @Override
    public void start()
    {
       // loadHomePageData();

        List<ImageInfo> list = new ArrayList<>();
        for(int i = 0; i < 10; i++)
        {
            ImageInfo imageInfo = new ImageInfo();
            imageInfo.setDesc("测试mvp架构  " + i);
            list.add(imageInfo);
        }
        fragment.showHomePageData(list);
    }

    @Override
    public void loadHomePageData()
    {
        int page = fragment.getPage();
        modle.getHomePageDataByVolley(context, page, PAGE_SIZE, new CallBackListener<List<ImageInfo>>()
        {
            @Override
            public void onSuccess(List<ImageInfo> msg)
            {
                fragment.showHomePageData(msg);
            }

            @Override
            public void onFailed(String errorMsg)
            {
                fragment.showErrorMessage(errorMsg);
            }
        });
    }

    @Override
    public void login(String name, String password)
    {
        Map<String,String> map = new HashMap<>();
        map.put("telephone", MD5Utils.encode("12345678911"));
        map.put("password", MD5Utils.encode("111111"));

        modle.login(map, new CallBackListener<UserInfo>()
        {
            @Override
            public void onSuccess(UserInfo msg)
            {
                Toast.makeText(BaseApplication.getIntance(),"成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailed(String errorMsg)
            {
                Toast.makeText(BaseApplication.getIntance(), "失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
