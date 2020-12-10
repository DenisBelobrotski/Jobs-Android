package by.belobr.jobs.job.data

import by.belobr.jobs.database.dao.JobWithCompanyWithContactsDao
import by.belobr.jobs.database.entities.JobWithCompanyWithContactsEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext


class JobRepository(
    private val jobDao: JobWithCompanyWithContactsDao,
    private val dispatcher: CoroutineDispatcher
) {

    suspend fun getJob(id: Long): JobWithCompanyWithContactsEntity {
        return withContext(dispatcher) {
            jobDao.getJobWithCompanyAndContacts(id)
        }
    }

}