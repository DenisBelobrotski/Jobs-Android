package by.belobr.jobs.home.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.belobr.jobs.R
import by.belobr.jobs.home.ui.data.JobItem

class JobsAdapter(private val listener: Listener) :
    RecyclerView.Adapter<JobViewHolder>() {

    var items: List<JobItem> = emptyList()
        set(value) {
            val diff = JobItemDiffCallback(field, value)
            val difference = DiffUtil.calculateDiff(diff)
            field = value
            difference.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_job, parent, false)
        return JobViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    interface Listener : JobViewHolder.Listener
}