package com.emrecura.recipeapp_mvvm.app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.emrecura.recipeapp_mvvm.R

class TagsItemAdapter (private val tagsList: List<String>) : RecyclerView.Adapter<TagsItemAdapter.TagsViewHolder>() {

    class TagsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val tName: TextView = itemView.findViewById(R.id.tag_name_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.tags_item_row, parent, false)
        return TagsViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return tagsList.size
    }

    override fun onBindViewHolder(holder: TagsViewHolder, position: Int) {
        val tag = tagsList[position]
        holder.tName.text = tag
    }
}