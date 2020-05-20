package com.study.study_module.net.urlconnection;

import java.util.Map;

/**
 * 说明：$
 * <p>
 * date: 2019/12/30 16:31
 *
 * @author syd
 * @version 1.0
 */
public interface IEncrypt {
    public String encrypt(String src);

    public String dencypt(String src);

    String encrypt(String urlPath, Map<String, Object> params);
}
