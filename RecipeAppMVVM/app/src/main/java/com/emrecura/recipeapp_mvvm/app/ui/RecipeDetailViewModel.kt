package com.emrecura.recipeapp_mvvm.app.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emrecura.recipeapp_mvvm.app.data.Recipe

class RecipeDetailViewModel: ViewModel() {

    private val _recipe = MutableLiveData<Recipe>()
    val recipe: LiveData<Recipe> get() = _recipe

    fun setRecipe(recipe: Recipe) {
        _recipe.value = recipe
    }
}
