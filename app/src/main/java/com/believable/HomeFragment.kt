package com.believable

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.believable.CartFragment.Companion.cartList
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.drawer_layout.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.tvCategories
import kotlinx.android.synthetic.main.toolbar.*


class HomeFragment : Fragment(), HomeCategoryAdapter.OnItemClickListener,
    CategoryAdapter.OnItemClickListener {

    lateinit var categoryAdapter: HomeCategoryAdapter
    lateinit var newArrivalAdapter: CategoryAdapter
    lateinit var adapters: Adapters
    val newArrivalList = ArrayList<HomeCategoryModel.Data.Category.GroceryDetails>()
    val categoryList = ArrayList<HomeCategoryModel.Data.Category>()
    lateinit var productsDR: DatabaseReference

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        productsDR = FirebaseDatabase.getInstance().getReference("Data")
        getPrducts()
        initView()
        onClick()

        val list = mutableListOf<Int>()
        list.add(R.drawable.food1)
        list.add(R.drawable.food2)
        list.add(R.drawable.food3)
        adapters = Adapters(context)
        adapters.setContentList(list)
        viewpager.adapter=adapters

        categoryAdapter = HomeCategoryAdapter(context, categoryList, this)
        newArrivalAdapter = CategoryAdapter(context, newArrivalList, this)

        rvHomeCategory.apply {
            
            adapter = categoryAdapter
            layoutManager = GridLayoutManager(context, 2)
        }

    }

    private fun getPrducts() {
        productsDR.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(context, "Database error :" + p0.toString(), Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(snapShot: DataSnapshot) {
                categoryList.clear()
                newArrivalList.clear()
                snapShot.children.forEach {
                    if (it.key == "Categories") {
                        it.children.forEach { category ->
                            val list = ArrayList<HomeCategoryModel.Data.Category.GroceryDetails>()
                            category.child("list").children.forEach { details ->
                                list.add(
                                    HomeCategoryModel.Data.Category.GroceryDetails(
                                        details.child("img").value.toString(),
                                        details.child("item").value.toString(),
                                        details.child("price").value.toString()
                                    )
                                )
                            }
                            categoryList.add(
                                HomeCategoryModel.Data.Category(
                                    list,
                                    category.child("image").value.toString(),
                                    category.child("name").value.toString()
                                )
                            )
                        }
                        categoryAdapter.notifyDataSetChanged()
                    } else if (it.key == "NewArrivals") {
                        it.children.forEach { details ->
                            newArrivalList.add(
                                HomeCategoryModel.Data.Category.GroceryDetails(
                                    details.child("img").value.toString(),
                                    details.child("item").value.toString(),
                                    details.child("price").value.toString()
                                )
                            )
                        }
                        newArrivalAdapter.notifyDataSetChanged()

                    }
//                    else if (it.key == "BudgetFriendly") {
//                        it.children.forEach { details ->
//                            newArrivalList.add(
//                                HomeCategoryModel.Data.Category.GroceryDetails(
//                                    details.child("img").value.toString(),
//                                    details.child("item").value.toString(),
//                                    details.child("price").value.toString()
//                                )
//                            )
//                        }
//                        newArrivalAdapter.notifyDataSetChanged()
//                    }

                }
            }

        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    private fun initView() {

        val actionBarDrawerToggle =
            ActionBarDrawerToggle(context as Activity?,
                drawerLayout, R.string.open_drawer, R.string.close_drawer)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        activity?.setActionBar(toolbar)
    }

    private fun onClick() {
        iv_home.setOnClickListener {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }

        ivCancel.setOnClickListener {
            drawerLayout.closeDrawer(GravityCompat.START)
        }

        tvCategories.setOnClickListener {
            rvHomeCategory.apply {
                adapter = categoryAdapter
                layoutManager = GridLayoutManager(context, 2)
            }
            categoryAdapter.notifyDataSetChanged()

            tvCategories.setBackgroundResource(R.drawable.btn_green)
            tvCategories.setTextColor(Color.parseColor("#ffffff"))
            tvNewArrival.setBackgroundResource(R.drawable.btn_white)
            tvNewArrival.setTextColor(Color.parseColor("#6fae07"))
            tvBudgetFriendly.setBackgroundResource(R.drawable.btn_white)
            tvBudgetFriendly.setTextColor(Color.parseColor("#6fae07"))

        }
        tvNewArrival.setOnClickListener {

            rvHomeCategory.apply {
                adapter = newArrivalAdapter
                layoutManager = GridLayoutManager(context, 2)
            }
            newArrivalAdapter.notifyDataSetChanged()

            tvNewArrival.setBackgroundResource(R.drawable.btn_green)
            tvNewArrival.setTextColor(Color.parseColor("#ffffff"))
            tvCategories.setBackgroundResource(R.drawable.btn_white)
            tvCategories.setTextColor(Color.parseColor("#6fae07"))
            tvBudgetFriendly.setBackgroundResource(R.drawable.btn_white)

            tvBudgetFriendly.setTextColor(Color.parseColor("#6fae07"))
        }
        tvBudgetFriendly.setOnClickListener {
            rvHomeCategory.apply {
                adapter = newArrivalAdapter
                layoutManager = GridLayoutManager(context, 2)
            }
            newArrivalAdapter.notifyDataSetChanged()

            tvBudgetFriendly.setBackgroundResource(R.drawable.btn_green)
            tvBudgetFriendly.setTextColor(Color.parseColor("#ffffff"))
            tvNewArrival.setBackgroundResource(R.drawable.btn_white)
            tvNewArrival.setTextColor(Color.parseColor("#6fae07"))
            tvCategories.setBackgroundResource(R.drawable.btn_white)
            tvCategories.setTextColor(Color.parseColor("#6fae07"))
        }

    }

    override fun onItemClick(position: Int, view: View) {
        val intent = Intent(context, CategoryActivity::class.java)
        intent.putExtra("CategoryName", categoryList[position].name)
        intent.putParcelableArrayListExtra("Data", categoryList[position].list)
        startActivity(intent)
    }

    override fun onAddCart(position: Int, it: View?) {
        newArrivalList[position].qty += 1
        var present =false
        cartList.forEach {
            if(it.item == newArrivalList[position].item){
                present =true
            }
        }
        if(!present)
            cartList.add(newArrivalList[position])

        newArrivalAdapter.notifyDataSetChanged()
    }

}


