package com.study.utils;

import android.content.Context;
import android.view.KeyEvent;

import com.study.R;
import com.study.utils.view.DialogMy;

/**
 * 操作 DialogMy  的工具类
 * Created by sunyidong on 2016/11/3.
 */

@SuppressWarnings("unused")
public class DialogUtils {

    /**
     * 生成一个 DialogMy 对象
     *
     * @param dialogMy       dialogMy
     * @param context        context
     * @param callBack       回调
     * @param layoutResource dialog 的填充布局
     * @return dialogMy
     */
    public static DialogMy createDialog(DialogMy dialogMy, Context context,
                                        DialogMy.CallBack callBack, int layoutResource) {
        if (dialogMy == null) {
            dialogMy = new DialogMy(context, R.style.MyDialogCommon, callBack, layoutResource);
        }
        return dialogMy;
    }

    public static DialogMy createDialog(DialogMy dialogMy, Context context,
                                        DialogMy.CallBack callBack, int layoutResource, int style) {
        if (dialogMy == null) {
            dialogMy = new DialogMy(context, style, callBack, layoutResource);
        }
        return dialogMy;
    }

    /**
     * 对 dialog 设置后退按钮处理事件
     *
     * @param dialogMy dialog
     * @return dialog
     */
    public static DialogMy setBackListner(final DialogMy dialogMy) {
        if (dialogMy != null) {
            dialogMy.setOnKeyListener((dialog, keyCode, event) -> {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    dialogMy.dismiss();
                    // 可以根据具体业务书写
                }
                return false;
            });
        }

        return dialogMy;

    }

    /**
     * 关闭 dialog
     *
     * @param dialogMy dialog
     */
    public static void dismiss(DialogMy dialogMy) {
        if (dialogMy != null) {
            if (dialogMy.isShowing()) {
                dialogMy.dismiss();
            }
        }
    }

    /**
     * show dialog 默认显示在 central
     *
     * @param dialogMy dialog
     */
    public static void show(DialogMy dialogMy) {
        if (dialogMy != null) {
            dialogMy.show();
        }
    }

    /**
     * 根据 gravity show
     *
     * @param dialogMy dialog
     * @param gravity  Gravity.TOP、BOTTOM、CEN....
     */
    public static void show(DialogMy dialogMy, int gravity) {
        if (dialogMy != null) {
            dialogMy.showDialog(gravity);
        }
    }


}
