package vn.com.recpic.SettingScreen.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import vn.com.recpic.R;
import vn.com.recpic.SettingScreen.listener.NotificationListener;
import vn.com.recpic.SettingScreen.model.Notification;

/**
 * Created by Administrator on 2/8/2017.
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {
    private Context context;
    private NotificationViewHolder holder;
    private ArrayList<Notification> arrayList;
    private Notification notification;
    private NotificationListener listener;
    public NotificationAdapter(Context context, ArrayList<Notification> arrayList, NotificationListener listener ){
        this.context = context;
        this.arrayList = arrayList;
        this.listener = listener;
    }

    public class NotificationViewHolder extends RecyclerView.ViewHolder{
        public TextView txtTitle;

        public NotificationViewHolder(View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.txttitleSMS);
        }
    }

    @Override
    public NotificationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_bank_sms, parent, false);
        holder = new NotificationViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(NotificationViewHolder holder, final int position) {
        notification = arrayList.get(position);
        holder.txtTitle.setText(notification.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onClickItem(notification.getId(), position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

}
