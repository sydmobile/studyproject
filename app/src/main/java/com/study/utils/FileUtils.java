package com.study.utils;

import android.app.DownloadManager;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;

import java.io.File;

import androidx.annotation.RequiresApi;

/**
 * 说明：文件工具类
 * <p>
 * date: 2020/5/16 19:53
 *
 * @author syd
 * @version 1.0
 */
public class FileUtils {

    /**
     * 通过 downloadId 获取 File
     *
     * @param context    上下文
     * @param downloadId downloadId
     * @return 下载的File
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static File queryDownloadedApk(Context context, long downloadId) {
        File targetApkFile = null;
        DownloadManager downloader =
                (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        if (downloadId != -1) {
            DownloadManager.Query query = new DownloadManager.Query();
            query.setFilterById(downloadId);
            query.setFilterByStatus(DownloadManager.STATUS_SUCCESSFUL);
            Cursor cur = downloader.query(query);
            if (cur != null) {
                if (cur.moveToFirst()) {
                    String uriString =
                            cur.getString(cur.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
                    if (!TextUtils.isEmpty(uriString)) {
                        targetApkFile = new File(Uri.parse(uriString).getPath());
                    }
                }
                cur.close();
            }
        }
        return targetApkFile;
    }
}
