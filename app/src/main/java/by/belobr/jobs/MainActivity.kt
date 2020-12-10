package by.belobr.jobs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.belobr.jobs.contacts.ui.ContactsFragment
import by.belobr.jobs.home.ui.HomeFragment
import by.belobr.jobs.insertion.ui.InsertionFragment
import by.belobr.jobs.job.ui.JobFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container, HomeFragment())
                .commit()
        }
    }

    fun onJobSelected(jobId: Long) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, JobFragment.newInstance(jobId))
            .addToBackStack(null)
            .commit()
    }

    fun onAddJob() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, InsertionFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    fun onShowContacts(companyId: Long) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, ContactsFragment.newInstance(companyId))
            .addToBackStack(null)
            .commit()
    }
}