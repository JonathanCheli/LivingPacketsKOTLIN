package com.example.lp_jc_kotlin

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lp_jc_kotlin.Adapter.MainRecyclerAdapter
import com.example.lp_jc_kotlin.Adapter.MainRecyclerAdapter.MainViewHolder
import com.example.lp_jc_kotlin.Helpers.PreConfig
import com.example.lp_jc_kotlin.Helpers.PreConfig.readListFromPref
import com.example.lp_jc_kotlin.Model.AllRecipe
import com.example.lp_jc_kotlin.Model.RecipeItem
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    var layoutManager: RecyclerView.LayoutManager? = null
    var mainRecyclerAdapter: MainRecyclerAdapter? = null
    var pos = 0
    var newPos = 0
    var allRecipeList: MutableList<AllRecipe>? = null
    var list: List<RecipeItem>? = null
    var recipeItemList: MutableList<RecipeItem>? = null
    var recipeItemList1: MutableList<RecipeItem>? = null
    var recipeItemList2: MutableList<RecipeItem>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // here we will add some dummy data to our model class
        // here we will add data to category item model class


        // added in 2nd category
        recipeItemList2 = ArrayList()
        (recipeItemList2 as ArrayList<RecipeItem>).add(RecipeItem(0, R.drawable.pancakes))
        (recipeItemList2 as ArrayList<RecipeItem>).add(RecipeItem(1, R.drawable.pancakes))
        (recipeItemList2 as ArrayList<RecipeItem>).add(RecipeItem(2, R.drawable.pancakes1))
        (recipeItemList2 as ArrayList<RecipeItem>).add(RecipeItem(3, R.drawable.pancakes3))
        (recipeItemList2 as ArrayList<RecipeItem>).add(RecipeItem(4, R.drawable.pancakes4))
        (recipeItemList2 as ArrayList<RecipeItem>).add(RecipeItem(5, R.drawable.pancakes5))
        (recipeItemList2 as ArrayList<RecipeItem>).add(RecipeItem(6, R.drawable.pancakes6))

        // added in 1st category
        recipeItemList1 = ArrayList()
        (recipeItemList1 as ArrayList<RecipeItem>).add(RecipeItem(0, R.drawable.fries))
        (recipeItemList1 as ArrayList<RecipeItem>).add(RecipeItem(1, R.drawable.fries2))
        (recipeItemList1 as ArrayList<RecipeItem>).add(RecipeItem(2, R.drawable.fries2))
        (recipeItemList1 as ArrayList<RecipeItem>).add(RecipeItem(3, R.drawable.fries3))
        (recipeItemList1 as ArrayList<RecipeItem>).add(RecipeItem(4, R.drawable.fries4))
        (recipeItemList1 as ArrayList<RecipeItem>).add(RecipeItem(5, R.drawable.fries5))
        (recipeItemList1 as ArrayList<RecipeItem>).add(RecipeItem(6, R.drawable.fries6))

        // added in category 0
        recipeItemList = ArrayList()
        (recipeItemList as ArrayList<RecipeItem>).add(RecipeItem(0, R.drawable.chili))
        (recipeItemList as ArrayList<RecipeItem>).add(RecipeItem(1, R.drawable.chilli1))
        (recipeItemList as ArrayList<RecipeItem>).add(RecipeItem(2, R.drawable.chilli2))
        (recipeItemList as ArrayList<RecipeItem>).add(RecipeItem(3, R.drawable.chilli3))
        (recipeItemList as ArrayList<RecipeItem>).add(RecipeItem(4, R.drawable.chilli4))
        (recipeItemList as ArrayList<RecipeItem>).add(RecipeItem(5, R.drawable.chilli5))
        (recipeItemList as ArrayList<RecipeItem>).add(RecipeItem(6, R.drawable.chilli6))


        // added in 3rd category
        allRecipeList = ArrayList()
        (allRecipeList as ArrayList<AllRecipe>).add(AllRecipe("Chilli sin Carne", """
     -Tofu 
     -Tomatos
     -Kidney Beans 
     -Corn
     """.trimIndent(), recipeItemList as ArrayList<RecipeItem>))
        (allRecipeList as ArrayList<AllRecipe>).add(AllRecipe("Pancakes", """
     -Flour 
     -Milk 
     -Baking Soda
     -Eggs
     """.trimIndent(), recipeItemList2 as ArrayList<RecipeItem>))
        (allRecipeList as ArrayList<AllRecipe>).add(AllRecipe("Another One", """
     This recipe is quit easy, 
     just put some fries in the oven
     """.trimIndent(), recipeItemList1 as ArrayList<RecipeItem>))


        list = ArrayList()


        val intent = intent


        val title =intent.getStringExtra("title")
        val description =intent.getStringExtra("description")
        val list = intent.getParcelableArrayListExtra<AllRecipe>("list")
        val pos = intent.getIntExtra("myPosition", pos)
        if (title != null && description != null) {
            if (readListFromPref(this) != null) {
                allRecipeList = readListFromPref(this)
                allRecipeList!!.add(AllRecipe(title, description, list as ArrayList<RecipeItem>))
            }
        }
        setMainRecipeRecycler(allRecipeList)
    }

    fun setMainRecipeRecycler(allRecipeList: List<AllRecipe>?) {
        val mainRecipeRecycler = findViewById<RecyclerView>(R.id.main_recycler)
        layoutManager = LinearLayoutManager(this)
        mainRecipeRecycler.setHasFixedSize(true)
        mainRecipeRecycler.setLayoutManager(layoutManager)
        mainRecyclerAdapter = allRecipeList?.let { MainRecyclerAdapter(this, it) }
        mainRecipeRecycler.setAdapter(mainRecyclerAdapter)
        ItemTouchHelper(itemTouchHelperCallBack).attachToRecyclerView(mainRecipeRecycler)
        mainRecyclerAdapter!!.notifyDataSetChanged()
    }

    fun navegateToAddRecipe(view: View?) {
        val intent = Intent(this@MainActivity, AddRecipe::class.java)
        PreConfig.writeListInPref(applicationContext, allRecipeList)
        startActivity(intent)
    }

    var itemTouchHelperCallBack: ItemTouchHelper.SimpleCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (viewHolder is MainViewHolder) {
                allRecipeList!!.removeAt(viewHolder.getAdapterPosition())
                PreConfig.writeListInPref(applicationContext, allRecipeList)
                mainRecyclerAdapter!!.notifyItemRemoved(viewHolder.getAdapterPosition())
                newPos = viewHolder.getAdapterPosition()
            }
        }
    }
}