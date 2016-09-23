package cn.feicui.com.houserkeeper.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import cn.feicui.com.houserkeeper.fragment.ViewPagerFragment;


/**
 * Created by Administrator on 2016/9/19 0019.
 */
public class MyFirstViewPagerAdapter extends FragmentStatePagerAdapter {

    int[] imgs ;
    public void setImgs( int[] imgs) {
        this.imgs =imgs;
//        Random r = new Random();
//        for (int i = 0; i < count; i++) {
//            imgs[i] = Color.rgb(r.nextInt(256),r.nextInt(256),r.nextInt(256));
//        }
    }

    public MyFirstViewPagerAdapter(FragmentManager fm) {
        super(fm);
        //FragmentManager 管理fragment的添加，替换，移除等等。。。
    }

    @Override
    public Fragment getItem(int position) {
        //等价于listview的适配器的getcount方法
        ViewPagerFragment fragment = new ViewPagerFragment();
        fragment.initData(imgs[position]);
        return fragment;
    }

    @Override
    public int getCount() {
        return imgs.length;
    }
}
