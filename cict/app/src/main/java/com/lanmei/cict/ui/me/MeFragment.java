package com.lanmei.cict.ui.me;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanmei.cict.R;
import com.lanmei.cict.ui.login.LoginActivity;
import com.xson.common.app.BaseFragment;
import com.xson.common.helper.SharedAccount;
import com.xson.common.utils.IntentUtil;

import butterknife.InjectView;
import butterknife.OnClick;


/**
 * Created by xkai on 2018/12/4.
 * me
 */

public class MeFragment extends BaseFragment {

    @InjectView(R.id.pic_iv)
    ImageView picIv;
    @InjectView(R.id.name_tv)
    TextView nameTv;
    @InjectView(R.id.phone_number_tv)
    TextView phoneNumberTv;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        nameTv.setText(SharedAccount.getInstance(context).getName());
        phoneNumberTv.setText(SharedAccount.getInstance(context).getMobile());
    }

    @OnClick({R.id.set_up_sub_tv, R.id.pic_iv, R.id.my_information_tv, R.id.my_vote_tv, R.id.my_booking_tv, R.id.set_up_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.set_up_sub_tv:
                break;
            case R.id.pic_iv:
                break;
            case R.id.my_information_tv:
                break;
            case R.id.my_vote_tv:
                break;
            case R.id.my_booking_tv:
                break;
            case R.id.set_up_tv:
                SharedAccount.getInstance(context).setNoFirstLogin(false);
                IntentUtil.startActivity(context, LoginActivity.class);
                getActivity().finish();
                break;
        }
    }
}
