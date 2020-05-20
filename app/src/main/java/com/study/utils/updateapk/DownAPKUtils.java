package com.study.utils.updateapk;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;

import com.study.userful.util.L;

import java.io.File;
import java.lang.ref.WeakReference;

import androidx.core.content.FileProvider;

import static com.study.config.App.APK_DOWN;
import static com.study.utils.FileUtils.queryDownloadedApk;

/**
 * 说明：下载 apk 工具类
 * <p>
 * date: 2020/5/14 9:49
 *
 * @author syd
 * @version 1.0
 * @link https://juejin.im/post/5ad4ab7af265da239d49c8f9#heading-2
 */
public class DownAPKUtils {

    public static final String TAG = "DownAPKUtils";
    private WeakReference<Activity> weakReference;
    private DownloadManager mDownloadManager;
    private DownloadChangeObserver mDownLoadChangeObserver;
    private DownloadReceiver mDownloadReceiver;
    private long mReqId;
    private OnUpdateListener mUpdateListener;
    private String apkName;

    /**
     * 构造方法
     * 创建 DownloadManager observer Receiver
     *
     * @param activity activity
     * @param apkName  xx.apk
     */
    public DownAPKUtils(Activity activity, String apkName) {
        weakReference = new WeakReference<>(activity);
        mDownloadManager =
                (DownloadManager) weakReference.get().getSystemService(Context.DOWNLOAD_SERVICE);
        mDownLoadChangeObserver = new DownloadChangeObserver(new Handler());
        mDownloadReceiver = new DownloadReceiver();
        this.apkName = apkName;

    }

    /**
     * 设置下载进度更新回调
     *
     * @param mUpdateListener Update
     */
    public void setUpdateListener(OnUpdateListener mUpdateListener) {
        this.mUpdateListener = mUpdateListener;
    }

    /**
     * 执行下载 apk
     *
     * @param apkUrl apk url
     * @param title  title
     * @param desc   描述
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void downloadApk(String apkUrl, String title, String desc) {

        // 要下载的文件
        File apkFile =
                new File(weakReference.get().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
                        , apkName);
        if (apkFile.exists()) {
            apkFile.delete();
        }

        // 请求对象
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(apkUrl));
        // 设置 title
        request.setTitle(title);
        // 设置描述
        request.setDescription(desc);
        // 完成后显示通知栏
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        // 设置下载的位置
        request.setDestinationInExternalFilesDir(weakReference.get(),
                Environment.DIRECTORY_DOWNLOADS, apkName);
        // 用于打开的类型
        // 这个决定了下载的文件用什么方式打开
//        request.setMimeType("image/jpeg");
        // application/vnd.android.package-archive 说明它是一个 apk
        request.setMimeType("application/vnd.android.package-archive");
        //记住reqId
        mReqId = mDownloadManager.enqueue(request);
    }

    /**
     * 取消下载
     * 在失败后或者退出要调用，否则下载还会继续
     */
    public void cancel() {
        mDownloadManager.remove(mReqId);
    }

