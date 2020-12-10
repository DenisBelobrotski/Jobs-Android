package by.belobr.jobs.insertion.ui

import androidx.annotation.StringRes


sealed class InsertionFormItem {

    data class EditText(
        val id: String,
        @StringRes val hint: Int,
        val value: String? = null
    ) : InsertionFormItem()

    data class Range(
        val id: String,
        @StringRes val hint: Int,
        val from: Int? = null,
        val to: Int? = null
    ) : InsertionFormItem()

    data class Contact(
        val id: String,
        val name: String? = null,
        val contact: String? = null
    ) : InsertionFormItem()

    data class Checkbox(
        val id: String,
        @StringRes val hint: Int,
        val value: Boolean = false
    ) : InsertionFormItem()

    object AddButton : InsertionFormItem()
    object AddContactButton : InsertionFormItem()

}