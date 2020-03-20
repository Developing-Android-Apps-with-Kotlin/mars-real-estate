package se.stylianosgakis.marsrealestate.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import se.stylianosgakis.marsrealestate.model.MarsProperty
import java.net.UnknownHostException

class MarsRepository(
    private val api: MarsApiService
) {
    suspend fun getProperties(filter: String? = null): List<MarsProperty> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getProperties(filter)
                val properties = response.body()
                if (response.isSuccessful && properties != null) {
                    return@withContext properties
                }
            } catch (e: UnknownHostException) {
            }
            listOf()
        }
    }
}