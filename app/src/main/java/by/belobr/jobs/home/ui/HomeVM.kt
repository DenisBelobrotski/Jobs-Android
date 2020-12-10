package by.belobr.jobs.home.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.belobr.jobs.home.data.HomeRepository
import by.belobr.jobs.home.ui.data.JobItem
import by.belobr.jobs.home.ui.mapper.JobsMapper
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class HomeVM(
    private val homeRepository: HomeRepository,
    private val jobsMapper: JobsMapper
) : ViewModel() {

    val jobs = MutableLiveData<List<JobItem>>()
    private lateinit var items: List<JobItem>
    private var searchJob: Job? = null

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            val data = homeRepository.getJobs()
            items = jobsMapper.mapJobs(data)
            jobs.postValue(items)
        }
    }

    fun remove(position: Int) {
        viewModelScope.launch {
            val newItems = items.toMutableList()
            val id = newItems[position].id
            homeRepository.removeJob(id)
            newItems.removeAt(position)
            items = newItems
            jobs.postValue(newItems)
        }
    }

    fun search(text: String?) {
        val query = text ?: ""
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(DEBOUNCE_SEARCH_TIME)
            val data = homeRepository.getJobs(query)
            items = jobsMapper.mapJobs(data)
            jobs.postValue(items)
        }
    }

    companion object {
        private const val DEBOUNCE_SEARCH_TIME = 500L
    }
}