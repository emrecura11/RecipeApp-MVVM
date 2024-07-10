package com.emrecura.recipeapp_mvvm.app.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.emrecura.recipeapp_mvvm.R
import com.emrecura.recipeapp_mvvm.app.configs.ApiClient
import com.emrecura.recipeapp_mvvm.app.data.Recipes
import com.emrecura.recipeapp_mvvm.app.services.RecipeService
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var recipeService: RecipeService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recipeService = ApiClient.getClient().create(RecipeService::class.java)

        lifecycleScope.launch {
            getRecipes()
        }

    }

     fun getRecipes() {
        val call = recipeService.getAllRecipes(30)

        call.enqueue(object : Callback<Recipes> {
            override fun onResponse(call: Call<Recipes>, respose: Response<Recipes>) {
                if (respose.isSuccessful){
                    Log.d("recipes", respose.body()!!.recipes.toString())
                }
            }

            override fun onFailure(call: Call<Recipes>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}