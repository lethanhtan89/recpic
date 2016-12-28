package vn.com.recpic.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import vn.com.recpic.NoteScreen.model.Notes;

/**
 * Created by Administrator on 23/12/2016.
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String TAG = SQLiteOpenHelper.class.getSimpleName();
    private static final String DB_NAME = "Recpic";
    private static String DB_PATH = "";
    private static final int DB_VERSION = 1;
    private static final String DB_TABLE = "Notes";
    private static final String NOTE_ID = "_id";
    private static final String NOTE_TITLE = "title";
    private static final String NOTE_CONTENT = "content";

    private final Context mContext;
    private SQLiteDatabase mSqLiteDatabase;
    public DataBaseHelper(Context mContext){
        super(mContext, DB_NAME, null, DB_VERSION);
        if(Build.VERSION.SDK_INT >15){
            DB_PATH = mContext.getApplicationInfo().dataDir + "/databases/";
        }else {
            DB_PATH = Environment.getDataDirectory() + "/data/" + mContext.getPackageName() + "/databases/";
        }
        this.mContext = mContext;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
      String sql="create table Notes" +
                "(" +
                "_id integer primary key autoincrement," +
                "title text," +
                "content text" +
                "images text" +
                ") ";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Notes");
        onCreate(db);
    }

    public void CheckandCopyDatabase(){
        boolean dbExist = checkDataBase();
        if(dbExist){
            Log.d("TAG", "Database already exists ");
        }else {
            this.getReadableDatabase();
        }
        try {
            CopyDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void CopyDatabase() throws IOException{
        InputStream inputStream = mContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream outputStream = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) >0){
            outputStream.write(buffer, 0, length);
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();

    }

    public void openDatabase(){
        String myPath = DB_PATH + DB_NAME;
        mSqLiteDatabase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public boolean checkDataBase(){
        SQLiteDatabase checkDB = null;
        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
        }catch (SQLiteException e){
            e.printStackTrace();
        }
        if (checkDB != null){
            checkDB.close();
        }
        return checkDB !=null ? true : false;

    }

    public synchronized void close(){
        if(mSqLiteDatabase != null){
            mSqLiteDatabase.close();
        }
        super.close();
    }

    public Cursor QueryData(String query){
        return mSqLiteDatabase.rawQuery(query, null);
    }

    public ArrayList<Notes> getAllNotes() {
        ArrayList<Notes> notesArrayList = new ArrayList<Notes>();
        try {
            CheckandCopyDatabase();
            openDatabase();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }

        try {
            //SQLiteDatabase db = this.getWritableDatabase();
            Cursor mCursor = QueryData("select * from Notes ORDER BY _ID DESC");
            if (mCursor != null) {
                if (mCursor.moveToFirst()) {
                    do {
                        Notes notes = new Notes();
                        notes.setId(mCursor.getInt(0));
                        notes.setTitle(mCursor.getString(1));
                        notes.setContent(mCursor.getString(2));
                        notesArrayList.add(notes);
                    } while (mCursor.moveToNext());
                }
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        return notesArrayList;
    }

    public void addNewNote(Notes notes){
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(NOTE_TITLE, notes.getTitle());
            values.put(NOTE_CONTENT, notes.getContent());
            db.insert(DB_TABLE, null, values);
            db.setTransactionSuccessful();
        }catch(SQLiteException e){
            e.printStackTrace();
            Log.d(TAG, "can not add infomation into database");
        }
        finally {
            db.endTransaction();
        }
    }

    public void removeNote(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("Notes", "id=?", new String[]{id + ""});
    }

    public void editNote(Notes notes){
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(NOTE_TITLE, notes.getTitle());
            values.put(NOTE_CONTENT, notes.getContent());
            db.update(DB_TABLE, values, NOTE_ID + " = ?", new String[]{notes.getId() + ""});
            db.close();
        }catch (SQLiteException e){
            e.printStackTrace();
        }
    }
}
