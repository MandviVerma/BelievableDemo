package com.believable

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.fragment_home.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class HomeFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater!!.inflate(R.layout.fragment_home, container, false)

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
        return view
    }


}