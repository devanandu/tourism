package deva.tourism;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by deva on 07/07/17.
 */

public class DB extends SQLiteOpenHelper {
    public DB(Context context) {
        super(context,"tourism",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users(name text,pass text)");

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
        SQLiteDatabase db=getWritableDatabase();
        //db.execSQL("insert into users values('deva','abc')");
        Cursor c=db.rawQuery("select * from users",null);
    }
    String attend(String name)
    {
        SQLiteDatabase db=getWritableDatabase();
        Cursor c=db.rawQuery("select attendence from student where name='"+name+"' ",null);
        c.moveToFirst();
        return c.getString(0);

    }
}