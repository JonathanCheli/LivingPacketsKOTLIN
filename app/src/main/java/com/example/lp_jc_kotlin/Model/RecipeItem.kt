package com.example.lp_jc_kotlin.Model

import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Parcel
import android.os.Parcelable
import androidx.lifecycle.ViewModel


class RecipeItem() : ViewModel(), Parcelable {
    private var itemId: Int? = null
    var imageUrl: Int? = null
    var uriImg: Uri? = null
    private var imageDrawable: Drawable? = null


    constructor(itemId: Int?, imageDrawable: Drawable?): this(){
        this.itemId = itemId
        this.imageDrawable = imageDrawable
    }



    var recipeItemList: List<RecipeItem>? = null

    constructor(parcel: Parcel) : this() {
        itemId = parcel.readValue(Int::class.java.classLoader) as? Int
        imageUrl = parcel.readValue(Int::class.java.classLoader) as? Int
        uriImg = parcel.readParcelable(Uri::class.java.classLoader)
        recipeItemList = parcel.createTypedArrayList(CREATOR)
    }

    constructor(i: Int, uri: Uri):this(){
        this.itemId = i
        this.uriImg = uri

    }

    constructor(i: Int, drawa: Drawable):this(){
        this.itemId = i
        this.imageDrawable = drawa
    }

    constructor(i: Int, imag: Int):this(){
        this.itemId = i
        this.imageUrl = imag
    }

    constructor(list: List<RecipeItem>) :this(){
        this.recipeItemList = list
    }




    constructor(itemId: Int?, recipeItemList: List<RecipeItem>?) :this(){
        this.itemId = itemId
        this.recipeItemList = recipeItemList
    }




    /*

    constructor() : this() {

    }


     */






    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(itemId)
        parcel.writeValue(imageUrl)
        parcel.writeParcelable(uriImg, flags)
        parcel.writeTypedList(recipeItemList)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RecipeItem> {
        override fun createFromParcel(parcel: Parcel): RecipeItem {
            return RecipeItem(parcel)
        }

        override fun newArray(size: Int): Array<RecipeItem?> {
            return arrayOfNulls(size)
        }
    }
}

/*
constructor(parcel: Parcel) :this(
    parcel.readParcelable(Uri::class.java.classLoader)
)



override fun describeContents(): Int {
    return 0
}

override fun writeToParcel(dest: Parcel, flags: Int) {
    itemId?.let { dest.writeInt(it) }
    imageUrl?.let { dest.writeInt(it) }
    dest.writeParcelable(uriImg, flags)

}


companion object CREATOR : Parcelable.Creator<RecipeItem> {
    override fun createFromParcel(parcel: Parcel): RecipeItem {
        return RecipeItem(parcel)
    }

    override fun newArray(size: Int): Array<RecipeItem?> {
        return arrayOfNulls(size)
    }
}
}


 */

