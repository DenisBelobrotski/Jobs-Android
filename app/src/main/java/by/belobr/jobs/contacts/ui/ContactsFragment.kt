package by.belobr.jobs.contacts.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import by.belobr.jobs.R
import by.belobr.jobs.contacts.ui.adapter.ContactsAdapter
import by.belobr.jobs.contacts.ui.data.ContactItem
import kotlinx.android.synthetic.main.fragment_contacts.*
import org.koin.android.viewmodel.ext.android.viewModel


class ContactsFragment : Fragment(R.layout.fragment_contacts), ContactsAdapter.Listener {

    private val contactsVM: ContactsVM by viewModel()

    private val companyId by lazy {
        arguments?.getLong(KEY_COMPANY_ID)
            ?: throw RuntimeException("Company id must be set than starting ContactsFragment.")
    }

    private lateinit var adapter: ContactsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewModel()
        setUpRecyclerView()
    }

    override fun onCallClick(item: ContactItem) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:${item.contact}")
        startActivity(intent)
    }

    private fun setUpRecyclerView() {
        val ctx = context ?: return
        adapter = ContactsAdapter(this)
        contacts.layoutManager = LinearLayoutManager(ctx)
        contacts.adapter = adapter
    }

    private fun setUpViewModel() {
        contactsVM.init(companyId)
        contactsVM.items.observe(viewLifecycleOwner, Observer {
            adapter.items = it
        })
    }

    companion object {

        private const val KEY_COMPANY_ID = "COMPANY_ID"

        fun newInstance(companyId: Long): Fragment {
            return ContactsFragment().apply {
                arguments = bundleOf(KEY_COMPANY_ID to companyId)
            }
        }
    }
}