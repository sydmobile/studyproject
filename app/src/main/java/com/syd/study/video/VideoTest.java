package com.syd.study.video;

import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.syd.study.R;
import com.syd.study.base.BaseActivity;

import androidx.annotation.Nullable;

/**
 * 说明：$
 * <p>
 * date: 2019/12/20 9:44
 *
 * @author syd
 * @version 1.0
 */
public class VideoTest extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        VideoView videoView = findViewById(R.id.vv);
        videoView.setMediaController(new MediaController(this));
        videoView.setVideoPath("http://app.nxssxy.com:1002/upload/newsVideo/20191219" +
                "/6371239424761718755335131.mp4");
        videoView.start();
        videoView.requestFocus();
    }
}
