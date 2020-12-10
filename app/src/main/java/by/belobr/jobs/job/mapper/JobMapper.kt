package by.belobr.jobs.job.mapper

import by.belobr.jobs.database.entities.JobWithCompanyWithContactsEntity
import by.belobr.jobs.job.ui.data.JobItem

class JobMapper {

    fun mapJob(jobWithCompanyWithContactsEntity: JobWithCompanyWithContactsEntity): JobItem {
        with(jobWithCompanyWithContactsEntity) {
            return JobItem(
                job.title,
                companyWithContacts.company.name,
                companyWithContacts.company.location,
                job.salaryFrom,
                job.salaryTo,
                job.description,
                companyWithContacts.company.companyId,
                job.isFullWorkDay,
                job.isExperienceRequired
            )
        }

    }
}