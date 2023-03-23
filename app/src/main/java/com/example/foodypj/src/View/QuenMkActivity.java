package com.example.foodypj.src.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodypj.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class QuenMkActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edResetEmail;
    Button btnResetEmail;
    TextView txtDangNhap;

    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._layout_quenmk);

        firebaseAuth = FirebaseAuth.getInstance();

        edResetEmail = findViewById(R.id.edResetEmail);
        btnResetEmail = findViewById(R.id.btnResetEmail);
        txtDangNhap = findViewById(R.id.txtDangNhap);

        btnResetEmail.setOnClickListener(this);
        txtDangNhap.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String email = edResetEmail.getText().toString();

        int id = v.getId();
        switch (id){
            case R.id.btnResetEmail:
                if (email.trim().length() == 0 || checkMail(email)){
                    Toast.makeText(this, "Hay nhap dung email", Toast.LENGTH_SHORT).show();
                }else{
                    firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(QuenMkActivity.this, "Kiem tra email de reset", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                break;
            case R.id.txtDangNhap:
                Intent iDangNhap = new Intent(this,DangNhapActivity.class);
                startActivity(iDangNhap);
        }
    }

    private boolean checkMail(String email){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}