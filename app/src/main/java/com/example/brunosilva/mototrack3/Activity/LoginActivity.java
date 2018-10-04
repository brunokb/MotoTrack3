package com.example.brunosilva.mototrack3.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.brunosilva.mototrack3.DAO.ConfigFirebase;
import com.example.brunosilva.mototrack3.Entities.Users;
import com.example.brunosilva.mototrack3.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText edtEmail;
    private EditText edtPass;
    private Button btnLogin;
    private FirebaseAuth authentify;
    private Users user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail = (EditText) findViewById(R.id.editEmail);
        edtPass = (EditText) findViewById(R.id.editPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "AQUI", Toast.LENGTH_SHORT).show();
                if(!edtEmail.getText().toString().equals("") && !edtPass.getText().toString().equals("")){
                    user = new Users();
                    user.setEmail(edtEmail.getText().toString());
                    user.setPass(edtPass.getText().toString());
                    validLogin();
                }else{
                    Toast.makeText(LoginActivity.this, "Email or pass empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void validLogin(){
        authentify = ConfigFirebase.getFirebaseAuth();
        authentify.signInWithEmailAndPassword(user.getEmail(),user.getPass()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    openMainScreen();
                    Toast.makeText(LoginActivity.this,"OK",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(LoginActivity.this,"NOPS",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void openMainScreen(){
        Intent intentFirstActivity = new Intent(LoginActivity.this, FirstActivity.class);
        startActivity(intentFirstActivity);
    }
}
