package com.study.study_module.rx.retrofit_rxjava;

/**
 * 说明：异常处理
 * <p>
 * date: 2020/4/17 16:54
 *
 * @author syd
 * @version 1.0
 */
public class Fault extends RuntimeException {
    public int errorCode;
    public String errorMsg;
    public Fault(int errorCode,String errorMsg){
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
