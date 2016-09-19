package cn.feicui.com.housekeeper.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.feicui.com.housekeeper.R;

/**
 * Created by Administrator on 2016/9/19 0019.
 */
public class ViewPagerFragment extends Fragment {

    private String text;
    private int resColor;

    public void initData(String text, int resColor)
    {

        this.text = text;
        this.resColor = resColor;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_pager,null);
        TextView tv = (TextView) view.findViewById(R.id.tv_view_pager);
        tv.setText(text);
        tv.setBackgroundColor(resColor);
        return view;
    }
}
