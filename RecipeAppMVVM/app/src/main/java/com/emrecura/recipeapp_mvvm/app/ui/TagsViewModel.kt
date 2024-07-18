package com.emrecura.recipeapp_mvvm.app.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emrecura.recipeapp_mvvm.app.configs.ApiClient
import com.emrecura.recipeapp_mvvm.app.data.Recipe
import com.emrecura.recipeapp_mvvm.app.data.Recipes
import com.emrecura.recipeapp_mvvm.app.services.RecipeService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TagsViewModel : ViewModel() {

    private val _recipes = MutableLiveData<List<Recipe>>()
    val recipes: LiveData<List<Recipe>> get() = _recipes

    private val _tags = MutableLiveData<List<String>>()
    val tags: LiveData<List<String>> get() = _tags



    init {
        getTags()
    }

    private fun getTags() {
        val service = ApiClient.getClient().create(RecipeService::class.java)
        val call = service.getTagsName()

        call.enqueue(object : Callback<List<String>> {
            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                if (response.isSuccessful) {
                    _tags.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                // Hata durumunda yapılacaklar
            }
        })
    }

    fun getRecipesByTag(tag: String) {
        val service = ApiClient.getClient().create(RecipeService::class.java)
        val call = service.getRecipesByTag(tag)

        call.enqueue(object : Callback<Recipes> {
            override fun onResponse(call: Call<Recipes>, response: Response<Recipes>) {
                if (response.isSuccessful) {
                    _recipes.value = response.body()?.recipes
                    Log.d("TAG", _recipes.value.toString())
                }
            }

            override fun onFailure(call: Call<Recipes>, t: Throwable) {
                // Hata durumunda yapılacaklar

            }
        })
    }
}
