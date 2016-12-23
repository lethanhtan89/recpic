package vn.com.recpic.NoteScreen.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import vn.com.recpic.NoteScreen.model.Notes;
import vn.com.recpic.R;

/**
 * Created by Administrator on 22/12/2016.
 */

public class NotesApdapter extends RecyclerView.Adapter<NotesApdapter.NoteViewHolder> {
    private Context mContext;
    private ArrayList<Notes> notesArrayList;

    public class NoteViewHolder extends RecyclerView.ViewHolder{
        public TextView txtTitle, txtTime;
        public NoteViewHolder(View itemView) {
            super(itemView);

            txtTitle = (TextView) itemView.findViewById(R.id.tv_title);
            txtTime = (TextView) itemView.findViewById(R.id.tv_time);
        }
    }

    public NotesApdapter(Context mContext, ArrayList<Notes> notesArrayList){
        this.mContext = mContext;
        this.notesArrayList = notesArrayList;
    }


    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_fragment_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        Notes notes = notesArrayList.get(position);

        holder.txtTitle.setText(notes.getTitle());
        holder.txtTime.setText(notes.getContent());
    }


    @Override
    public int getItemCount() {
        return notesArrayList.size();
    }
}
