package com.believable

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class HomeCategoryAdapter(
    private var mContext: Context?,
    private var categories: ArrayList<HomeCategoryModel.Data.Category>,
    private var mListener: OnItemClickListener) : RecyclerView.Adapter<HomeCategoryAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.home_categories, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(categories[position])

        holder.itemView.setOnClickListener {
            mListener.onItemClick(position,it)
        }
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvCategory = itemView.findViewById<TextView>(R.id.tvCategories)
        private val ivCategory = itemView.findViewById<ImageView>(R.id.ivCategories)
        val clCategory = itemView.findViewById<ConstraintLayout>(R.id.clCategory)

        fun setData(category: HomeCategoryModel.Data.Category) {
            tvCategory.text = category.name
            Glide.with(mContext!!)
                .load(category.image)
                .centerCrop()
                .placeholder(android.R.drawable.ic_media_rew)
                .into(ivCategory);
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int,view: View)
    }

}