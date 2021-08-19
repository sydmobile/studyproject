package com.study.study_module.mvp;

/**
 * 说明：$
 * <p>
 * date: 2019/11/28 15:57
 *
 * @author syd
 * @version 1.0
 */
public interface BaseView {

    void showError(String msg);

    public static void main(String[] args) {
        int b = Integer.parseInt("11000101",2);
        byte a = (byte)b;
        String a1 = Integer.toBinaryString(-95);
        System.out.println(b);
        System.out.println(a);
        System.out.println(a1);
        byte aa = -1;
        System.out.println(aa&0xFF);

    }
}
