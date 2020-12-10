package by.belobr.jobs.contacts.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.belobr.jobs.contacts.data.ContactsRepository
import by.belobr.jobs.contacts.ui.data.ContactItem
import by.belobr.jobs.contacts.ui.mapper.ContactMapper
import kotlinx.coroutines.launch

class ContactsVM(
    private val contactsRepository: ContactsRepository,
    private val contactMapper: ContactMapper
) : ViewModel() {

    val items = MutableLiveData<List<ContactItem>>()

    fun init(companyId: Long) {
        viewModelScope.launch {
            val entities = contactsRepository.getContacts(companyId)
            val contacts = entities.map { contactMapper.mapContact(it) }
            items.postValue(contacts)
        }
    }
}