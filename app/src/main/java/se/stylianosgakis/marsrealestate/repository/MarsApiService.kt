package se.stylianosgakis.marsrealestate.repository

import retrofit2.http.GET
import retrofit2.http.Query
import se.stylianosgakis.marsrealestate.model.MarsProperty

interface MarsApiService {
    @GET("realestate")
    suspend fun getProperties(@Query("filter") type: String? = null): List<MarsProperty>
}
