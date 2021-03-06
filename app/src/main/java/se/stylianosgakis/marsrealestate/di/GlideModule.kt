package se.stylianosgakis.marsrealestate.di

import android.app.Application
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import se.stylianosgakis.marsrealestate.R

val glideModule = module {
    //TODO Manage to inject the Glide instance in the BindAdapter method
    // link: https://stackoverflow.com/questions/60763561/how-to-provide-something-through-di-koin-to-a-bindingadapter-method
    single<RequestOptions> { provideRequestOptions() }
    single<RequestManager> { provideRequestManager(androidApplication(), get<RequestOptions>()) }
}

fun provideRequestManager(
    application: Application,
    requestOptions: RequestOptions
): RequestManager {
    return Glide.with(application)
        .setDefaultRequestOptions(requestOptions)
}

fun provideRequestOptions(): RequestOptions {
    return RequestOptions()
        .placeholder(R.drawable.loading_animation)
        .error(R.drawable.ic_broken_image)
}