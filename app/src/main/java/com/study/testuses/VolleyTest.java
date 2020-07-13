package com.study.testuses;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.study.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * 说明：$
 * <p>
 * date: 2020/4/8 9:54
 *
 * @author syd
 * @version 1.0
 */
public class VolleyTest extends AppCompatActivity {
    RequestQueue requestQueue = Volley.newRequestQueue(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    void test() {

        StringRequest request = new StringRequest("url", response -> {


        }, error -> {

        });
    }

    /**
     * 获取当前程序可以获取的最大内存空间
     */
    public void getRunTimeMemory() {
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
    }

    public void getImageSize() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher, options);
        int imageHeight = options.outHeight;
        int imageWidth = options.outWidth;
        String imageType = options.outMimeType;
    }

    public int calculateInSampleSize(int requestHeight, int requestWidth,
                                     BitmapFactory.Options options) {
        int height = options.outHeight;
        int width = options.outWidth;

        int radio = 1;
        if (requestHeight < height || requestWidth < width) {

            int radioHeight = height / requestHeight;
            int radioWidth = width / requestWidth;
            radio = radioHeight > radioWidth ? radioWidth : radioHeight;
        }
        return radio;
    }

    public Bitmap decodeSampleBitmapFromResource(Resources res,int resId,int reqWidth,int reqHeight){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res,resId);
        options.inSampleSize = calculateInSampleSize(reqHeight,reqWidth,options);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res,resId,options);
    }
    // 内存缓存技术对那些大量占用应用程序宝贵内存的图片提供了快速访问的方法。

    public void cache(){


    }
}
