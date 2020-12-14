package com.example.provasqllite01

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {


    lateinit var lists: ArrayList<RecycleClass01>
    lateinit var DB: SQHelper
    lateinit var dataSql: Cursor


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))


        lists = ArrayList<RecycleClass01>()
        DB = SQHelper(applicationContext)
        dataSql = DB.data_getter   //data_getter è il Cursor

       


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            R.id.toAdd_subject -> {
                openAdd_subject()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun openAdd_subject() {

        val intent = Intent(this, add_subject::class.java)
        startActivity(intent)

    }

    fun porco(){

        println("porcodio")
    }


    override fun onStart() {
        super.onStart()

        updateData()
        recyFiller()


        val tastoVolante01 = findViewById<FloatingActionButton>(R.id.fab)

        tastoVolante01.setOnClickListener { view ->

            println("premuto volante uno")
            updateData()
            startActivity(Intent(this, MainActivity::class.java))
            Toast.makeText(applicationContext, "Aggiornato", Toast.LENGTH_LONG).show()
        }


        val tastoVolante02 = findViewById<FloatingActionButton>(R.id.fab2)

        tastoVolante02.setOnClickListener { view ->

            println("premuto volante due")

            val DB = SQHelper(applicationContext)
            DB.resetID()

            Toast.makeText(applicationContext, "Azzerato indice", Toast.LENGTH_LONG).show()
        }

    }


    fun recyFiller(){


        val recycle =  findViewById<RecyclerView>(R.id.recycle01)    //recyclerView contenuto nel xml di questa activity
        val adapter = RecycleAdapter01(lists, applicationContext)
        // val adapter = view.context?.let { RecycleAdapter01(listSql, it) }
        //il layout manager, ce ne sono di vario tipo, sono quelli che definiscono come distribuire graficamente gli elementi della lista
        recycle.layoutManager = GridLayoutManager(applicationContext, 3)    //spanCount è il numero di colonne
        recycle.adapter = adapter


    }

    fun updateData() {

        if (dataSql.count == 0)
            Toast.makeText(applicationContext, "Vuoto", Toast.LENGTH_LONG).show()
        else {

            lists.clear()
            while (dataSql.moveToNext()) {


                lists.add(RecycleClass01(dataSql.getString(0), dataSql.getString(1), dataSql.getString(2)))

                println("0: " + dataSql.getString(0) + " 1: " + dataSql.getString(1) + " 2: " + dataSql.getString(2))
            }
        }
    }



}
