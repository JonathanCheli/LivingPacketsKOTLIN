package com.example.lp_jc_kotlin.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.lp_jc_kotlin.Adapter.RecipeItemRecyclerAdapter.RecipeItemViewHolder
import com.example.lp_jc_kotlin.Model.RecipeItem
import com.example.lp_jc_kotlin.R
import com.squareup.picasso.Picasso

class RecipeItemRecyclerAdapter : RecyclerView.Adapter<RecipeItemViewHolder> {
    private var context: Context? = null
    private var recipeItemList: List<RecipeItem>? = null

    constructor() {}
    constructor(context: Context?, recipeItemList: List<RecipeItem>?) {
        this.context = context
        this.recipeItemList = recipeItemList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeItemViewHolder {
        return RecipeItemViewHolder(LayoutInflater.from(context).inflate(R.layout.recipe_row_items, parent, false))
    }

    override fun onBindViewHolder(holder: RecipeItemViewHolder, position: Int) {
        val item = recipeItemList!![position]
        if (item.imageUrl != null) {
            Picasso.get().load(item.imageUrl!!).resize(400, 400).centerCrop().into(holder.itemImage)
        } else {
            Picasso.get().load(item.uriImg).resize(400, 400).centerCrop().into(holder.itemImage)
        }
    }

    fun setAddRecipeList(RecipeItemList: List<RecipeItem>?) {
        recipeItemList = RecipeItemList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return recipeItemList!!.size
    }

    class RecipeItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemImage: ImageView

        init {
            itemImage = itemView.findViewById(R.id.item_image)
        }
    }
}