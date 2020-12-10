package by.belobr.jobs.database.entities


import androidx.annotation.NonNull
import androidx.room.Entity

@Entity(tableName = "contacts", primaryKeys = ["name", "contact", "companyId"])
data class ContactEntity(
    @NonNull val name: String,
    @NonNull var companyId: Long = 0,
    @NonNull val contact: String
)