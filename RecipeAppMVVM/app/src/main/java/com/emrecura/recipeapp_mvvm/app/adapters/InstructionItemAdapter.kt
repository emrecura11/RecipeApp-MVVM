package com.emrecura.recipeapp_mvvm.app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.emrecura.recipeapp_mvvm.R
import com.emrecura.recipeapp_mvvm.app.data.Recipe
import kotlinx.coroutines.newFixedThreadPoolContext

class InstructionItemAdapter (private val instructions: List<String>,  private val onItemClicked: (String) -> Unit) :
    RecyclerView.Adapter<InstructionItemAdapter.InstructionViewHolder>() {

    class InstructionViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val iTextView: TextView = itemView.findViewById(R.id.instruction_text_view)

        fun bind(string: String, onItemClicked: (String) -> Unit) {
            itemView.setOnClickListener {
                onItemClicked(string)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstructionViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.instruction_item_row, parent, false)
        return InstructionViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return instructions.size
    }

    override fun onBindViewHolder(holder: InstructionViewHolder, position: Int) {
        holder.iTextView.text = instructions[position]

        holder.bind(instructions[position], onItemClicked)
    }
}