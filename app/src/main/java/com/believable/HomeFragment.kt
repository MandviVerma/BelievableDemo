package com.believable

import android.app.ActionBar
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
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.drawer_layout.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.tvCategories
import kotlinx.android.synthetic.main.home_categories.*
import kotlinx.android.synthetic.main.toolbar.*


class HomeFragment : Fragment(), HomeCategoryAdapter.OnItemClickListener {
    lateinit  var categoryAdapter: HomeCategoryAdapter
    val categoryList = ArrayList<HomeCategoryModel.Data.Category>()
    lateinit var productsDR: DatabaseReference
    lateinit var ab: ActionBar

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        productsDR = FirebaseDatabase.getInstance().getReference("Data")
        getPrducts()
        initView()
        onClick()

        categoryAdapter = HomeCategoryAdapter(context, categoryList, this)
        rvHomeCategory.apply {
            adapter = categoryAdapter
            layoutManager = GridLayoutManager(context, 2)
        }

    }

    private fun getPrducts() {
        productsDR.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(context, "Database error :" + p0.toString(), Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onDataChange(snapShot: DataSnapshot) {
                val homeCategoryModel  = snapShot.getValue(HomeCategoryModel::class.java)
                categoryList.addAll(homeCategoryModel?.data?.Categories!!)
                categoryAdapter.notifyDataSetChanged()
            }

        })

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater!!.inflate(R.layout.fragment_home, container, false)

        return view
    }


    private fun initView() {

        val actionBarDrawerToggle =
            ActionBarDrawerToggle(
                context as Activity?,
                drawerLayout,
                R.string.open_drawer,
                R.string.close_drawer
            )
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        activity?.setActionBar(toolbar)
        //activity?.actionBar?.setDisplayHomeAsUpEnabled(true)
        //activity?.actionBar?.setHomeButtonEnabled(true)
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
            tvCategories.setBackgroundResource(R.drawable.btn_green);
            tvCategories.setTextColor(Color.parseColor("#ffffff"))
            tvNewArrival.setTextColor(Color.parseColor("#6fae07"))
            tvBudgetFriendly.setTextColor(Color.parseColor("#6fae07"))

        }
        tvNewArrival.setOnClickListener {
            tvNewArrival.setBackgroundResource(R.drawable.btn_green);
            tvNewArrival.setTextColor(Color.parseColor("#ffffff"))
            tvCategories.setTextColor(Color.parseColor("#6fae07"))
            tvBudgetFriendly.setTextColor(Color.parseColor("#6fae07"))
        }
        tvBudgetFriendly.setOnClickListener {
            tvBudgetFriendly.setBackgroundResource(R.drawable.btn_green);
            tvBudgetFriendly.setTextColor(Color.parseColor("#ffffff"))
            tvNewArrival.setTextColor(Color.parseColor("#6fae07"))
            tvCategories.setTextColor(Color.parseColor("#6fae07"))
        }

    }

    override fun onItemClick(position: Int, view: View) {
        val intent = Intent(
            context,
            CategoryActivity::class.java
        )

        startActivity(intent)
    }


}


