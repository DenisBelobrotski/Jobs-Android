package by.belobr.jobs.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "companies", indices = [Index(value = ["name", "location"], unique = true)])
data class CompanyEntity(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "location") val location: String
) {
    @PrimaryKey(autoGenerate = true)
    var companyId: Long = 0
}