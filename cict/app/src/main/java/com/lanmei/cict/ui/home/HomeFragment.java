package com.lanmei.cict.ui.home;

import android.os.Bundle;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.lanmei.cict.R;
import com.lanmei.cict.adapter.HomeAdAdapter;
import com.lanmei.cict.bean.AdBean;
import com.lanmei.cict.ui.home.activity.TalkToCeoActivity;
import com.lanmei.cict.ui.home.activity.VoterActivity;
import com.xson.common.app.BaseFragment;
import com.xson.common.utils.IntentUtil;
import com.xson.common.widget.CenterTitleToolbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;


/**
 * Created by xkai on 2018/12/4.
 * 首页
 */

public class HomeFragment extends BaseFragment {


    @InjectView(R.id.toolbar)
    CenterTitleToolbar toolbar;

    @InjectView(R.id.banner)
    ConvenientBanner banner;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        toolbar.setTitle("CICT");
        initBanner();

    }

    private void initBanner() {
        List<AdBean> list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            AdBean bean = new AdBean();
            switch (i) {
                case 0:
                    bean.setId(R.mipmap.home_banner1);
                    break;
                case 1:
                    bean.setId(R.mipmap.home_banner2);
                    break;
            }
            list.add(bean);
        }
        banner.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new HomeAdAdapter();
            }
        }, list);
        banner.setPageIndicator(new int[]{R.drawable.shape_item_index_white, R.drawable.shape_item_index_red});
        banner.setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
        banner.startTurning(3000);
    }


    @OnClick({R.id.vehicle_book_tv, R.id.menu_voting_tv, R.id.talk_to_ceo_rl, R.id.my_voting_tv, R.id.my_booking_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.vehicle_book_tv:

                break;
            case R.id.menu_voting_tv:
                IntentUtil.startActivity(context, VoterActivity.class);
                break;
            case R.id.talk_to_ceo_rl:
                IntentUtil.startActivity(context, TalkToCeoActivity.class);
                break;
            case R.id.my_voting_tv:

                break;
            case R.id.my_booking_tv:

                break;
        }
    }
}
