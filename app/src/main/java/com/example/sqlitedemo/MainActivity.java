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

public class MainActivity extends AppCompatActivity {

    EditText username,password,repassword;
    Button signup,signin;
    DBHelper DB;
    ImageView instagram,twitter,facebook,youtube;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        repassword=findViewById(R.id.repassword);
        signup=findViewById(R.id.signup);
        signin=findViewById(R.id.signin);
        DB= new DBHelper(this);
        facebook = findViewById(R.id.iv_facebook);
        instagram = findViewById(R.id.iv_instagram);
        twitter = findViewById(R.id.iv_twitter);
        youtube = findViewById(R.id.iv_youtube);

        signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
            String user=username.getText().toString();
            String pass=password.getText().toString();
            String repass=repassword.getText().toString();

            if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(repass))

                    Toast.makeText(MainActivity.this, "All fields required", Toast.LENGTH_SHORT).show();

                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser==false){
                            Boolean insert = DB.insertData(user,pass);
                            if(insert==true){
                                Toast.makeText(MainActivity.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(MainActivity.this,"Registration Failed",Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(MainActivity.this,"User allreday exists",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(MainActivity.this,"Passwords are not matched",Toast.LENGTH_SHORT).show();
                    }
                }


            }

        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
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