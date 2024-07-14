package com.emrecura.recipeapp_mvvm.app.ui

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
import retrofit2.create

class HomeViewModel : ViewModel() {
    private val _recipes = MutableLiveData<List<Recipe>>()
    val recipes: LiveData<List<Recipe>> get() = _recipes

    private val _tags = MutableLiveData<List<String>>()
    val tags: LiveData<List<String>> get() = _tags

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    init {
        loadTags()
        loadRecipes()
    }

    private fun loadRecipes() {
        _isLoading.value = true
        val recipeService = ApiClient.getClient().create(RecipeService::class.java)
        val call = recipeService.getAllRecipes(30)
        call.enqueue(object : Callback<Recipes> {
            override fun onResponse(call: Call<Recipes>, response: Response<Recipes>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _recipes.value = response.body()?.recipes ?: emptyList()
                } else {
                    _error.value = "Failed to load recipes: ${response.message()}"
                }
            }

            override fun onFailure(call: Call<Recipes>, t: Throwable) {
                _isLoading.value = false
                _error.value = "Failed to load recipes: ${t.message}"
            }
        })
    }

    private fun loadTags() {
        val tagsService = ApiClient.getClient().create(RecipeService::class.java)
        val call = tagsService.getTagsName()
        call.enqueue(object : Callback<List<String>>{
            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                if (response.isSuccessful){
                    _tags.value = response.body() ?: emptyList()
                }
                else{
                    _error.value =  "Failed to load tags: ${response.message()}"
                }
            }

            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                _error.value =  "Failed to load tags: ${t.message}"
            }

        })
    }
}
