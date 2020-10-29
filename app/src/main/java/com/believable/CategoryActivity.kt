package com.believable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_category.*

class CategoryActivity : AppCompatActivity(), CategoryAdapter.OnItemClickListener {
    lateinit var categoryAdapter: HomeCategoryAdapter
    var categoryList: ArrayList<CategoryModel> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        intRecView()
    }

    private fun intRecView() {
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

        startActivity(intent)
    }

    override fun onAddCart(position: Int, it: View?) {
        val cartFragment = CartFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, cartFragment).commit()
    }

}
