package se.stylianosgakis.marsrealestate.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class MarsProperty : Parcelable {
    @SerializedName("price")
    @Expose
    var price: Int? = null

    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("type")
    @Expose
    var type: String? = null

    @SerializedName("img_src")
    @Expose
    var imgSrc: String? = null
}