package com.believable

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.fragment_home.*


class CartFragment : Fragment(),CartAdapter.OnItemClickListener {
    lateinit var cartAdapter: CartAdapter

    companion object{

        var cartList =ArrayList<HomeCategoryModel.Data.Category.GroceryDetails>()
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cartAdapter = CartAdapter(context, cartList, this)
        rvCart.apply {
            adapter = cartAdapter
            layoutManager = LinearLayoutManager(context)
            btnCheckOut.setOnClickListener {
                val intent = Intent(
                    context,
                    PaymentActivity::class.java
                )
                startActivity(intent)
            }
        }

    }

    override fun onItemClick(position: Int, view: View) {

    }

    override fun onRemove(position: Int, it: View?) {
    }

    override fun OnAdd(position: Int, it: View?) {
    }

}