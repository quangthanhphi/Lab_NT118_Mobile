package com.example.lab3_contact;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ContactAdapter contactAdapter;
    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHandler(this);

        // Inserting Contacts
        Log.d("Insert: ", "Inserting ..");
        db.addContact(new Contact("Ravi", "9100000000"));
        db.addContact(new Contact("Srinivas", "9199999999"));
        db.addContact(new Contact("Tommy", "9522222222"));
        db.addContact(new Contact("Karthik", "9533333333"));

        // Reading all contacts
        Log.e("Reading: ", "Reading all contacts..");
        List<Contact> contacts = db.getAllContacts();

        // Create the adapter to convert the array to views
        contactAdapter = new ContactAdapter(this, contacts);

        // Attach the adapter to a ListView
        ListView listView = findViewById(R.id.list_view_contacts);
        listView.setAdapter(contactAdapter);

        // Set a long click listener to delete the contact
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Contact contact = contactAdapter.getItem(position);
                if (contact != null) {
                    db.deleteContact(contact);
                    contactAdapter.remove(contact);
                    contactAdapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Deleted contact: " + contact.getName(), Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }
}
