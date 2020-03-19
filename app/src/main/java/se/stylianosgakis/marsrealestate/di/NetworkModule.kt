package se.stylianosgakis.marsrealestate.di

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import se.stylianosgakis.marsrealestate.repository.MarsApiService
import se.stylianosgakis.marsrealestate.repository.MarsRepository

private const val BASE_URL = "https://mars.udacity.com/"
val networkModule = module {
    single<Retrofit> { buildRetrofit() }
    single<MarsApiService> { get<Retrofit>().create<MarsApiService>() }
    single<MarsRepository> { MarsRepository(get<MarsApiService>()) }
}

fun buildRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}