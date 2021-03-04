package com.example.lp_jc_kotlin.Adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.lp_jc_kotlin.Adapter.AddRecipeAdapter.ExampleViewHolder
import com.example.lp_jc_kotlin.Model.RecipeItem
import com.example.lp_jc_kotlin.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso

class AddRecipeAdapter : RecyclerView.Adapter<ExampleViewHolder>() {
    private var mAddRecList /* = new ArrayList<>(); */: List<RecipeItem>? = null
    private val context: Context? = null
    private var mListener: OnItemClickListener? = null

    /*
    public AddRecipeAdapter() {
    }

     */
    interface OnItemClickListener {
        fun onItemClick(position: Int)
        fun onDeleteClick(position: Int)
        fun onLongItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        mListener = listener
    }

    class ExampleViewHolder(itemView: View, listener: OnItemClickListener?) : RecyclerView.ViewHolder(itemView) {
        var imageView2: ImageView
        var floatingactionbutton: FloatingActionButton

        init {
            imageView2 = itemView.findViewById(R.id.item_image2)
            floatingactionbutton = itemView.findViewById(R.id.image_3)
            itemView.setOnClickListener {
                if (listener != null) {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position)
                    }
                }
            }
            itemView.setOnLongClickListener { v ->
                val which_item = adapterPosition
                AlertDialog.Builder(v.context)
                        .setIcon(R.drawable.ic_clear_black_24)
                        .setTitle("Are you sure ?")
                        .setMessage("Do you want to delete this picture ?")
                        .setPositiveButton("Yes") { dialog, which -> listener!!.onLongItemClick(which_item) }
                        .setNegativeButton("No", null)
                        .show()
                true
            }
            floatingactionbutton.setOnClickListener { listener!!.onDeleteClick(adapterPosition) }
        }
    }

    fun setAddRecipeList(addRecipeItemList: List<RecipeItem>?) {
        mAddRecList = addRecipeItemList
        notifyDataSetChanged()
    }

    /*  public AddRecipeAdapter(ArrayList<AddRecipeItem> addRecipeItemList) {
        mAddRecList = addRecipeItemList;
    }


   */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.add_recipe_row_items, parent, false)
        return ExampleViewHolder(v, mListener)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentAddRecipeItem = mAddRecList!![position]
        Picasso.get().load(currentAddRecipeItem.uriImg)
                .resize(400, 400).centerCrop()
                .into(holder.imageView2)
    }

    override fun getItemCount(): Int {
        return mAddRecList!!.size
    }
}