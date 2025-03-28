package com.example.apk_by_rk;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegisterActivity extends AppCompatActivity {

      EditText edName , edPhone , edPass , edConPass ;
      Button btn ;
      TextView tv ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edName = findViewById(R.id.editTextName);
        edPhone= findViewById(R.id.editTextPhone);
        edPass= findViewById(R.id.editTextTextPassword);
        edConPass= findViewById(R.id.editTextTextConPass);
        btn = findViewById(R.id.RegistrationButton);
        tv = findViewById(R.id.textView5existingUser);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this , Login.class ) );
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edName.getText().toString();
                String phone = edPhone.getText().toString();
                String pass= edPass.getText().toString();
                String conPassword = edConPass.getText().toString();

                Database db = new Database(getApplicationContext() , "healbharat" , null ,1);

                if(name.length()==0 || phone.length()==0||pass.length()==0 || conPassword.length()==0 )
                {
                    Toast.makeText(getApplicationContext() , "Please fill all details" , Toast.LENGTH_SHORT ) .show();
                }
                else{
                    if(pass.compareTo(conPassword)==0)
                    {
                        db.register(name,phone, pass);
                        Toast.makeText(getApplicationContext() , "Registration successfully" , Toast.LENGTH_SHORT ) .show();
                        startActivity(new Intent(RegisterActivity.this , Login.class ) );
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext() , "Password and confirm password did not match" , Toast.LENGTH_SHORT ) .show();
                    }
                }
            }
        });
//        tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(Login.this , RegisterActivity.class ) );
//            }
//        });

    }
}