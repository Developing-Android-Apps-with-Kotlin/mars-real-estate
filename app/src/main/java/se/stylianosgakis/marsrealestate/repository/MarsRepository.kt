package se.stylianosgakis.marsrealestate.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import se.stylianosgakis.marsrealestate.model.MarsProperty
import java.net.UnknownHostException

class MarsRepository(
    private val api: MarsApiService
) {
    suspend fun getProperties(filter: String? = null): List<MarsProperty> {
        return withContext<List<MarsProperty>>(Dispatchers.IO) {
            try {
                val response = api.getProperties(filter)
                if (response.isSuccessful) {
                    val properties = response.body()
                    return@withContext properties ?: listOf()
                }
            } catch (e: UnknownHostException) {
            }
            listOf()
        }
    }
}