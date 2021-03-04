package com.example.lp_jc_kotlin.Model

import android.os.Parcel
import android.os.Parcelable
import com.google.android.material.tabs.TabLayout

class AllRecipe(val recipeTitle: String?, val recipeDescription: String?, val recipeItemList: ArrayList<RecipeItem>) : Parcelable {

    constructor() : this("","", ArrayList<RecipeItem>()

    )



    constructor(parcel: Parcel) :this(
         parcel.readString(),
         parcel.readString(),
         parcel.readArrayList(TabLayout.Tab::class.java.classLoader) as ArrayList<RecipeItem>)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(recipeTitle)
        parcel.writeString(recipeDescription)
        parcel.writeList(recipeItemList)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AllRecipe> {
        override fun createFromParcel(parcel: Parcel): AllRecipe {
            return AllRecipe(parcel)
        }

        override fun newArray(size: Int): Array<AllRecipe?> {
            return arrayOfNulls(size)
        }
    }
}


