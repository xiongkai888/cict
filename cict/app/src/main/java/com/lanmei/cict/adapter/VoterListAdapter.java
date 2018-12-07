package com.lanmei.cict.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanmei.cict.R;
import com.lanmei.cict.bean.VoterListBean;
import com.xson.common.adapter.SwipeRefreshAdapter;
import com.xson.common.widget.FormatTextView;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * 投票列表
 */
public class VoterListAdapter extends SwipeRefreshAdapter<VoterListBean> {

    public VoterListAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder2(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_voter_list, parent, false));
    }


    @Override
    public void onBindViewHolder2(RecyclerView.ViewHolder holder, int position) {
        final VoterListBean bean = getItem(position);
        if (bean == null) {
            return;
        }
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.setParameter(bean,position);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                IntentUtil.startActivity(context, LoginActivity.class);
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.image)
        ImageView image;
        @InjectView(R.id.name_tv)
        TextView nameTv;
        @InjectView(R.id.voter_num_tv)
        FormatTextView voterNumTv;
        @InjectView(R.id.voter_iv)
        ImageView voterIv;

        ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }

        public void setParameter(final VoterListBean bean,final int position) {
            image.setImageResource(bean.getPicId());
            nameTv.setText(bean.getName());
            voterNumTv.setTextValue(bean.getNum());

            voterIv.setImageResource(bean.isClick()?R.mipmap.icon_voter_on:R.mipmap.icon_voter_off);
            voterIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bean.setClick(!bean.isClick());
//                    notifyItemChanged(position);
                    notifyDataSetChanged();
                }
            });
        }
    }
}
