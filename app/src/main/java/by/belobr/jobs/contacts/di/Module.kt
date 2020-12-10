package by.belobr.jobs.contacts.di

import by.belobr.jobs.contacts.data.ContactsRepository
import by.belobr.jobs.contacts.ui.ContactsVM
import by.belobr.jobs.contacts.ui.mapper.ContactMapper
import kotlinx.coroutines.Dispatchers
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val contactsModule = module {
    single { ContactMapper() }
    single { ContactsRepository(get(), get()) }
    viewModel { ContactsVM(get(), get()) }
}