package com.emrecura.recipeapp_mvvm.app.services

import com.emrecura.recipeapp_mvvm.app.data.Recipes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeService {

    @GET("recipes")
    fun getAllRecipes(@Query("limit") limit : Int) : Call<Recipes>
    @GET("recipes/tags")
    fun getTagsName() : Call<List<String>>
    @GET("recipes/search")
    fun searchRecipes(@Query("q") query: String): Call<Recipes>
    @GET("recipes/tag/{tagName}")
    fun getRecipesByTag(@Path("tagName") tagName: String): Call<Recipes>
}