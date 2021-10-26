package com.example.headsupgame

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import kotlin.random.Random

class DBHlpr(context  : Context) : SQLiteOpenHelper(context,"headGames.dp",null,1) {
    var sqLiteDatabase: SQLiteDatabase =writableDatabase

    override fun onCreate(db: SQLiteDatabase?) {
        if(db!=null){
            db.execSQL("create table Heads (Name text,Taboo1 text ,Taboo2 text ,Taboo3 text)")
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
    fun savedat(s1:String, s2:String,s3:String,s4:String){
        val cv= ContentValues()
        cv.put("Name",s1)
        cv.put("Taboo1",s2)
        cv.put("Taboo2",s3)
        cv.put("Taboo3",s4)
      sqLiteDatabase.insert("Heads",null,cv)

    }
    @SuppressLint("Range")
    fun readData(): ArrayList<Heads> {
        val list: ArrayList<Heads> = ArrayList()
        val db = this.readableDatabase
            //var random= Random.nextInt(0,list.size)
        val query = "Select * from Heads"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                val game = Heads()
                game.name = result.getString(result.getColumnIndex("Name"))
                game.taboo1 = result.getString(result.getColumnIndex("Taboo1"))
                game.taboo2 = result.getString(result.getColumnIndex("Taboo2"))
                game.taboo3 = result.getString(result.getColumnIndex("Taboo3"))


                list.add(game)
            }
            while (result.moveToNext())
        }
        return list
    }


    fun update(s1: String, s2: String): Int {
        sqLiteDatabase=writableDatabase

        var c:Cursor =sqLiteDatabase.query("Heads",null,"Name=?", arrayOf(s1),null,null,null)
        if(c.count>0){
            val cv = ContentValues()
        cv.put("Name", s2)
        sqLiteDatabase.update("Heads", cv, "Name = ?", arrayOf(s1))
            return 1
        }
        else{
            return -1
        }


    }

    fun del(s1: String) :Int{
        sqLiteDatabase=writableDatabase
        var c:Cursor =sqLiteDatabase.query("Heads",null,"Name=?", arrayOf(s1),null,null,null)
        if(c.count>0){
            sqLiteDatabase.delete("Heads", "Name =?",  arrayOf(s1))
            return 1
        }
       else{
           return  -1
       }
    }
}

