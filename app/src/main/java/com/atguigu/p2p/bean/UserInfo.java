package com.atguigu.p2p.bean;

/**
 * Created by 李金桐 on 2017/3/15.
 * QQ: 474297694
 * 功能: xxxx
 */

public class UserInfo {
    private DataBean data;
    private boolean success;
   public class DataBean {
        /**
         * imageurl : http://182.92.5.3:8081/P2PInvest/images/tx.png
         * iscredit : true
         * name : xiaolongge
         * phone : 15321970103
         */

        private String imageurl;
        private String iscredit;
        private String name;
        private String phone;

        public String getImageurl() {
            return imageurl;
        }

        public void setImageurl(String imageurl) {
            this.imageurl = imageurl;
        }

        public String getIscredit() {
            return iscredit;
        }

        public void setIscredit(String iscredit) {
            this.iscredit = iscredit;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
