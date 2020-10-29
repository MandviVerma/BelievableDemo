package com.believable

data class HomeCategoryModel(
    val `data`: Data
) {
    data class Data(
        val Categories: List<Category>
    ) {
        data class Category(
            val list: List<GroceryDetails>,
            val image: String="",
            val name: String=""
        ) {
            data class GroceryDetails(
                val img: String="",
                val item: String="",
                val price: String="")
        }
    }
}

