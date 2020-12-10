package by.belobr.jobs.contacts.ui.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.belobr.jobs.R
import by.belobr.jobs.contacts.ui.data.ContactItem

class ContactViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(item: ContactItem, listener: Listener) {
        view.findViewById<TextView>(R.id.name).text = item.name
        view.findViewById<TextView>(R.id.contact).text = item.contact
        view.findViewById<View>(R.id.call).setOnClickListener { listener.onCallClick(item) }
    }

    interface Listener {
        fun onCallClick(item: ContactItem)
    }
}