package in.kestone.testingapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.kestone.testingapp.Utility.PermissionHelperJava;
import in.kestone.testingapp.Utility.SqLiteDB;

public class ActivityImageUpload extends AppCompatActivity implements View.OnClickListener {

    private static final int RESULT_IMAGE_BROWSE = 101;
    private static final String TAG = "MainActivity";
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.imageViewget)
    ImageView imageViewget;
    @BindView(R.id.btn_browse_image)
    Button browse_image;
    @BindView(R.id.btn_upload)
    Button upload;
    @BindView(R.id.btn_get)
    Button getImage;
    Intent intent;
    SqLiteDB sqDB;
    String filePath;
    long filelength = 0;
    PermissionHelperJava permissionHelper;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_upload);

        ButterKnife.bind(this);

        browse_image.setOnClickListener(this);
        upload.setOnClickListener(this);
        getImage.setOnClickListener(this);


    }


    @SuppressLint("NewApi")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_upload:
                // button upload image//---//check file size (file size <=2mb)

                if (filelength <= 2000) {
                    sqDB = new SqLiteDB(ActivityImageUpload.this);
                    sqDB.save(filePath);
                } else {
                    Toast.makeText(this, "File size not more then 2MB.", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.btn_browse_image:
                //check permission
                permissionHelper = new PermissionHelperJava(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,}
                        , 100);
                permissionHelper.request(new PermissionHelperJava.PermissionCallback() {
                    @Override
                    public void onPermissionGranted() {
                        Log.d(TAG, "onPermissionGranted() called");

                    }

                    @Override
                    public void onIndividualPermissionGranted(String[] grantedPermission) {
                        Log.d(TAG, "onIndividualPermissionGranted() called with: grantedPermission = [" + TextUtils.join(",", grantedPermission) + "]");

                    }

                    @Override
                    public void onPermissionDenied() {
                        Log.d(TAG, "onPermissionDenied() called");
                    }

                    @SuppressLint("NewApi")
                    @Override
                    public void onPermissionDeniedBySystem() {
                        Log.d(TAG, "onPermissionDeniedBySystem() called");
                        permissionHelper.openAppDetailsActivity();
                    }
                });

                break;

            //button to get file from sqlite
            case R.id.btn_get:
                imageViewget.setImageBitmap(sqDB.get());
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_IMAGE_BROWSE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();

            int index = cursor.getColumnIndex(filePathColumn[0]);
            filePath = cursor.getString(index);
            cursor.close();

            Bitmap bitmap = BitmapFactory.decodeFile(filePath);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] imageInByte = stream.toByteArray();
            filelength = (imageInByte.length) / 1024;

            if (filelength <= 2000) {
                imageView.setImageBitmap(BitmapFactory.decodeFile(filePath));
            } else {
                Toast.makeText(this, "File size not more then 2MB.", Toast.LENGTH_SHORT).show();
            }
            Log.e("File length ", "" + filelength + "\n" + imageInByte);
        }
    }

    @SuppressLint("NewApi")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permissionHelper != null) {
            permissionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);

            loadImage();
        }
    }

    //load image from internal storage
    private void loadImage() {
        intent = new Intent(Intent.ACTION_PICK);//, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, RESULT_IMAGE_BROWSE);
    }
}
