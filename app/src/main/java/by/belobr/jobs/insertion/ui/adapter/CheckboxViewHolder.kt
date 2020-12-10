package by.belobr.jobs.insertion.ui.adapter

import android.view.View
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.recyclerview.widget.RecyclerView
import by.belobr.jobs.R
import by.belobr.jobs.insertion.ui.InsertionFormItem
import com.google.android.material.textfield.TextInputLayout

class CheckboxViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(item: InsertionFormItem.Checkbox, listener: Listener) {
        val check = view.findViewById<AppCompatCheckBox>(R.id.checkbox)
        check.isChecked = item.value
        check.setText(item.hint)
        check.setOnCheckedChangeListener { _, b -> listener.onCheckboxChanged(item, b)  }
    }

    interface Listener {
        fun onCheckboxChanged(item: InsertionFormItem.Checkbox, newValue: Boolean)
    }

}