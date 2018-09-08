package com.ch.manager.entity;

import org.beetl.sql.core.annotatoin.Table;

import java.util.Date;

@Table(name = "mt_user_info")
public class MtUserInfo extends BaseEntity {

    private String userId;
    private String name;
    private String wxName;
    private String age;
    private String place;
    private Date birth;
    private String phone;
    private String zhiYe;
    private String education;
    private String address;
    private String xingGe;
    private String wannaGo;
    private String wannaEat;
    private String wannaPlay;
    private String wannaSee;
    private String hobby;
    private String desc;

    public MtUserInfo(){
    }

    public MtUserInfo(String userId){
        this.setUserId(userId);
        this.setIsDelete("0");
    }

    public void init(){
        this.setIsDelete("0");
        this.setCreateDate(new Date());
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWxName() {
        return wxName;
    }

    public void setWxName(String wxName) {
        this.wxName = wxName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getZhiYe() {
        return zhiYe;
    }

    public void setZhiYe(String zhiYe) {
        this.zhiYe = zhiYe;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getXingGe() {
        return xingGe;
    }

    public void setXingGe(String xingGe) {
        this.xingGe = xingGe;
    }

    public String getWannaGo() {
        return wannaGo;
    }

    public void setWannaGo(String wannaGo) {
        this.wannaGo = wannaGo;
    }

    public String getWannaEat() {
        return wannaEat;
    }

    public void setWannaEat(String wannaEat) {
        this.wannaEat = wannaEat;
    }

    public String getWannaPlay() {
        return wannaPlay;
    }

    public void setWannaPlay(String wannaPlay) {
        this.wannaPlay = wannaPlay;
    }

    public String getWannaSee() {
        return wannaSee;
    }

    public void setWannaSee(String wannaSee) {
        this.wannaSee = wannaSee;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
