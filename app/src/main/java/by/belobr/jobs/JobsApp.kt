package by.belobr.jobs

import android.app.Application
import by.belobr.jobs.contacts.di.contactsModule
import by.belobr.jobs.core.di.coreModule
import by.belobr.jobs.database.di.databaseModule
import by.belobr.jobs.home.di.homeModule
import by.belobr.jobs.insertion.di.insertionModule
import by.belobr.jobs.job.di.jobModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class JobsApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@JobsApp)
            modules(
                homeModule,
                jobModule,
                insertionModule,
                databaseModule,
                coreModule,
                contactsModule
            )
        }
    }

}