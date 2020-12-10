package by.belobr.jobs.insertion.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import by.belobr.jobs.R
import by.belobr.jobs.insertion.ui.adapter.InsertionAdapter
import kotlinx.android.synthetic.main.fragment_insertion.*
import org.koin.android.viewmodel.ext.android.viewModel

class InsertionFragment : Fragment(R.layout.fragment_insertion), InsertionAdapter.Listener {

    private val insertionVM: InsertionVM by viewModel()

    private lateinit var adapter: InsertionAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecycler()
        setUpViewModel()
    }

    private fun setUpRecycler() {
        val ctx = context ?: return
        adapter = InsertionAdapter(this)
        insertion.layoutManager = LinearLayoutManager(ctx)
        insertion.adapter = adapter
    }

    private fun setUpViewModel() {
        insertionVM.items.observe(viewLifecycleOwner, Observer {
            adapter.items = it
        })
        insertionVM.close.observe(viewLifecycleOwner, Observer { 
            activity?.onBackPressed()
        })
    }

    override fun onTextChanged(item: InsertionFormItem.EditText, newValue: String?) {
        insertionVM.onEditTextChanged(item, newValue)
    }

    override fun onAddButtonClick() {
        insertionVM.onAddButtonClick()
    }

    override fun onRangeChanged(item: InsertionFormItem.Range, from: Int?, to: Int?) {
        insertionVM.onRangeChanged(item, from, to)
    }

    override fun onContactChanged(item: InsertionFormItem.Contact, newValue: String?) {
        insertionVM.onContactChanged(item, newValue)
    }

    override fun onContactNameChanged(item: InsertionFormItem.Contact, newValue: String?) {
        insertionVM.onContactNameChanged(item, newValue)
    }

    override fun onCheckboxChanged(item: InsertionFormItem.Checkbox, newValue: Boolean) {
        insertionVM.onCheckboxChanged(item, newValue)
    }

    override fun onAddContactButtonClick() {
        insertionVM.onAddContactButtonClick()
    }

    companion object {
        fun newInstance(): Fragment = InsertionFragment()
    }
}