package com.hadesky.cacw.ui.view;

import com.hadesky.cacw.bean.TeamBean;

import java.util.List;

/**
 *
 * Created by dzysg on 2016/3/21 0021.
 */
public interface MyTeamView extends BaseView
{
    void showTeamList(List<TeamBean> list);
}
