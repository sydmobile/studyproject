package com.study.study_module.rx.retrofit_rxjava;


import rx.Observable;

/**
 * 说明：$
 * <p>
 * date: 2020/4/17 15:36
 *
 * @author syd
 * @version 1.0
 */
public class WanAndroidLoader extends ObjectLoader {
    private WanAndroidService mWanAndroidService;
    public WanAndroidLoader(){
        RetrofitServiceManager retrofitServiceManager = RetrofitServiceManager.getInstance();
        mWanAndroidService = RetrofitServiceManager.getInstance().create(WanAndroidService.class);
    }

    public Observable<WXdata> getWxArticle(String id,String page){
//        observe(mWanAndroidService.getArticle(id,page)).subscribe(new Subscriber<BaseResponse<WXdata>>() {
//            @Override
//            public void onCompleted() {
//                Log.e("===", "onCompleted: ");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.e("===", "onError: ");
//            }
//
//            @Override
//            public void onNext(BaseResponse<WXdata> wxArticleBaseResponse) {
//                Log.e("===", "onError: ");
//            }
//        });
//        return observe(mWanAndroidService.getArticle(id,page)).map(new PayLoad<>());
        return mWanAndroidService.getArticle(id,page).map(new PayLoad<>());


    }

}
