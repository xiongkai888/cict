package com.lanmei.cict.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanmei.cict.R;
import com.lanmei.cict.bean.TalkToCeoBean;
import com.lanmei.cict.utils.FormatTime;
import com.xson.common.adapter.SwipeRefreshAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * talk to ceo
 */
public class TalkToCeoAdapter extends SwipeRefreshAdapter<TalkToCeoBean> {

    private FormatTime formatTime;

    public TalkToCeoAdapter(Context context) {
        super(context);
        formatTime = new FormatTime(context);
        formatTime.setApplyToAllTime();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder2(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_talk_to_ceo, parent, false));
    }

    @Override
    public void onBindViewHolder2(RecyclerView.ViewHolder holder, int position) {
        TalkToCeoBean bean = getItem(position);
        if (bean == null) {
            return;
        }
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.setParameter(bean);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                IntentUtil.startActivity(context, LoginActivity.class);
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.pic_iv)
        ImageView picIv;
        @InjectView(R.id.name_tv)
        TextView nameTv;
        @InjectView(R.id.content_tv)
        TextView contentTv;
        @InjectView(R.id.time_tv)
        TextView timeTv;
        @InjectView(R.id.message_num_tv)
        TextView messageNumTv;

        ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }


        public void setParameter(TalkToCeoBean bean) {
            nameTv.setText(bean.getName());
            contentTv.setText(bean.getContent());
            picIv.setImageResource(bean.getPicId());
        }
    }

}
