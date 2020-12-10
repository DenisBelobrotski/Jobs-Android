package by.belobr.jobs.database.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import by.belobr.jobs.database.JobDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    fun buildDatabase(app: Application): JobDatabase {
        return Room.databaseBuilder(app, JobDatabase::class.java, "jobs").build()
    }

    single {
        buildDatabase(androidApplication())
    }

    single {
        get<JobDatabase>().jobDao()
    }

    single {
        get<JobDatabase>().contactDao()
    }

    single {
        get<JobDatabase>().companyDao()
    }

    single {
        get<JobDatabase>().jobWithCompanyWithContactsDao()
    }

}