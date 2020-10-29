package com.believable



import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CategoryAdapter(
    private var mContext: Context?,
    private var categories: ArrayList<HomeCategoryModel.Data.Category.GroceryDetails>,
    private var mListener: OnItemClickListener) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_new_arrival, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(categories[position],position)
        holder.itemView.setOnClickListener {
            mListener.onItemClick(position,it)
        }

    }

    override fun getItemCount(): Int {
        return categories.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvItem = itemView.findViewById<TextView>(R.id.tvItem)
        private val ivItem = itemView.findViewById<ImageView>(R.id.ivItem)

        val tvItemAmount = itemView.findViewById<TextView>(R.id.tvItemAmount)
        val tvQty = itemView.findViewById<TextView>(R.id.tvQty)
        val ivCart=itemView.findViewById<ImageView>(R.id.ivAddCart)

        fun setData(category: HomeCategoryModel.Data.Category.GroceryDetails, position: Int) {
            tvItem.text=category.item
            tvItemAmount.text=category.price
            Glide.with(mContext!!)
                .load(category.img)
                .centerCrop().placeholder(android.R.drawable.ic_media_rew)
                .into(ivItem)

            ivCart.setOnClickListener{
                mListener.onAddCart(position,it)
            }

            tvQty.text = category.qty.toString()
        }

    }

    interface OnItemClickListener {
        fun onItemClick(position: Int,view: View)
        fun onAddCart(position: Int, it: View?)
    }


}