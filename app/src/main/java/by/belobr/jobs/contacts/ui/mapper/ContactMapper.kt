package by.belobr.jobs.contacts.ui.mapper

import by.belobr.jobs.contacts.ui.data.ContactItem
import by.belobr.jobs.database.entities.ContactEntity

class ContactMapper {

    fun mapContact(entity: ContactEntity): ContactItem {
        return ContactItem(
            entity.name,
            entity.contact
        )
    }
}