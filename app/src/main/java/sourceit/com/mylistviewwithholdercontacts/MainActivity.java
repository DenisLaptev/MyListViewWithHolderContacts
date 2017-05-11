package sourceit.com.mylistviewwithholdercontacts;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import sourceit.com.mylistviewwithholdercontacts.model.MyContact;

import static sourceit.com.mylistviewwithholdercontacts.AddContactActivity.NEW_CONTACT_KEY;
import static sourceit.com.mylistviewwithholdercontacts.MyConstants.ADD_NEW_CONTACT_FLAG;

public class MainActivity extends AppCompatActivity {

    //Этот ключ используем для передачи объекта MyContact с помощью интента.
    public static final String KEY = "myContact";


    Button btnAddContact;

    //Здесь используется ListView и адаптер с холдером.
    ListView listView;
    MyAdapter adapter;
    //MyContact[] contactsArray = new MyContact[16];
    List<MyContact> contactsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //contactsArray = generateContacts();
        contactsList = generateContacts();

        btnAddContact = (Button) findViewById(R.id.btn_addcontact);
        btnAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddContactActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();


        //Получаем из интента, если он есть,
        //новый контакт MyContact.
        if (getIntent() != null && ADD_NEW_CONTACT_FLAG > 0) {
            MyContact newContact = (MyContact) getIntent().getSerializableExtra(NEW_CONTACT_KEY);
            contactsList.add(newContact);
            ADD_NEW_CONTACT_FLAG = 0;
        }

        listView = (ListView) findViewById(R.id.list_view);
        //adapter = new MyAdapter(this, contactsArray);
        adapter = new MyAdapter(this, contactsList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_SHORT).show();

                //Получаем объект MyContact,
                //который соответствует пункту под номером position в списке.
                //MyContact myContact = contactsArray[position];
                MyContact myContact = contactsList.get(position);
                Intent intent = new Intent(MainActivity.this, ContactActivity.class);
                //кладём в интент объект MyContact, реализующий интерфейс Serializable.
                intent.putExtra(KEY, myContact);
                startActivity(intent);
            }
        });

    }

    /*
    public MyContact[] generateContacts() {
        MyContact[] contactsArray = new MyContact[16];
        String name;
        String email;
        String address;
        String phone;
        @ColorInt
        int color;
        for (int i = 0; i < 16; i++) {
            name = "name" + i;
            email = "email" + i;
            address = "address" + i;
            phone = "phone" + i;
            color = Color.parseColor("#0000" + Integer.toHexString(i) + "" + Integer.toHexString(i));
            contactsArray[i] = new MyContact(name, email, address, phone, color);
        }
        return contactsArray;
    }
    */


    public List<MyContact> generateContacts() {
        List<MyContact> contactsList = new ArrayList<>();
        String name;
        String email;
        String address;
        String phone;
        @ColorInt
        int color;
        for (int i = 0; i < 16; i++) {
            name = "name" + i;
            email = "email" + i;
            address = "address" + i;
            phone = "phone" + i;
            color = Color.parseColor("#0000" + Integer.toHexString(i) + "" + Integer.toHexString(i));
            contactsList.add(new MyContact(name, email, address, phone, color));
        }
        return contactsList;
    }
}
