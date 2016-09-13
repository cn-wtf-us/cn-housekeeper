package cn.feicui.com.housekeeper.fragment;


import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import cn.feicui.com.housekeeper.R;
import cn.feicui.com.housekeeper.util.SdCardUtil;

/**
 * Created by Administrator on 2016/9/12 0012.
 */
public class FileOuterStoreFragment extends Fragment implements View.OnClickListener{

    private static final String TAG = "FileOuterStoreFragment";
    private EditText et_username;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.sp_view, null);
        et_username = (EditText) view.findViewById(R.id.et_username);
        Button btn_write = (Button) view.findViewById(R.id.btn_write);
        Button btn_read = (Button) view.findViewById(R.id.btn_read);
        btn_write.setOnClickListener(this);
        btn_read.setOnClickListener(this);
        context = getContext();
        return view ;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_write:
                //将edittext中的内容写入到sdcard中
                //先判断sdcard是否可写
                boolean writable = SdCardUtil.isExternalStorageWritable();
                if (writable) {
                    //写入数据
                    String userName = et_username.getText().toString().trim();
                    File directory = Environment.getExternalStorageDirectory();
                    File file = new File(directory,"text/un.txt");
                    if (file.exists()) {
                        Log.d(TAG, file.getName() + "已存在");
                    } else {
                        try {

                            File dir = file.getParentFile();
                            if (!dir.exists()) {
                                //如果父目录不存在，就创建
                                dir.mkdirs();
                            }
                            file.createNewFile();
                            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                            bw.write(userName);
                            bw.flush();
                            //真正开发在finally语句中关流。。。
                            bw.close();
                            Log.d(TAG, file.getName() + "data save success");
                        } catch (IOException e) {
                            Log.d(TAG, file.getName() + "创建失败");
                            e.printStackTrace();
                        }
                    }

                } else {
                    //sdcard不存在
                    Toast.makeText(context, "sdcard不存在", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_read:
                boolean readable = SdCardUtil.isExternalStorageReadable();
                if (readable) {
                    //读取数据
                    File directory = Environment.getExternalStorageDirectory();
                    File file = new File(directory,Environment.DIRECTORY_PICTURES);
                   File[] files = file.listFiles();
                    try {
                        BufferedInputStream bi = new BufferedInputStream(new FileInputStream(files[0]));
                        File file_img = new File(context.getFilesDir(), "cc.png");
                        BufferedOutputStream bo = new BufferedOutputStream(new FileOutputStream(file_img));

                        byte[] buf = new byte[1024];
                        int eof = 0;

                        while ((eof = bi.read(buf)) != -1) {
                            bo.write(buf,0,eof);
                        }
                        //真正开发在finally语句中关流。。。
                        bi.close();
                        bo.close();
                        Log.d(TAG, file.getName() + "data read success");
                        //得到bitmap
                      /*  Bitmap bitmap = BitmapFactory.decodeFile(file_img.getName());
                        //
                        iv.setimagebitmap(bitmap);
                        自己完善，输入图片名，显示对应的图片
                        */

                    } catch (IOException e) {
                        Log.d(TAG, file.getName() + "data read failed");
                        e.printStackTrace();
                    }
                }
                break;
        }

    }
}
