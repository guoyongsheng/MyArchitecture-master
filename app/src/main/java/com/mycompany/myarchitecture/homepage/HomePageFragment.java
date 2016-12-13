package com.mycompany.myarchitecture.homepage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mycompany.myarchitecture.R;
import com.mycompany.myarchitecture.base.BaseFragment;
import com.mycompany.myarchitecture.entity.ImageInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/8/19.
 * <p>
 * fragment呈现UI
 */
public class HomePageFragment extends BaseFragment implements HomePageContract.View<HomePagePresenter> {
    private int page = 1; //当前页数
    private HomePagePresenter presenter;
    private RecyclerView recyclerView;
    private HomePageAdapter adapter;
    private List<ImageInfo> list = new ArrayList<>();

    public static HomePageFragment newInstance() {
        return new HomePageFragment();
    }


    @Override
    public void setPresenter(HomePagePresenter presenter) {
        this.presenter = presenter; //view层持有presenter层的引用
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (inflater == null) {
            return null;
        }

        View view = inflater.inflate(R.layout.fragment_homepage, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new HomePageAdapter(getActivity(), list, presenter);
        recyclerView.setAdapter(adapter);
        login("", "");
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void showHomePageData(List<ImageInfo> msg) {
        list.addAll(msg);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void showErrorMessage(String errorMsg) {
        Toast.makeText(getActivity(), errorMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void login(String name, String password) {
        presenter.login(name, password);
    }

    //获取当前页数
    public int getPage() {
        return page;
    }
}
