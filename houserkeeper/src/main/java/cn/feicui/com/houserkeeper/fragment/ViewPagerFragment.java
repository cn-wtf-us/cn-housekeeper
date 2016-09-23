package cn.feicui.com.houserkeeper.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import cn.feicui.com.houserkeeper.R;

/**
 * Created by Administrator on 2016/9/22 0022.
 */
public class ViewPagerFragment extends Fragment {

    private int resImg;
    public TextView jump;
    public View view;

    public void initData(int resImg)
    {
        this.resImg = resImg;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_view_pager,null);
        ImageView iv = (ImageView) view.findViewById(R.id.iv);
        iv.setImageResource(resImg);
        return view;
    }

    public void showJump() {
        if (jump!=null) {
            jump.setVisibility(View.VISIBLE);
        }
    }
}
