package com.lanmei.cict.ui.manual;

import android.os.Bundle;

import com.lanmei.cict.R;
import com.lanmei.cict.adapter.ManualAdapter;
import com.lanmei.cict.bean.ManualBean;
import com.xson.common.app.BaseFragment;
import com.xson.common.widget.CenterTitleToolbar;
import com.xson.common.widget.SmartSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;


/**
 * Created by xkai on 2018/12/4.
 * Manual
 */

public class ManualFragment extends BaseFragment {

    @InjectView(R.id.toolbar)
    CenterTitleToolbar toolbar;

    @InjectView(R.id.pull_refresh_rv)
    SmartSwipeRefreshLayout smartSwipeRefreshLayout;

    @Override
    public int getContentViewId() {
        return R.layout.activity_single_listview_no;
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        toolbar.setTitle("Manual");

        ManualAdapter adapter = new ManualAdapter(context);
        adapter.setData(getList());
        smartSwipeRefreshLayout.initWithLinearLayout();
        smartSwipeRefreshLayout.setAdapter(adapter);
        smartSwipeRefreshLayout.setMode(SmartSwipeRefreshLayout.Mode.NO_PAGE);
        adapter.notifyDataSetChanged();

    }

    private List<ManualBean> getList() {
        List<ManualBean> list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            ManualBean bean = new ManualBean();
            switch (i){
                case 0:
                    bean.setName("Register");
                    bean.setPicId(R.mipmap.manual_register);
                    break;
                case 1:
                    bean.setName("Sign");
                    bean.setPicId(R.mipmap.manual_sign);
                    break;
            }
            bean.setPosition(i+1);
            list.add(bean);
        }
        return list;
    }

}