    /**
     * 对应{@link Activity}
     */
    public void resume() {
        // 注册监听  content://downloads/my_downloads 代表了 Downloader 下载的文件
        weakReference.get().getContentResolver()
                .registerContentObserver(Uri.parse("content://downloads/my_downloads"), true,
                        mDownLoadChangeObserver);
        // 注册广播，监听 APK 是否下载完成
        weakReference.get().registerReceiver(mDownloadReceiver,
                new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

    }

    /**
     * 对应 {@link Activity onPause}
     */
    public void onPause() {
        weakReference.get().getContentResolver().unregisterContentObserver(mDownLoadChangeObserver);
        weakReference.get().unregisterReceiver(mDownloadReceiver);
    }

    /**
     * 监听中使用，更新下载状态
     */
    private void updateView() {
        int[] bytesAndStatus = new int[]{0, 0, 0};
        DownloadManager.Query query = new DownloadManager.Query().setFilterById(mReqId);
        try (Cursor c = mDownloadManager.query(query)) {
            if (c != null && c.moveToFirst()) {
                // 已经下载的字节数
                bytesAndStatus[0] =
                        c.getInt(c.getColumnIndexOrThrow(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
                // 总需下载的字节数
                bytesAndStatus[1] =
                        c.getInt(c.getColumnIndexOrThrow(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
                // 状态所在的列索引
                bytesAndStatus[2] =
                        c.getInt(c.getColumnIndexOrThrow(DownloadManager.COLUMN_STATUS));

            }
        }

        if (mUpdateListener != null) {
            mUpdateListener.update(bytesAndStatus[0], bytesAndStatus[1]);
        }

        L.e(APK_DOWN,
                "下载进度:" + bytesAndStatus[0] + "/" + bytesAndStatus[1] + "/" + bytesAndStatus[2]);

    }

    /**
     * 安装 apk 下载完毕后自动安装
     *
     * @param context context
     * @param intent  intent
     */
    private void installApk(Context context, Intent intent) {
        long completeDownLoadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
        L.e(APK_DOWN, "installApk()");
        Uri uri;
        Intent intentInstall = new Intent();
        intentInstall.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intentInstall.setAction(Intent.ACTION_VIEW);
        if (completeDownLoadId == mReqId) {
            // 6.0 以下 获取的 下载资源的 uri 是正常的
            // file:///storage/emulated/0/Android/data/packgeName/files/Download/xxx.apk
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                uri = mDownloadManager.getUriForDownloadedFile(completeDownLoadId);
                // 6.0 后获取的 uri 为 content://downloads/my_downloads/10 这种 这个时候安装就会报错
                // 因为 7.0 后才开始了使用 content:// 内容提供者这种形式来代替直接使用 file:/// 这种
            } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                // 因此需要拿到真实的 File
                File apkFile = queryDownloadedApk(context, completeDownLoadId);
                uri = Uri.fromFile(apkFile);
            } else {
                // 7.0 后不允许直接使用 file:/// 这种 uri
                // TODO 需要清单注册
                uri = FileProvider.getUriForFile(context, "package_name.fileProvider",
                        new File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),
                                apkName));
                // 添加权限
                intentInstall.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION |
                        Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            }
            // 安装应用
            L.e(APK_DOWN, "兼容性处理完毕，开始安装");
            intentInstall.setDataAndType(uri, "application/vnd.android.package-archive");
            context.startActivity(intentInstall);
        }
    }

    public interface OnUpdateListener {
        void update(int currentByte, int totalByte);
    }

    public interface AndroidOInstallPermissionListener {
        void permissionSuccess();


        void permissionFail();
    }

    class DownloadChangeObserver extends ContentObserver {

        /**
         * Creates a content observer.
         *
         * @param handler The handler to run {@link #onChange} on, or null if none.
         */
        DownloadChangeObserver(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            updateView();
        }
    }

    class DownloadReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            boolean haveInstallPermission;
            // 兼容 Android 8.0
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // 先获取是否有安装未知来源应用的权限
                haveInstallPermission = context.getPackageManager().canRequestPackageInstalls();
                // 没有权限
                if (!haveInstallPermission) {
                    // 弹窗,并去设置页面授权
                    AndroidOPermissionActivity.sListener = new AndroidOInstallPermissionListener() {

                        @Override
                        public void permissionSuccess() {
                            installApk(context, intent);
                        }

                        @Override
                        public void permissionFail() {
                            L.e(APK_DOWN, "授权失败，无法安装应用");
                        }
                    };
                    Intent intent1 = new Intent(context, AndroidOPermissionActivity.class);
                    context.startActivity(intent1);

                } else {
                    installApk(context, intent);
                }
            } else {
                installApk(context, intent);
            }
        }
    }
}

