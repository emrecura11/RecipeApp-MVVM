package com.emrecura.recipeapp_mvvm.app.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emrecura.recipeapp_mvvm.R
import com.emrecura.recipeapp_mvvm.app.adapters.InstructionItemAdapter
import com.emrecura.recipeapp_mvvm.app.adapters.RecipeItemAdapter


class TagsFragment : Fragment() {

    private val tagsViewModel: TagsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_tags, container, false)


        val recipesRecyclerView: RecyclerView = view.findViewById(R.id.tag_recipe_recycler_view)
        val tagsRecyclerView: RecyclerView = view.findViewById(R.id.tag_recycler_view)
        val arrowBack: ImageView = view.findViewById(R.id.tag_arrow_back)
        val titleText: TextView = view.findViewById(R.id.tag_title)

        recipesRecyclerView.layoutManager = GridLayoutManager(context, 2)
        tagsRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)



        tagsViewModel.tags.observe(viewLifecycleOwner, Observer { tags ->
            tagsRecyclerView.adapter = InstructionItemAdapter(tags) { tag ->
                tagsViewModel.getRecipesByTag(tag)
                titleText.text = tag
                titleText.visibility = View.VISIBLE
                tagsRecyclerView.visibility = View.INVISIBLE
                recipesRecyclerView.visibility = View.VISIBLE
                arrowBack.visibility = View.VISIBLE
            }
        })

        tagsViewModel.recipes.observe(viewLifecycleOwner, Observer { recipes ->
            if (recipes != null && recipes.isNotEmpty()) {
                recipesRecyclerView.adapter = RecipeItemAdapter(recipes) { recipe ->
                    val intent = Intent(context, RecipeDetailActivity::class.java)
                    intent.putExtra("recipe", recipe)
                    startActivity(intent)
                }
            } else {
                // Recipe listesi boş olduğunda..
            }
        })



        arrowBack.setOnClickListener {
            tagsRecyclerView.visibility = View.VISIBLE
            recipesRecyclerView.visibility = View.INVISIBLE
            arrowBack.visibility = View.INVISIBLE
            titleText.visibility = View.INVISIBLE

        }





        return view
    }

}