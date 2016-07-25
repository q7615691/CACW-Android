package com.hadesky.cacw.ui.activity;

import android.content.DialogInterface;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.hadesky.cacw.R;
import com.hadesky.cacw.adapter.MessageListAdapter;
import com.hadesky.cacw.adapter.base.BaseAdapter;
import com.hadesky.cacw.adapter.viewholder.BaseViewHolder;
import com.hadesky.cacw.bean.MessageBean;
import com.hadesky.cacw.presenter.MessageListPresenter;
import com.hadesky.cacw.presenter.MessageListPresenterImpl;
import com.hadesky.cacw.ui.view.MessageListView;

import java.util.ArrayList;
import java.util.List;

public class MessageListActivity extends BaseActivity implements MessageListView, SwipeRefreshLayout.OnRefreshListener, BaseViewHolder.OnItemLongClickListener {

    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private BaseAdapter<MessageBean> mAdapter;
    private MessageListPresenter mListPresenter;

    @Override
    public int getLayoutId()
    {
        return R.layout.activity_message_list;
    }

    @Override
    public void initView()
    {
        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.layout_swipe_refresh);
        mRecyclerView = (RecyclerView) findViewById(R.id.rcv_message);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public void setupView()
    {
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setColorSchemeColors(ResourcesCompat.getColor(getResources(), R.color.color_primary, null));
        mRefreshLayout.setProgressViewOffset(true, -100, 50);
        mAdapter = new MessageListAdapter(new ArrayList<MessageBean>(), R.layout.list_item_message, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        mListPresenter = new MessageListPresenterImpl(this);
    }

    @Override
    public void showMessage(List<MessageBean> list)
    {
        mAdapter.setDatas(list);
    }

    @Override
    public void showProgress()
    {
        mRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress()
    {
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showMsg(String s)
    {
        showToast(s);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        mListPresenter.onDestroy();
    }

    @Override
    public void onRefresh() {
        mListPresenter.loadMessage();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mListPresenter.loadMessageQuietly();
    }

    @Override
    public boolean OnItemLongClick(View view, final int position) {
        new AlertDialog.Builder(this).setItems(new String[]{"删除聊天记录"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mListPresenter.deleteMessage(mAdapter.getDatas().get(position));
                mAdapter.getDatas().remove(position);
                mAdapter.notifyItemRemoved(position);
            }
        }).create().show();
        return true;
    }
}
