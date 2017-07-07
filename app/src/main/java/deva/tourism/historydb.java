package deva.tourism;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by deva on 07/07/17.
 */

public class historydb extends SQLiteOpenHelper {
    public historydb(Context context) {
        super(context,"history",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table history(name text,places text,created_at DATETIME DEFAULT CURRENT_TIMESTAMP)");

    }

    String view(String name,String pass)
    {
        SQLiteDatabase db=getWritableDatabase();
        Cursor c=db.rawQuery("select * from users where name='"+name+"' ",null);
        c.moveToFirst();
        if(c.getCount()==0)
            return "No Such username";
        else if(pass.equals(c.getString(1)))
            return "success";
        else
            return "Incorrect password";
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
    void all()
    {
//        SQLiteDatabase db=getWritableDatabase();
//        SimpleDateFormat dateFormat = new SimpleDateFormat(
//                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
//        Date date = new Date();
        //db.execSQL("insert into history values('abin','thrissur,guruvayoor','"+dateFormat.format(date)+"')");
        //db.execSQL("insert into history values('hitha','thrissur,guruvayoor,athirapilly','"+dateFormat.format(date)+"')");
        //Cursor c=db.rawQuery("select * from users",null);
    }
    Cursor get()
    {
        SQLiteDatabase db=getWritableDatabase();
        Cursor c=db.rawQuery("select * from history",null);
        return c;

    }
}