package cn.feicui.com.housekeeper.biz;

import android.app.IntentService;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/9/21 0021.
 */
public class HelloIntentService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public HelloIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String value = intent.getStringExtra("key");
        Toast.makeText(HelloIntentService.this, value, Toast.LENGTH_SHORT).show();
    }
}
