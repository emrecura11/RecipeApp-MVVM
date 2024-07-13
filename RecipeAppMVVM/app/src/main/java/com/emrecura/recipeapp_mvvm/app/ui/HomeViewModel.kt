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

class HomeViewModel : ViewModel() {
    private val _recipes = MutableLiveData<List<Recipe>>()
    val recipes: LiveData<List<Recipe>> get() = _recipes

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    init {
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
}
