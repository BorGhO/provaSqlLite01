package com.example.provasqllite01

import android.database.Cursor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

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

        (activity as MainActivity?)?.scriviQualcosa()    //questo per usare un metodo dell'activity in un fragment

    }

}