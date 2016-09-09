package cn.feicui.com.housekeeper.fragment;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import cn.feicui.com.housekeeper.R;

/**
 * Created by Administrator on 2016/9/8 0008.
 */



/*把fragment当成一个动态显示UI的view*/
    /*第二步*/
public class ObjectAnimatorFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.object_animator_view, null);
        ImageView iv_hzw = (ImageView) view.findViewById(R.id.iv_hzw);
//        ImageView iv_hzw = (ImageView) findViewById(R.id.iv_hzw);
//
        //3.0以上的系统才能看到效果

        AnimatorSet animator = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.animator_alpha);
        animator.setTarget(iv_hzw);
        animator.start();
        return view ;
    }


}
