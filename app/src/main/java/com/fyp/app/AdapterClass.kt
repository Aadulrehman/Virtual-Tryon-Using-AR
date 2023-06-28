package com.fyp.app

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class AdapterClass(private val dataList: ArrayList<DataClass>): RecyclerView.Adapter<AdapterClass.ViewHolderClass>() {

    var onItemClick: ((DataClass) -> Unit)? = null

    private var button1ClickListener: ButtonClickListener? = null
    private var button2ClickListener: ButtonClickListener? = null

    interface ButtonClickListener {
        fun onButton1Click(position: Int)
        fun onButton2Click(position: Int)
    }
    fun setButton1ClickListener(listener: ButtonClickListener) {
        button1ClickListener = listener
    }
    fun setButton2ClickListener(listener: ButtonClickListener) {
        button2ClickListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolderClass(itemView)
    }
    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val currentItem = dataList[position]
        holder.rvImage.setImageResource(currentItem.dataImage)
        holder.rvTitle.text = currentItem.dataTitle
        holder.button.setOnClickListener {
            button1ClickListener?.onButton1Click(position)
        }
        holder.fbutton.setOnClickListener {
            button2ClickListener?.onButton2Click(position)
        }

        holder.itemView.setOnClickListener{
            onItemClick?.invoke(currentItem)
        }
    }
    override fun getItemCount(): Int {
        return dataList.size
    }
    class ViewHolderClass(itemView: View): RecyclerView.ViewHolder(itemView) {
        val rvImage: ImageView = itemView.findViewById(R.id.image)
        val rvTitle: TextView = itemView.findViewById(R.id.title)
        val button: Button = itemView.findViewById(R.id.mycam)
        val fbutton:Button= itemView.findViewById(R.id.favbtn)
    }
}