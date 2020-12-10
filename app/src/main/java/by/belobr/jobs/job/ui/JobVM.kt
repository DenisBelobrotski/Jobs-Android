package by.belobr.jobs.job.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.belobr.jobs.job.data.JobRepository
import by.belobr.jobs.job.mapper.JobMapper
import by.belobr.jobs.job.ui.data.JobItem
import kotlinx.coroutines.launch

class JobVM(
    private val jobRepository: JobRepository,
    private val jobMapper: JobMapper
) : ViewModel() {

    val item = MutableLiveData<JobItem>()

    fun init(jobId: Long) {
        viewModelScope.launch {
            val entity = jobRepository.getJob(jobId)
            val job = jobMapper.mapJob(entity)
            item.postValue(job)
        }
    }
}