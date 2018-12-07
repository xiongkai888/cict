package com.lanmei.cict.ui.about;

import android.os.Bundle;

import com.lanmei.cict.R;
import com.xson.common.app.BaseFragment;
import com.xson.common.widget.CenterTitleToolbar;

import butterknife.InjectView;


/**
 * Created by xkai on 2018/12/4.
 * About
 */

public class AboutFragment extends BaseFragment {

    @InjectView(R.id.toolbar)
    CenterTitleToolbar toolbar;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_about;
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        toolbar.setTitle("About");
    }
}
