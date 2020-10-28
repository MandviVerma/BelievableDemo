package com.believable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.drawer_layout.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity() {
    internal var prevMenuItem: MenuItem? = null
    lateinit var viewPager: ViewPager

    lateinit var drawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()

        onClick()
        menu_bottom.setItemSelected(R.id.home)
        menu_bottom.setOnItemSelectedListener {
            when (it) {

                R.id.home -> {
                    val homeFragment = HomeFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, homeFragment).commit()
                    supportActionBar?.hide()
                    drawerLayout.closeDrawer(GravityCompat.START)
                }
                R.id.cart -> {
                    val cartFragment = CartFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, cartFragment).commit()

                }
                R.id.profile -> {
                    val profileFragment = ProfileFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, profileFragment).commit()

                }
            }
        }


        initViewPager()

    }


    private fun initViewPager() {
        TODO("Not yet implemented")
    }

    private fun initView() {
        drawerLayout = findViewById(R.id.drawerLayout)
        val actionBarDrawerToggle =
            ActionBarDrawerToggle(this, drawerLayout, R.string.open_drawer, R.string.close_drawer)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)
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

    }
}