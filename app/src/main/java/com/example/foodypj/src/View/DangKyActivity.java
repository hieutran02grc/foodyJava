package com.example.foodypj.src.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.foodypj.Controller.DangKyController;
import com.example.foodypj.Model.ThanhVienModel;
import com.example.foodypj.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DangKyActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edEmailDKs;
    EditText edPasswordDK;
    EditText edNhapLaiPW;
    Button btnDangKy;
    TextView txtdangnhap;
    DangKyController dangKyController;

    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._layout_dangky);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        btnDangKy = findViewById(R.id.btnDangKy);
        edEmailDKs = findViewById(R.id.edEmailDK);
        edPasswordDK = findViewById(R.id.edPasswordDK);
        edNhapLaiPW = findViewById(R.id.edNhapLaiPW);
        txtdangnhap = findViewById(R.id.txtDangNhap);

        txtdangnhap.setOnClickListener(this);
        btnDangKy.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.btnDangKy:
                DangKy();
                break;
            case R.id.txtDangNhap:
                Intent iDangNhap = new Intent(this,DangNhapActivity.class);
                startActivity(iDangNhap);
                break;
            case R.id.txtQuenMK:
                Intent iQuenmk = new Intent(this,QuenMkActivity.class);
                startActivity(iQuenmk);
        }

    }

    private void DangKy(){
        progressDialog.setMessage("Đang xử lý");
        progressDialog.setIndeterminate(true);

        String email = edEmailDKs.getText().toString();
        String matkhau = edPasswordDK.getText().toString();
        String nhaplaimk = edNhapLaiPW.getText().toString();

        if(email.trim().length() == 0){
            Toast.makeText(this, "chưa nhập tên tài khoản", Toast.LENGTH_SHORT).show();
        }else if(matkhau.trim().length() == 0){
            Toast.makeText(this, "chưa nhập mật khẩu", Toast.LENGTH_SHORT).show();
        }else if(!nhaplaimk.equals(matkhau)){
            Toast.makeText(this, "Nhập lại mật khẩu chưa chính xác", Toast.LENGTH_SHORT).show();
        }else{
            progressDialog.show();
            firebaseAuth.createUserWithEmailAndPassword(email,matkhau).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressDialog.dismiss();
                        ThanhVienModel thanhVienModel = new ThanhVienModel();
                        thanhVienModel.setHoten(email);
                        thanhVienModel.setHinhanh("user.jpg");
                        String uid = task.getResult().getUser().getUid();
                        dangKyController =new DangKyController();
                        dangKyController.ThemThongTinThanhVienController(thanhVienModel, uid);
                        Toast.makeText(DangKyActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();

                    }else{
                        progressDialog.dismiss();
                        Toast.makeText(DangKyActivity.this, "Đăng ký không thành công", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
}