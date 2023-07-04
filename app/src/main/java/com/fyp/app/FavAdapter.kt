package com.fyp.app

import android.provider.ContactsContract.Data
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FavAdapter(private var fav:MutableList<Fav>):RecyclerView.Adapter<FavAdapter.ViewHolder2>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder2 {
        val itemview=LayoutInflater.from(parent.context)
        val list=itemview.inflate(R.layout.item_layout_second,parent,false)
        return ViewHolder2(list)
    }

    override fun onBindViewHolder(holder: FavAdapter.ViewHolder2, position: Int) {
        val titles=fav[position]
        holder.CustomBind(titles)
    }

    override fun getItemCount(): Int {
        return fav.size
    }
    class ViewHolder2(private var itemview: View):RecyclerView.ViewHolder(itemview){

        fun CustomBind(title:Fav){
            val titlename:TextView=itemview.findViewById(R.id.title)

            titlename.text=title.title.toString()
        }
    }
}