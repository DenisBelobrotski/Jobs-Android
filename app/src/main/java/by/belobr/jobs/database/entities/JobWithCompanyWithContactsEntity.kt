package by.belobr.jobs.database.entities

import androidx.room.Embedded
import androidx.room.Relation


data class JobWithCompanyWithContactsEntity(
    @Embedded val job: JobEntity,
    @Relation(
        entity = CompanyEntity::class,
        parentColumn = "companyId",
        entityColumn = "companyId"
    )
    val companyWithContacts: CompanyWithContactsEntity
)