package com.cead.androidsiete

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.CardView
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class CardViewCustom : AppCompatActivity() {
    /*
        cardview: El sistema proporciona la CardViewAPI como una forma fácil de mostrar información dentro de las tarjetas que tienen un
        aspecto coherente en toda la plataforma. Estas tarjetas tienen una elevación predeterminada por encima de su grupo de vistas contenidas,
        por lo que el sistema dibuja sombras debajo de ellas. Las tarjetas proporcionan una manera fácil de contener un grupo de vistas mientras
        que proporcionan un estilo consistente para el contenedor.

     */


    var lisMovies: ArrayList<Movie> ? = null
    var mLayoutManager: RecyclerView.LayoutManager? = null
    var mAdapter: RecyclerView.Adapter<MyAdapterCard.ViewHolder>?= null
    var mRecyclerView : RecyclerView ? = null
    var counter: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_view)

        lisMovies = getAllMovies()

        mLayoutManager = LinearLayoutManager(this)

        mRecyclerView = findViewById(R.id.my_recycler_view_dos)

        mAdapter = MyAdapterCard(lisMovies, R.layout.recycler_card, object : MyAdapterCard.OnItemClickListener{
            override fun onItemclick(movie: Movie?, position: Int) {
                Toast.makeText(this@CardViewCustom,"elemento a borrar"+ movie.toString(), Toast.LENGTH_SHORT).show()
                removePelicula(position)
            }

        }, this)

        mRecyclerView!!.setHasFixedSize(true)

        mRecyclerView!!.itemAnimator = DefaultItemAnimator()
        mRecyclerView!!.layoutManager = mLayoutManager
        mRecyclerView!!.adapter = mAdapter






    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item!!.itemId) {
            R.id.add_name -> {

                addPelicula(0)
                return true
            }
            R.id.nextActivity -> {
               Toast.makeText(this, "Bien XD", Toast.LENGTH_LONG).show()
                return true
            }
            else ->{
                return super.onOptionsItemSelected(item)
            }
        }
    }


    fun addPelicula(position: Int){

        lisMovies!!.add(position, Movie("newChoc"+(++counter), R.mipmap.ic_niceandroid))
        mAdapter!!.notifyItemInserted(position)
        mLayoutManager!!.scrollToPosition(position)


    }

    fun removePelicula(position: Int){
        lisMovies!!.removeAt(position)
        mAdapter!!.notifyItemRemoved(position)
    }


    fun getAllMovies(): ArrayList<Movie>? {
        var lista: ArrayList<Movie> = arrayListOf(
            Movie("chocolate",  R.drawable.chcolate),
            Movie("chocolate1", R.drawable.choc_materia_prima),
            Movie("chocolate2", R.drawable.chocolate_preparacion),
            Movie("chocolate3", R.drawable.chocolates_ingredientes))
        return lista
    }
}
