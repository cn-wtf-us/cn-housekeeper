package cn.feicui.com.housekeeper.fragment;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.feicui.com.housekeeper.R;

/**
 * Created by Administrator on 2016/9/8 0008.
 */



/*把fragment当成一个动态显示UI的view*/
    /*第二步*/
public class DrawableAnimatorFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.drawable_animator_view, null);

        View v = view.findViewById(R.id.iv_hzw);
        //当使用到的方式是子类特有的，需要强转成子类对象的引用
        //比如 settext方法就是Textview特有的

//        TextView iv_hzw = (TextView) v;
//        iv_hzw.setText("我是textview");
//        if (v instanceof ImageView) {
//            ImageView iv_hzw = (ImageView) v;
//        } else if (v instanceof TextView) {
//            TextView iv_hzw = (TextView) v;
//        }

        //通过代码动态改变帧动画
        v.setBackgroundResource(R.drawable.drawable_animation);
        final AnimationDrawable animation = (AnimationDrawable) v.getBackground();
        view.findViewById(R.id.btn_start).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        animation.start();
                    }
                }
        );

        return view ;
    }
    //反射写法只在activity界面有效
//    public void start(View view) {
//
//    }


}
