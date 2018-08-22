package in.kestone.testingapp.widget;

import android.content.Context;
import android.graphics.Typeface;

public class SetTypeFace {
    public static Typeface normalTypeface(Context context){
        return Typeface.createFromAsset(context.getAssets(),"font/helvetica_neue.ttf");
    }
}
