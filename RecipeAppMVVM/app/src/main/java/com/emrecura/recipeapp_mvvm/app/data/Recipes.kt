package com.emrecura.recipeapp_mvvm.app.data

data class Recipes(
    val limit: Int,
    val recipes: List<Recipe>,
    val skip: Int,
    val total: Int
)