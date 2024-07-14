package com.emrecura.recipeapp_mvvm.app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emrecura.recipeapp_mvvm.R
import com.emrecura.recipeapp_mvvm.app.adapters.RecipeItemAdapter
import com.emrecura.recipeapp_mvvm.app.adapters.TagsItemAdapter

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val recipesRecyclerView: RecyclerView = view.findViewById(R.id.recipes_recycler_view)
        val tagsRecyclerView: RecyclerView = view.findViewById(R.id.kitchens_recycler_view)
        val progressBar: ProgressBar = view.findViewById(R.id.progressBar)

        recipesRecyclerView.layoutManager = LinearLayoutManager(context)
        tagsRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        homeViewModel.tags.observe(viewLifecycleOwner, Observer {tags ->
            tagsRecyclerView.adapter = TagsItemAdapter(tags)
        })

        homeViewModel.recipes.observe(viewLifecycleOwner, Observer { recipes ->
            recipesRecyclerView.adapter = RecipeItemAdapter(recipes)
        })

        homeViewModel.error.observe(viewLifecycleOwner, Observer { errorMessage ->
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
        })

        homeViewModel.isLoading.observe(viewLifecycleOwner, Observer { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        })

        return view
    }
}
