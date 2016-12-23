package vn.com.recpic.NoteScreen.activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
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
    private EditText etTitle, etContent;
    private LinearLayout lnCreate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        etTitle = (EditText) findViewById(R.id.et_note_title);
        etContent = (EditText) findViewById(R.id.et_note_content);
        lnCreate = (LinearLayout) findViewById(R.id.ln_add_note_ok);
        setActionBar();
        addNewNote();

    }

    private void addNewNote(){
        final String title = etTitle.getText().toString();
        final String content = etContent.getText().toString();
        lnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notes notes = new Notes(title, content);
                Intent intent = new Intent();
                intent.putExtra("notes", notes);
                setResult(RESULT_OK, intent);
                //startActivityForResult(intent, RESULT_OK);
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
