package vn.dlu.nhantinsms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Magnifier;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnSend;
    EditText editTextPhone, editTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initControl();
    }

    private void initControl() {
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextMessage = findViewById(R.id.editTextMessage);
        btnSend = findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                    SenMess();
                } else {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS}, 100);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // check condition
        if (requestCode == 100 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            SenMess();
        } else {
            Toast.makeText(this, "Permission is denied", Toast.LENGTH_SHORT).show();
        }
    }

    private void SenMess() {
        String phone = editTextPhone.getText().toString();
        String message = editTextMessage.getText().toString();
        if (!phone.isEmpty() && !message.isEmpty()) {
            // init mess
            SmsManager smsManager = SmsManager.getDefault();
            //send mess
            smsManager.sendTextMessage(phone, null, message, null, null);
            // display toast mess
            Toast.makeText(this, "Gửi tin nhắn thành công!", Toast.LENGTH_SHORT).show();
        } else {
            // Display Toast if text is empty
            Toast.makeText(this, "Vui lòng nhập số điện thoại và nội dung", Toast.LENGTH_SHORT).show();
        }
    }
}