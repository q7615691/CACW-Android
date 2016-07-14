package com.hadesky.cacw.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.hadesky.cacw.R;
import com.hadesky.cacw.bean.TeamBean;
import com.hadesky.cacw.config.MyApp;
import com.hadesky.cacw.ui.fragment.ProjectFragment;

public class ProjectsActivity extends BaseActivity {
    private Toolbar toolbar;

    private TeamBean mTeam;


    @Override
    public int getLayoutId() {
        return R.layout.activity_project;
    }

    @Override
    public void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }


    @Override
    public void setupView() {


        Intent i = getIntent();
        mTeam = (TeamBean) i.getSerializableExtra(ProjectFragment.TeamTAG);
        if (mTeam==null)
        {
            showToast("找不到项目");
            finish();
            return;
        }




        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(mTeam.getTeamName());
        }

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.container);

        if (fragment == null) {
            fragment = new ProjectFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable(ProjectFragment.TeamTAG,mTeam);
            fragment.setArguments(bundle);
            fm.beginTransaction()
                    .add(R.id.container, fragment)
                    .commit();
        }
    }

    private void createProject()
    {
        if (mTeam.getAdminUserId().equals(MyApp.getCurrentUser().getObjectId())) {

            View view = getLayoutInflater().inflate(R.layout.dialog_nick_name, null);
            final EditText editText = (EditText) view.findViewById(R.id.edit_text);
            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.project_name))
                    .setView(view)
                    .setPositiveButton(getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {





                        }
                    });
            builder.create().show();
        }else
        {
            showToast("你当前不是管理员");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_projects, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add) {

            createProject();
            return true;
        }else if (item.getItemId()==android.R.id.home)
        {
            finish();
        }

        return false;
    }
}
