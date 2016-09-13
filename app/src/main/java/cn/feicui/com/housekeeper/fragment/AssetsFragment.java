package cn.feicui.com.housekeeper.fragment;


import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import cn.feicui.com.housekeeper.R;

/**
 * Created by Administrator on 2016/9/12 0012.
 */
public class AssetsFragment extends Fragment implements View.OnClickListener{

    private static final String TAG = "AssetsFragment";
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
                //因为构造方法私有了，不能直接new
                AssetManager assetManager = context.getAssets();
                String path = "db/commonnum.db";
                try {
                    InputStream inputStream = assetManager.open(path);

                    //包数据库文件保存到内置存储 file目录下
                    File filesDir = context.getFilesDir();
                    File file  = new File(filesDir,path);
                    File parentDir = file.getParentFile();
                    if (!parentDir.exists()) {
                        parentDir.mkdirs();
                    }
                    FileOutputStream fileOutputStream = context.openFileOutput(file.getName(), Context.MODE_APPEND);

                    BufferedOutputStream bufo = new BufferedOutputStream(fileOutputStream);
                    BufferedInputStream bufi = new BufferedInputStream(inputStream);

                    byte[] buf = new byte[1024];
                    int eof = 0;
                    while ((eof = bufi.read(buf)) != -1) {
                        bufo.write(buf,0,eof);
                        bufo.flush();
                    }
                    //关流，在finally中
                    bufi.close();
                    bufo.close();
                    Log.d(TAG, "save data success");
                } catch (IOException e) {
                    Log.d(TAG, "onClick: "+path+"路径错误");
                    e.printStackTrace();
                }

                break;

            case R.id.btn_read:

                break;

        }

    }
}
