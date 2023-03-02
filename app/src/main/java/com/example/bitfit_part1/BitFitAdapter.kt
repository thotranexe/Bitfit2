package com.example.bitfit_part1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BitFitAdapter(private val context: Context, private val items: MutableList<BitFitItem>) : RecyclerView.Adapter<BitFitAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val foodNameTv : TextView
        private val caloriesTv : TextView

        init {
            foodNameTv = itemView.findViewById(R.id.foodNameTextView)
            caloriesTv = itemView.findViewById(R.id.caloriesTextView)
        }

        fun bind(item: BitFitItem) {
            foodNameTv.text = item.itemName
            caloriesTv.text = item.calories
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.bitfit_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }
}