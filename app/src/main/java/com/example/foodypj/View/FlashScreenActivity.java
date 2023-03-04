package com.example.foodypj.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

import com.example.foodypj.R;

import org.w3c.dom.Text;

public class FlashScreenActivity extends AppCompatActivity {

    TextView txtVersion, txtCompany, txtLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_flashcreen);
        txtVersion = findViewById(R.id.txtVersion);
        txtCompany = findViewById(R.id.txtCompany);
        txtLoading = findViewById(R.id.txtLoading);
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(),0);
            txtVersion.setText(getString(R.string.version) + " " + packageInfo.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}