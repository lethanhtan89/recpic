package vn.com.recpic.NoteScreen.activity;

import android.content.Intent;
import android.os.Bundle;
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

import vn.com.recpic.NoteScreen.model.Notes;
import vn.com.recpic.R;

/**
 * Created by Administrator on 28/12/2016.
 */

public class EditNoteActivity extends AppCompatActivity {
    private static final String TAG_NOTE_RECEIVE = "Notes_Edit";
    private static final String TAG_NOTE_SEND = "Notes_Updated";
    private EditText etTitle, etContent;
    private TextView txtToolbarEdit;
    private LinearLayout lnEdit;
    private Notes notes;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        etTitle = (EditText) findViewById(R.id.et_note_title);
        etContent = (EditText) findViewById(R.id.et_note_content);
        lnEdit = (LinearLayout) findViewById(R.id.ln_add_note_ok);
        txtToolbarEdit = (TextView) findViewById(R.id.txtToolBarNote);

        notes = (Notes) getIntent().getExtras().get(TAG_NOTE_RECEIVE);
        etTitle.setText(notes.getTitle());
        etContent.setText(notes.getContent());

        setupToolbar();
        editNote();
    }

    private void editNote(){

        lnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString();
                String content = etContent.getText().toString();
                notes.setTitle(title);
                notes.setContent(content);
                Intent intent = new Intent();
                intent.putExtra(TAG_NOTE_SEND, notes);
                setResult(RESULT_OK,intent);
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
        txtToolbarEdit.setText(notes.getTitle());

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
                break;
            case R.id.budget_action_noti:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
