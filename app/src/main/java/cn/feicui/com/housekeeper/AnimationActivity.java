package cn.feicui.com.housekeeper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cn.feicui.com.housekeeper.fragment.ObjectAnimatorFragment;
import cn.feicui.com.housekeeper.fragment.ViewAnimFragment;

public class AnimationActivity extends AppCompatActivity {

    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        /*第三步
        这个包可以兼容2.0以上的系统
        * 如果fragment导入的是android.support.v4.app.Fragment包， 使用getSupportFragmentManager()
        *
        * //这个包只能在3.0以上的系统才有效果
        * 如果导入的是 android.app.Fragment 包，就用这个方法  getFragmentManager()
        * */
        /*FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        ObjectAnimatorFragment fragment = new ObjectAnimatorFragment();
        transaction.add(R.id.frame_layout,fragment);
        transaction.commit();*/

    }

    public void view(View view) {
        //简写方式
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (fragment != null) {
            transaction.remove(fragment);
        }
        fragment = new ViewAnimFragment();
        transaction.add(R.id.frame_layout, fragment).commit();
    }

    public void object(View view) {
        //简写方式
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (fragment != null) {
            transaction.remove(fragment);
        }
        fragment = new ObjectAnimatorFragment();
        transaction.add(R.id.frame_layout, fragment).commit();
    }

}
