package com.believable

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class HomeCategoryModel(
    val `data`: Data
) {
    data class Data(
        val Categories: List<Category>
    ) {
        data class Category(
            val list: ArrayList<GroceryDetails>,
            val image: String="",
            val name: String=""
        ) {
            @Parcelize
            data class GroceryDetails(
                val img: String="",
                val item: String="",
                val price: String=""):Parcelable
        }
    }
}

