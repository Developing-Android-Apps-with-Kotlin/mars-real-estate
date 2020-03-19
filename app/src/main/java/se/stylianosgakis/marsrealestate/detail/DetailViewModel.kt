package se.stylianosgakis.marsrealestate.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import se.stylianosgakis.marsrealestate.R
import se.stylianosgakis.marsrealestate.model.MarsProperty

class DetailViewModel(
    marsProperty: MarsProperty, app: Application
) : AndroidViewModel(app) {
    private val _selectedProperty = MutableLiveData(marsProperty)
    val selectedProperty: LiveData<MarsProperty>
        get() = _selectedProperty

    // Formatting LiveData
    val displayPropertyPrice: LiveData<String> = Transformations.map(selectedProperty) {
        app.applicationContext.getString(
            when (it.isRental) {
                true -> R.string.display_price_monthly_rental
                false -> R.string.display_price
            }, it.price
        )
    }
    val displayPropertyType: LiveData<String> = Transformations.map(selectedProperty) {
        app.applicationContext.getString(
            R.string.display_type,
            app.applicationContext.getString(
                when (it.isRental) {
                    true -> R.string.type_rent
                    false -> R.string.type_sale
                }
            )
        )
    }
}
