package by.belobr.jobs.home.ui.mapper

import by.belobr.jobs.database.entities.JobWithCompanyWithContactsEntity
import by.belobr.jobs.home.ui.data.JobItem

class JobsMapper {

    fun mapJobs(items: List<JobWithCompanyWithContactsEntity>): List<JobItem> {
        return items.map {
            val job = it.job
            val company = it.companyWithContacts.company
            JobItem(
                job.uid,
                company.companyId,
                job.title,
                job.salaryFrom,
                job.salaryTo,
                company.name,
                company.location,
                job.description
            )
        }
    }

}