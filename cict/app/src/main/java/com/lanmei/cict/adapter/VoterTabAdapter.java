package com.lanmei.cict.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lanmei.cict.R;
import com.lanmei.cict.bean.VoterTabBean;
import com.xson.common.adapter.SwipeRefreshAdapter;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * 代替垂直tabLayout
 */
public class VoterTabAdapter extends SwipeRefreshAdapter<VoterTabBean> {

    public VoterTabAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder2(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_voter_tab, parent, false));
    }


    @Override
    public void onBindViewHolder2(RecyclerView.ViewHolder holder, int position) {
        final VoterTabBean bean = getItem(position);
        if (bean == null) {
            return;
        }
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.setParameter(bean, position);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bean.isChoose()) {
                    return;
                }
                List<VoterTabBean> list = getData();
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    list.get(i).setChoose(false);
                }
                bean.setChoose(true);
                notifyDataSetChanged();
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.indicator_tv)
        TextView indicatorTv;
        @InjectView(R.id.name_tv)
        TextView nameTv;
        @InjectView(R.id.voter_rl)
        RelativeLayout voterRl;

        ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }

        public void setParameter(final VoterTabBean bean, final int position) {
            indicatorTv.setVisibility(bean.isChoose() ? View.VISIBLE : View.GONE);
            nameTv.setText(bean.getName());
            voterRl.setBackgroundColor(bean.isChoose() ? context.getResources().getColor(R.color.white) : context.getResources().getColor(R.color.colorF8F8F8));
        }
    }
}
