package com.study.study_module.retrofit;

import rx.Observable;
import rx.functions.Func1;

/**
 * 说明：$
 * <p>
 * date: 2020/1/8 14:25
 *
 * @author syd
 * @version 1.0
 */
public class TopicLoader extends ObjectLoader{
    private HotTopicService mHotTopicService;
    protected HotTopicService p_hot;
    public HotTopicService public_hot;
    HotTopicService hot;
    public TopicLoader() {

        mHotTopicService = RetrofitServiceManager.getInstance().create(HotTopicService.class);
    }

    public Observable getHotTopic(){
        return observe(mHotTopicService.getTopic()
        .map(new Func1<Topics, Object>() {
            @Override
            public Object call(Topics topics) {
                return null;
            }
        }));
    }



}
