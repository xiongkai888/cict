package com.lanmei.cict.ui.home.activity;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.lanmei.cict.R;
import com.lanmei.cict.adapter.VoterFiltrateAdapter;
import com.lanmei.cict.adapter.VoterListAdapter;
import com.lanmei.cict.adapter.VoterTabAdapter;
import com.lanmei.cict.bean.VoterFiltrateBean;
import com.lanmei.cict.bean.VoterListBean;
import com.lanmei.cict.bean.VoterTabBean;
import com.lanmei.cict.utils.CommonUtils;
import com.xson.common.app.BaseActivity;
import com.xson.common.utils.SysUtils;
import com.xson.common.widget.SmartSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 投票
 */
public class VoterActivity extends BaseActivity {

    @InjectView(R.id.toolbar_name_tv)
    TextView toolbarNameTv;
    @InjectView(R.id.menu_tv)
    TextView menuTv;
    @InjectView(R.id.line_tv)
    TextView lineTv;

//    @InjectView(R.id.tablayout)
//    VerticalTabLayout tablayout;//垂直tabLayout

    @InjectView(R.id.pull_refresh_rv)
    SmartSwipeRefreshLayout smartSwipeRefreshLayout;

    @InjectView(R.id.recyclerView)
    RecyclerView recyclerView;//代替垂直tabLayout

    VoterListAdapter adapter;

    @InjectView(R.id.breakfast_tv)
    TextView breakfastTv;
    @InjectView(R.id.lunch_tv)
    TextView lunchTv;
    @InjectView(R.id.dinner_tv)
    TextView dinnerTv;
    @InjectView(R.id.midnight_tv)
    TextView midnightTv;

    @Override
    public int getContentViewId() {
        return R.layout.activity_voter;
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        toolbarNameTv.setText("Monday");
        setTextView(breakfastTv);
        CommonUtils.setCompoundDrawables(this, menuTv, R.mipmap.icon_filtrate, 0, 2);
        CommonUtils.setCompoundDrawables(this, toolbarNameTv, R.mipmap.common_filter_arrow_down_sub, R.color.black, 2);

//        VoterVerticalTabAdapter tabAdapter = new VoterVerticalTabAdapter(this);
//        tablayout.setTabAdapter(tabAdapter);
//        tablayout.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabView tab, int position) {
//            }
//
//            @Override
//            public void onTabReselected(TabView tab, int position) {
//
//            }
//        });

        VoterTabAdapter voterTabAdapter = new VoterTabAdapter(this);
        voterTabAdapter.setData(getVoterTabList());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(voterTabAdapter);

        adapter = new VoterListAdapter(this);
        adapter.setData(getVoterListBeanList());
        smartSwipeRefreshLayout.initWithLinearLayout();
        smartSwipeRefreshLayout.setAdapter(adapter);
        smartSwipeRefreshLayout.setMode(SmartSwipeRefreshLayout.Mode.NO_PAGE);
        adapter.notifyDataSetChanged();
    }




