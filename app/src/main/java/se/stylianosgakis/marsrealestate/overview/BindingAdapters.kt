package se.stylianosgakis.marsrealestate.overview

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import org.koin.core.KoinComponent
import org.koin.core.inject
import se.stylianosgakis.marsrealestate.R
import se.stylianosgakis.marsrealestate.model.MarsProperty

object GlideInstance : KoinComponent {
    val glide: RequestManager by inject()
}

@BindingAdapter("imageUrl")
fun ImageView.bindImage(imageUrl: String?) {
    imageUrl?.let { url ->
        val parsedImageUrl = url.toUri().buildUpon().scheme("https").build()
        GlideInstance.glide
            .load(parsedImageUrl)
            .into(this)
    }
}

@BindingAdapter("apiStatus")
fun ImageView.bindStatus(status: ApiStatus?) {
    when (status) {
        ApiStatus.LOADING -> {
            this.visibility = View.VISIBLE
            this.setImageResource(R.drawable.loading_animation)
        }
        ApiStatus.ERROR -> {
            this.visibility = View.VISIBLE
            this.setImageResource(R.drawable.ic_connection_error)
        }
        ApiStatus.DONE -> {
            this.visibility = View.GONE
        }
    }
}

@BindingAdapter("listData")
fun RecyclerView.bindRecyclerView(data: List<MarsProperty>?) {
    val adapter = this.adapter as PhotoGridAdapter
    adapter.submitList(data)
}
