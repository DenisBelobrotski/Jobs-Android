package by.belobr.jobs.database

import androidx.room.Database
import androidx.room.RoomDatabase
import by.belobr.jobs.database.dao.CompanyDao
import by.belobr.jobs.database.dao.ContactDao
import by.belobr.jobs.database.dao.JobDao
import by.belobr.jobs.database.dao.JobWithCompanyWithContactsDao
import by.belobr.jobs.database.entities.CompanyEntity
import by.belobr.jobs.database.entities.ContactEntity
import by.belobr.jobs.database.entities.JobEntity

@Database(
    entities = [ContactEntity::class, JobEntity::class, CompanyEntity::class],
    version = 1
)
abstract class JobDatabase : RoomDatabase() {
    abstract fun jobDao(): JobDao
    abstract fun contactDao(): ContactDao
    abstract fun companyDao(): CompanyDao
    abstract fun jobWithCompanyWithContactsDao(): JobWithCompanyWithContactsDao
}