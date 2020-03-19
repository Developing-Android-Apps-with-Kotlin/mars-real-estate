package se.stylianosgakis.marsrealestate.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MarsProperty(
    @SerializedName("price")
    @Expose
    var price: Float,
    @SerializedName("id")
    @Expose
    var id: String,
    @SerializedName("type")
    @Expose
    var type: String,
    @SerializedName("img_src")
    @Expose
    var imageSource: String
) : Parcelable {
    @IgnoredOnParcel
    val isRental: Boolean = type == "rent"
}
