package in.kestone.testingapp.Utility;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SqLiteDB {
    SQLiteDatabase db;
    Bitmap bmp = null;
    Activity activity;

    public SqLiteDB(Activity activity) {
        this.activity = activity;
        //database created
        db = activity.openOrCreateDatabase("test.db", Context.MODE_PRIVATE, null);
        //table created
        db.execSQL("create table if not exists imageTb ( a blob )");

    }

    public void save(String filePath) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(filePath);

            byte[] image = new byte[fis.available()];
            fis.read(image);
            ContentValues values = new ContentValues();
            values.put("a", image);
            db.insert("imageTb", null, values);
            fis.close();
            Toast.makeText(activity, "Done", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Bitmap get() {
        Cursor c = db.rawQuery("select * from imageTb", null);
        if (c.moveToLast()) {
            Log.e("Count ", ""+c.getCount());
            byte[] image = c.getBlob(0);
            bmp = BitmapFactory.decodeByteArray(image, 0, image.length);
        }
        return bmp;
    }
}
