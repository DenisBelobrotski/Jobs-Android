package by.belobr.jobs.contacts.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.belobr.jobs.R
import by.belobr.jobs.contacts.ui.data.ContactItem

class ContactsAdapter(private val listener: Listener) :
    RecyclerView.Adapter<ContactViewHolder>() {

    var items: List<ContactItem> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_contact, parent, false)
        return ContactViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    interface Listener : ContactViewHolder.Listener
}