package by.belobr.jobs.home.di

import by.belobr.jobs.home.data.HomeRepository
import by.belobr.jobs.home.ui.HomeVM
import by.belobr.jobs.home.ui.mapper.JobsMapper
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    single { HomeRepository(get(), get()) }
    factory { JobsMapper() }
    viewModel { HomeVM(get(),  get()) }
}