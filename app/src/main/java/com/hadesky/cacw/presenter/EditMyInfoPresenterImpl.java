package com.hadesky.cacw.presenter;

import android.net.Uri;
import android.util.Log;

import com.hadesky.cacw.bean.UserBean;
import com.hadesky.cacw.config.MyApp;
import com.hadesky.cacw.ui.view.EditMyInfoView;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by MicroStudent on 2016/7/10.
 */

public class EditMyInfoPresenterImpl implements EditMyInfoPresenter {
    private EditMyInfoView mEditMyInfoView;
    private UserBean mCurrentUser;

    public EditMyInfoPresenterImpl(EditMyInfoView view) {
        mEditMyInfoView = view;
        mCurrentUser = MyApp.getCurrentUser();
    }

    @Override
    public void updateUserAvatar(Uri avatarPath) {
//        BmobUser newUser = new BmobUser();
//        newUser.setEmail("xxx@163.com");
//        BmobUser bmobUser = BmobUser.getCurrentUser(context);
//        newUser.update(bmobUser.getObjectId(),new UpdateListener() {
//            @Override
//            public void done(BmobException e) {
//                if(e==null){
//                    toast("更新用户信息成功");
//                }else{
//                    toast("更新用户信息失败:" + e.getMessage());
//                }
//            }
//        });
    }

    @Override
    public void updateSexual(final Byte sex) {
        UserBean newUser = new UserBean();
        newUser.setSex(sex);
        UserBean currentUser = BmobUser.getCurrentUser(UserBean.class);

        newUser.update(currentUser.getObjectId(),new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    mEditMyInfoView.setSex(sex);
                }else{
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void updateSummary(final String summary) {
        UserBean newUser = new UserBean();
        newUser.setSummary(summary);
        UserBean currentUser = BmobUser.getCurrentUser(UserBean.class);

        newUser.update(currentUser.getObjectId(),new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    mEditMyInfoView.setSummary(summary);
                }else{
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void updateNickName(final String nickName) {
        if (nickName == null || nickName.isEmpty()) {
            mEditMyInfoView.showMsg("不能设置为空");
            return;
        }
        UserBean newUser = new UserBean();
        newUser.setNickName(nickName);
        UserBean currentUser = BmobUser.getCurrentUser(UserBean.class);

        newUser.update(currentUser.getObjectId(),new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    mEditMyInfoView.setNickName(nickName);
                }else{
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void loadInfo() {
        if (mCurrentUser != null) {
            mEditMyInfoView.setSex(mCurrentUser.getSex());
            mEditMyInfoView.setNickName(mCurrentUser.getNickName());
            mEditMyInfoView.setSummary(mCurrentUser.getSummary());
            mEditMyInfoView.setUserName(mCurrentUser.getUsername());
        }
    }
}
