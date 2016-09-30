package com.mycompany.myarchitecture.homepage;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mycompany.myarchitecture.R;
import com.mycompany.myarchitecture.entity.ImageInfo;

import java.util.List;

/**
 * Created by lenovo on 2016/8/19.
 * 适配器
 */
public class HomePageAdapter extends RecyclerView.Adapter<HomePageAdapter.ViewHolder>
{
    private Context context; //上下文对象
    private List<ImageInfo> list; //数据集合
    private LayoutInflater inflater;
    private HomePagePresenter presenter;

    public HomePageAdapter(Context context, List<ImageInfo> list, HomePagePresenter presenter)
    {
        this.presenter = presenter;
        this.context = context;
        this.list = list;
        if(context != null)
        {
            inflater = LayoutInflater.from(context);
        }
    }

    @Override
    public HomePageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        if(inflater  == null)
        {
            return null;
        }
        View view = inflater.inflate(R.layout.item_homepage, parent, false);
        return new HomePageAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HomePageAdapter.ViewHolder viewHolder, int position)
    {
        if(viewHolder == null || list == null || list.size() <= position)
        {
            return;
        }

        ImageInfo imageInfo = list.get(position);
        String desc = imageInfo.getDesc();
        viewHolder.textDesc.setText(desc);

        //点击事件
        viewHolder.textDesc.setOnClickListener(new OnClickView());
    }

    @Override
    public int getItemCount()
    {
        if(list != null)
        {
            return list.size();
        }
        return 0;
    }


    //内部类---点击事件
    private class OnClickView implements View.OnClickListener{

        @Override
        public void onClick(View view)
        {
            if(view == null)
            {
                return;
            }

            int key = view.getId();
            switch (key)
            {
            case R.id.tv_desc:
                Intent intent = new Intent(context, HomePageActivity.class);
                context.startActivity(intent);
                break;


            default:
                break;
            }
        }
    }


    //静态内部类
    protected static final class ViewHolder extends RecyclerView.ViewHolder
    {
        public ViewHolder(View itemView)
        {
            super(itemView);
            textDesc = (TextView) itemView.findViewById(R.id.tv_desc);
        }


        private TextView textDesc; //描述
    }
}
