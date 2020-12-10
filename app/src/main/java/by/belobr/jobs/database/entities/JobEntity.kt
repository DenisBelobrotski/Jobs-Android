package by.belobr.jobs.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jobs")
data class JobEntity(
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "salaryFrom") val salaryFrom: Int,
    @ColumnInfo(name = "salaryTo") val salaryTo: Int,
    @ColumnInfo(name = "companyId") var companyId: Long,
    @ColumnInfo(name = "isExperienceRequired") var isExperienceRequired: Boolean,
    @ColumnInfo(name = "isFullWorkDay") var isFullWorkDay: Boolean
) {

    @PrimaryKey(autoGenerate = true)
    var uid: Long = 0

}