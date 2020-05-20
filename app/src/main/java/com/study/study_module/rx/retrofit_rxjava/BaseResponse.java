package com.study.study_module.rx.retrofit_rxjava;

/**
 * 说明：$
 * <p>
 * date: 2020/4/17 16:48
 *
 * @author syd
 * @version 1.0
 */
public class BaseResponse<T> {
    public int errorCode;
    public String errorMsg;
    public T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return errorCode == 0;
    }
}
