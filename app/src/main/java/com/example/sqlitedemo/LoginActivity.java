package com.example.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText username,password;
    ImageView instagram,twitter,facebook,youtube;
    Button signin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username=findViewById(R.id.username1);
        password=findViewById(R.id.password1);
        signin=findViewById(R.id.signin1);
        DB=new DBHelper(this);
        facebook = findViewById(R.id.iv_facebook);
        instagram = findViewById(R.id.iv_instagram);
        twitter = findViewById(R.id.iv_twitter);
        youtube = findViewById(R.id.iv_youtube);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user=username.getText().toString();
                String pass=password.getText().toString();

                if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass))
                    Toast.makeText(LoginActivity.this,"All fields are required",Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkuserpass=DB.checkusernamepassword(user,pass);
                    if(checkuserpass==true){
                        Toast.makeText(LoginActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sAppLink = "https://www.facebook.com/profile.php?id=100022302885916&mibextid=ZbWKwL";
                String sPackage = "com.facebook.katana";
                String sWebLink = "https://www.facebook.com/profile.php?id=100022302885916&mibextid=ZbWKwL";

                openLink(sAppLink, sPackage, sWebLink);

            }
        });
        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sAppLink = "twitter://user?screen_name=rutwik_plays";
                String sPackage = "com.twitter.android";
                String sWebLink = "https://twitter.com/rutwik_plays";

                openLink(sAppLink, sPackage, sWebLink);
            }
        });
        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sAppLink = "https://www.instagram.com/rutwik.xd";
                String sPackage = "com.instagram.android";

                openLink(sAppLink, sPackage, sAppLink);

            }
        });
        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sAppLink = "twitter://user?screen_name=rutwik_plays";
                String sPackage = "com.twitter.android";
                String sWebLink = "https://www.youtube.com/channel/UCigwgVsKV4r0hUdyxa7bijQ";

                openLink(sAppLink, sPackage, sWebLink);
            }
        });


    }
    private void openLink(String sAppLink,String sPackage, String sWebLink){

        try{
            Uri uri = Uri.parse(sAppLink);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(uri);
            intent.setPackage(sPackage);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }catch(ActivityNotFoundException activityNotFoundException){
            Uri uri = Uri.parse(sWebLink);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }




    }
