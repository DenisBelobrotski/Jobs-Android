package by.belobr.jobs.home.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import by.belobr.jobs.home.ui.data.JobItem

class JobItemDiffCallback(private val oldItems: List<JobItem>, private val newItems: List<JobItem>) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition].id == newItems[newItemPosition].id
    }

    override fun getOldListSize(): Int = oldItems.size

    override fun getNewListSize(): Int = newItems.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition] == newItems[newItemPosition]
    }
}