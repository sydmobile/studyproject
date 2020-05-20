package com.study.study_module.rx.retrofit_rxjava;

import rx.functions.Func1;

/**
 * 说明：$
 * <p>
 * date: 2020/4/17 16:50
 *
 * @author syd
 * @version 1.0
 */
public class PayLoad<T> implements Func1<BaseResponse<T>,T> {

    @Override
    public T call(BaseResponse<T> tBaseResponse){
        if (!tBaseResponse.isSuccess()){
            throw new Fault(tBaseResponse.errorCode,tBaseResponse.errorMsg);
        }
        return tBaseResponse.data;
    }
}
