package cn.feicui.com.houserkeeper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2016/9/19 0019.
 * 让以后我们写的所有的activity都继承自当前的
 * 也即是说，我们写的很多activity会有一些共性的方法或属性，面向对象思想向上抽取出一个分类
 */
public class BaseActivity extends AppCompatActivity {

    /**
     * 不带参数跳转
     *
     * @param clazz 被跳转的界面
     */
    public void startActivity(Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    /**
     * 带参数跳转
     *
     * @param clazz 被跳转的界面
     * @param bundle 传递的数据包裹
     */
    public void startActivityWithBundle(Class clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
