package com.example.provasqllite01

import android.database.Cursor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    lateinit var listSql: ArrayList<RecycleClass01>
    lateinit var DB: SQHelper
    lateinit var dataSql: Cursor


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       /* view.findViewById<Button>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }*/



        listSql = ArrayList<RecycleClass01>()
        DB = SQHelper(view.context)
        dataSql = DB.data_getter   //data_getter è il Cursor

       // updateData()
       //refreshFrag(view)


        (activity as MainActivity?)?.porco()    //questo per usare un metodo dell'activity in un fragment



    }


/*
    fun refreshFrag(view: View){


        val recycle =  view.findViewById<RecyclerView>(R.id.recycle01)    //recyclerView contenuto nel xml di questa activity
        val adapter = RecycleAdapter01(listSql, view.context)
       // val adapter = view.context?.let { RecycleAdapter01(listSql, it) }
        //il layout manager, ce ne sono di vario tipo, sono quelli che definiscono come distribuire graficamente gli elementi della lista
        recycle.layoutManager = GridLayoutManager(view.context, 3)    //spanCount è il numero di colonne
        recycle.adapter = adapter


    }

    fun updateData() {

        if (dataSql.count == 0)
            Toast.makeText(view?.context, "Vuoto", Toast.LENGTH_LONG).show()
        else {

            while (dataSql.moveToNext()) {


                listSql.add(RecycleClass01(dataSql.getString(0), dataSql.getString(1), dataSql.getString(2)))

                println("0: " + dataSql.getString(0) + " 1: " + dataSql.getString(1) + " 2: " + dataSql.getString(2))
            }
        }
    }*/
}