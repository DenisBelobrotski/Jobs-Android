package by.belobr.jobs.job.ui

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import by.belobr.jobs.MainActivity
import by.belobr.jobs.R
import by.belobr.jobs.core.utils.TextUtils
import by.belobr.jobs.job.ui.data.JobItem
import kotlinx.android.synthetic.main.fragment_job.*
import org.koin.android.viewmodel.ext.android.viewModel

class JobFragment : Fragment(R.layout.fragment_job) {

    private val jobId by lazy {
        arguments?.getLong(KEY_JOB_ID)
            ?: throw RuntimeException("Job id must be set than starting JobFragment.")
    }

    private val jobViewModel: JobVM by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupActions()
    }

    private fun mainActivity() = activity as? MainActivity

    private fun setupViewModel() {
        jobViewModel.init(jobId)
        jobViewModel.item.observe(viewLifecycleOwner, Observer { setupJob(it) })
    }

    private fun setupJob(jobItem: JobItem) {
        val ctx = context ?: return
        title.text = jobItem.title
        salary.text = TextUtils.formatSalary(ctx, jobItem.salaryFrom, jobItem.salaryTo)
        description.text = jobItem.description
        location.text = jobItem.location
        company.text = jobItem.company
        contacts.setOnClickListener { mainActivity()?.onShowContacts(jobItem.companyId) }
        time.text =
            if (jobItem.isFullWorkDay) getString(R.string.job_full_workday_yes) else getString(R.string.job_full_workday_no )
        experience.text =
            if (jobItem.isExperienceRequired) getString(R.string.job_experience_yes) else getString(R.string.job_experience_no)
    }

    private fun setupActions() {
        close.setOnClickListener { activity?.onBackPressed() }
    }

    companion object {

        private const val KEY_JOB_ID = "JOB_ID"

        fun newInstance(jobId: Long): JobFragment {
            return JobFragment().apply {
                this.arguments = bundleOf(KEY_JOB_ID to jobId)
            }
        }

    }
}