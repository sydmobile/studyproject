package com.study.study_module.net.urlconnection;

import java.util.HashMap;
import java.util.Map;

/**
 * 说明：$
 * <p>
 * date: 2019/12/30 15:36
 *
 * @author syd
 * @version 1.0
 */
public interface IRequest {

    public String getBaseUrl();

    public String getMethod();

    public IEncrypt getEncrypt();

    public HashMap<String,Object> getParam();

    public Map<String,FilePair> getFilePair();

    public Map<String,String> getHeaders();

}
