package cn.feicui.com.housekeeper.adapter;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.Random;

import cn.feicui.com.housekeeper.fragment.ViewPagerFragment;

/**
 * Created by Administrator on 2016/9/19 0019.
 */
public class MyFirstViewPagerAdapter extends FragmentStatePagerAdapter {

    private int count ;
    int[] colors ;
    public void setCount(int count) {
        this.count = count;
        colors = new int[count];
        Random r = new Random();
        for (int i = 0; i < count; i++) {
            colors[i] = Color.rgb(r.nextInt(256),r.nextInt(256),r.nextInt(256));
        }
    }

    public MyFirstViewPagerAdapter(FragmentManager fm) {
        super(fm);
        //FragmentManager 管理fragment的添加，替换，移除等等。。。
    }

    @Override
    public Fragment getItem(int position) {
        //等价于listview的适配器的getcount方法
        ViewPagerFragment fragment = new ViewPagerFragment();
        fragment.initData(position+"",colors[position]);
        return fragment;
    }

    @Override
    public int getCount() {
        return count;
    }
}
