package cn.feicui.com.housekeeper;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TakePicActivity extends AppCompatActivity
{

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1;
    private Uri fileUri;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_pic);
        imageView = (ImageView) findViewById(R.id.imageView);

        findViewById(R.id.btn_take_pic).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                takePic();
            }

            private void takePic()
            {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE); // create a file to save the image
                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file name
                startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
            }
        });

    }

    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;

    /**
     * Create a file Uri for saving an image or video
     */
    private  Uri getOutputMediaFileUri(int type)
    {
        File outputMediaFile = getOutputMediaFile(type);
        Uri uri = Uri.fromFile(outputMediaFile);
        return uri;
    }

    /**
     * Create a File for saving an image or video
     */
    private File getOutputMediaFile(int type)
    {
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.
        String state = Environment.getExternalStorageState();
        if (!state.equals(Environment.MEDIA_MOUNTED))
        {
            throw new RuntimeException("external storage is not mounted");
        }

//        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
//                Environment.DIRECTORY_PICTURES), "MyCameraApp");
        File mediaStorageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists())
        {
            if (!mediaStorageDir.mkdirs())
            {
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }
        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE)
        {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_" + timeStamp + ".jpg");
        }
        else if (type == MEDIA_TYPE_VIDEO)
        {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_" + timeStamp + ".mp4");
        }
        else
        {
            return null;
        }

        return mediaFile;

    }
        @Override
        protected void onActivityResult ( int requestCode, int resultCode, Intent data)
        {
            super.onActivityResult(requestCode, resultCode, data);
//            Uri pic = data.getData();
            if (requestCode==CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE)
            {
                if (resultCode==RESULT_OK)
                {
//                    Toast.makeText(TakePicActivity.this, data.getData()+"", Toast.LENGTH_SHORT).show();
                    File outputMediaFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);
                    BitmapFactory.Options opt = new BitmapFactory.Options();
                    opt.inSampleSize = 8;
                    Bitmap bitmap = BitmapFactory.decodeFile(outputMediaFile.getName(),opt);
                    imageView.setImageBitmap(bitmap);
                }
                else if (resultCode == RESULT_CANCELED)
                {
                    Toast.makeText(TakePicActivity.this, "cancle", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(TakePicActivity.this, "failed", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }
