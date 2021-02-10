package com.believable

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_category.*

class CategoryActivity : AppCompatActivity(), CategoryAdapter.OnItemClickListener {
    lateinit var categoryAdapter: CategoryAdapter
    var categoryList: ArrayList<HomeCategoryModel.Data.Category.GroceryDetails> = ArrayList()
    var name = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_category)

        name = intent.getStringExtra("CategoryName") ?: ""
        categoryList.addAll(intent.getParcelableArrayListExtra("Data") ?: categoryList)

        intRecView()
        tvItem.text = name

        btnViewCart.setOnClickListener {
            finish()
        }

    }

    private fun intRecView() {
        categoryAdapter = CategoryAdapter(this, categoryList, this)

        rv_category.apply {
            adapter = categoryAdapter
            layoutManager = GridLayoutManager(context, 2)
        }
    }

    override fun onItemClick(position: Int, view: View) {
        val intent = Intent(
            this,
            ItemsOfCategory::class.java
        )
        intent.putExtra("CategoryName", categoryList[position].item)
        intent.putExtra("CategoryImage", categoryList[position].img)


        startActivity(intent)
    }

    override fun onAddCart(position: Int, it: View?) {
        categoryList[position].qty += 1
        var present = false
        CartFragment.cartList.forEach {
            if (it.item == categoryList[position].item) {
                present = true
            }
        }
        if (!present)
            CartFragment.cartList.add(categoryList[position])
        categoryAdapter.notifyDataSetChanged()
    }

}

