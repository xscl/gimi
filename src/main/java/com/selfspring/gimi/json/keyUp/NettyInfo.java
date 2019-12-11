package com.selfspring.gimi.json.keyUp;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by ckyang on 2019/10/22.
 */
public class NettyInfo {
    private String boxname;

    private String wzid;

    private String msgtype;

    private String goods;

    private String message;

    public String getBoxname() {
        return boxname;
    }

    public void setBoxname(String boxname) {
        this.boxname = boxname;
    }

    public String getWzid() {
        return wzid;
    }

    public void setWzid(String wzid) {
        this.wzid = wzid;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static void main(String[] args) {
//        NettyInfo inf = new NettyInfo();
//        inf.setBoxname("boxname");
//        inf.setGoods("goods");
//        inf.setMessage("message");
//        inf.setWzid("wzid");
//        inf.setMsgtype("msgtype");
//        System.out.println(JSON.toJSONString(inf));

        String resp = "{\"BOXNAME\":\"boxname\",\"GOODS\":\"goods\",\"MESSAGE\":\"message\",\"MSGTYPE\":\"msgtype\",\"WZID\":\"wzid\"}";
        JSONObject obj = JSONObject.parseObject(resp);
        NettyInfo nt = JSONObject.toJavaObject(obj, NettyInfo.class);
        System.out.println(nt.getBoxname());

    }
}
