package com.lanmei.cict.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.lanmei.cict.R;
import com.lanmei.cict.bean.AdBean;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 首页图片轮播适配器
 */
public class HomeAdAdapter implements Holder<AdBean> {

    @InjectView(R.id.banner_img)
    ImageView bannerImg;

    @Override
    public View createView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_banner_img, null);
        ButterKnife.inject(this,view);
        return view;
    }

    @Override
    public void UpdateUI(final Context context, final int position, final AdBean data) {
        if (data == null){
            return;
        }
        bannerImg.setImageResource(data.getId());
//        ImageHelper.load(context,data.getPic(),bannerImg,null,true,R.drawable.default_pic,R.drawable.default_pic);
//        bannerImg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (StringUtils.isEmpty(data.getLink())){
//                    return;
//                }
//                context.startActivity(new  Intent(Intent.ACTION_VIEW, Uri.parse(data.getLink())));
//            }
//        });
    }
}
