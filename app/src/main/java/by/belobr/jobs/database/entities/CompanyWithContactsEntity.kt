package by.belobr.jobs.database.entities

import androidx.room.Embedded
import androidx.room.Relation

data class CompanyWithContactsEntity(
    @Embedded val company: CompanyEntity,
    @Relation(
        parentColumn = "companyId",
        entityColumn = "companyId"
    )
    val contacts: List<ContactEntity>
)