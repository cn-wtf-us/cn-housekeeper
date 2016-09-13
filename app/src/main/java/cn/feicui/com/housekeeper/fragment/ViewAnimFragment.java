package cn.feicui.com.housekeeper.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import cn.feicui.com.housekeeper.R;

/**
 * Created by Administrator on 2016/9/8 0008.
 */
public class ViewAnimFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_anim_view, null);
        final ImageView iv_hzw = (ImageView) view.findViewById(R.id.iv_hzw);

        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.set_anim);

        //给动画设置一个监听
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Toast.makeText(getContext(), "onAnimationStart", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //跳转界面动作
                Toast.makeText(getContext(), "onAnimationEnd", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Toast.makeText(getContext(), "onAnimationRepeat", Toast.LENGTH_SHORT).show();
            }
        });

        iv_hzw.startAnimation(animation);

//        iv_hzw.setAnimation(animation);
//        animation.start();
        return view;
    }
}
