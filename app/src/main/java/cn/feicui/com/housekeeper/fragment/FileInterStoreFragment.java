package cn.feicui.com.housekeeper.fragment;

import android.content.Context;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import cn.feicui.com.housekeeper.R;

/**
 * Created by Administrator on 2016/9/8 0008.
 *
/*把fragment当成一个动态显示UI的view*/
/*第二步*/
public class FileInterStoreFragment extends Fragment implements View.OnClickListener {

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
        Context context = getContext();
        final String FILE_USERNAME = "file_username";
        switch (v.getId()) {
            case R.id.btn_write:
                //用户输入的用户名
                String username = et_username.getText().toString().trim();
                //判断输入的数据是否为空
                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(context, "username  can not be null", Toast.LENGTH_SHORT).show();
                } else {
                    //将用户名存到内置存储
                    File file = new File(context.getFilesDir(), FILE_USERNAME);
                    FileOutputStream outputStream = null;
                    String name = "";
                    try {
                        //获取文件名
                        name = file.getName();
                        /*
                        * Context.MODE_APPEND 是在原来的基础上进行追加数据
                        * */
                        outputStream = context.openFileOutput(name, Context.MODE_APPEND);
                        outputStream.write(username.getBytes());
                        Toast.makeText(context, "username write successful", Toast.LENGTH_SHORT).show();
                    } catch (FileNotFoundException e) {
                        Toast.makeText(context, name+"文件找不到", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    } catch (IOException e) {
                        Toast.makeText(context, name+"文件写入数据失败", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }finally {
                        if (outputStream!=null) {
                            try {
                                //释放资源
                                outputStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }
                break;
            case R.id.btn_read:
                //将用户名存到内置存储
                File file = new File(context.getFilesDir(), FILE_USERNAME);
                FileInputStream fileInputStream = null;
                String file_name = null;
                String read_username = "";
                try {
                    file_name = file.getName();
                    fileInputStream = context.openFileInput(file_name);
                    byte[] buf = new byte[1024];
                    int eof = 0;
                    while ((eof=fileInputStream.read(buf)) !=-1) {
                        read_username += new String(buf, 0, buf.length);
                    }
                    et_username.setText(read_username);
                    Toast.makeText(context, "username read successful", Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    Toast.makeText(context, file_name+"文件找不到", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                } catch (IOException e) {
                    Toast.makeText(context, file_name+"文件读取数据失败", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                finally {
                    if (fileInputStream!=null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
        }
    }
}
