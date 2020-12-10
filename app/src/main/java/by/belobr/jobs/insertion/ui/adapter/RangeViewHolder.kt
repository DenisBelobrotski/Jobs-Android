package by.belobr.jobs.insertion.ui.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.belobr.jobs.R
import by.belobr.jobs.insertion.ui.InsertionFormItem
import com.google.android.material.textfield.TextInputLayout

class RangeViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(item: InsertionFormItem.Range, listener: Listener) {
        val from = view.findViewById<TextInputLayout>(R.id.from)
        val to = view.findViewById<TextInputLayout>(R.id.to)
        val title = view.findViewById<TextView>(R.id.title)
        title.setText(item.hint)
        from.editText?.setText(item.from?.toString())
        from.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
            override fun afterTextChanged(p0: Editable?) {
                listener.onRangeChanged(
                    item,
                    p0?.toString()?.toIntOrNull(),
                    to.editText?.text?.toString()?.toIntOrNull()
                )
            }
        })
        to.editText?.setText(item.to?.toString())
        to.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
            override fun afterTextChanged(p0: Editable?) {
                listener.onRangeChanged(
                    item,
                    from.editText?.text?.toString()?.toIntOrNull(),
                    p0?.toString()?.toIntOrNull()
                )
            }
        })
    }

    interface Listener {
        fun onRangeChanged(item: InsertionFormItem.Range, from: Int?, to: Int?)
    }
}