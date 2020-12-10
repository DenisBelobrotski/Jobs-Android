package by.belobr.jobs.insertion.ui.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.belobr.jobs.R
import by.belobr.jobs.insertion.ui.InsertionFormItem
import com.google.android.material.textfield.TextInputLayout

class ContactViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(item: InsertionFormItem.Contact, listener: Listener) {
        val contact = view.findViewById<TextInputLayout>(R.id.contact)
        val name = view.findViewById<TextInputLayout>(R.id.name)
        contact.editText?.setText(item.contact)
        contact.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
            override fun afterTextChanged(p0: Editable?) {
                listener.onContactChanged(item, p0?.toString())
            }
        })
        name.editText?.setText(item.name)
        name.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
            override fun afterTextChanged(p0: Editable?) {
                listener.onContactNameChanged(item, p0?.toString())
            }
        })
    }

    interface Listener {
        fun onContactChanged(item: InsertionFormItem.Contact, newValue: String?)
        fun onContactNameChanged(item: InsertionFormItem.Contact, newValue: String?)
    }

}