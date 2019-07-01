package com.syd.study.entity;

/**
 * 说明：结果结构对应的实体
 * <p>
 * date: 2019/6/14 15:37
 *
 * @author syd
 * @version 1.0
 */
public class ResultEntity {

    /**
     * success : true
     * code : 0
     * message : all right
     * data : {"id":1,"refCoordX":1.29442322623E7,"refCoordY":4840925.3448,"fengniaoMapId":"ww2222",
     * "fengniaoMapThemeId":"ww2222"}
     */

    private boolean success;
    private int code;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * refCoordX : 1.29442322623E7
         * refCoordY : 4840925.3448
         * fengniaoMapId : ww2222
         * fengniaoMapThemeId : ww2222
         */

        private int id;
        private double refCoordX;
        private double refCoordY;
        private String fengniaoMapId;
        private String fengniaoMapThemeId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public double getRefCoordX() {
            return refCoordX;
        }

        public void setRefCoordX(double refCoordX) {
            this.refCoordX = refCoordX;
        }

        public double getRefCoordY() {
            return refCoordY;
        }

        public void setRefCoordY(double refCoordY) {
            this.refCoordY = refCoordY;
        }

        public String getFengniaoMapId() {
            return fengniaoMapId;
        }

        public void setFengniaoMapId(String fengniaoMapId) {
            this.fengniaoMapId = fengniaoMapId;
        }

        public String getFengniaoMapThemeId() {
            return fengniaoMapThemeId;
        }

        public void setFengniaoMapThemeId(String fengniaoMapThemeId) {
            this.fengniaoMapThemeId = fengniaoMapThemeId;
        }
    }
}
