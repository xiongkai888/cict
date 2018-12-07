package com.lanmei.cict.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanmei.cict.R;
import com.lanmei.cict.bean.ManualBean;
import com.xson.common.adapter.SwipeRefreshAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Manual
 */
public class ManualAdapter extends SwipeRefreshAdapter<ManualBean> {


    public ManualAdapter(Context context) {
        super(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder2(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_manual, parent, false));
    }

    @Override
    public void onBindViewHolder2(RecyclerView.ViewHolder holder, int position) {
        ManualBean bean = getItem(position);
        if (bean == null){
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

        @InjectView(R.id.position_tv)
        TextView positionTv;
        @InjectView(R.id.name_tv)
        TextView nameTv;
        @InjectView(R.id.pic_iv)
        ImageView picIv;


        ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }

        public void setParameter(ManualBean bean,int position) {
            positionTv.setText(bean.getPosition()+"");
            nameTv.setText(bean.getName());
            picIv.setImageResource(bean.getPicId());
        }
    }

}
