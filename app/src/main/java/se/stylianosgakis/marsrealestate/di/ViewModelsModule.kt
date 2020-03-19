package se.stylianosgakis.marsrealestate.di

import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import se.stylianosgakis.marsrealestate.detail.DetailViewModel
import se.stylianosgakis.marsrealestate.model.MarsProperty
import se.stylianosgakis.marsrealestate.overview.OverviewViewModel
import se.stylianosgakis.marsrealestate.repository.MarsRepository

val viewModelsModule = module {
    viewModel<OverviewViewModel> { OverviewViewModel(get<MarsRepository>()) }
    viewModel<DetailViewModel> { (marsProperty: MarsProperty) ->
        DetailViewModel(marsProperty, androidApplication())
    }
}
