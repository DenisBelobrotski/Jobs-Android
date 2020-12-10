package by.belobr.jobs.database.dao

import androidx.room.*
import by.belobr.jobs.database.entities.JobEntity
import by.belobr.jobs.database.entities.JobWithCompanyWithContactsEntity

@Dao
interface JobDao {

    @Transaction
    @Insert
    suspend fun insertJob(job: JobEntity)

}