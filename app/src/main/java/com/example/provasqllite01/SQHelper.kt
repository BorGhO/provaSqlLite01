package com.example.provasqllite01

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQHelper(context: Context) : SQLiteOpenHelper (context, DB_name, null, 1){

    val data_getter: Cursor get(){
            //this intende questa classe
        val DB = this.writableDatabase  //questa variabile serve a rendere il DB editabile, perchè di suo è chiuso
        var data = DB.rawQuery("select * from $TB_name", null) //questo trasferisce a data (che è una var, notare) tutti i valori di DB

        return data
    }

    fun ADD_DATA(title_toAdd:String, desc_toAdd:String){    //per aggiungere righe alla tabella. L'id non si specifica perchè c'è l'AUTOINCREMENT

        val DB = this.writableDatabase
        val values = ContentValues()
        values.put(title, title_toAdd)  //il primo argomento è il field che c'è già, il secondo il valore da inserire
        values.put(desc, desc_toAdd)
        DB.insert (TB_name, null, values)   //questo per inserire i valori nel DB

    }


    fun DELETE_DATA(id: String): Int{   //questo deleta l'elemento in posizione id e lo ritorna anche

        val DB = this.writableDatabase          //id = ? è il where
        val item = DB.delete(TB_name, "id = ?", arrayOf(id))
        return item
    }

    companion object{


        val DB_name = "cosoDB.db "   //importante mettere l'estensione .db
        val TB_name = "cosoTable "  //forse c'è da mettere sempre uno spazio alla fine dei nomi, per un bug. forse
        val tempTB_name = "tempTable"
        val id = "ID"
        val title = "Coso_title"
        val desc = "Coso_desc"
    }

    override fun onCreate(db: SQLiteDatabase?) {
                                                            //ID è l'id della colonna. L'autoincrement vuol dire che aumenta da solo man mano che vengono aggiunti elementi
        db?.execSQL("create table $TB_name(ID INTEGER PRIMARY KEY, Coso_title TEXT, Coso_desc TEXT)")

    }

    fun resetID(){  //questo resetta l'indice, che è la primary key. lo fa copiando i dati in una cartella temporanea, poi deleta la cartella principale, la ricrea e ci mette dentro
        println("resetID")
        val DB = this.writableDatabase

        DB.execSQL("DROP TABLE IF EXISTS $tempTB_name")
        DB.execSQL("create table '$tempTB_name' (ID INTEGER PRIMARY KEY, Coso_title TEXT, Coso_desc TEXT)")

        DB.execSQL("INSERT INTO $tempTB_name($title, $desc) SELECT $title, $desc FROM $TB_name");

        DB.execSQL("DROP TABLE IF EXISTS $TB_name")

        DB.execSQL("create table $TB_name(ID INTEGER PRIMARY KEY, Coso_title TEXT, Coso_desc TEXT)")

        DB.execSQL("INSERT INTO $TB_name SELECT * FROM $tempTB_name");
        DB.execSQL("DROP TABLE IF EXISTS $tempTB_name")

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TB_name")
    }

}