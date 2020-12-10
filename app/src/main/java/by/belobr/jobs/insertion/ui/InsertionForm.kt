package by.belobr.jobs.insertion.ui

import by.belobr.jobs.R
import by.belobr.jobs.database.entities.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.withContext
import java.util.*

class InsertionForm(private val dispatcher: Dispatchers) {

    private var items: MutableList<InsertionFormItem> =
        mutableListOf(
            InsertionFormItem.EditText(EDIT_TITLE, R.string.insertion_title),
            InsertionFormItem.EditText(EDIT_DESCRIPTION, R.string.insertion_description),
            InsertionFormItem.Range(RANGE_SALARY, R.string.insertion_salary),
            InsertionFormItem.Checkbox(CHECK_EXPERIENCE, R.string.insertion_experience),
            InsertionFormItem.Checkbox(CHECK_WORK_DAY, R.string.insertion_work_day),
            InsertionFormItem.EditText(COMPANY_NAME, R.string.insertion_company),
            InsertionFormItem.EditText(COMPANY_LOCATION, R.string.insertion_company_location),
            InsertionFormItem.Contact(UUID.randomUUID().toString()),
            InsertionFormItem.AddContactButton,
            InsertionFormItem.AddButton
        )

    private var _changes: MutableSharedFlow<List<InsertionFormItem>>? = null
    var changes: SharedFlow<List<InsertionFormItem>>? = null

    suspend fun init() {
        if (_changes == null) {
            _changes = MutableSharedFlow(1, 1)
            changes = _changes?.asSharedFlow()
        }
        withContext(dispatcher.IO) {
            _changes?.emit(List(items.size) { items[it] })
        }
    }

    suspend fun onEditTextChanged(item: InsertionFormItem.EditText, newValue: String?) {
        withContext(dispatcher.IO) {
            val last = items.last { it is InsertionFormItem.EditText && it.id == item.id }
            val index = items.indexOf(last)
            if (index == -1) return@withContext
            items[index] = item.copy(value = newValue)
        }
    }

    suspend fun onRangeChanged(item: InsertionFormItem.Range, from: Int?, to: Int?) {
        withContext(dispatcher.IO) {
            val last = items.last { it is InsertionFormItem.Range && it.id == item.id }
            val index = items.indexOf(last)
            if (index == -1) return@withContext
            items[index] = item.copy(from = from, to = to)
        }
    }

    suspend fun onContactChanged(item: InsertionFormItem.Contact, newValue: String?) {
        withContext(dispatcher.IO) {
            val last =
                items.last { it is InsertionFormItem.Contact && it.id == item.id } as InsertionFormItem.Contact
            val index = items.indexOf(last)
            if (index == -1) return@withContext
            items[index] = last.copy(contact = newValue)
        }
    }

    suspend fun onAddContact() {
        withContext(dispatcher.IO) {
            val lastIndex = items.indexOfLast { it is InsertionFormItem.Contact }
            items.add(lastIndex, InsertionFormItem.Contact(UUID.randomUUID().toString()))
            _changes?.emit(items)
        }
    }

    suspend fun onContactNameChanged(item: InsertionFormItem.Contact, newValue: String?) {
        withContext(dispatcher.IO) {
            val last =
                items.last { it is InsertionFormItem.Contact && it.id == item.id } as InsertionFormItem.Contact
            val index = items.indexOf(last)
            if (index == -1) return@withContext
            items[index] = last.copy(name = newValue)
        }
    }

    suspend fun onCheckboxChanged(item: InsertionFormItem.Checkbox, newValue: Boolean) {
        withContext(dispatcher.IO){
            val last = items.last { it is InsertionFormItem.Checkbox && it.id == item.id }
            val index = items.indexOf(last)
            if (index == -1) return@withContext
            items[index] = item.copy(value = newValue)
        }
    }

    suspend fun prepareData(): JobWithCompanyWithContactsEntity {
        return withContext(dispatcher.IO) {
            val title =
                (items.find { it is InsertionFormItem.EditText && it.id == EDIT_TITLE } as? InsertionFormItem.EditText)?.value
                    ?: ""
            val description =
                (items.find { it is InsertionFormItem.EditText && it.id == EDIT_DESCRIPTION } as? InsertionFormItem.EditText)?.value
                    ?: ""
            val isFullWorkday =
                (items.find { it is InsertionFormItem.Checkbox && it.id == CHECK_WORK_DAY } as? InsertionFormItem.Checkbox)?.value
                    ?: false
            val isExperienceRequired =
                (items.find { it is InsertionFormItem.Checkbox && it.id == CHECK_EXPERIENCE } as? InsertionFormItem.Checkbox)?.value
                    ?: false
            val salaryRange =
                (items.find { it is InsertionFormItem.Range && it.id == RANGE_SALARY } as? InsertionFormItem.Range)

            val company =
                (items.find { it is InsertionFormItem.EditText && it.id == COMPANY_NAME } as? InsertionFormItem.EditText)?.value
                    ?: ""
            val companyLocation =
                (items.find { it is InsertionFormItem.EditText && it.id == COMPANY_LOCATION } as? InsertionFormItem.EditText)?.value
                    ?: ""

            val contacts =
                items.filterIsInstance<InsertionFormItem.Contact>()
                    .map { ContactEntity(it.name ?: "", 0, it.contact ?: "") }

            val job = JobEntity(
                title,
                description,
                salaryRange?.from ?: 0,
                salaryRange?.to ?: 0,
                0,
                isExperienceRequired,
                isFullWorkday
            )
            val companyEntity = CompanyEntity(company, companyLocation)
            JobWithCompanyWithContactsEntity(
                job,
                CompanyWithContactsEntity(companyEntity, contacts)
            )
        }
    }

    companion object {
        private const val EDIT_TITLE = "EDIT_TITLE"
        private const val EDIT_DESCRIPTION = "EDIT_DESCRIPTION"
        private const val CHECK_EXPERIENCE = "CHECK_EXPERIENCE"
        private const val CHECK_WORK_DAY = "CHECK_WORD_DAY"
        private const val RANGE_SALARY = "RANGE_SALARY"
        private const val COMPANY_NAME = "COMPANY_NAME"
        private const val COMPANY_LOCATION = "COMPANY_LOCATION"
    }
}