package com.believable

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class Adapters(val context: Context?):RecyclerView.Adapter<Adapters.MyViewHolder>(){
    lateinit var  list:List<Int>
    fun setContentList(list:List<Int>){
        this.list=list
        notifyDataSetChanged()
    }
    inner class MyViewHolder(view: View):RecyclerView.ViewHolder(view){
var image=itemView.findViewById<ImageView>(R.id.list_item_image)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapters.MyViewHolder {
val view=LayoutInflater.from(context).inflate(R.layout.list_image,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: Adapters.MyViewHolder, position: Int) {
holder.image.setImageResource(list[position])
   }

    override fun getItemCount(): Int {
        return list.size     }
}