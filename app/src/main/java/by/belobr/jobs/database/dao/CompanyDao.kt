package by.belobr.jobs.database.dao

import androidx.room.*
import by.belobr.jobs.database.entities.CompanyEntity

@Dao
interface CompanyDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCompany(company: CompanyEntity): Long

    @Transaction
    @Query("SELECT * FROM companies WHERE name=:name and location=:location")
    suspend fun getCompany(name: String, location: String): CompanyEntity

}