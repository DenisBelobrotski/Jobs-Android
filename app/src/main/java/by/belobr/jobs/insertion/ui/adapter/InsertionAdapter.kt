package by.belobr.jobs.insertion.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.belobr.jobs.R
import by.belobr.jobs.insertion.ui.InsertionFormItem

class InsertionAdapter(private val listener: Listener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: List<InsertionFormItem> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return when (viewType) {
            R.layout.view_insetion_edit_text -> EditTextViewHolder(view)
            R.layout.view_insertion_add_button -> AddButtonViewHolder(view)
            R.layout.view_insertion_range -> RangeViewHolder(view)
            R.layout.view_insertion_contact -> ContactViewHolder(view)
            R.layout.view_insertion_checkbox -> CheckboxViewHolder(view)
            R.layout.view_insertion_add_contact -> AddContactButtonViewHolder(view)
            else -> throw Exception()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is InsertionFormItem.EditText -> R.layout.view_insetion_edit_text
            is InsertionFormItem.AddButton -> R.layout.view_insertion_add_button
            is InsertionFormItem.Range -> R.layout.view_insertion_range
            is InsertionFormItem.Contact -> R.layout.view_insertion_contact
            is InsertionFormItem.Checkbox -> R.layout.view_insertion_checkbox
            is InsertionFormItem.AddContactButton -> R.layout.view_insertion_add_contact
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is EditTextViewHolder -> holder.bind(
                items[position] as InsertionFormItem.EditText,
                listener
            )
            is ContactViewHolder -> holder.bind(
                items[position] as InsertionFormItem.Contact,
                listener
            )
            is RangeViewHolder -> holder.bind(
                items[position] as InsertionFormItem.Range,
                listener
            )
            is CheckboxViewHolder -> holder.bind(
                items[position] as InsertionFormItem.Checkbox,
                listener
            )
            is AddButtonViewHolder -> holder.bind(listener)
            is AddContactButtonViewHolder -> holder.bind(listener)
        }
    }

    interface Listener :
        EditTextViewHolder.Listener,
        AddButtonViewHolder.Listener,
        RangeViewHolder.Listener,
        ContactViewHolder.Listener,
        CheckboxViewHolder.Listener,
        AddContactButtonViewHolder.Listener
}