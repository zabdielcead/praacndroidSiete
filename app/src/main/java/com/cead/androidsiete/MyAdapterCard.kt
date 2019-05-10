package com.cead.androidsiete

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class MyAdapterCard (private var movies : List<Movie> ?, private var layout: Int ?,  private var onItemClickListener: OnItemClickListener?, private var context: Context ? ) : RecyclerView.Adapter<MyAdapterCard.ViewHolder>() {



    override fun getItemCount(): Int {
        return movies!!.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        var v: View =  LayoutInflater.from(parent.context).inflate( layout!! ,parent, false)
        var vh  = ViewHolder(v,context!!)
        return vh
    }

    override fun onBindViewHolder(holder: MyAdapterCard.ViewHolder, position: Int) {

        holder.bind(movies!![position], onItemClickListener!!)
    }





    class ViewHolder: RecyclerView.ViewHolder {
        var txtViewName: TextView? = null
        var imageViewPoster: ImageView? = null
        var context: Context? = null

        constructor(itemview: View?, context: Context?) : super(itemview!!) {
            this.txtViewName = itemview!!.findViewById(R.id.txtCardViewItem)
            this.imageViewPoster = itemview!!.findViewById(R.id.imgCardViewItem)
            this.context = context!!
        }


        fun bind(  movie: Movie, listener : OnItemClickListener){
            txtViewName!!.text = movie.name
           // imageViewPoster!!.setImageResource(movie.poster)  lo sustituimos con picasso
            Picasso.with(context).load(movie.poster).fit().into(imageViewPoster)

            itemView.setOnClickListener{
                listener.onItemclick(movie, adapterPosition)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemclick (movie:Movie ? , position:Int)
    }
}