package vn.com.recpic.NoteScreen.activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

import vn.com.recpic.NoteScreen.fragment.NotesFragment;
import vn.com.recpic.NoteScreen.model.Notes;
import vn.com.recpic.R;

/**
 * Created by Administrator on 22/12/2016.
 */

public class AddNoteActivity extends AppCompatActivity {
    private static final String TAG_NOTE_SEND_LIST = "Notes_Send";
    private EditText etTitle, etContent;
    private LinearLayout lnCreate;
    private TextView txtNoteToolbarTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        etTitle = (EditText) findViewById(R.id.et_note_title);
        etContent = (EditText) findViewById(R.id.et_note_content);
        lnCreate = (LinearLayout) findViewById(R.id.ln_add_note_ok);
        txtNoteToolbarTitle = (TextView) findViewById(R.id.txtToolBarNote);
        setupToolbar();
        addNewNote();
    }

    private void addNewNote(){

        lnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString();
                String content = etContent.getText().toString();
                Notes notes = new Notes(title, content);
                Intent i=new Intent();
                i.putExtra(TAG_NOTE_SEND_LIST, notes);
                //i.putExtra("content", content);
                setResult(RESULT_OK,i);
                finish();
            }
        });
    }

    private void setupToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.note_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.icon_cancel));
        getSupportActionBar().setTitle(" ");
        txtNoteToolbarTitle.setText(getResources().getString(R.string.note_title));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_budget, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
                onBackPressed();
            case R.id.budget_action_noti:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
