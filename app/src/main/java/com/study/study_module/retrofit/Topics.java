package com.study.study_module.retrofit;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * 说明：$
 * <p>
 * date: 2020/1/3 11:29
 *
 * @author syd
 * @version 1.0
 */
public class Topics {

    @NotNull
    @Override
    public String toString() {
        return "Topics{" +
                "pageSize=" + pageSize +
                ", totalItems=" + totalItems +
                ", totalPages=" + totalPages +
                ", data=" + data +
                '}';
    }

    private int pageSize;
    private int totalItems;
    private int totalPages;
    private List<DataBean> data;

    public int getPageSize() {

        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 7SVG9JQicw9
         * newsArray : [{"id":2229161,"url":"https://tech.sina.com.cn/roll/2020-01-03/doc-iihnzhha0020929.shtml","title":"张忠谋谈三星：台积电暂时占优 但还没赢得整个战争","siteName":"新浪科技","mobileUrl":"https://tech.sina.com.cn/roll/2020-01-03/doc-iihnzhha0020929.shtml","autherName":"TechWeb","duplicateId":1,"publishDate":"2020-01-03T02:51:13.000Z","language":"zh-cn","statementType":1},{"id":2229209,"url":"https://tech.sina.com.cn/roll/2020-01-03/doc-iihnzahk1686522.shtml","title":"张忠谋谈三星:台积电暂时占优 但还没赢得整个战争","siteName":"新浪科技","mobileUrl":"https://tech.sina.com.cn/roll/2020-01-03/doc-iihnzahk1686522.shtml","autherName":"","duplicateId":1,"publishDate":"2020-01-03T02:56:17.000Z","language":"zh-cn","statementType":1},{"id":2229162,"url":"https://tech.qq.com/a/20200103/022854.htm","title":"张忠谋谈竞争对手三星：目前台积电暂时占优 但还没赢得整个战争","siteName":"腾讯科技","mobileUrl":"https://tech.qq.com/a/20200103/022854.htm","autherName":"www.qq.com","duplicateId":2,"publishDate":"2020-01-03T02:49:14.000Z","language":"zh-cn","statementType":1}]
         * createdAt : 2020-01-03T03:01:37.420Z
         * eventData : []
         * publishDate : 2020-01-03T03:05:53.082Z
         * summary : 台积电创始人张忠谋日前在接受媒体采访时谈到了竞争对手三星电子，他表示，三星电子是很厉害的对手，目前台积电暂时占优势，但台积电跟三星的战争绝对还没结束，台积电还没有赢。
         * title : 张忠谋谈三星：台积电暂时占优，但还没赢得整个战争
         * updatedAt : 2020-01-03T03:19:20.614Z
         * timeline : 7SkrX89hG6i
         * order : 208104
         * hasInstantView : true
         * extra : {"instantView":true}
         */

        private String id;
        private String createdAt;
        private String publishDate;
        private String summary;
        private String title;
        private String updatedAt;
        private String timeline;
        private int order;
        private boolean hasInstantView;
        private ExtraBean extra;
        private List<NewsArrayBean> newsArray;
        private List<?> eventData;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getPublishDate() {
            return publishDate;
        }

        public void setPublishDate(String publishDate) {
            this.publishDate = publishDate;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getTimeline() {
            return timeline;
        }

        public void setTimeline(String timeline) {
            this.timeline = timeline;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public boolean isHasInstantView() {
            return hasInstantView;
        }

        public void setHasInstantView(boolean hasInstantView) {
            this.hasInstantView = hasInstantView;
        }

        public ExtraBean getExtra() {
            return extra;
        }

        public void setExtra(ExtraBean extra) {
            this.extra = extra;
        }

        public List<NewsArrayBean> getNewsArray() {
            return newsArray;
        }

        public void setNewsArray(List<NewsArrayBean> newsArray) {
            this.newsArray = newsArray;
        }

        public List<?> getEventData() {
            return eventData;
        }

        public void setEventData(List<?> eventData) {
            this.eventData = eventData;
        }

        public static class ExtraBean {
            /**
             * instantView : true
             */

            private boolean instantView;

            public boolean isInstantView() {
                return instantView;
            }

            public void setInstantView(boolean instantView) {
                this.instantView = instantView;
            }
        }

        public static class NewsArrayBean {
            /**
             * id : 2229161
             * url : https://tech.sina.com.cn/roll/2020-01-03/doc-iihnzhha0020929.shtml
             * title : 张忠谋谈三星：台积电暂时占优 但还没赢得整个战争
             * siteName : 新浪科技
             * mobileUrl : https://tech.sina.com.cn/roll/2020-01-03/doc-iihnzhha0020929.shtml
             * autherName : TechWeb
             * duplicateId : 1
             * publishDate : 2020-01-03T02:51:13.000Z
             * language : zh-cn
             * statementType : 1
             */

            private int id;
            private String url;
            private String title;
            private String siteName;
            private String mobileUrl;
            private String autherName;
            private int duplicateId;
            private String publishDate;
            private String language;
            private int statementType;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getSiteName() {
                return siteName;
            }

            public void setSiteName(String siteName) {
                this.siteName = siteName;
            }

            public String getMobileUrl() {
                return mobileUrl;
            }

            public void setMobileUrl(String mobileUrl) {
                this.mobileUrl = mobileUrl;
            }

            public String getAutherName() {
                return autherName;
            }

            public void setAutherName(String autherName) {
                this.autherName = autherName;
            }

            public int getDuplicateId() {
                return duplicateId;
            }

            public void setDuplicateId(int duplicateId) {
                this.duplicateId = duplicateId;
            }

            public String getPublishDate() {
                return publishDate;
            }

            public void setPublishDate(String publishDate) {
                this.publishDate = publishDate;
            }

            public String getLanguage() {
                return language;
            }

            public void setLanguage(String language) {
                this.language = language;
            }

            public int getStatementType() {
                return statementType;
            }

            public void setStatementType(int statementType) {
                this.statementType = statementType;
            }
        }
    }
}
