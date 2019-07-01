package com.syd.study.recyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import com.syd.study.R;

import org.jetbrains.annotations.NotNull;

/**
 * 说明：$d$
 * <p>
 * date: 2019/6/24 15:01
 *
 * @author syd
 * @version 1.0
 */
public class CustomItemDecoration extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};

    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;
    /** 用于绘制间隔样式 */
    private Drawable mDivider;
    /** 列表的方向，水平/竖直 */
    private int mOrientation;

    private Paint paint;
    private Bitmap bitmap;

    public CustomItemDecoration(Context context,int mOrientation){
        TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
        setOrientation(mOrientation);

        paint = new Paint();
        paint.setAntiAlias(true);
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);


    }

    private void setOrientation(int orientation){
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST){
            throw new IllegalArgumentException("invalid orientation");
        }
        mOrientation = orientation;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent,
                               @NonNull RecyclerView.State state) {

       if (mOrientation == VERTICAL_LIST){
           outRect.set(30,80,30,80);
       }else {
           outRect.set(20,40,60,80);
       }
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (mOrientation == VERTICAL_LIST){
            // 获取分隔线左右坐标
            int left = parent.getPaddingLeft();
            int right = parent.getWidth()-parent.getPaddingRight();
            int childCount = parent.getChildCount();
            for (int i=0;i<childCount;i++){
                View child = parent.getChildAt(i);
                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
                int top = child.getBottom()+params.bottomMargin+80;
                int bottom = top+20;
                mDivider.setBounds(left,top,right,bottom);
//                mDivider.setColorFilter(Color.RED, PorterDuff.Mode.SRC);
                mDivider.setTint(Color.RED);
                mDivider.setTintMode(PorterDuff.Mode.SRC);
                mDivider.draw(c);

            }
        }

        Log.e("onDraw","onDraw");
    }

    public void onDrawOver(@NotNull Canvas canvas, @NotNull RecyclerView parent, @NotNull RecyclerView.State state){
        if (mOrientation == VERTICAL_LIST){
            int childCount = parent.getChildCount();
            for (int i = 0;i<childCount;i++){
                View child = parent.getChildAt(i);
                canvas.drawBitmap(bitmap,new Rect(0,0,bitmap.getWidth(),bitmap.getHeight()),new Rect(child.getLeft()+20,child.getTop(),child.getLeft()+20+
                        (child.getBottom()-child.getTop()),child.getBottom()),paint);
            }
        }
        Log.e("onDrawOver","===");
    }
}
