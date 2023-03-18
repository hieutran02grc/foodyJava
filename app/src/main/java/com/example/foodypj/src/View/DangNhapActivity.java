package com.example.foodypj.src.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodypj.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class DangNhapActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener, FirebaseAuth.AuthStateListener {

    Button btnDangNhapGoole;
    Button btnDangNhap;
    EditText edEmail,edPassword;
    Context context;
    GoogleApiClient apiClient;
    TextView txtDangKyMoi, txtQuenMK;
    public static int REQUESTCODE_DANGNHAP_GOOGLE =99;
    public static int KIEMTRA_PROVIDER_DANGNHAP =0 ;

    private boolean isGuest = false;
    private GoogleSignInClient mClient;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_dangnhap);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signOut();

        btnDangNhapGoole = (Button) findViewById(R.id.btnDangNhapGoogle);
        btnDangNhap = findViewById(R.id.btnDangKy);
        edEmail = findViewById(R.id.edEmailDN);
        edPassword = findViewById(R.id.edPasswordDN);
        txtDangKyMoi = findViewById(R.id.txtDangKyMoi);
        txtQuenMK = findViewById(R.id.txtQuenMK);

        btnDangNhapGoole.setOnClickListener(this);
        btnDangNhap.setOnClickListener(this);
        txtDangKyMoi.setOnClickListener(this);
        txtQuenMK.setOnClickListener(this);

/*        createRequest();*/
        TaoClientDangNhapGoogle();
    }

  /*  private void createRequest() {
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mClient = GoogleSignIn.getClient(this, signInOptions);
    }

    private void loginUser(){
        Intent intent = mClient.getSignInIntent();

    }

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),result -> {
       if(result.getResultCode() == Activity.RESULT_OK){
           Intent data = result.getData();

           Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
           try {
               GoogleSignInAccount account = task.getResult(ApiException.class);
               auth(account.getIdToken());
           }catch (ApiException e){
               throw new RuntimeException(e);
           }
       }
    });

    private void auth(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken,null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task ->{
                    if(task.isSuccessful()){
                        Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(this, "Login Faild", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void userProfile(){
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user!=null){
            Toast.makeText(this, "Da thanh cong", Toast.LENGTH_SHORT).show();
        }else{

        }
    }

    private void logoutUser(){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this,DangNhapActivity.c));
    }*/

    private void TaoClientDangNhapGoogle(){
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder()
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        apiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,signInOptions)
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(this);
    }

    private  void ChungThucDangNhapFireBase(String tokenID){
        if(KIEMTRA_PROVIDER_DANGNHAP == 1) {
            AuthCredential authCredential = GoogleAuthProvider.getCredential(tokenID, null);
            firebaseAuth.signInWithCredential(authCredential);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == REQUESTCODE_DANGNHAP_GOOGLE)
            if(requestCode == RESULT_OK){
                GoogleSignInResult signInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                GoogleSignInAccount account = signInResult.getSignInAccount();
                String tokenID = account.getIdToken();
                ChungThucDangNhapFireBase(tokenID);
            }
    }

    private void DangNhapGoogle(GoogleApiClient apiClient){
        KIEMTRA_PROVIDER_DANGNHAP = 1;
        Intent iDNGoogle = Auth.GoogleSignInApi.getSignInIntent(apiClient);
        startActivityForResult(iDNGoogle,REQUESTCODE_DANGNHAP_GOOGLE);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch(id){
            case R.id.btnDangNhapGoogle:
                DangNhapGoogle(apiClient);
                break;
            case R.id.btnDangKy:
                DangNhap();
                break;
            case R.id.txtDangKyMoi:
                Intent iDangKy = new Intent(this,DangKyActivity.class);
                startActivity(iDangKy);
            case R.id.txtQuenMK:
                Intent iQuenmk = new Intent(this,QuenMkActivity.class);
                startActivity(iQuenmk);
        }
    }

    private void DangNhap(){
        String email = edEmail.getText().toString();
        String password = edPassword.getText().toString();
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(DangNhapActivity.this,TrangChuActivity.class);
                            context.startActivity(intent);
                        }else {
                            Toast.makeText(getApplicationContext(), "Login Fail", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user != null){
            Intent iTrangChu = new Intent(this,TrangChuActivity.class);
            startActivity(iTrangChu);
        }
    }
}
