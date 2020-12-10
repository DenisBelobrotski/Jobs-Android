package by.belobr.jobs.insertion.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.belobr.jobs.insertion.data.InsertionRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class InsertionVM(
    private val insertionForm: InsertionForm,
    private val insertionRepository: InsertionRepository
) : ViewModel() {

    val items = MutableLiveData<List<InsertionFormItem>>()
    val close = MutableLiveData<Unit>()

    init {
        viewModelScope.launch {
            insertionForm.init()
            insertionForm.changes?.collect { items.postValue(it) }
        }
    }

    fun onEditTextChanged(item: InsertionFormItem.EditText, newValue: String?) {
        viewModelScope.launch {
            insertionForm.onEditTextChanged(item, newValue)
        }
    }

    fun onAddButtonClick() {
        viewModelScope.launch {
            insertionRepository.insert(insertionForm.prepareData())
            close.postValue(Unit)
        }
    }

    fun onAddContactButtonClick() {
        viewModelScope.launch {
            insertionForm.onAddContact()
        }
    }

    fun onRangeChanged(item: InsertionFormItem.Range, from: Int?, to: Int?) {
        viewModelScope.launch {
            insertionForm.onRangeChanged(item, from, to)
        }
    }

    fun onContactChanged(item: InsertionFormItem.Contact, newValue: String?) {
        viewModelScope.launch {
            insertionForm.onContactChanged(item, newValue)
        }
    }

    fun onContactNameChanged(item: InsertionFormItem.Contact, newValue: String?) {
        viewModelScope.launch {
            insertionForm.onContactNameChanged(item, newValue)
        }
    }

    fun onCheckboxChanged(item: InsertionFormItem.Checkbox, newValue: Boolean) {
        viewModelScope.launch {
            insertionForm.onCheckboxChanged(item, newValue)
        }
    }

}