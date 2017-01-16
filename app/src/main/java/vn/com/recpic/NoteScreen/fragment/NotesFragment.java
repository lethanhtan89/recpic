package vn.com.recpic.NoteScreen.fragment;

import android.app.ActionBar;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import vn.com.recpic.NoteScreen.activity.AddNoteActivity;
import vn.com.recpic.NoteScreen.activity.EditNoteActivity;
import vn.com.recpic.NoteScreen.adapter.NotesApdapter;
import vn.com.recpic.NoteScreen.listener.NotesListener;
import vn.com.recpic.NoteScreen.model.Notes;
import vn.com.recpic.Notification.fragment.NofiticationFragment;
import vn.com.recpic.R;
import vn.com.recpic.database.DataBaseHelper;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Administrator on 21/12/2016.
 */

public class NotesFragment extends Fragment implements NotesListener {
    private static final String TAG_NOTE = "Notes_Send";
    private static final String TAG_NOTE_RECEIVE = "Notes_Updated";
    private static final String TAG_NOTE_SEND = "Notes_Edit";
    private DataBaseHelper mDataBaseHelper;
    private RecyclerView mRecyclerView;
    private ArrayList<Notes> mNotesArrayList = new ArrayList<Notes>();
    private Cursor mCursor;
    private NotesApdapter mNotesApdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ActionBar actionBar = getActivity().getActionBar();
        //actionBar.setTitle("NOTE");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_note, container, false);
        init(view);
        return view;
    }

    private void init(View view){
        LinearLayout lnAddNote = (LinearLayout) view.findViewById(R.id.ln_add_note);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.note_recycle_view);
        registerForContextMenu(view.findViewById(R.id.note_recycle_view));

        lnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddNoteActivity.class);
                startActivityForResult(intent, 999);
            }
        });
        loadDatabase();

    }

    private void loadDatabase(){
        mDataBaseHelper = new DataBaseHelper(getContext());
        //Show database
        mNotesArrayList = mDataBaseHelper.getAllNotes();
        mNotesApdapter = new NotesApdapter(getContext(), mNotesArrayList, this);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), 1));
        mRecyclerView.setAdapter(mNotesApdapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 999 && resultCode == RESULT_OK){
            Notes notes = (Notes) data.getExtras().get(TAG_NOTE);
            mDataBaseHelper.addNewNote(notes);
            Toast.makeText(getContext(), "Add note successful", Toast.LENGTH_SHORT).show();
            loadDatabase();
        }

        if(requestCode == 333 && resultCode ==RESULT_OK){
            Notes notes = (Notes) data.getExtras().get(TAG_NOTE_RECEIVE);
            mDataBaseHelper.editNote(notes);
            Toast.makeText(getContext(), "Edit note successful", Toast.LENGTH_SHORT).show();
            loadDatabase();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_budget, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.budget_action_noti:
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.containerView, new NofiticationFragment()).commit();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_notes, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();

        //final Notes notes = (Notes) mRecyclerView.getChildAdapterPosition(info.position);
        //long id = mRecyclerView.getChildItemId(info.position);
        int id = item.getItemId();
        switch (id){
            case R.id.no_remove:
                Toast.makeText(getContext(), "Remove", Toast.LENGTH_SHORT).show();
                break;
            case R.id.no_edit:
                Toast.makeText(getContext(), "Edit", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onContextItemSelected(item);
    }

    @Override
    public void onClickView(int note_id, int position) {
        Notes notes = mNotesArrayList.get(position);
        Intent intent = new Intent(getContext(),EditNoteActivity.class);
        intent.putExtra(TAG_NOTE_SEND, notes);
        startActivityForResult(intent,333);

    }
}
