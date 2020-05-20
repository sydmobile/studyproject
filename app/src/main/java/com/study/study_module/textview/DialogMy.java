package com.study.study_module.textview;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;

import com.study.R;


/**
 * 自定义的dialog
 * Created by 孙亦东 on 2016/9/7.
 */
@SuppressWarnings("unused")
public class DialogMy extends Dialog {
    private CallBack callBack;
    private int view;

    public DialogMy(Context context, CallBack callBack, int view) {
        super(context, R.style.MyDialogCommon);
        this.callBack = callBack;
        this.view = view;
    }

    public DialogMy(Context context, int themeResId, CallBack callBack, int view) {
        super(context, themeResId);
        this.callBack = callBack;
        this.view = view;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(view);
        callBack.init(this);

    }

    public void showDialog(int gravity) {
        Window window = getWindow();
        if (window != null) {
            window.setGravity(gravity);
        }
        show();

    }

    public void showDialog() {
        show();
    }

    @Override
    public void dismiss() {
        super.dismiss();


    }

    public interface CallBack {
        void init(DialogMy dialogMy);
    }
}
