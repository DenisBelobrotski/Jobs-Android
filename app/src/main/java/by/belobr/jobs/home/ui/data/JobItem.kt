package by.belobr.jobs.home.ui.data

data class JobItem(
    val id: Long,
    val companyId: Long,
    val title: String,
    val salaryFrom: Int,
    val salaryTo: Int,
    val company: String,
    val location: String,
    val description: String
)