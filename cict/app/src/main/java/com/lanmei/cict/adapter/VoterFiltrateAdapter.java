package com.lanmei.cict.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lanmei.cict.R;
import com.lanmei.cict.bean.VoterFiltrateBean;
import com.xson.common.adapter.SwipeRefreshAdapter;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * voter(下拉筛选)
 */
public class VoterFiltrateAdapter extends SwipeRefreshAdapter<VoterFiltrateBean> {


    public VoterFiltrateAdapter(Context context) {
        super(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder2(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_voter_filtrate, parent, false));
    }

    @Override
    public void onBindViewHolder2(RecyclerView.ViewHolder holder, int position) {
        final VoterFiltrateBean bean = getItem(position);
        if (bean == null) {
            return;
        }
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.setParameter(bean);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!bean.isSelect()) {
                    List<VoterFiltrateBean> list = getData();
                    int size = list.size();
                    for (int i = 0; i < size; i++) {
                        VoterFiltrateBean examinationTopicBean = list.get(i);
                        if (examinationTopicBean.isSelect()) {
                            examinationTopicBean.setSelect(false);
                        }
                    }
                    bean.setSelect(!bean.isSelect());
                    notifyDataSetChanged();
                }
                if (voterFiltrateListener != null) {
                    voterFiltrateListener.onFiltrate(bean);
                }
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.name_tv)
        TextView nameTv;

        ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }


        public void setParameter(VoterFiltrateBean bean) {
            nameTv.setTextColor(bean.isSelect() ? context.getResources().getColor(R.color.color398de3) : context.getResources().getColor(R.color.color666));
            nameTv.setText(bean.getName());
        }
    }

    private VoterFiltrateListener voterFiltrateListener;

    public void setVoterFiltrateListener(VoterFiltrateListener voterFiltrateListener) {
        this.voterFiltrateListener = voterFiltrateListener;
    }

    public interface VoterFiltrateListener {
        void onFiltrate(VoterFiltrateBean bean);
    }

}
