package com.cead.androidsiete

import android.support.v7.widget.RecyclerView
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView

class MyAdapter(private var names : List<String> ?, private var layout: Int ?,  private var onItemClickListener: OnItemClickListener?) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {



    override fun getItemCount(): Int {
        return names!!.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        var v: View =  LayoutInflater.from(parent.context).inflate( layout!! ,parent, false)
        var vh  = ViewHolder(v)
        return vh
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         holder.bind(names!![position], onItemClickListener!!)
    }


    class ViewHolder: RecyclerView.ViewHolder {
        var txtViewName: TextView  ? = null

        constructor(itemview: View?) : super(itemview!!) {
            this.txtViewName = itemview!!.findViewById(R.id.txtView)
        }


        fun bind(  name: String, listener : OnItemClickListener){
            txtViewName!!.text = name
            itemView.setOnClickListener {
                listener.onItemclick(name, adapterPosition)
            }//recycler view
        }
    }

    interface OnItemClickListener {
        fun onItemclick (name:String ? , position:Int)
    }
}