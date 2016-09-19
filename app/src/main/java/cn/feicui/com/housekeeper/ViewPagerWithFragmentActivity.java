package cn.feicui.com.housekeeper;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import cn.feicui.com.housekeeper.adapter.MyFirstViewPagerAdapter;

public class ViewPagerWithFragmentActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_with_fragment);

        final ImageView[] imageViews = new ImageView[3];
        imageViews[0] = (ImageView) findViewById(R.id.tv1);
        imageViews[1] = (ImageView) findViewById(R.id.tv2);
        imageViews[2] = (ImageView) findViewById(R.id.tv3);

        ViewPager viewPager = (ViewPager) findViewById(R.id.vp);
        //创建适配器
        MyFirstViewPagerAdapter adapter = new MyFirstViewPagerAdapter(
                getSupportFragmentManager()//返回android.support.v4.app.FragmentManager对象
        );
        //初始化viewpager要显示多少个页面
        adapter.setCount(3);
        //绑定适配器
        viewPager.setAdapter(adapter);

        //给viewPager添加滑动监听的事件
        viewPager.addOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        super.onPageSelected(position);
                        //当前页面选中的时候，回调此方法
                        Toast.makeText(ViewPagerWithFragmentActivity.this, ""+position, Toast.LENGTH_SHORT).show();
                        //在设置选中之前，先把所有的变成不选中
                        for (int i = 0; i < imageViews.length; i++) {
                            imageViews[i].setBackgroundResource(R.drawable.indicator_unselected);
                        }
                        imageViews[position].setBackgroundResource(R.drawable.indicator_selected);
                    }
                }
        );
    }
}
