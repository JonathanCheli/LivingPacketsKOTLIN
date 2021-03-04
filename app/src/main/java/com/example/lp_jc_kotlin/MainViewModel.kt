package com.example.lp_jc_kotlin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lp_jc_kotlin.Model.RecipeItem
import java.util.*

class MainViewModel : ViewModel() {
    private var mutableLiveData: MutableLiveData<List<RecipeItem>?>? = null
    val imageModelList: LiveData<List<RecipeItem>?>
        get() {
            if (mutableLiveData == null) {
                mutableLiveData = MutableLiveData()
                initImageModel()
            }
            return mutableLiveData as MutableLiveData<List<RecipeItem>?>
        }

    private fun initImageModel() {
        val recipeItemList: MutableList<RecipeItem> = ArrayList()
        recipeItemList.add(RecipeItem())
        recipeItemList.add(RecipeItem())
        recipeItemList.add(RecipeItem())
        recipeItemList.add(RecipeItem())
        recipeItemList.add(RecipeItem())
        recipeItemList.add(RecipeItem())
        recipeItemList.add(RecipeItem())
        mutableLiveData!!.value = recipeItemList
    }

    fun changeRecipeImage(position: Int, recipeItem: RecipeItem) {
        if (mutableLiveData!!.value != null) {
            val recipeItemList: MutableList<RecipeItem> = ArrayList(mutableLiveData!!.value)
            recipeItemList[position] = recipeItem
            mutableLiveData!!.value = recipeItemList
        }
    }

    fun replacePicture(position: Int, recipeItem: RecipeItem) {
        if (mutableLiveData!!.value != null) {
            val recipeItemList: MutableList<RecipeItem> = ArrayList(mutableLiveData!!.value)
            recipeItemList[position] = recipeItem
            mutableLiveData!!.value = recipeItemList
        }
    }



    fun deletePicture(position: Int) {
        if (mutableLiveData!!.value != null) {
            val recipeItemList: List<RecipeItem> = ArrayList(mutableLiveData!!.value).apply {
                removeAt(position)
            }
            mutableLiveData!!.setValue(recipeItemList)
        }
    }







    fun addRecipeImage(recipeItem: RecipeItem) {
        if (mutableLiveData!!.value != null) {
            val recipeItemList: MutableList<RecipeItem> = ArrayList(mutableLiveData!!.value)
            recipeItemList.add(recipeItem)
            mutableLiveData!!.value = recipeItemList
        }
    }
}





