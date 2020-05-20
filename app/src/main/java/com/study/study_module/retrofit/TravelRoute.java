package com.study.study_module.retrofit;

import java.util.List;

/**
 * 说明：$
 * <p>
 * date: 2020/1/3 13:44
 *
 * @author syd
 * @version 1.0
 */
public class TravelRoute {

    /**
     * totalCount : 513
     * totalPage : 103
     * currentPage : 1
     * pageSize : 5
     * list : [{"rid":1,"rname":"【旅展 半价特惠 重走丝路\u2022漫游宁夏 双飞4天】银川西部影视城 穆民新村 中卫沙坡头【品美酒 回族学唱花儿
     * 感悟民俗】","price":999,"routeIntroduce":"走进【宁夏沙坡头】，感受西北大漠风情、体会\u201c大漠孤烟直，长河落日圆\u201d的塞上风光！",
     * "rflag":"1","rdate":"2018-02-09 01:13:16","isThemeTour":"0","count":0,"cid":5,
     * "rimage":"img/product/small/m304b69a4c8328f7d6b8d5dadef020fe07.jpg","sid":1,
     * "sourceId":"23677","category":null,"seller":null,"routeImgList":null},{"rid":2,
     * "rname":"【官网专享 立减¥500 张家界天门山+大峡谷+凤凰古城+玻璃栈道+玻璃桥 高铁4天 无自费5钻】印象鲵宴-赶年宴+2晚蓝湾博格酒店","price":1799,
     * "routeIntroduce":"官网专线，顶级品质！全程超豪华住宿，2晚入住超豪华铂金-蓝湾博格国际酒店，独家尊享金马VIP贵宾专用楼层。","rflag":"1",
     * "rdate":"2018-02-09 01:13:17","isThemeTour":"0","count":990,"cid":5,
     * "rimage":"img/product/small/m34866f055de8630e94e25c40f277a79ba.jpg","sid":1,
     * "sourceId":"22066","category":null,"seller":null,"routeImgList":null},{"rid":3,
     * "rname":"【官网专享 5折预售 越南下龙湾+河内+芒街 高铁4天 高级团】双导游服务 免收服务小费【越超值·美食越南】","price":1199,
     * "routeIntroduce":"双导游服务，免收服务小费，周全照顾贴心服务随心出游！品尝越南特色国宝美食，升级一餐越式炸鸡火锅宴！","rflag":"1",
     * "rdate":"2018-02-09 01:13:17","isThemeTour":"0","count":0,"cid":5,
     * "rimage":"img/product/small/m3db4d2277b5df3d98597f79082ef92d6d.jpg","sid":1,
     * "sourceId":"21998","category":null,"seller":null,"routeImgList":null},{"rid":4,
     * "rname":"【官网专享 送箱 ¥1099秒杀 华东五市+乌镇+南浔 双飞6天 南京进上海出】升级1晚豪华酒店 漫步西湖【水墨江南】","price":1099,
     * "routeIntroduce":"升级入住1晚豪华酒店；畅玩江南两大经典水乡\u2014\u2014乌镇水乡和南浔水乡，体验这里的历史文化底蕴、清丽婉约的水乡古镇风貌。",
     * "rflag":"1","rdate":"2018-02-09 01:13:17","isThemeTour":"0","count":0,"cid":5,
     * "rimage":"img/product/small/m3d91ef60e0c7fdeee97a4e2d3e5a42e84.jpg","sid":1,
     * "sourceId":"21943","category":null,"seller":null,"routeImgList":null},{"rid":5,"rname":"梅州
     * 双飞3天2晚 自由行套票【含广州直飞梅州早对晚含税往返机票+2晚梅州市区酒店】","price":999,
     * "routeIntroduce":"含广州直飞梅州早对晚含税往返机票+2晚梅州市区酒店！","rflag":"1","rdate":"2018-02-09 01:13:17",
     * "isThemeTour":"0","count":0,"cid":5,
     * "rimage":"img/product/small/m3ac1aa10b493b4b22221e19ba39f7e6a1.jpg","sid":1,
     * "sourceId":"21917","category":null,"seller":null,"routeImgList":null}]
     */

    private int totalCount;
    private int totalPage;
    private int currentPage;
    private int pageSize;
    private List<ListBean> list;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * rid : 1
         * rname : 【旅展 半价特惠 重走丝路•漫游宁夏 双飞4天】银川西部影视城 穆民新村 中卫沙坡头【品美酒 回族学唱花儿 感悟民俗】
         * price : 999.0
         * routeIntroduce : 走进【宁夏沙坡头】，感受西北大漠风情、体会“大漠孤烟直，长河落日圆”的塞上风光！
         * rflag : 1
         * rdate : 2018-02-09 01:13:16
         * isThemeTour : 0
         * count : 0
         * cid : 5
         * rimage : img/product/small/m304b69a4c8328f7d6b8d5dadef020fe07.jpg
         * sid : 1
         * sourceId : 23677
         * category : null
         * seller : null
         * routeImgList : null
         */

        private int rid;
        private String rname;
        private double price;
        private String routeIntroduce;
        private String rflag;
        private String rdate;
        private String isThemeTour;
        private int count;
        private int cid;
        private String rimage;
        private int sid;
        private String sourceId;
        private Object category;
        private Object seller;
        private Object routeImgList;

        public int getRid() {
            return rid;
        }

        public void setRid(int rid) {
            this.rid = rid;
        }

        public String getRname() {
            return rname;
        }

        public void setRname(String rname) {
            this.rname = rname;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getRouteIntroduce() {
            return routeIntroduce;
        }

        public void setRouteIntroduce(String routeIntroduce) {
            this.routeIntroduce = routeIntroduce;
        }

        public String getRflag() {
            return rflag;
        }

        public void setRflag(String rflag) {
            this.rflag = rflag;
        }

        public String getRdate() {
            return rdate;
        }

        public void setRdate(String rdate) {
            this.rdate = rdate;
        }

        public String getIsThemeTour() {
            return isThemeTour;
        }

        public void setIsThemeTour(String isThemeTour) {
            this.isThemeTour = isThemeTour;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getCid() {
            return cid;
        }

        public void setCid(int cid) {
            this.cid = cid;
        }

        public String getRimage() {
            return rimage;
        }

        public void setRimage(String rimage) {
            this.rimage = rimage;
        }

        public int getSid() {
            return sid;
        }

        public void setSid(int sid) {
            this.sid = sid;
        }

        public String getSourceId() {
            return sourceId;
        }

        public void setSourceId(String sourceId) {
            this.sourceId = sourceId;
        }

        public Object getCategory() {
            return category;
        }

        public void setCategory(Object category) {
            this.category = category;
        }

        public Object getSeller() {
            return seller;
        }

        public void setSeller(Object seller) {
            this.seller = seller;
        }

        public Object getRouteImgList() {
            return routeImgList;
        }

        public void setRouteImgList(Object routeImgList) {
            this.routeImgList = routeImgList;
        }
    }
}
