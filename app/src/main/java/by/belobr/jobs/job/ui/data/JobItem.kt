package by.belobr.jobs.job.ui.data

data class JobItem(
    val title: String,
    val company: String,
    val location: String,
    val salaryFrom: Int,
    val salaryTo: Int,
    val description: String,
    val companyId: Long,
    val isFullWorkDay: Boolean,
    val isExperienceRequired: Boolean
)