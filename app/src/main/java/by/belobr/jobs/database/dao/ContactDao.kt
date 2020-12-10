package by.belobr.jobs.database.dao

import androidx.room.*
import by.belobr.jobs.database.entities.ContactEntity

@Dao
interface ContactDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertContact(contact: ContactEntity): Long

    @Transaction
    @Query("SELECT * FROM contacts WHERE companyId=:companyId")
    suspend fun getContacts(companyId: Long): List<ContactEntity>

}