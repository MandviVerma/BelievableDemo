package com.believable

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class CartAdapter(
    private var mContext: Context?,
    private var categories: ArrayList<HomeCategoryAdapter>,
    private var mListener: OnItemClickListener


) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(categories[position].toString())

        holder.itemView.setOnClickListener {
            mListener.onItemClick(position, it)
        }
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvItem = itemView.findViewById<TextView>(R.id.tvItem)
        val tvItemAmount = itemView.findViewById<TextView>(R.id.tvItemAmount)
        val ivRemove = itemView.findViewById<ImageView>(R.id.ivRemove)
        val ivAdd = itemView.findViewById<ImageView>(R.id.ivAdd)
        val ItemAmount = itemView.findViewById<ImageView>(R.id.ItemAmount)


        fun setData(category: String) {
            ivRemove.setOnClickListener {
                mListener.onRemove(position, it)
            }
            ivAdd.setOnClickListener {
                mListener.OnAdd(position, it)
            }
        }


    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, view: View)
        fun onRemove(position: Int, it: View?)
        fun OnAdd(position: Int, it: View?)


    }
}