package com.example.provasqllite01

import android.app.Dialog
import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.*
import androidx.recyclerview.widget.RecyclerView
import com.example.provasqllite01.R.color
import com.google.android.material.snackbar.Snackbar
import java.util.*
import kotlin.collections.ArrayList

//il : qui intende dire che si sta facendo un'estensione della classe seguente, quindi RecyclerAdapter01 estende RecyclerView.Adapter
class RecycleAdapter01 (data:ArrayList<RecycleClass01>, var context: Context) : RecyclerView.Adapter<RecycleAdapter01.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){   //qui si estende la proprità ViewHolder di Recy, che è il contenitore degli elementi

        internal var title:TextView
        internal var desc: TextView
        internal var ind: TextView
        internal var card: ConstraintLayout


        init{

            title = itemView.findViewById(R.id.recycleText01)
            desc = itemView.findViewById(R.id.recycleDesc01)
            ind = itemView.findViewById(R.id.recycleIndex01)
            card = itemView.findViewById(R.id.card01)
        }
    }




    internal var data:List<RecycleClass01>

    init{

        this.data = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {     //va fatto l'override di tutti i metodi di RecyclerView.Adapter

        val layout = LayoutInflater.from(context).inflate(R.layout.custom_item, parent, false)

        return ViewHolder(layout)
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.title.text = data[position].title
        holder.desc.text = data[position].desc
        holder.ind.text = data[position].indexSql


        if (position % 2 == 1)
            holder.desc.setTextColor( getColor(context, color.purple_500))

        holder.card.setOnClickListener {

            var indice :String = holder.ind.text.toString()

            println("ID: "+indice)

            holder.title.setTextColor(getColor(context, color.red))
            holder.desc.setTextColor(getColor(context, color.red))
            holder.ind.setTextColor(getColor(context, color.red))

            parla("è stato eliminato il dato con indice "+holder.ind.text.toString())

            val DB: SQHelper = SQHelper(context)
            DB.DELETE_DATA(indice)


            Snackbar.make(holder.card,"Eliminato l'elemento "+ indice, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()

        }
    }

    override fun getItemCount(): Int{

        return data.size
    }



    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun parla (daDire:String){

        println(daDire)

        lateinit var toSpeech : TextToSpeech

        toSpeech = TextToSpeech(context, TextToSpeech.OnInitListener { status ->

            if (status == TextToSpeech.SUCCESS){

                toSpeech.language = Locale.ITALIAN
                toSpeech.speak(daDire, TextToSpeech.QUEUE_FLUSH, null, null)

            }


        })


    }


}

