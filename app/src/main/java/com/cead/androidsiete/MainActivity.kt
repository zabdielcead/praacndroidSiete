package com.cead.androidsiete

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.*
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

class MainActivity : AppCompatActivity() {


    //Reciclerview es mas avanzada de un listview
    //Reciclerview consta de un layout manager (linearlayoutManager, gridlayoutManager, staggeredlayoutmanager )


    var names: ArrayList<String>? = null
    var myRecyclerView: RecyclerView? = null
    var myAdapter: RecyclerView.Adapter<MyAdapter.ViewHolder>? = null
    var myLayoutManager: RecyclerView.LayoutManager? = null
    var counter: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        names = getAllNames()

        myRecyclerView = findViewById(R.id.my_recycler_view)

        myLayoutManager = LinearLayoutManager(this)
        myLayoutManager = GridLayoutManager(this,2)
        myLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)   // renderiza los item de diferente tamaño

        myAdapter = MyAdapter(names, R.layout.recycler_view_item, object : MyAdapter.OnItemClickListener {

            override fun onItemclick(name: String?, position: Int) {
                Toast.makeText(this@MainActivity, "nombre: $name position: $position", Toast.LENGTH_LONG).show()
                deleteName(position)


            }


        })


        // myRecyclerView!!.setHasFixedSize(true) // si sabemos que el layout no se va hacer mas grande el tamaño es contante mandamos true este caso es ,as para LinearLayoutmanager y gridLayoutManager
        myRecyclerView!!.itemAnimator = DefaultItemAnimator()

        myRecyclerView!!.layoutManager = myLayoutManager
        myRecyclerView!!.adapter = myAdapter


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item!!.itemId) {
            R.id.add_name -> {

                addName(0)
                return true
            }
            R.id.nextActivity -> {
                var intent = Intent(this@MainActivity, CardViewCustom::class.java)
                startActivity(intent)
                return true
            }
            else ->{
                return super.onOptionsItemSelected(item)
            }
        }
    }


    fun getAllNames(): ArrayList<String> {
        return arrayListOf("JUAN", "CARLOS", "ITZEL", "MARIANO")
    }

    fun addName(position: Int){
        names!!.add(position,"New name"+(++counter))
        myAdapter!!.notifyItemInserted(position)
        myLayoutManager!!.scrollToPosition(position)
    }

    fun deleteName(position: Int){
        names!!.removeAt(position)
        myAdapter!!.notifyItemRemoved(position)

    }


}
