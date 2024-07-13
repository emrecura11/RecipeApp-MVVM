package com.emrecura.recipeapp_mvvm.app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.emrecura.recipeapp_mvvm.R
import com.emrecura.recipeapp_mvvm.app.data.Recipe

class RecipeItemAdapter (private val recipeList: List<Recipe>) : RecyclerView.Adapter<RecipeItemAdapter.RecipeViewHolder>() {

    class RecipeViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val rImage: ImageView = itemView.findViewById(R.id.recipe_image)
        val rName: TextView = itemView.findViewById(R.id.recipe_name)
        val rDescription: TextView = itemView.findViewById(R.id.recipe_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recipe_item_row, parent, false)
        return RecipeViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipeList[position]
        Glide.with(holder.itemView.context).load(recipe.image).into(holder.rImage)
        holder.rName.text = recipe.name
        holder.rDescription.text = recipe.cuisine

    }
}