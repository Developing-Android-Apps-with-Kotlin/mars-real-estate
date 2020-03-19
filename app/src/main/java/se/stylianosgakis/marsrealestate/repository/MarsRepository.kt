package se.stylianosgakis.marsrealestate.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import se.stylianosgakis.marsrealestate.model.MarsProperty

class MarsRepository(
    private val api: MarsApiService
) {
    suspend fun getProperties(filter: String? = null): List<MarsProperty> {
        return withContext(Dispatchers.IO) {
            return@withContext api.getProperties(filter)
        }
    }
}