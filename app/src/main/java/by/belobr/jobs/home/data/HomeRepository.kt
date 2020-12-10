package by.belobr.jobs.home.data

import by.belobr.jobs.database.dao.JobWithCompanyWithContactsDao
import by.belobr.jobs.database.entities.JobWithCompanyWithContactsEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeRepository(
    private val dispatchers: Dispatchers,
    private val jobsDao: JobWithCompanyWithContactsDao
) {

    suspend fun getJobs(): List<JobWithCompanyWithContactsEntity> {
        return withContext(dispatchers.IO) {
            jobsDao.getJobsWithCompanyAndContacts()
        }
    }

    suspend fun getJobs(query: String) : List<JobWithCompanyWithContactsEntity> {
        return withContext(dispatchers.IO) {
            jobsDao.getJobsWithCompanyAndContacts(query)
        }
    }

    suspend fun removeJob(id: Long) {
        return withContext(dispatchers.IO) {
            jobsDao.removeJob(id)
        }
    }

}