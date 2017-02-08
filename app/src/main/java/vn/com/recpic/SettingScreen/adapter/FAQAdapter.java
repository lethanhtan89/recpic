package vn.com.recpic.SettingScreen.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import vn.com.recpic.R;
import vn.com.recpic.SettingScreen.listener.FAQListener;
import vn.com.recpic.SettingScreen.model.FAQ;

/**
 * Created by Administrator on 2/8/2017.
 */

public class FAQAdapter extends RecyclerView.Adapter<FAQAdapter.FAQViewHolder> {
    private Context context;
    private ArrayList<FAQ> arrayList;
    private FAQ faq;
    private FAQViewHolder holder;
    private FAQListener listener;

    public FAQAdapter(Context context, ArrayList<FAQ> arrayList, FAQListener listener){
        this.context = context;
        this.arrayList = arrayList;
        this.listener = listener;
    }

    @Override
    public FAQViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_faq, parent, false);
        holder = new FAQViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(FAQViewHolder holder, final int position) {
        faq = arrayList.get(position);
        holder.txtContent.setText(faq.getContent());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onClickItem(faq.getId(), position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class FAQViewHolder extends RecyclerView.ViewHolder{
        TextView txtContent;
        public FAQViewHolder(View itemView) {
            super(itemView);
            txtContent = (TextView) itemView.findViewById(R.id.txtContent);
        }
    }
}
