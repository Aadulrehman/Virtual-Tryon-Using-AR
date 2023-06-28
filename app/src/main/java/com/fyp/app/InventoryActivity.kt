package com.fyp.app

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView


class InventoryActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var dataList: ArrayList<DataClass>
    private lateinit var adapter: AdapterClass
    lateinit var imageList:Array<Int>
    lateinit var titleList:Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory)


        imageList = arrayOf(
            R.drawable.g1,
            R.drawable.g2,
            R.drawable.g3,
            R.drawable.g4,
            R.drawable.g5,
            R.drawable.g6,
            R.drawable.g7,
            R.drawable.splash)

        titleList = arrayOf(
            "Black Round Frame",
            "Tatum Frame",
            "Oval Frame",
            "Silver Frame",
            "Golden Round Frame",
            "Hughes Frame",
            "Geometric Frame",
            "Glasses 8")


        recyclerView = findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        dataList = arrayListOf<DataClass>()
        getData()


        adapter = AdapterClass(dataList)
        recyclerView.adapter = adapter



        adapter.setButton1ClickListener(object : AdapterClass.ButtonClickListener {
            override fun onButton1Click(position: Int) {
                if(position==0){
                    val intent = Intent(this@InventoryActivity, TryOnGlassesActivity::class.java)
                    intent.putExtra("fname", "F3.sfb")
                    startActivity(intent)
                }
                if(position==1){
                    val intent = Intent(this@InventoryActivity, TryOnGlassesActivity::class.java)
                    intent.putExtra("fname", "yellow_sunglasses.sfb")
                    startActivity(intent)
                }
                if(position==2){
                    val intent = Intent(this@InventoryActivity, TryOnGlassesActivity::class.java)
                    intent.putExtra("fname", "test.sfb")
                    startActivity(intent)
                }
                if(position==3){
                    val intent = Intent(this@InventoryActivity, TryOnGlassesActivity::class.java)
                    intent.putExtra("fname", "F6.sfb")
                    startActivity(intent)
                }
                if(position==4){
                    val intent = Intent(this@InventoryActivity, TryOnGlassesActivity::class.java)
                    intent.putExtra("fname", "glasses.sfb")
                    startActivity(intent)
                }
                if(position==5){
                    val intent = Intent(this@InventoryActivity, TryOnGlassesActivity::class.java)
                    intent.putExtra("fname", "F4.sfb")
                    startActivity(intent)
                }
                if(position==6){
                    val intent = Intent(this@InventoryActivity, TryOnGlassesActivity::class.java)
                    intent.putExtra("fname", "sunglasses.sfb")
                    startActivity(intent)
                }
            }

            override fun onButton2Click(position: Int) {

            }
        })
        adapter.setButton2ClickListener(object : AdapterClass.ButtonClickListener {
            override fun onButton1Click(position: Int) {
            }

            override fun onButton2Click(position: Int) {
                startActivity(Intent(applicationContext, FavouriteActivity::class.java))
            }
        })


        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.BottomNavigation)
        bottomNavigationView.selectedItemId = R.id.home

        bottomNavigationView.setOnItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.home -> return@setOnItemSelectedListener true
                R.id.camera -> {
                    startActivity(Intent(applicationContext, GlassesActivity::class.java))
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    finish()
                    return@setOnItemSelectedListener true
                }
                R.id.fav-> {
                    startActivity(Intent(applicationContext, FavouriteActivity::class.java))
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    finish()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }



    }
    private fun getData(){
        for (i in imageList.indices){
            val dataClass = DataClass(imageList[i], titleList[i])
            dataList.add(dataClass)
        }
        recyclerView.adapter = AdapterClass(dataList)
    }
}