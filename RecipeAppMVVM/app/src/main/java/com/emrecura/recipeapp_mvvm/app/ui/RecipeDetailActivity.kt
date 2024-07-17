package com.emrecura.recipeapp_mvvm.app.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.emrecura.recipeapp_mvvm.R
import com.emrecura.recipeapp_mvvm.app.adapters.InstructionItemAdapter
import com.emrecura.recipeapp_mvvm.app.adapters.TagsItemAdapter
import com.emrecura.recipeapp_mvvm.app.data.Recipe
import com.emrecura.recipeapp_mvvm.databinding.ActivityRecipeDetailBinding

class RecipeDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeDetailBinding
    private val viewModel: RecipeDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeDetailBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val recipe = intent.getSerializableExtra("recipe") as Recipe
        recipe.let {
            viewModel.setRecipe(it)
        }
        val ingredientRecyclerView = binding.detailIngredientsRecyclerView
        val instructionsRecyclerView = binding.detailInstructionsRecyclerView

        ingredientRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        instructionsRecyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false)



        viewModel.recipe.observe(this, Observer { recipe ->
            Glide.with(this).load(recipe.image).into(binding.dProductImage)
            binding.dCuisine.text = recipe.cuisine
            binding.ratingBar.rating = recipe.rating.toFloat()
            binding.recipeTitle.text = recipe.name
            ingredientRecyclerView.adapter = TagsItemAdapter(recipe.ingredients)
            instructionsRecyclerView.adapter = InstructionItemAdapter(recipe.instructions)
        })
    }
}