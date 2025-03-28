package com.example.apk_by_rk;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class Login extends AppCompatActivity {

    EditText edUsername , edPassword ;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        edUsername = findViewById(R.id.editTextPhone);
        edPassword = findViewById(R.id.editTextTextPassword);
        btn = findViewById(R.id.LoginButton);
        tv = findViewById(R.id.textView5NewUser);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 String phone = edUsername.getText().toString();
                String pass= edPassword.getText().toString();
                Database db = new Database(getApplicationContext() , "healbharat" , null ,1);
                 if(phone.length()==0 || pass.length()==0)
                 {
                     Toast.makeText(getApplicationContext() , "Please fill all details" , Toast.LENGTH_SHORT ) .show();
                 }
                else{

                    if(db.login(phone, pass)==1 )
                    {
                        Toast.makeText(getApplicationContext() , "Login Success" , Toast.LENGTH_SHORT ) .show();
                        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs" , Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("phone",phone) ;
                        // to save out data with key and value
                        editor.apply();
                        startActivity(new Intent(Login.this , home1Activity.class ) );
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext() , "Invalid phone number and password" , Toast.LENGTH_SHORT ) .show();
                    }

                 }


            }
        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this , RegisterActivity.class ) );
            }
        });


    }
}