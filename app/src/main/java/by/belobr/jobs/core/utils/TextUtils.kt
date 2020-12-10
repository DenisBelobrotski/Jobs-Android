package by.belobr.jobs.core.utils

import android.content.Context
import by.belobr.jobs.R


object TextUtils {

    fun formatSalary(context: Context, from: Int, to: Int): String {
        return when {
            from == 0 && to == 0 -> context.getString(R.string.salary_contract)
            to == 0 -> context.getString(R.string.salary_only_from, from.toString())
            from == 0 -> context.getString(R.string.salary_only_to, to.toString())
            else -> context.getString(R.string.salary_from_to, from.toString(), to.toString())
        }
    }

}