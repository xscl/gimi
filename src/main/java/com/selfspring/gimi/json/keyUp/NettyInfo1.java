package com.selfspring.gimi.json.keyUp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by ckyang on 2019/10/22.
 */
public class NettyInfo1 {

    @JSONField(name="BOXNAME")
    private String BOXNAME;

    @JSONField(name="WZID")
    private String WZID;

    @JSONField(name="MSGTYPE")
    private String MSGTYPE;

    @JSONField(name="GOODS")
    private String GOODS;

    @JSONField(name="MESSAGE")
    private String MESSAGE;

    public String getBOXNAME() {
        return BOXNAME;
    }

    public void setBOXNAME(String BOXNAME) {
        this.BOXNAME = BOXNAME;
    }

    public String getWZID() {
        return WZID;
    }

    public void setWZID(String WZID) {
        this.WZID = WZID;
    }

    public String getMSGTYPE() {
        return MSGTYPE;
    }

    public void setMSGTYPE(String MSGTYPE) {
        this.MSGTYPE = MSGTYPE;
    }

    public String getGOODS() {
        return GOODS;
    }

    public void setGOODS(String GOODS) {
        this.GOODS = GOODS;
    }

    public String getMESSAGE() {
        return MESSAGE;
    }

    public void setMESSAGE(String MESSAGE) {
        this.MESSAGE = MESSAGE;
    }

    public static void main(String[] args) {
        NettyInfo1 inf = new NettyInfo1();
        inf.setBOXNAME("boxname");
        inf.setGOODS("goods");
        inf.setMESSAGE("message");
        inf.setWZID("wzid");
        inf.setMSGTYPE("msgtype");
        System.out.println(JSON.toJSONString(inf));
    }
}
