package vn.com.recpic.NoteScreen.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
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
    private LinearLayout lnEdit;
    private Notes notes;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        etTitle = (EditText) findViewById(R.id.et_note_title);
        etContent = (EditText) findViewById(R.id.et_note_content);
        lnEdit = (LinearLayout) findViewById(R.id.ln_add_note_ok);

        notes = (Notes) getIntent().getExtras().get(TAG_NOTE_RECEIVE);
        etTitle.setText(notes.getTitle());
        etContent.setText(notes.getContent());

        setActionBar();
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

    private void setActionBar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.chevron_left);
        TextView tv = new TextView(getApplicationContext());
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        tv.setLayoutParams(lp);
        tv.setGravity(Gravity.CENTER);
        tv.setText(getResources().getString(R.string.ac_add_note));
        tv.setTextColor(getResources().getColor(R.color.white));
        tv.setTextSize(14);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(tv);
    }
}
