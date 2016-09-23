package cn.feicui.com.housekeeper;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.feicui.com.housekeeper.util.SdCardUtil;

public class TakePicActivity extends AppCompatActivity {

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1;
    private static final int REQUEST_IMAGE_CAPTURE = 2;
    private Uri fileUri;
    private ImageView imageView;
    private String mCurrentPhotoPath;
    private String TAG = "TakePicActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_pic);
        imageView = (ImageView) findViewById(R.id.imageView);

        findViewById(R.id.btn_take_pic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePic();
            }

            private void takePic() {
                //保存缩略图
//                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
//                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
//                }

                if (!SdCardUtil.isExternalStorageWritable()) {
                    Toast.makeText(TakePicActivity.this, "sd 未挂载", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "takePic: sd 未挂载");
                    return;
                }
                //保存大图片
                dispatchTakePictureIntent();
                //存到拍好的图片到手机的画廊App中
//                galleryAddPic(mCurrentPhotoPath);
                //缩放拍完的大图，并设置到imageview控件中
                scaleImg(imageView,mCurrentPhotoPath);

            }
        });

    }

    /**
     * 缩放拍完的大图，并设置到imageview控件中
     * @param  mImageView 是要放图片的控件
     * @param  mCurrentPhotoPath 是图片的存放的路径
     */
    private void scaleImg(ImageView mImageView,String mCurrentPhotoPath) {
        //得到存放放图片控件的宽高  90*90
        int targetW = mImageView.getWidth();
        int targetH = mImageView.getHeight();

        //获取图片原始的宽高 ，比如尺寸是900*1080
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        //只解密图片的边界，不解密内容，快，效率，只需要知道宽高
        bmOptions.inJustDecodeBounds = true;
        //解密图片文件，得到图片的参数 Options
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // 得到缩放比例因子  10
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;//是缩放比例 1/10*1/10
        bmOptions.inPurgeable = true;//当系统内存吃紧的时候，会缩小图片的像素

        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        mImageView.setImageBitmap(bitmap);
    }

    /**
     * 保存拍好的照片到画廊
     */
    private void galleryAddPic(String filePath) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(filePath);
        Uri contentUri = Uri.fromFile(f);
        //数据资源也是以Uri形式
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

    /**
     *  创建存放大图片的文件
     */
    static final int REQUEST_TAKE_PHOTO = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            //创建图片存放的file
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                Toast.makeText(TakePicActivity.this, "创建存放图片的文件失败", Toast.LENGTH_SHORT).show();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                //告诉相机App存放图片的路径 uri--unify resource identity
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",//authorities
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_.jpg";
        //新创建的文件夹名必须和xml/file_paths的path值一致
        //手机Andorid(系统目录)/data/data/包名/files/文件名
        File storageDir = new File(getFilesDir(), "images");
        if (storageDir.mkdir()) {
            Toast.makeText(TakePicActivity.this, storageDir.toString(), Toast.LENGTH_SHORT).show();
        }
        File image = new File(storageDir,imageFileName);
        if (image.createNewFile()) {
            Toast.makeText(TakePicActivity.this, image.toString(), Toast.LENGTH_SHORT).show();
        }
       // File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//        File image = File.createTempFile(
//                imageFileName,  /* prefix */
//                ".jpg",         /* suffix */
//                storageDir      /* directory */
//        );
        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //接收返回的缩略图
         if (resultCode == RESULT_OK && requestCode == REQUEST_IMAGE_CAPTURE) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }


    }
}