    private List<VoterTabBean> getVoterTabList() {
        List<VoterTabBean> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            VoterTabBean bean = new VoterTabBean();
            switch (i){
                case 0:
                    bean.setName("Snack");//"Snack","Frunt","Drink","Others"
                    bean.setChoose(true);
                    break;
                case 1:
                    bean.setName("Frunt");
                    break;
                case 2:
                    bean.setName("Drink");
                    break;
                case 3:
                    bean.setName("Others");
                    break;
            }
            list.add(bean);
        }
        return list;
    }

    private List<VoterListBean> getVoterListBeanList() {
        List<VoterListBean> list = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            VoterListBean bean = new VoterListBean();
            switch (i){
                case 0:
                    bean.setPicId(R.mipmap.temp_cai);
                    bean.setName("Diced chicken in bean sauce");
                    break;
                case 1:
                    bean.setPicId(R.mipmap.temp_cai1);
                    bean.setName("Stewed Pork Ball in Brown Sauce");
                    break;
                case 2:
                    bean.setPicId(R.mipmap.temp_cai2);
                    bean.setName("Spicy diced chicken with peanuts");
                    break;
                case 3:
                    bean.setPicId(R.mipmap.temp_cai3);
                    bean.setName("syrup of plum");
                    break;
                case 4:
                    bean.setPicId(R.mipmap.temp_cai4);
                    bean.setName("Steamed spareribs with rice flour");
                    break;
                case 5:
                    bean.setPicId(R.mipmap.temp_cai1);
                    bean.setName("Two color chopped head fish head");
                    break;
                case 6:
                    bean.setPicId(R.mipmap.temp_cai2);
                    bean.setName("Chinese style salmon");
                    break;
            }
            bean.setNum(CommonUtils.generateNumberString(2,true));
            list.add(bean);
        }
        return list;
    }

    @OnClick({R.id.toolbar_name_tv, R.id.back_iv, R.id.menu_tv, R.id.breakfast_tv, R.id.lunch_tv, R.id.dinner_tv, R.id.midnight_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_iv:
                finish();
                break;
            case R.id.menu_tv:
                CommonUtils.developing(this);
                break;
            case R.id.toolbar_name_tv:
                popupWindow();
                break;
            case R.id.breakfast_tv:
                setTextView(breakfastTv);
                break;
            case R.id.lunch_tv:
                setTextView(lunchTv);
                break;
            case R.id.dinner_tv:
                setTextView(dinnerTv);
                break;
            case R.id.midnight_tv:
                setTextView(midnightTv);
                break;
        }
    }

    private void setTextView(TextView textView) {
        breakfastTv.setTextColor(getResources().getColor(R.color.black));
        lunchTv.setTextColor(getResources().getColor(R.color.black));
        dinnerTv.setTextColor(getResources().getColor(R.color.black));
        midnightTv.setTextColor(getResources().getColor(R.color.black));

        textView.setTextColor(getResources().getColor(R.color.red));
    }


    private PopupWindow window;

    private void popupWindow() {
        CommonUtils.setCompoundDrawables(getContext(), toolbarNameTv, R.mipmap.common_filter_arrow_up_sub, R.color.color398de3, 2);
        if (window != null) {
            window.showAsDropDown(lineTv);
            return;
        }
        RecyclerView view = new RecyclerView(this);
        view.setLayoutManager(new GridLayoutManager(this, 4));
        view.setBackgroundColor(getResources().getColor(R.color.white));

        List<VoterFiltrateBean> list;

        VoterFiltrateAdapter voterFiltrateAdapter = new VoterFiltrateAdapter(this);
        voterFiltrateAdapter.setData(getList());
        view.setAdapter(voterFiltrateAdapter);
        voterFiltrateAdapter.setVoterFiltrateListener(new VoterFiltrateAdapter.VoterFiltrateListener() {
            @Override
            public void onFiltrate(VoterFiltrateBean bean) {
                toolbarNameTv.setText(bean.getName());
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
        window.showAsDropDown(lineTv);
//        L.d(L.TAG,"width:"+width+",paddingRight:"+paddingRight+",xoff:"+xoff);

        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                CommonUtils.setCompoundDrawables(getContext(), toolbarNameTv, R.mipmap.common_filter_arrow_down_sub, R.color.black, 2);
            }
        });
    }

    private List<VoterFiltrateBean> getList() {
        List<VoterFiltrateBean> list = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            VoterFiltrateBean bean = new VoterFiltrateBean();
            switch (i) {
                case 0:
                    bean.setName("Monday");
                    bean.setSelect(true);
                    break;
                case 1:
                    bean.setName("Tuesday");
                    break;
                case 2:
                    bean.setName("Wednesday");
                    break;
                case 3:
                    bean.setName("Thursday");
                    break;
                case 4:
                    bean.setName("Friday");
                    break;
                case 5:
                    bean.setName("Saturday");
                    break;
                case 6:
                    bean.setName("Sunday");
                    break;
            }
            bean.setId("" + i);
            list.add(bean);
        }
        return list;
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
