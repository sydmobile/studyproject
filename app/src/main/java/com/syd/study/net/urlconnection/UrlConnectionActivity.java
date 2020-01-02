package com.syd.study.net.urlconnection;

import android.os.Bundle;
import android.text.TextUtils;

import com.syd.study.R;
import com.syd.study.base.BaseActivity;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

import javax.net.ssl.HttpsURLConnection;

import androidx.annotation.Nullable;

/**
 * 说明：$
 * <p>
 * date: 2019/12/30 15:43
 *
 * @author syd
 * @version 1.0
 */
public class UrlConnectionActivity extends BaseActivity {
    private static final String END = "/r/n";
    public static int connectionTimeOut = 30000;
    public static int readSocketTimeOut = 30000;

    /**
     * 处理请求头
     *
     * @param urlConnection urlConnection
     * @param method        请求方法
     * @param mHeaders      请求头
     * @throws ProtocolException 异常
     */
    private static void normalSetting(HttpURLConnection urlConnection, Method method,
                                      Map<String, String> mHeaders
    ) throws ProtocolException {
        urlConnection.setConnectTimeout(connectionTimeOut);
        urlConnection.setReadTimeout(readSocketTimeOut);
        urlConnection.setRequestMethod(method.toString());

        if (mHeaders != null && mHeaders.size() > 0) {
            Set<String> stringKeys = mHeaders.keySet();
            for (String key : stringKeys) {
                urlConnection.setRequestProperty(key, mHeaders.get(key));
            }
        }
        if (method == Method.GET) {
            // 设置请求头
            urlConnection.setRequestProperty("Accept-Encoding", "gzip");

        } else {
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
        }

    }

