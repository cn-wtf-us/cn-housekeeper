package cn.feicui.com.housekeeper;

import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.FileDescriptor;
import java.io.IOException;

public class MediaPlayerActivity extends AppCompatActivity {

    private static final String TAG = "MediaPlayerActivity";
    private int currentPosition;
    private SeekBar seekbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);
        /*onCreate方法会被调用几次,什么时候会调用？
            实现拖拽播放音乐
         */
        //创建mediaplayer对象，关联R.raw.rongma指向的文件
//        final MediaPlayer player = MediaPlayer.create(this, R.raw.door_of_moon);
        final MediaPlayer player = new MediaPlayer();
        initMediaPlayer(player);
        //设置循环播放，默认是false
//        player.setLooping(true);
        //获取播放时长，单位是毫秒
        int duration = player.getDuration();
        Log.d(TAG, "duration: " + duration);

        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                //当音乐播放完成的时候，会回调此方法
                Toast.makeText(MediaPlayerActivity.this, "播放完成", Toast.LENGTH_SHORT).show();
            }
        });
        player.setOnSeekCompleteListener(
                new MediaPlayer.OnSeekCompleteListener() {
                    @Override
                    public void onSeekComplete(MediaPlayer mp) {
                        Toast.makeText(MediaPlayerActivity.this, "seek", Toast.LENGTH_SHORT).show();
                        mp.start();
                    }
                }
        );
        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.start();
                Toast.makeText(MediaPlayerActivity.this, "开始播放", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.pause).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.pause();
                Toast.makeText(MediaPlayerActivity.this, "暂停", Toast.LENGTH_SHORT).show();
//                player.seekTo(100);
            }
        });
        findViewById(R.id.stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.stop();
            }
        });

        seekbar = (SeekBar) findViewById(R.id.seekBar);
        seekbar.setMax(duration);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(
                    SeekBar seekBar,//表示当前的seekbar
                    int progress,//进度值,0-max,默认是100，可以自己设置最大值得大小,setMax()
                    boolean fromUser //初始值是否由用户自己设置
            ) {
//                Toast.makeText(MediaPlayerActivity.this, fromUser + "" + progress, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //每次触摸seekbar的圆圈时都会触发一次
                Toast.makeText(MediaPlayerActivity.this, "start", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //完成一次触摸后，回调此方法
                player.seekTo(seekBar.getProgress());
                player.start();
                Toast.makeText(MediaPlayerActivity.this, "stop", Toast.LENGTH_SHORT).show();
            }
        });
        //每个一段时间，重复做一件事情，第一种方案，开辟子线程
        updateSeekBarProgress(player);
    }

    private boolean initMediaPlayer(MediaPlayer player) {
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);//设置播放的类型STREAM_MUSIC
        try {
            //设置播放的资源文件
            AssetFileDescriptor afd = getAssets().openFd("door_of_moon.mp3");
            if (afd==null) {
                return false;
            }
            FileDescriptor fd = afd.getFileDescriptor();
            player.setDataSource(fd,afd.getStartOffset(),afd.getLength());
            //准备播放，new 的方式必须要调用
            afd.close();
            player.prepare();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private void updateSeekBarProgress(final MediaPlayer player) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                //获取播放器当播放的位置，单位毫秒
                while (currentPosition <= player.getDuration()) {
                    try {
                        Thread.sleep(100);
                        currentPosition = player.getCurrentPosition();
                        //更新seekBar的进度
                        seekbar.setProgress(currentPosition);
                        Log.d(TAG, "run: " + currentPosition);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

    }
}
