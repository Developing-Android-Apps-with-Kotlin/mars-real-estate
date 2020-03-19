package se.stylianosgakis.marsrealestate.overview

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import se.stylianosgakis.marsrealestate.PhotoGridAdapter
import se.stylianosgakis.marsrealestate.R
import se.stylianosgakis.marsrealestate.model.MarsProperty

@BindingAdapter("imageUrl")
fun ImageView.bindImage(imageUrl: String?) {
    imageUrl?.let { url ->
        val parsedImageUrl = url.toUri().buildUpon().scheme("https").build()
        Glide.with(this.context)
            .load(parsedImageUrl)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
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
