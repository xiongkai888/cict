package com.lanmei.cict.ui.home.activity;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.lanmei.cict.R;
import com.lanmei.cict.adapter.TalkToCeoAdapter;
import com.lanmei.cict.adapter.VoterFiltrateAdapter;
import com.lanmei.cict.bean.TalkToCeoBean;
import com.lanmei.cict.bean.VoterFiltrateBean;
import com.lanmei.cict.utils.CommonUtils;
import com.xson.common.app.BaseActivity;
import com.xson.common.utils.IntentUtil;
import com.xson.common.utils.SysUtils;
import com.xson.common.widget.CenterTitleToolbar;
import com.xson.common.widget.SmartSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * talk to ceo
 */
public class TalkToCeoActivity extends BaseActivity {

    @InjectView(R.id.ll_by)
    LinearLayout llBy;
    @InjectView(R.id.toolbar)
    CenterTitleToolbar toolbar;

    @InjectView(R.id.pull_refresh_rv)
    SmartSwipeRefreshLayout smartSwipeRefreshLayout;
    @InjectView(R.id.by_tv)
    TextView byTv;
    @InjectView(R.id.time_tv)
    TextView timeTv;

    @Override
    public int getContentViewId() {
        return R.layout.activity_talk_to_ceo;
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle(R.string.talk_to_ceo);
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.mipmap.back);

        smartSwipeRefreshLayout.initWithLinearLayout();
        TalkToCeoAdapter adapter = new TalkToCeoAdapter(this);
        adapter.setData(getList());
        smartSwipeRefreshLayout.setAdapter(adapter);
        smartSwipeRefreshLayout.setMode(SmartSwipeRefreshLayout.Mode.NO_PAGE);
        adapter.notifyDataSetChanged();
    }

    private List<TalkToCeoBean> getList() {
        List<TalkToCeoBean> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            TalkToCeoBean bean = new TalkToCeoBean();
            switch (i) {
                case 0:
                    bean.setName("Sun Xiaoming");
                    bean.setPicId(R.mipmap.temp_pic);
                    bean.setContent("Hello, boss, I'd like to ask about leave, if I want to take it. How long will it take to get there in advance?");
                    break;
                case 1:
                    bean.setName("Jacket");
                    bean.setPicId(R.mipmap.temp_pic1);
                    bean.setContent("Hello, boss, this is Sun Jacket. I'll ask you a question about the canteen. Can you add a little more to that dish? Look forward to your reply? Thank you!");
                    break;
                case 2:
                    bean.setName("Chen Xiaoming");
                    bean.setPicId(R.mipmap.temp_pic);
                    bean.setContent("Hello, boss, this is Chen Xiaoming. I asked about the salary. I have been working for three years. The salary can not be raised. Thank you.");
                    break;
                case 3:
                    bean.setName("Chen Xiaohong");
                    bean.setPicId(R.mipmap.temp_pic1);
                    bean.setContent("Hello, boss, I'm Chen Xiaohong. I'll ask you about using a car. I've been working for three years. Can I have a car for me alone? Thank you!");
                    break;
                case 4:
                    bean.setName("Wang Dazhong");
                    bean.setPicId(R.mipmap.temp_pic);
                    bean.setContent("Hello, CEO, I'm Wang Dazhong. I'll ask you about attendance. If I am full-time, can you give me a reward? Thank you!");
                    break;
            }
            list.add(bean);
        }
        return list;
    }

    private PopupWindow window;

    private void popupWindow() {
        CommonUtils.setCompoundDrawables(getContext(), byTv, R.mipmap.common_filter_arrow_up, R.color.color398de3, 2);
        if (window != null) {
            window.showAsDropDown(llBy);
            return;
        }
        RecyclerView view = new RecyclerView(this);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setBackgroundColor(getResources().getColor(R.color.white));

        VoterFiltrateAdapter voterFiltrateAdapter = new VoterFiltrateAdapter(this);
        voterFiltrateAdapter.setData(getListSub());
        view.setAdapter(voterFiltrateAdapter);
        voterFiltrateAdapter.setVoterFiltrateListener(new VoterFiltrateAdapter.VoterFiltrateListener() {
            @Override
            public void onFiltrate(VoterFiltrateBean bean) {
//                byTv.setText(bean.getName());
                window.dismiss();
            }
        });
//        int width = UIBaseUtils.dp2pxInt(this, 80);
        window = new PopupWindow(view, SysUtils.getScreenWidth(this), ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setContentView(view);
        window.setFocusable(true);
        window.setOutsideTouchable(true);
        window.setBackgroundDrawable(new BitmapDrawable());
//        int paddingRight = UIBaseUtils.dp2pxInt(this, 0);
//        int xoff = SysUtils.getScreenWidth(this) - width - paddingRight;
        window.showAsDropDown(llBy);
//        L.d(L.TAG,"width:"+width+",paddingRight:"+paddingRight+",xoff:"+xoff);

        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                CommonUtils.setCompoundDrawables(getContext(), byTv, R.mipmap.common_filter_arrow_down, R.color.black, 2);
            }
        });
    }


    private List<VoterFiltrateBean> getListSub() {
        List<VoterFiltrateBean> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            VoterFiltrateBean bean = new VoterFiltrateBean();
            switch (i) {
                case 0:
                    bean.setName("About the use of cars");
//                    bean.setSelect(true);
                    break;
                case 1:
                    bean.setName("On the menu of the canteen");
                    break;
                case 2:
                    bean.setName("About attendance problems");
                    break;
                case 3:
                    bean.setName("On Wage Treatment");
                    break;
                case 4:
                    bean.setName("Some Problems Concerning Port Construction");
                    break;
            }
            bean.setId("" + i);
            list.add(bean);
        }
        return list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_write_message, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_write_message:
                IntentUtil.startActivity(this, MessageActivity.class);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean isUp;

    @OnClick({R.id.by_rl, R.id.time_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.by_rl:
                popupWindow();
                break;
            case R.id.time_rl:
                CommonUtils.setCompoundDrawables(this, timeTv, isUp?R.mipmap.o_bothway_up:R.mipmap.o_bothway_down, 0, 2);
                isUp = !isUp;
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (window != null) {
            if (window.isShowing()) {
                window.dismiss();
            }
            window = null;
        }
    }
}
