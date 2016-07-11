package com.hadesky.cacw.ui.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hadesky.cacw.R;
import com.hadesky.cacw.config.MyApp;
import com.hadesky.cacw.tag.IntentTag;
import com.hadesky.cacw.ui.activity.MyInfoActivity;
import com.hadesky.cacw.ui.activity.MyTeamActivity;
import com.hadesky.cacw.ui.activity.SettingActivity;
import com.hadesky.cacw.ui.activity.UserInfoActivity;
import com.hadesky.cacw.ui.widget.AnimProgressDialog;

/**
 * MeFragment
 * Created by Bright Van on 2015/9/7/007.
 */

public class MeFragment extends BaseFragment implements View.OnClickListener {
    private ImageView userImageView;
    private TextView userName;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initViews(View view) {
        userImageView = (ImageView) view.findViewById(R.id.iv_me_avatar);
        userName = (TextView) view.findViewById(R.id.tv_me_username);
        //暂时使用这种方式设置listener
        view.findViewById(R.id.layout_setting).setOnClickListener(this);
        view.findViewById(R.id.layout_complete).setOnClickListener(this);
        view.findViewById(R.id.layout_memo).setOnClickListener(this);
        view.findViewById(R.id.layout_myinfo).setOnClickListener(this);
        view.findViewById(R.id.layout_remind).setOnClickListener(this);
        view.findViewById(R.id.layout_my_team).setOnClickListener(this);
    }

    @Override
    protected void setupViews(Bundle bundle) {
        MyApp app = (MyApp) getActivity().getApplication();
        loadUserInfo();
    }

    private void loadUserInfo() {
        userName.setText(getNickName());
//        userImageView.setImageBitmap(getUserAvatar(app));
    }

    /**
     * 在Session中获取昵称
     * @return 用户名
     */
    private String getNickName() {
        String nickName = MyApp.getCurrentUser().getNickName();
        if (nickName == null) {
            return "蚂蚁";
        }
        return nickName;
    }

    private Bitmap getUserAvatar() {

        return BitmapFactory.decodeResource(getResources(), R.drawable.default_user_image);
    }


    @Override
    public void onClick(View v) {
        final int id = v.getId();
        switch (id) {
            case R.id.layout_setting:
                startActivity(new Intent(getContext(), SettingActivity.class));
                break;
            case R.id.layout_myinfo:
                //这里不需要传入任何信息
                Intent intent = new Intent(getContext(), MyInfoActivity.class);
                intent.putExtra(IntentTag.TAG_USER_ID, (long)0);//因为暂时没联网，默认用0号当作为当前用户
                startActivity(intent);
                break;
            case R.id.layout_complete:
                Toast.makeText(getContext(), "已完成事项", Toast.LENGTH_SHORT).show();
                break;
            case R.id.layout_remind:
                Toast.makeText(getContext(), "已设置提醒", Toast.LENGTH_SHORT).show();
                intent = new Intent(getContext(), UserInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.layout_memo:
                AnimProgressDialog dialog = new AnimProgressDialog(getContext(), true, null, "载入备忘录中...");
                dialog.show();
                break;
            case R.id.layout_my_team:
                Intent intent1 = new Intent(getContext(), MyTeamActivity.class);
                startActivity(intent1);
                break;
            default:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        loadUserInfo();
    }
}
