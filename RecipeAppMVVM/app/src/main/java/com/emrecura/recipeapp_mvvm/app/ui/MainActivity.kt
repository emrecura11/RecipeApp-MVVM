package com.emrecura.recipeapp_mvvm.app.ui

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.emrecura.recipeapp_mvvm.R
import com.emrecura.recipeapp_mvvm.app.configs.ApiClient
import com.emrecura.recipeapp_mvvm.app.data.Recipes
import com.emrecura.recipeapp_mvvm.app.services.RecipeService
import androidx.lifecycle.lifecycleScope
import com.emrecura.recipeapp_mvvm.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        binding.bottomNavigation.setOnItemSelectedListener {item ->
            when(item.itemId) {
                R.id.home -> replaceFragment(HomeFragment())
                R.id.search -> replaceFragment(SearchFragment())
                R.id.tags -> replaceFragment(TagsFragment())
            }
            true

        }

        replaceFragment(HomeFragment())

    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit()
    }



}