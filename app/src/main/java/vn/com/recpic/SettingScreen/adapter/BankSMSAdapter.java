package vn.com.recpic.SettingScreen.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import vn.com.recpic.R;
import vn.com.recpic.SettingScreen.listener.BankSMSListener;
import vn.com.recpic.SettingScreen.model.BankSMS;

/**
 * Created by Administrator on 2/7/2017.
 */

public class BankSMSAdapter extends RecyclerView.Adapter<BankSMSAdapter.BankSMSViewHolder> {
    private ArrayList<BankSMS> bankSMSArrayList;
    private BankSMS bankSMS;
    private BankSMSViewHolder holder;
    private Context context;
    private BankSMSListener listener;

    public BankSMSAdapter(Context context, ArrayList<BankSMS> bankSMSArrayList, BankSMSListener listener){
        this.context = context;
        this.bankSMSArrayList = bankSMSArrayList;
        this.listener = listener;
    }

    public class BankSMSViewHolder extends RecyclerView.ViewHolder{
        public TextView txtSMS;
        public BankSMSViewHolder(View itemView) {
            super(itemView);
            txtSMS = (TextView) itemView.findViewById(R.id.txttitleSMS);
        }
    }
    @Override
    public BankSMSViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_bank_sms, parent, false);
        holder = new BankSMSViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(BankSMSViewHolder holder, final int position) {
        bankSMS = bankSMSArrayList.get(position);
        holder.txtSMS.setText(bankSMS.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onclickItem(bankSMS.getId(), position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return bankSMSArrayList.size();
    }
}
