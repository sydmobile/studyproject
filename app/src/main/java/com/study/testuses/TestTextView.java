package com.study.testuses;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;

import com.study.R;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 说明：$
 * <p>
 * date: 2020/6/12 11:29
 *
 * @author syd
 * @version 1.0
 */
public class TestTextView extends androidx.appcompat.widget.AppCompatTextView {
    public TestTextView(Context context) {
        super(context);
    }

    public TestTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, R.attr.test);

    }

    public TestTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        try {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TestTextView,
                    defStyleAttr, 0);
            String s = typedArray.getString(R.styleable.TestTextView_hello);
            Log.e("==","===="+s);
        }catch (Exception e){
            Log.e("==","异常");
        }

    }

}
