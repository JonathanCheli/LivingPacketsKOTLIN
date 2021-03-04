package com.example.lp_jc_kotlin

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.lp_jc_kotlin.Model.RecipeItem
import androidx.recyclerview.widget.RecyclerView
import android.widget.EditText
import com.example.lp_jc_kotlin.Adapter.AddRecipeAdapter
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.lifecycle.ViewModelProvider
import android.widget.Toast
import android.content.Intent
import android.os.Parcelable
import android.net.Uri
import android.view.View
import android.widget.Button
import java.util.ArrayList

class AddRecipe : AppCompatActivity() {
    var uri: Uri? = null
    var recipeI: RecipeItem? = null
    var mAdapter: AddRecipeAdapter? = null
    private var mainViewModel: MainViewModel? = null
    var pos = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_recipe)


        val floatingView3 = findViewById<FloatingActionButton>(R.id.add_new_image)
        val recyclerView = findViewById<RecyclerView>(R.id.item_recycler2)



        val editTitle = (findViewById<EditText>(R.id.edit_title))
        val editDescription = (findViewById<EditText>(R.id.edit_description))


        mAdapter = AddRecipeAdapter()
        recyclerView?.adapter = mAdapter
        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel!!.imageModelList.observe(this, { recipeItems ->
            mAdapter!!.setAddRecipeList(recipeItems)
            mAdapter!!.notifyDataSetChanged()
            mAdapter!!.setOnItemClickListener(object : AddRecipeAdapter.OnItemClickListener {
                override fun onItemClick(position: Int) {
                    pickImageFromGallery()
                    pos = position
                }

                override fun onDeleteClick(position: Int) {
                    recipeI = RecipeItem()
                    mainViewModel!!.replacePicture(position, recipeI!!)
                    mAdapter!!.notifyDataSetChanged()
                }


                override fun onLongItemClick(position: Int) {
                    recipeI = RecipeItem()



                    mainViewModel!!.deletePicture(position)



                    mAdapter!!.notifyDataSetChanged()
                    Toast.makeText(this@AddRecipe, "The image has been deleted", Toast.LENGTH_SHORT).show()
                }


            })




            findViewById<Button>(R.id.save_data_id).setOnClickListener(View.OnClickListener {
                var title : String = editTitle.text.toString()
                var description : String = editDescription.text.toString()
                if (title.trim { it <= ' ' }.isEmpty() || description.trim { it <= ' ' }.isEmpty()) {
                    Toast.makeText(this@AddRecipe, "Please insert a title and description", Toast.LENGTH_SHORT).show()
                    return@OnClickListener
                }
                val intent = Intent(this@AddRecipe, MainActivity::class.java)
                intent.putExtra("myPosition", pos)
                intent.putExtra("title", title)
                intent.putExtra("description", description)
                intent.putParcelableArrayListExtra("list", recipeItems as ArrayList<out Parcelable?>)
                setResult(RESULT_OK, intent)
                startActivity(intent)
                finish()
                onBackPressed()
            })
        })
        floatingView3.setOnClickListener(View.OnClickListener {
            recipeI = RecipeItem()
            mainViewModel!!.addRecipeImage(recipeI!!)
            Toast.makeText(this@AddRecipe, "a new image has been added!", Toast.LENGTH_SHORT).show()
            mAdapter!!.notifyDataSetChanged()
        })
    }

    private fun pickImageFromGallery() {
        val pickImage = Intent()
        pickImage.type = "image/*"
        pickImage.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(pickImage, PICK_IMAGE)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null && data.data != null) {
            uri = data.data
            if (uri != null) {
                recipeI = RecipeItem(0, uri!!)
                mainViewModel!!.replacePicture(pos, recipeI!!)
                mAdapter!!.notifyDataSetChanged()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("position", pos)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        pos = savedInstanceState.getInt("position")
    }

    companion object {
        private const val PICK_IMAGE = 1
    }
}