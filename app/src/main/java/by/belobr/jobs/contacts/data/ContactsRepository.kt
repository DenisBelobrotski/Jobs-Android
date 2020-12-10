package by.belobr.jobs.contacts.data

import by.belobr.jobs.database.dao.ContactDao
import by.belobr.jobs.database.entities.ContactEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ContactsRepository(
    private val contactDao: ContactDao,
    private val dispatchers: Dispatchers
) {

    suspend fun getContacts(companyId: Long): List<ContactEntity> {
        return withContext(dispatchers.IO) {
            contactDao.getContacts(companyId)
        }
    }
}