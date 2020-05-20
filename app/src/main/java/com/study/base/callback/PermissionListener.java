package com.study.base.callback;

import java.util.List;

/**
 * 说明：授权回调
 * <p>
 * date: 2019/5/10 17:33
 *
 * @author syd
 * @version 1.0
 */
public interface PermissionListener {

    void onGranted();

    void onDenied(List<String> deniedPermission);
}
