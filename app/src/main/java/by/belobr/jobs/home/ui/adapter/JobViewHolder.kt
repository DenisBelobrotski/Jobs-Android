package by.belobr.jobs.home.ui.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.belobr.jobs.R
import by.belobr.jobs.core.utils.TextUtils
import by.belobr.jobs.home.ui.data.JobItem
import kotlinx.android.synthetic.main.view_job.view.*

class JobViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(item: JobItem, listener: Listener) {
        view.setOnClickListener { listener.onJobItemClick(item.id) }
        view.findViewById<TextView>(R.id.title).text = item.title
        view.findViewById<TextView>(R.id.company).text = item.company
        view.findViewById<TextView>(R.id.location).text = item.location
        view.findViewById<TextView>(R.id.description).text = item.description
        view.findViewById<TextView>(R.id.salary).text = TextUtils.formatSalary(view.context, item.salaryFrom, item.salaryTo)
        view.findViewById<View>(R.id.contacts).setOnClickListener { listener.onShowContactClick(item) }
    }

    interface Listener {
        fun onJobItemClick(id: Long)
        fun onShowContactClick(item: JobItem)
    }
}