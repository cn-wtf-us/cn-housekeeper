package cn.feicui.com.housekeeper.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.feicui.com.housekeeper.R;

/**
 * Created by Administrator on 2016/9/8 0008.
 */



/*把fragment当成一个动态显示UI的view*/
    /*第二步*/
public class SPFragment extends Fragment implements View.OnClickListener {

    private EditText et_username;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.sp_view, null);
        et_username = (EditText) view.findViewById(R.id.et_username);
        Button btn_write = (Button) view.findViewById(R.id.btn_write);
        Button btn_read = (Button) view.findViewById(R.id.btn_read);
        btn_write.setOnClickListener(this);
        btn_read.setOnClickListener(this);
        return view ;
    }


    @Override
    public void onClick(View v) {
        //根据被点击按钮的id属性来区分是哪个按钮被点击
        switch (v.getId()) {
            case R.id.btn_write:
                //用户输入的用户名
                String username = et_username.getText().toString().trim();
                //判断输入的数据是否为空
                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(getContext(), "username  can not be null", Toast.LENGTH_SHORT).show();
                } else {
                    //将用户名存到sdcard中
                    //用activity的名字作为SharedPreferences的存储数据的文件名
                    SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString(getString(R.string.preference_username_key), username);
                    editor.apply();
                    Toast.makeText(getContext(), "username write successful", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_read:
                SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                String un = sharedPref.getString(getString(R.string.preference_username_key), "default value");
                et_username.setText(un);
                Toast.makeText(getContext(), "username read successful", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
