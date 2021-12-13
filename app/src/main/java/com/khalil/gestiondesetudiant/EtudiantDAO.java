package com.khalil.gestiondesetudiant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EtudiantDAO {

    public static final String CREATE_TABLE = "create table student ( id INTEGER PRIMARY KEY AUTOINCREMENT, Fname TEXT, Sname TEXT,Cls TEXT);";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS student ";

    public DataBaseHandler dbHandler;

    public EtudiantDAO(Context context) {
        dbHandler = new DataBaseHandler(context, "", null, 1);
    }

    public void insertdata(Etudiant e) {
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("Fname", e.getFname());
        values.put("Sname", e.getSname());
        values.put("Cls", e.getCls());
        db.insert("student", null, values);
        db.close();

    }
    public void deleData(Integer first){
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        db.delete("student","id='"+first+"'",null);
    }
    public Cursor showdata(){
        SQLiteDatabase db = dbHandler.getReadableDatabase();
        String req="SELECT * FROM student";
        Cursor c=db.rawQuery(req,null);
        return c;
    }
    public Cursor showEtudiant(Integer i){
        SQLiteDatabase db = dbHandler.getReadableDatabase();
        String req="SELECT * FROM student WHERE id='"+i+"'";
        Cursor c=db.rawQuery(req,null);
        return c;
    }
    public void updateData(Etudiant e, Integer i){
        SQLiteDatabase db = dbHandler.getWritableDatabase();

        ContentValues v = new ContentValues();
        v.put("Fname", e.getFname());
        v.put("Sname", e.getSname());
        v.put("Cls", e.getCls());

        db.update("student",v,"id="+i,null);

    }



}
