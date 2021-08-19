package com.study.testuses;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.study.R;
import com.study.adapter.TestAdapter;
import com.study.base.BaseActivity;
import com.study.userful.util.L;
import com.study.utils.SPUtils;
import com.study.utils.ScreenUtil;
import com.study.utils.updateapk.DownAPKUtils;
import com.study.utils.volley.HttpRequest;
import com.study.utils.volley.Parameter;
import com.study.utils.volley.ResponseListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import butterknife.BindView;

/**
 * 说明：测试使用
 * <p>
 * date: 2019/6/24 19:22
 *
 * @author syd
 * @version 1.0
 */
public class TestActivity extends BaseActivity {
//    @BindView(R.id.gv)
//    GridView gv;
//    @BindView(R.id.et_params)
//    EditText etParams;
//    @BindView(R.id.tv_show)
//    TextView tvShow;
//    @BindView(R.id.iv_source)
//    ImageView ivSource;
//    @BindView(R.id.iv_show)
//    ImageView ivShow;
//    String[] commands = new String[]{"test", "save1", "save2", "save3", "show", "image",
//            "getHeight",
//            "volley", "dialog", "download", "copy"};
    DownAPKUtils downAPKUtils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        downAPKUtils = new DownAPKUtils(this, "app.apk");


    }

    @Override
    protected void onResume() {
        super.onResume();
        downAPKUtils.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        downAPKUtils.onPause();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_test1;
    }


    @Override
    public void initView() {
        super.initView();
//        gv.setAdapter(new TestAdapter(this, commands));
//        gv.setOnItemClickListener((parent, view, position, id) -> {
//            switch (commands[position]) {
//                case "test":
//                    // 临时测试使用
//                    test();
//                    break;
//                case "save1":
//                    // 保存字符串
//                    save1();
//                    break;
//                case "save2":
//                    // 保存int
//                    save2();
//                    break;
//                case "show":
//                    // 显示保存内容
//                    show();
//                    break;
//                case "image":
//                    // 展示裁剪处理过的图片
//                    showNewImage();
//                    break;
//                case "getHeight":
//                    // 屏幕信息工具类实验
//                    screen();
//                    break;
//                case "volley":
//                    // volley 工具类实验
//                    volley();
//                    break;
//                case "download":
//                    Uri r = Uri.parse("package:" + getPackageName());
//                    Uri uri = Uri.fromFile(getExternalCacheDir());
//                    downAPKUtils.downloadApk("", "下载APK", "正在更新");
//                    break;
//                case "copy":
//                    ClipboardManager clipboardManager =
//                            (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
//                    clipboardManager.setPrimaryClip(ClipData.newPlainText("label", "复制的内容"));
//                    break;
//
//
//            }
//        });
    }

    private void test() {

    }


    private void save1() {
        SPUtils.putAndApply("one", "sunyidong");
    }

    private void save2() {
        SPUtils.putAndApply("two", 12);
    }

    private void showNewImage() {
//        Bitmap bitmapSource = BitmapFactory.decodeResource(getResources(),
//                R.drawable.fangdong);
//        int width = bitmapSource.getWidth();
//        int height = bitmapSource.getHeight();
//        int size = Math.min(width, height);
//        Bitmap b = Bitmap.createBitmap(bitmapSource, 1, 1, size - 2, size - 2);
//        Bitmap source = Bitmap.createBitmap(b.getWidth(), b.getHeight(), Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(source);
//        Paint paint = new Paint();
//        paint.setShader(new BitmapShader(b, BitmapShader.TileMode.CLAMP,
//                BitmapShader.TileMode.CLAMP));
//        canvas.drawCircle((size - 2) / 2.0f, (size - 2) / 2.0f, (size - 2) / 2.0f, paint);
//        ivShow.setImageBitmap(source);
    }

    private void screen() {
        L.e("height:" + ScreenUtil.getHeightPixels());
        L.e("ydpi:" + ScreenUtil.getYdpi());
        L.e("xdpi:" + ScreenUtil.getXdpi());
        L.e("status:" + ScreenUtil.getStatusBarHeight() + "==" + ScreenUtil.getNavigationBarHeight());
        Display defaultDisplay = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        int x = point.x;
        int y = point.y;
        Log.e(TAG, "x = " + x + ",y = " + y);


        Point outSize1 = new Point();
        getWindowManager().getDefaultDisplay().getRealSize(outSize1);
        int x1 = outSize1.x;
        int y1 = outSize1.y;
        Log.e(TAG, "x = " + x1 + ",y = " + y1);
        //x = 1440,y = 2960
        Rect outSize = new Rect();
        getWindowManager().getDefaultDisplay().getRectSize(outSize);
        int left = outSize.left;
        int top = outSize.top;
        int right = outSize.right;
        int bottom = outSize.bottom;
        Log.e(TAG, "left = " + left + ",top = " + top + ",right = " + right + "," +
                "bottom = " + bottom);

    }

    private void volley() {
//        HttpRequest.getInstance().getRequest("http://192.168.1.100:8100/" +
//                        "firew/uav/readyInfo",
//                new Parameter().add("manufactureId", "111111"), null,
//                new ResponseListener() {
//                    @Override
//                    protected void onResponse(String main, Exception error) {
//                        tvShow.setText(main);
//                        if (error == null) {
//                            tvShow.setText(main);
//                        }
//                    }
//                });
    }

    private void show() {
//        tvShow.setText(String.format("%s", SPUtils.get("two", 1) + "" + SPUtils.get("one", "")));

    }


    public static void main(String[] args) {
        boolean is = true;
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        for (String n:list){
            if ("3".equals(n)){
                return;
            }

        }
        String s = "sss";
    }



}
