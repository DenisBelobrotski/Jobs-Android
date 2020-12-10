package by.belobr.jobs.insertion.di

import by.belobr.jobs.insertion.data.InsertionRepository
import by.belobr.jobs.insertion.ui.InsertionForm
import by.belobr.jobs.insertion.ui.InsertionVM
import by.belobr.jobs.job.data.JobRepository
import by.belobr.jobs.job.ui.JobVM
import kotlinx.coroutines.Dispatchers
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val insertionModule = module {
    single { InsertionRepository(get(), get(), get(), get()) }
    factory { InsertionForm(get()) }
    viewModel { InsertionVM(get(), get()) }
}