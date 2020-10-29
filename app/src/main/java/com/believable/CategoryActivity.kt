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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
//        ivArrow.setOnClickListener {
//            super.onBackPressed()
//        }
        categoryList.addAll(intent.getParcelableArrayListExtra("Data") ?: categoryList)
        setContentView(R.layout.activity_category)
        intRecView()

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

        startActivity(intent)
    }

    override fun onAddCart(position: Int, it: View?) {

    }

}

