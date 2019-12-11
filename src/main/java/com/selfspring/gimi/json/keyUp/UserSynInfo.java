package com.selfspring.gimi.json.keyUp;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * Created by ckyang on 2019/10/14.
 */
public class UserSynInfo {
    /**
     * 用户Id
     *
     * 作为更新表的主键使用
     */
    @JSONField(name="UserId")
    private Long userId;
    /**
     * 用户昵称
     */
    @JSONField(name="NickName")
    private String nickName;
    /**
     * 用户的NetId
     */
    @JSONField(name="NetId")
    private Integer netId;
    /**
     * 用户加入家庭时间，时间格式为标准时间戳格式，如2016-03-23 11:43:07
     */
    @JSONField(name="CreateTime",format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 全同步不需要，单条同步需要
     */
    @JSONField(name="SN")
    private String sN;

    /**
     * 更新时间，时间格式为标准时间戳格式，如2016-03-23 11:43:07
     */
    @JSONField(name="UpdateTime",format="yyyy-MM-dd HH:mm:ss")
    private String updateTime;


    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getNickName() {
        return nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public Integer getNetId() {
        return netId;
    }
    public void setNetId(Integer netId) {
        this.netId = netId;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public String getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
    public String getsN() {
        return sN;
    }
    public void setsN(String sN) {
        this.sN = sN;
    }
}
