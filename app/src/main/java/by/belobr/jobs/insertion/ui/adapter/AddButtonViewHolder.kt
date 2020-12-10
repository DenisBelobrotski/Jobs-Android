package by.belobr.jobs.insertion.ui.adapter

import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import by.belobr.jobs.R

class AddButtonViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(listener: Listener) {
        view.findViewById<Button>(R.id.addButton).setOnClickListener {
            listener.onAddButtonClick()
        }
    }

    interface Listener {
        fun onAddButtonClick()
    }

}