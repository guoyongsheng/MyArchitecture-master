package com.mycompany.myarchitecture.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by lenovo on 2016/8/19.
 * 工具类 把fragment添加到activity中
 */
public class ActivityUtils {
    public static void addFragmentToActivity(FragmentManager fragmentManager, int fragmentId, Fragment fragment) {
        if (fragmentManager == null || fragment == null) {
            return;
        }

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(fragmentId, fragment);
        fragmentTransaction.commit();
    }
}
