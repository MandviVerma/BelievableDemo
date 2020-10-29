package com.believable



import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class CategoryAdapter(
    private var mContext: Context?,
    private var categories: ArrayList<CategoryModel>,
    private var mListener: OnItemClickListener


) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_new_arrival, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(categories[position].toString())
        holder.itemView.setOnClickListener {
            mListener.onItemClick(position,it)
        }

    }

    override fun getItemCount(): Int {
        return categories.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvItem = itemView.findViewById<TextView>(R.id.tvItem)
        val tvItemAmount = itemView.findViewById<ConstraintLayout>(R.id.tvItemAmount)
        val ivCart=itemView.findViewById<ImageView>(R.id.ivAddCart)



        fun setData(category: String) {

            ivCart.setOnClickListener{
                mListener.onAddCart(position,it)
            }

        }

    }

    interface OnItemClickListener {
        fun onItemClick(position: Int,view: View)
        fun onAddCart(position: Int, it: View?)
    }


}