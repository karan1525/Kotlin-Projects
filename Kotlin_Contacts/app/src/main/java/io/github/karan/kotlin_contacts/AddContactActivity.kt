package io.github.karan.kotlin_contacts

import android.content.ContentUris
import android.content.ContentValues
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import android.widget.Toast

class AddContactActivity : AppCompatActivity() {

    private var firstName: EditText? = null
    private var lastName: EditText? = null
    private var street: EditText? = null
    private var city: EditText? = null
    private var state: EditText? = null
    private var zip: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)

        // get values of EditText fields
        firstName = findViewById(R.id.editText_firstName)
        lastName = findViewById(R.id.editText_lastname)
        street = findViewById(R.id.editText_street)
        city = findViewById(R.id.editText_city)
        state = findViewById(R.id.editText_state)
        zip = findViewById(R.id.editText_zipcode)
    }

    fun okButtonClicked(view: View) {
        val displayFirstName = firstName!!.text.toString()
        val displayLastName = lastName!!.text.toString()
        val displayStreet = street!!.text.toString()
        val displayCity = city!!.text.toString()
        val displayState = state!!.text.toString()
        val displayZip = zip!!.text.toString()

        //Names, input validation
        val nameEmpty = displayFirstName.isEmpty() && displayLastName.isEmpty()

        if (nameEmpty) {
            Toast.makeText(this, "A First or Last Name is required",
                    Toast.LENGTH_SHORT).show()
        } else {
            val accountType: String? = null
            val accountName: String? = null

            // Create the contact
            val values = ContentValues()
            values.put(ContactsContract.RawContacts.ACCOUNT_TYPE, accountType)
            values.put(ContactsContract.RawContacts.ACCOUNT_NAME, accountName)
            val rawContactUri = contentResolver.insert(
                    ContactsContract.RawContacts.CONTENT_URI, values)
            val rawContactId = ContentUris.parseId(rawContactUri)

            values.clear()

            // Add the first and last names to the new contact
            values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId)
            values.put(ContactsContract.Data.MIMETYPE,
                    ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
            values.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, displayFirstName)
            values.put(ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME, displayLastName)
            contentResolver.insert(ContactsContract.Data.CONTENT_URI, values)

            // Add the postal information to the new contact
            values.put(ContactsContract.Data.MIMETYPE,
                    ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE)
            values.put(ContactsContract.CommonDataKinds.StructuredPostal.STREET, displayStreet)
            values.put(ContactsContract.CommonDataKinds.StructuredPostal.REGION, displayState)
            values.put(ContactsContract.CommonDataKinds.StructuredPostal.POSTCODE, displayZip)
            values.put(ContactsContract.CommonDataKinds.StructuredPostal.TYPE,
                    ContactsContract.CommonDataKinds.StructuredPostal.TYPE_HOME)
            values.put(ContactsContract.CommonDataKinds.StructuredPostal.CITY, displayCity)

            contentResolver.insert(ContactsContract.Data.CONTENT_URI, values)
        }

        finish()

    }

    fun cancelButtonClicked(view: View) {
        finish()
    }
}
