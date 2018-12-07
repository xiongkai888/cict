package com.lanmei.cict.ui.home.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.lanmei.cict.R;
import com.lanmei.cict.adapter.VoterFiltrateAdapter;
import com.lanmei.cict.bean.VoterFiltrateBean;
import com.lanmei.cict.helper.BGASortableNinePhotoHelper;
import com.lanmei.cict.helper.SimpleTextWatcher;
import com.lanmei.cict.utils.CommonUtils;
import com.lanmei.cict.view.ScrollEditText;
import com.xson.common.app.BaseActivity;
import com.xson.common.utils.StringUtils;
import com.xson.common.utils.SysUtils;
import com.xson.common.widget.CenterTitleToolbar;
import com.xson.common.widget.FormatTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;
import cn.bingoogolapple.photopicker.widget.BGASortableNinePhotoLayout;

/**
 * Message
 */
public class MessageActivity extends BaseActivity implements BGASortableNinePhotoLayout.Delegate {

    @InjectView(R.id.toolbar)
    CenterTitleToolbar toolbar;

    @InjectView(R.id.content_et)
    ScrollEditText contentEt;
    @InjectView(R.id.content_num_tv)
    TextView contentNumTv;
    @InjectView(R.id.upload_pictures_num_tv)
    FormatTextView uploadPicturesNumTv;

    @InjectView(R.id.snpl_moment_add_photos)
    BGASortableNinePhotoLayout mPhotosSnpl;//拖拽排序九宫格控件
    BGASortableNinePhotoHelper mPhotoHelper;

    @InjectView(R.id.type_tv)
    TextView typeTv;

    @Override
    public int getContentViewId() {
        return R.layout.activity_message;
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("Message");
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.mipmap.back);
        initPhotoHelper();

        contentEt.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (StringUtils.isEmpty(s)) {
                    contentNumTv.setText(String.format(getString(R.string.num_600), CommonUtils.isZero));
                } else {
                    contentNumTv.setText(String.format(getString(R.string.num_600), s.length() + ""));
                }
            }
        });
    }

    private void initPhotoHelper() {
        mPhotoHelper = new BGASortableNinePhotoHelper(this, mPhotosSnpl);
        // 设置拖拽排序控件的代理
        mPhotoHelper.setDelegate(this);
        mPhotoHelper.setMaxItemCount(3);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sub, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sub:
                CommonUtils.developing(this);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClickAddNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position, ArrayList<String> models) {
        if (mPhotoHelper != null) {
            mPhotoHelper.onClickAddNinePhotoItem(sortableNinePhotoLayout, view, position, models);
        }
    }

    @Override
    public void onClickDeleteNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position, String model, ArrayList<String> models) {
        if (mPhotoHelper != null) {
            mPhotoHelper.onClickDeleteNinePhotoItem(sortableNinePhotoLayout, view, position, model, models);
            uploadPicturesNumTv.setTextValue(String.valueOf(mPhotoHelper.getItemCount()));
        }
    }

    @Override
    public void onClickNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position, String model, ArrayList<String> models) {
        if (mPhotoHelper != null) {
            mPhotoHelper.onClickNinePhotoItem(sortableNinePhotoLayout, view, position, model, models);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mPhotoHelper != null) {
            mPhotoHelper.onActivityResult(requestCode, resultCode, data);
            uploadPicturesNumTv.setTextValue(String.valueOf(mPhotoHelper.getItemCount()));
        }
    }


    private PopupWindow window;

    private void popupWindow() {
        if (window != null) {
            window.showAsDropDown(typeTv);
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
                typeTv.setText(bean.getName());
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
        window.showAsDropDown(typeTv);
//        L.d(L.TAG,"width:"+width+",paddingRight:"+paddingRight+",xoff:"+xoff);
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

    @OnClick(R.id.type_tv)
    public void onViewClicked() {
        popupWindow();
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
