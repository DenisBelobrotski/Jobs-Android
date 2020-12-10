package by.belobr.jobs.home.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.belobr.jobs.MainActivity
import by.belobr.jobs.R
import by.belobr.jobs.home.ui.adapter.JobsAdapter
import by.belobr.jobs.home.ui.adapter.SwipeToDeleteHelper
import by.belobr.jobs.home.ui.data.JobItem
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home), JobsAdapter.Listener {

    private val homeViewModel: HomeVM by viewModel()

    private lateinit var adapter: JobsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupViewModel()
        setupActions()
    }

    private fun setupViewModel() {
        homeViewModel.jobs.observe(viewLifecycleOwner, Observer {
            adapter.items = it
            swipeToRefresh.isRefreshing = false
        })
    }

    private fun setupRecyclerView() {
        val ctx = context ?: return
        adapter = JobsAdapter(this)
        jobs.layoutManager = LinearLayoutManager(ctx)
        jobs.adapter = adapter
        val swipeToDeleteHelper = object : SwipeToDeleteHelper(ctx) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                homeViewModel.remove(viewHolder.adapterPosition)
            }
        }
        ItemTouchHelper(swipeToDeleteHelper).attachToRecyclerView(jobs)
    }

    private fun setupActions() {
        addJob.setOnClickListener { mainActivity()?.onAddJob() }
        swipeToRefresh.setOnRefreshListener { homeViewModel.refresh() }
        search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) = homeViewModel.search(p0?.toString())
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
        })
    }

    private fun mainActivity() = activity as? MainActivity

    override fun onJobItemClick(id : Long) {
        mainActivity()?.onJobSelected(id)
    }

    override fun onShowContactClick(item: JobItem) {
        mainActivity()?.onShowContacts(item.companyId)
    }

}