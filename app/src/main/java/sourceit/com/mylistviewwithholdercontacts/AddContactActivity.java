package sourceit.com.mylistviewwithholdercontacts;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sourceit.com.mylistviewwithholdercontacts.model.MyContact;

import static sourceit.com.mylistviewwithholdercontacts.MyConstants.ADD_NEW_CONTACT_FLAG;

public class AddContactActivity extends AppCompatActivity {

    public static final String NEW_CONTACT_KEY = "New contact key";

    //Объявляем View-элементы, используя ButterKnife.
    @BindView(R.id.name)
    EditText etName;

    @BindView(R.id.email)
    EditText etEmail;

    @BindView(R.id.address)
    EditText etAddress;

    @BindView(R.id.phone)
    EditText etPhone;

    @BindView(R.id.color)
    EditText etColor;
//Объявляем View-элементы, используя ButterKnife.


    @BindView(R.id.btn_add)
    Button btnAdd;

    //Объект нового контакта MyContact и его поля.
    MyContact newContact;

    String name;
    String email;
    String address;
    String phone;
    @ColorInt
    int color;
//Объект нового контакта MyContact и его поля.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        ButterKnife.bind(this);

    }

    private MyContact saveNewContact() {
        MyContact newContact;
        name = etName.getText().toString();
        email = etEmail.getText().toString();
        address = etAddress.getText().toString();
        phone = etPhone.getText().toString();

        //Чтобы распознать цвет, используем вспомогательные переменные.
        String stringColor = etColor.getText().toString();
        int intColor = Integer.parseInt(stringColor);
        //Чтобы распознать цвет, используем вспомогательные переменные.

        //color = Color.parseColor("#" + Integer.toHexString(intColor));
        color = Color.parseColor("#" + stringColor);

        newContact = new MyContact(name, email, address, phone, color);
        return newContact;
    }

    @OnClick(R.id.btn_add)
    void onButtonClick() {
        newContact = saveNewContact();
        Intent intent = new Intent(AddContactActivity.this, MainActivity.class);
        //кладём в интент объект MyContact, реализующий интерфейс Serializable.
        intent.putExtra(NEW_CONTACT_KEY, newContact);
        ADD_NEW_CONTACT_FLAG = 1;
        startActivity(intent);
    }
}
