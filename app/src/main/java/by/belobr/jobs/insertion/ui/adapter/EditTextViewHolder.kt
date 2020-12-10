package by.belobr.jobs.insertion.ui.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.belobr.jobs.R
import by.belobr.jobs.insertion.ui.InsertionFormItem
import com.google.android.material.textfield.TextInputLayout

class EditTextViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private var watcher: TextWatcher? = null

    fun bind(item: InsertionFormItem.EditText, listener: Listener) {
        initListenersIfNeeded(item, listener)
        val textField = view.findViewById<TextInputLayout>(R.id.textField)
        textField.hint = view.context.getString(item.hint)
        textField.editText?.apply {
            removeTextChangedListener(watcher)
            setText(item.value)
            addTextChangedListener(watcher)
        }
    }

    private fun initListenersIfNeeded(item: InsertionFormItem.EditText, listener: Listener) {
        if (watcher == null) {
            watcher = object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
                override fun afterTextChanged(p0: Editable?) {
                    listener.onTextChanged(item, p0?.toString())
                }
            }
        }
    }

    interface Listener {
        fun onTextChanged(item: InsertionFormItem.EditText, newValue: String?)
    }

}