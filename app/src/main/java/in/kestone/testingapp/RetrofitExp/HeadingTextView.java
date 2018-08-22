package in.kestone.testingapp.RetrofitExp;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.TextView;

import in.kestone.testingapp.widget.SetTypeFace;

public class HeadingTextView extends TextView {

    public HeadingTextView(Context context) {
        super(context);
        setCustomTypeFace();
    }

    private void setCustomTypeFace() {
//        if(isInEditMode()){
            setTypeface(SetTypeFace.normalTypeface(getContext()));
            setTextSize(13.0f);
//        }
    }

    public HeadingTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setCustomTypeFace();
    }

    public HeadingTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setCustomTypeFace();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public HeadingTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setCustomTypeFace();
    }
}
