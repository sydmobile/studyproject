package com.syd.study.net.urlconnection;

/**
 * 说明：$
 * <p>
 * date: 2019/12/30 15:41
 *
 * @author syd
 * @version 1.0
 */
public class FilePair {
    String mFileName;
    byte[] mBinaryData;
    public FilePair(String mFileName,byte[] mBinaryData){
        this.mFileName = mFileName;
        this.mBinaryData = mBinaryData;
    }
}
