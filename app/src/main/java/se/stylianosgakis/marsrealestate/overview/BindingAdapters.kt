package se.stylianosgakis.marsrealestate.overview

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.RequestManager
import org.koin.java.KoinJavaComponent.inject

@BindingAdapter("imageUrl")
fun ImageView.bindImage(imageUrl: String?) {
    val glide: RequestManager by inject(RequestManager::class.java)
    imageUrl?.let { url ->
        val parsedImageUrl = url.toUri().buildUpon().scheme("https").build()
        glide.load(parsedImageUrl)
            .into(this)
    }
}