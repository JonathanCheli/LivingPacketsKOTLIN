package com.example.lp_jc_kotlin.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lp_jc_kotlin.Adapter.MainRecyclerAdapter.MainViewHolder
import com.example.lp_jc_kotlin.Model.AllRecipe
import com.example.lp_jc_kotlin.Model.RecipeItem
import com.example.lp_jc_kotlin.R

class MainRecyclerAdapter(private val context: Context, private var allRecipeList: List<AllRecipe>) : RecyclerView.Adapter<MainViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(context).inflate(R.layout.main_recycler_row_items, parent, false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.recipeTitle.text = allRecipeList[position].recipeTitle
        holder.recipeDescription.text = allRecipeList[position].recipeDescription
        setRecipeItemRecycler(holder.itemRecycler, allRecipeList[position].recipeItemList)
    }

    override fun getItemCount(): Int {
        return allRecipeList.size
    }

    fun onRestoreRecipe(allRecipeList: List<AllRecipe>) {
        this.allRecipeList = allRecipeList
        notifyDataSetChanged()
    }

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var recipeTitle: TextView
        var recipeDescription: TextView
        var itemRecycler: RecyclerView

        init {
            recipeTitle = itemView.findViewById(R.id.recipe_title)
            recipeDescription = itemView.findViewById(R.id.recipe_description)
            itemRecycler = itemView.findViewById(R.id.item_recycler)
        }
    }

    private fun setRecipeItemRecycler(recyclerView: RecyclerView, categoryItemList: List<RecipeItem?>?) {
        val itemRecyclerAdapter = RecipeItemRecyclerAdapter(context, categoryItemList as List<RecipeItem>?)
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        recyclerView.adapter = itemRecyclerAdapter
    }
}