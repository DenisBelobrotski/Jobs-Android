package by.belobr.jobs.database.dao

import androidx.room.*
import by.belobr.jobs.database.entities.JobWithCompanyWithContactsEntity

@Dao
interface JobWithCompanyWithContactsDao {

    @Transaction
    @Query("SELECT * FROM jobs")
    suspend fun getJobsWithCompanyAndContacts(): List<JobWithCompanyWithContactsEntity>

    @Transaction
    @Query("SELECT * FROM jobs WHERE jobs.title LIKE '%' || :query || '%'")
    suspend fun getJobsWithCompanyAndContacts(query: String): List<JobWithCompanyWithContactsEntity>

    @Transaction
    @Query("SELECT * FROM jobs WHERE uid=:id")
    suspend fun getJobWithCompanyAndContacts(id: Long): JobWithCompanyWithContactsEntity

    @Transaction
    @Query("DELETE FROM jobs WHERE uid = :id")
    suspend fun removeJob(id: Long)
}