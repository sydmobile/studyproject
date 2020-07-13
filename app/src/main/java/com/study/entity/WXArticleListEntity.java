package com.study.entity;

import java.util.List;

/**
 * 说明：微信文章列表对应返回数据对象
 * <p>
 * date: 2020/5/28 14:26
 *
 * @author syd
 * @version 1.0
 */
public class WXArticleListEntity {

    /**
     * data : {"curPage":1,"datas":[{}],"pageCount":47,"size":20,"total":932}
     * errorCode : 0
     * errorMsg :
     */

    private DataBean data;
    private int errorCode;
    private String errorMsg;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public static class DataBean {

        /**
         * curPage : 1
         * datas : [{}]
         * pageCount : 47
         * size : 20
         * total : 932
         */

        private int curPage;
        private int pageCount;
        private int size;
        private int total;
        private List<WXArticle> datas;

        public int getCurPage() {
            return curPage;
        }

        public void setCurPage(int curPage) {
            this.curPage = curPage;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<WXArticle> getDatas() {
            return datas;
        }

        public void setDatas(List<WXArticle> datas) {
            this.datas = datas;
        }
    }
}
