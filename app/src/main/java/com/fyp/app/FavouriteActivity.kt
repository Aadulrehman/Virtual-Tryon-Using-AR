package com.fyp.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.*

class FavouriteActivity : AppCompatActivity() {
    private lateinit var dbRef: DatabaseReference
    private lateinit var rv:RecyclerView
    private lateinit var adapter:FavAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourite)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.BottomNavigation)
        bottomNavigationView.selectedItemId = R.id.fav
        val email=intent.getStringExtra("email").toString().trim()



        val favoriteList= mutableListOf<Fav>()
        dbRef= FirebaseDatabase.getInstance().getReference("Favourites")
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {


                favoriteList.clear()
                if(snapshot.exists()){
                    for (i in snapshot.children){
                        val fetchemail=i.child("email").value.toString().trim()
                        val fetchtitle=i.child("title").value.toString().trim()


                        if(email==fetchemail){
                            Log.d("Ola",fetchemail)
                            Log.d("Ola",email)


                            val con=i.getValue(Fav::class.java)
                            favoriteList.add(con!!)
                            Log.d("Ola",favoriteList.size.toString())
                        }
                    }
                    // Update the RecyclerView with the fetched data
                    setupRecyclerView(favoriteList)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext,"Firebase failed to retrieve information", Toast.LENGTH_SHORT).show()
            }

        })

        bottomNavigationView.setOnItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.fav -> return@setOnItemSelectedListener true
                R.id.camera -> {
                    val intent =Intent(this@FavouriteActivity,GlassesActivity::class.java)
                    intent.putExtra("email",email)
                    startActivity(intent)
                    return@setOnItemSelectedListener true
                }
                R.id.home-> {
                    val intent =Intent(this@FavouriteActivity,InventoryActivity::class.java)
                    intent.putExtra("email",email)
                    startActivity(intent)
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    finish()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }



    }
    private fun setupRecyclerView(favoriteList: MutableList<Fav>) {
        rv = findViewById(R.id.recyclerviewsecond)
        rv.layoutManager = LinearLayoutManager(this)
        Log.d("Ola","Favorite List size inside setupRecyclerView${favoriteList.size}")


        for (i in favoriteList) {
            Log.d("title", "favorite list titles${i.title}")
        }

        adapter = FavAdapter(favoriteList)
        rv.adapter = adapter
        adapter.notifyDataSetChanged()
        rv.scrollToPosition(favoriteList.size - 1)
    }

}