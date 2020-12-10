package by.belobr.jobs.job.di

import by.belobr.jobs.job.data.JobRepository
import by.belobr.jobs.job.mapper.JobMapper
import by.belobr.jobs.job.ui.JobVM
import kotlinx.coroutines.Dispatchers
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val jobModule = module {
    single { JobRepository(get(), Dispatchers.IO) }
    single { JobMapper() }
    viewModel { JobVM(get(), get()) }
}