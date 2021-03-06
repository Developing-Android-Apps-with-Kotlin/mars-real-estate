package se.stylianosgakis.marsrealestate.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import se.stylianosgakis.marsrealestate.model.MarsProperty
import se.stylianosgakis.marsrealestate.repository.MarsRepository
import se.stylianosgakis.marsrealestate.util.ApiFilter

enum class ApiStatus { LOADING, ERROR, DONE }

class OverviewViewModel(
    private val repository: MarsRepository
) : ViewModel() {
    // The internal MutableLiveData String that stores the status of the most recent request
    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    // The result
    private val _propertyList = MutableLiveData<List<MarsProperty>>()
    val propertyList: LiveData<List<MarsProperty>>
        get() = _propertyList

    // Navigation
    private val _navigateToSelectedProperty = MutableLiveData<MarsProperty?>()
    val navigateToSelectedProperty: LiveData<MarsProperty?>
        get() = _navigateToSelectedProperty

    fun triggerNavigateToPropertyDetails(marsProperty: MarsProperty) {
        _navigateToSelectedProperty.value = marsProperty
    }

    fun navigateToPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }

    // Call getMarsRealEstateProperties() on init so we can display status immediately.
    init {
        getMarsRealEstateProperties()
    }

    // Sets the value of the status LiveData to the Mars API status.
    fun getMarsRealEstateProperties(filter: ApiFilter = ApiFilter.SHOW_ALL) {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            val propertiesList = repository.getProperties(filter.value)
            _propertyList.value = propertiesList
            if (propertiesList.isEmpty()) {
                _status.value = ApiStatus.ERROR
            } else {
                _status.value = ApiStatus.DONE
            }
        }
    }
}
