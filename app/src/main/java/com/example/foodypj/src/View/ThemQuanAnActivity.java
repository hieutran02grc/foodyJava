package com.example.foodypj.src.View;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodypj.Model.ThucDonModel;
import com.example.foodypj.Model.TienIchModel;
import com.example.foodypj.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ThemQuanAnActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener{

    Button btnGioMoCua,btnGioDongCua;
    Spinner spinnerKhuVuc,spinnerThucDon;
    String gioMoCua,gioDongCua;
    LinearLayout khungTienIch;

    ArrayAdapter<String> adapterKhuVuc,adapterThucDon;


    List<ThucDonModel> thucDonModelList;
    List<String> khuVucList,thucDonList;
    List<String> selectedTienIchList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_themquanan);
        btnGioMoCua = (Button) findViewById(R.id.btnGioMoCua);
        btnGioDongCua = (Button) findViewById(R.id.btnGioDongCua);
        spinnerKhuVuc = (Spinner) findViewById(R.id.spinnerKhuVuc);
        spinnerThucDon = (Spinner) findViewById(R.id.spinnerThucDon);
        khungTienIch = (LinearLayout) findViewById(R.id.khungTienIch);

        thucDonModelList = new ArrayList<>();
        khuVucList = new ArrayList<>();
        thucDonList = new ArrayList<>();
        selectedTienIchList = new ArrayList<String>();

        adapterKhuVuc = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,khuVucList);
        spinnerKhuVuc.setAdapter(adapterKhuVuc);
        adapterKhuVuc.notifyDataSetChanged();

        adapterThucDon   = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,thucDonList);
        spinnerThucDon.setAdapter(adapterThucDon);
        adapterThucDon.notifyDataSetChanged();

        LayDanhSachKhuVuc();
        LayDanhSachThucDon();
        LayDanhSachTienIch();

        btnGioMoCua.setOnClickListener(this);
        btnGioDongCua.setOnClickListener(this);
        spinnerKhuVuc.setOnItemSelectedListener(this);
        spinnerThucDon.setOnItemSelectedListener(this);
    }
    private void LayDanhSachThucDon(){
        FirebaseDatabase.getInstance().getReference().child("thucdons").addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    ThucDonModel thucDonModel = new ThucDonModel();
                    String key = snapshot.getKey();
                    String value = snapshot.getValue(String.class);

                    thucDonModel.setTenthucdon(value);
                    thucDonModel.setMathucdon(key);

                    thucDonModelList.add(thucDonModel);
                    thucDonList.add(value);
                }
                adapterThucDon.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }

    private void LayDanhSachKhuVuc(){
        FirebaseDatabase.getInstance().getReference().child("khuvucs").addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String tenKhuVuc = snapshot.getKey();
                    khuVucList.add(tenKhuVuc);
                }
                adapterKhuVuc.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }

    private void LayDanhSachTienIch(){
        FirebaseDatabase.getInstance().getReference().child("quanlytienichs").addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String maTienIch = snapshot.getKey();
                    TienIchModel tienIchModel = snapshot.getValue(TienIchModel.class);
                    tienIchModel.setMaTienIch(maTienIch);

                    CheckBox checkBox = new CheckBox(ThemQuanAnActivity.this);
                    checkBox.setText(tienIchModel.getTentienich());
                    checkBox.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
                    checkBox.setTag(maTienIch);
                    checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            String maTienIch = buttonView.getTag().toString();
                            if(isChecked){
                                selectedTienIchList.add(maTienIch);
                            }else{
                                selectedTienIchList.remove(maTienIch);
                            }
                        }
                    });
                    khungTienIch.addView(checkBox);
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }
    @Override
    public void onClick(final View v) {
        Calendar calendar = Calendar.getInstance();
        int gio = calendar.get(Calendar.HOUR_OF_DAY);
        int phut = calendar.get(Calendar.MINUTE);
        switch (v.getId()){
            case R.id.btnGioDongCua:

                TimePickerDialog dongCuaTimePickerDialog = new TimePickerDialog(ThemQuanAnActivity.this, new TimePickerDialog.OnTimeSetListener(){
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        gioDongCua = hourOfDay + " : " + minute;
                        ((Button)v).setText(gioDongCua);
                    }
                },gio,phut, true);
                dongCuaTimePickerDialog.show();
                break;

            case R.id.btnGioMoCua:

                TimePickerDialog moCuaTimePickerDialog = new TimePickerDialog(ThemQuanAnActivity.this, new TimePickerDialog.OnTimeSetListener(){
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        gioMoCua = hourOfDay + " : " + minute;
                        ((Button)v).setText(gioMoCua);
                    }
                },gio,phut, true);
                moCuaTimePickerDialog.show();
                break;
        }
    }

    private void DateTimePickerDialog() {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
