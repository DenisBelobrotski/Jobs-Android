package by.belobr.jobs.core.di

import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val coreModule = module {

    single { Dispatchers }

}