package cn.feicui.com.housekeeper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cn.feicui.com.housekeeper.fragment.AssetsFragment;
import cn.feicui.com.housekeeper.fragment.FileInterStoreFragment;
import cn.feicui.com.housekeeper.fragment.FileOuterStoreFragment;
import cn.feicui.com.housekeeper.fragment.SPFragment;

public class WriterReadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writer_read);
    }
    public void sp(View view) {
        //简写方式
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, new SPFragment()).commit();
    }
    public void file(View view) {
        //简写方式
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, new FileInterStoreFragment()).commit();
    }
    public void sdcard(View view) {
        //简写方式
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, new FileOuterStoreFragment()).commit();
    }
    public void assets(View view) {
        //简写方式
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, new AssetsFragment()).commit();
    }
}
