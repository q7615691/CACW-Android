package com.hadesky.cacw.ui.fragment;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;

import com.hadesky.cacw.R;
import com.hadesky.cacw.bean.TeamBean;
import com.hadesky.cacw.presenter.SearchPresenter;
import com.hadesky.cacw.presenter.SearchTeamPresenterImpl;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchTeamFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchTeamFragment extends SearchFragment {

    public void updateSearchKey(String searchKey) {
        if (searchKey != null && searchKey.length() != 0) {
            mPresenter.search(searchKey);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search_team;
    }

    @Override
    protected SearchPresenter createPresenter() {
        return new SearchTeamPresenterImpl(this, getContext());
    }

    public void disableJoinButton(int position) {
        if (position >= 0 && position < mRecyclerView.getChildCount()) {
            View view = mRecyclerView.getChildAt(position);
            Button button = (Button) view.findViewById(R.id.bt_join);
            if (button != null) {
                button.setSelected(true);
                button.setEnabled(false);
            }
        }
    }
}