    private static String convertStreamToString(InputStream is) {
        InputStreamReader inputStreamReader = new InputStreamReader(is);
        BufferedReader reader = new BufferedReader(inputStreamReader, 512);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeQuietly(reader);
            closeQuietly(inputStreamReader);
        }
        return stringBuilder.toString();
    }

    private static void closeQuietly(Closeable io) {
        if (io != null) {
            try {
                io.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static HttpURLConnection openUrlConnection(URL url) throws IOException {
        String scheme = url.getProtocol();
        boolean isHttpsRequest = false;
        if ("https".equals(scheme)) {
            isHttpsRequest = true;
        }
        if (isHttpsRequest) {
            return (HttpsURLConnection) url.openConnection();
            // TODO 处理 https 证书 1.需要测试 https 请求;2.如需设置证书，需要验证是否会对其它 https 请求有影响
            // trustHost(httpURLConnection);
        } else {
            return (HttpURLConnection) url.openConnection();
        }
    }

    private static void addFormField(StringBuilder writer, final String name, final String value,
                                     String boundary) {
        writer.append("--").append(boundary).append(END)
                .append("Content-Disposition: form-data; name=\"")
                .append(name)
                .append("\"").append(END).append(END).append(value).append(END);


    }

    private static void addFilePart(String fieldName, byte[] data, String boundary,
                                    OutputStream outputStream) throws IOException {
        String stringBuilder = "--" + boundary + END +
                "Content-Disposition: form-data; name=\"" +
                "pic" + "\"; filename=\"" + fieldName + "\"" +
                END + "Content-Type: application/octet-stream" + END +
                END;
        outputStream.write(stringBuilder.getBytes());
        outputStream.write(data);
        outputStream.write(END.getBytes());
    }

    private static void finishWrite(OutputStream outputStream, String boundary) throws IOException {
        outputStream.write(END.getBytes());
        outputStream.write(("--" + boundary + "--").getBytes());
        outputStream.write(END.getBytes());
        outputStream.flush();
        outputStream.close();
    }

    private static InputStream wrapStream(String contentEncoding, InputStream inputStream) throws IOException {
        if (contentEncoding == null || "identity".equalsIgnoreCase(contentEncoding)) {
            return inputStream;
        }
        if ("gzip".equalsIgnoreCase(contentEncoding)) {
            return new GZIPInputStream(inputStream);
        }
        if ("deflate".equalsIgnoreCase(contentEncoding)) {
            return new InflaterInputStream(inputStream, new Inflater(false), 512);
        }
        throw new RuntimeException("unsupported content-encoding:" + contentEncoding);
    }

    /**
     * get 请求
     *
     * @param request 请求报文
     * @return 响应内容
     */
    public static String get(IRequest request) {
        InputStream inputStream = null;
        InputStream stream = null;
        HttpURLConnection httpURLConnection;
        try {
            URL url = new URL(buildGetUrl(request.getBaseUrl(), request.getParam(),
                    request.getEncrypt()));
            httpURLConnection = openUrlConnection(url);
            normalSetting(httpURLConnection, Method.GET, request.getHeaders());
            // 进行连接
            httpURLConnection.connect();
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inputStream = httpURLConnection.getInputStream();
                String contentEncoding = httpURLConnection.getContentEncoding();
                stream = wrapStream(contentEncoding, inputStream);
                return convertStreamToString(stream);

            }

        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } finally {
            closeQuietly(inputStream);
            closeQuietly(stream);
        }
        return "";
    }

    private static String buildGetUrl(String urlPath, Map<String, Object> params,
                                      IEncrypt encrypt) {
        if (TextUtils.isEmpty(urlPath) || params == null || params.size() == 0) {
            return urlPath;
        }
        if (!urlPath.endsWith("?")) {
            urlPath += "?";
        }
        String paramsStr = buildGetParams(params);

        // 加密使用
//        if (encrypt != null) {
////            paramsStr == encrypt.encrypt(urlPath);
//        }

        return urlPath + paramsStr;

    }

    private static String buildGetParams(Map<String, Object> params) {
        StringBuilder sb = new StringBuilder();
        Set<String> keys = params.keySet();
        for (String key : keys) {
            if (params.get(key) == null) {
                continue;
            }
            sb = sb.append(key).append("=")
                    .append(URLEncoder.encode(Objects.requireNonNull(params.get(key)).toString()))
                    .append("&");

        }
        return sb.substring(0, sb.length() - 1).toLowerCase();

    }

    public static String post(IRequest request) {
//        String boundary = UUID.randomUUID().toString();
//
//        HttpURLConnection httpURLConnection = null;
//        OutputStream outputStream = null;
//        InputStream inputStream = null;
//        URL url = null;
//        try {
//            url = new URL(request.getBaseUrl());
//            httpURLConnection = openUrlConnection(url);
//            if (request.getParam() != null && request.getParam().size() > 0) {
//                httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data; " +
//                        "boundary=" + boundary);
//                httpURLConnection.connect();
//                outputStream = httpURLConnection.getOutputStream();
//                addBodyParams(request.getParam(),request.getFilePair(),outputStream,boundary);
//
//            }else {
//                httpURLConnection.setRequestProperty("Content-Type",
//                "application/x-www-form-urlencoded");
//                Uri.Builder builder = new Uri.Builder();
//                for ()
//                builder.appendQueryParameter("content", message);
//                String query = builder.build().getEncodedQuery();
//                outputStream = new DataOutputStream(httpURLConnection.getOutputStream());
//                outputStream.write(query.getBytes());
//            }
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return "";

    }

    private static void addBodyParams(HashMap<String, Object> map, Map<String, FilePair> filePair,
                                      OutputStream outputStream, String boundary) throws IOException {
        boolean didWriteData = false;
        StringBuilder stringBuilder = new StringBuilder();
        Set<String> keys = ((Map<String, Object>) map).keySet();
        for (String key : keys) {
            if (((Map<String, Object>) map).get(key) != null) {
                addFormField(stringBuilder, key, ((Map<String, Object>) map).get(key).toString(),
                        boundary);
            }
        }
        if (stringBuilder.length() > 0) {
            didWriteData = true;
            outputStream = new DataOutputStream(outputStream);
            outputStream.write(stringBuilder.toString().getBytes());
        }
        // upload files like POST files to server
        if (filePair != null && filePair.size() > 0) {
            Set<String> fileKeys = filePair.keySet();
            for (String key : fileKeys) {
                FilePair pair = filePair.get(key);
                byte[] data = pair.mBinaryData;
                if (data == null || data.length < 1) {
                    continue;
                } else {
                    didWriteData = true;
                    addFilePart(pair.mFileName, data, boundary, outputStream);
                }
            }
        }

        if (didWriteData) {
            finishWrite(outputStream, boundary);
        }


    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
    }


    public enum Method {
        GET,
        POST
    }

}
