package com.hadesky.cacw.bean;


import java.io.Serializable;

/**
 *
 * Created by 45517 on 2015/10/17.
 */
public class UserBean implements Serializable
{

    public static final Byte SEX_MALE = 0;//性别男
    public static final Byte SEX_FEMALE = 1;//性别女
    public static final Byte SEX_UNKNOW = 2;//性别保密

    private String mUsername;
    private String mNickName;
    private int mId;
    private Byte mSex = 0;//0是男，1是女，2是保密
    private String mPhoneNumber;
    private String mShortNumber;//短号
    private String mSummary;//个人简介
    private String mAddress;//地址
    private String mEmail;
    public UserBean() {
    }

    public UserBean(String nickName)
    {
        mNickName = nickName;
    }
    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }

    public String getNickName()
    {
        if (mNickName == null) {
            return "蚂蚁";
        }
        return mNickName;
    }


    public int  getId()
    {
        return mId;
    }

    public void setId(int  id)
    {
        mId = id;
    }

    public String getUsername()
    {
        return mUsername;
    }

    public void setUsername(String username)
    {
        mUsername = username;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public void setNickName(String nickName)
    {
        mNickName = nickName;
    }


    public Byte getSex() {
        return mSex;
    }

    public void setSex(Byte mSex) {
        this.mSex = mSex;
    }

    public String getShortNumber() {
        return mShortNumber;
    }

    public void setShortNumber(String mShortNumber) {
        this.mShortNumber = mShortNumber;
    }

    public String getPhoneNumber()
    {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        mPhoneNumber = phoneNumber;
    }

    public String getEmail()
    {
        return mEmail;
    }

    public void setEmail(String email)
    {
        mEmail = email;
    }

    public String getAvatarUrl()
    {
       return "";
    }

    @Override
    public String toString() {
        return getNickName();
    }


}
