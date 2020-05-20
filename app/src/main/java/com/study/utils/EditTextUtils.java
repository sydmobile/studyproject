package com.study.utils;

import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextWatcher;
import android.text.style.AbsoluteSizeSpan;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by Administrator on 2016/8/11.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class EditTextUtils {

    public static boolean isEmptyAfterTrim(EditText editText) {
        if (editText == null)
            return true;
        else {
            if (editText.getText() == null) {
                return true;
            } else {
                String content = editText.getText().toString();
                return content.trim().length() == 0;
            }
        }
    }

    public static boolean isEmptyAfterTrim(EditText... editText) {
        for (EditText text : editText) {
            if (text == null)
                return true;
            else {
                if (text.getText() == null) {
                    return true;
                } else {
                    String content = text.getText().toString();
                    if (content.trim().length() == 0)
                        return true;
                }
            }
        }
        return false;
    }

    /**
     * 限制只能输入汉字
     */
    public static void filterOnlyChinese(final EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String editable = editText.getText().toString();
                String str = stringFilter(editable);
                if (!editable.equals(str)) {
                    editText.setText(str);
                    //设置新的光标所在位置
                    editText.setSelection(str.length());
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    /**
     * 限制不能输入特殊字符
     */
    public static void filterNoChar(final EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String editable = editText.getText().toString();
                String str = stringFilter(editable, "[^\u4e00-\u9fa5_a-zA-Z0-9]");
                if (!editable.equals(str)) {
                    editText.setText(str);
                    //设置新的光标所在位置
                    editText.setSelection(str.length());
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    /**
     * 判断是否有 num 长度
     *
     * @return 是否
     */
    public static boolean isBit18(EditText editText, int num) {
        if (editText != null && editText.getText() != null) {
            return editText.getText().toString().length() == num;
        } else {
            return false;
        }
    }

    /**
     * 去除不是汉字的内容
     *
     * @param str 原始字符串
     * @return 过滤后的字符串
     * @throws PatternSyntaxException 异常
     */
    public static String stringFilter(String str) throws PatternSyntaxException {
        //汉字
        String regEx = "[^\u4E00-\u9FA5]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    /**
     * 过滤掉对应字符
     *
     * @param str   原始字符
     * @param model 要过滤的字符（正则表达式）
     * @return 过滤后的字符串
     * @throws PatternSyntaxException 异常
     */
    public static String stringFilter(String str, String model) throws PatternSyntaxException {
        Pattern p = Pattern.compile(model);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    /**
     * 设置hint的size
     *
     * @param editText EditText
     * @param fontSize hint字体大小（像素）
     */
    public static void setHintSize(EditText editText, int fontSize) {
        CharSequence hint = editText.getHint();
        // 新建一个可以添加属性的文本对象
        SpannableString ss = new SpannableString(hint);
        // 新建一个属性对象,设置文字的大小
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(fontSize, true);
        // 附加属性到文本
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // 设置hint
        editText.setHint(new SpannedString(ss)); // 一定要进行转换,否则属性会消失
    }


}
