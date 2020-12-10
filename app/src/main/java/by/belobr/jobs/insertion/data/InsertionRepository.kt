package by.belobr.jobs.insertion.data

import by.belobr.jobs.database.dao.CompanyDao
import by.belobr.jobs.database.dao.ContactDao
import by.belobr.jobs.database.dao.JobDao
import by.belobr.jobs.database.entities.JobWithCompanyWithContactsEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class InsertionRepository(
    private val jobDao: JobDao,
    private val companyDao: CompanyDao,
    private val contactDao: ContactDao,
    private val dispatcher: Dispatchers
) {

    suspend fun insert(entity: JobWithCompanyWithContactsEntity) {
        withContext(dispatcher.IO) {
            val company = entity.companyWithContacts.company
            var companyId = companyDao.insertCompany(company)
            if (companyId == -1L) {
                companyId = companyDao.getCompany(company.name, company.location).companyId
            }
            entity.companyWithContacts.contacts.forEach {
                it.companyId = companyId
                contactDao.insertContact(it)
            }
            entity.job.companyId = companyId
            jobDao.insertJob(entity.job)
        }
    }

}