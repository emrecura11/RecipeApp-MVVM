package com.emrecura.recipeapp_mvvm.app.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emrecura.recipeapp_mvvm.R
import com.emrecura.recipeapp_mvvm.app.adapters.RecipeItemAdapter

class SearchFragment : Fragment() {

    private val searchViewModel: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =inflater.inflate(R.layout.fragment_search, container, false)

        val searchIcon: ImageView = view.findViewById(R.id.search_icon)
        val searchEditText: EditText = view.findViewById(R.id.search_edit_text)

        searchIcon.setOnClickListener {
            val query = searchEditText.text.toString()
            searchViewModel.searchRecipes(query)
        }

        val recipesRecyclerView: RecyclerView = view.findViewById(R.id.search_recycler_view)
        recipesRecyclerView.layoutManager = GridLayoutManager(context, 2)

        searchViewModel.recipes.observe(viewLifecycleOwner, Observer { recipes ->
            recipesRecyclerView.adapter = RecipeItemAdapter(recipes) { recipe->
                val intent = Intent(context, RecipeDetailActivity::class.java)
                intent.putExtra("recipe", recipe)
                startActivity(intent)
            }
        })

        searchViewModel.error.observe(viewLifecycleOwner, Observer { errorMessage ->
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
        })


        return view
    }

}
