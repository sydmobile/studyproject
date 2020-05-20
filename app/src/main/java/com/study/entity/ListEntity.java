package com.study.entity;

import java.util.List;

/**
 * 说明：$d$
 * <p>
 * date: 2019/6/14 15:38
 *
 * @author syd
 * @version 1.0
 */
public class ListEntity {

    /**
     * success : true
     * code : 0
     * message : all right
     * data : [{"id":5,"name":"铭晟云图总部","address":"北京市丰台区中核路一号","open":true,"logoUrl":"http://www.msyt-tech
     * .com/others/static/hospital/msyt-logo.jpg","currentHospital":true},{"id":6,"name":"北京妇幼保健院","address":null,
     * "open":true,"logoUrl":"http://www.msyt-tech.com/others/static/hospital/wujingyi.png","currentHospital":false},
     * {"id":1,"name":"北京海淀医院","address":null,"open":false,"logoUrl":"http://www.msyt-tech
     * .com/others/static/hospital/haidian.png","currentHospital":false},{"id":2,"name":"北京电力医院","address":null,
     * "open":false,"logoUrl":"http://www.msyt-tech.com/others/static/hospital/beijingdianli.png",
     * "currentHospital":false},{"id":3,"name":"北京协和医院","address":null,"open":false,"logoUrl":"http://www.msyt-tech
     * .com/others/static/hospital/beijingxiehe.png","currentHospital":false},{"id":4,"name":"武警医院","address":null,
     * "open":false,"logoUrl":"http://www.msyt-tech.com/others/static/hospital/wujingyi.png","currentHospital":false}]
     */

    private boolean success;
    private int code;
    private String message;
    private List<DataBean> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 5
         * name : 铭晟云图总部
         * address : 北京市丰台区中核路一号
         * open : true
         * logoUrl : http://www.msyt-tech.com/others/static/hospital/msyt-logo.jpg
         * currentHospital : true
         */

        private int id;
        private String name;
        private String address;
        private boolean open;
        private String logoUrl;
        private boolean currentHospital;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public boolean isOpen() {
            return open;
        }

        public void setOpen(boolean open) {
            this.open = open;
        }

        public String getLogoUrl() {
            return logoUrl;
        }

        public void setLogoUrl(String logoUrl) {
            this.logoUrl = logoUrl;
        }

        public boolean isCurrentHospital() {
            return currentHospital;
        }

        public void setCurrentHospital(boolean currentHospital) {
            this.currentHospital = currentHospital;
        }
    }
}
