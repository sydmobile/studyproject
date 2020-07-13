package com.study.study_module.material;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import com.study.R;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/**
 * 说明：$
 * <p>
 * date: 2020/7/7 17:00
 *
 * @author syd
 * @version 1.0
 */
public class CusBehavior extends CoordinatorLayout.Behavior {
    private int width;

    public CusBehavior(Context context, AttributeSet attrs){
        super(context, attrs);
        DisplayMetrics display = context.getResources().getDisplayMetrics();
        width = display.widthPixels;
    }

//    @Override
//    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull View child,
//                                   @NonNull View dependency) {
//        return dependency.getId() == R.id.dependency;
//    }
//
//    @Override
//    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull View child,
//                                          @NonNull View dependency) {
//            child.setY((int) (dependency.getY()+child.getHeight()));
//            return true;
//    }
}
