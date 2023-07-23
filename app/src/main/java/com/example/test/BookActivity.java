package com.example.test;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Calendar;

public class BookActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    Spinner spncn;
    TextView edtDate1;
    TextView edtDate2;
    EditText edtsoluong;

    public static TextView tvmaphong, tvloai;
    Button btndat;
    BottomNavigationView bottomNavigationView;
    ImageButton btnback;
    RoomDatabase db;
    public  static final  String SHARE_NAME = "sharepre";
    public static final String KEY_EMAIL = "email";
    public static SharedPreferences sharedPreferences;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        sharedPreferences = getSharedPreferences(SHARE_NAME, MODE_PRIVATE);
        String email = sharedPreferences.getString(KEY_EMAIL, null);
        db = new RoomDatabase(BookActivity.this);

        tvmaphong = findViewById(R.id.tvmaphong);
        tvloai = findViewById(R.id.tvcate);
        spncn = findViewById(R.id.spnchinhanh);
        btnback = findViewById(R.id.btnback);
        edtDate1 = findViewById(R.id.date1);
        edtsoluong=findViewById(R.id.edtsonguoi);
        edtDate2 = findViewById(R.id.date2);
        btndat = findViewById(R.id.btnBook);
        bottomNavigationView = findViewById(R.id.bottomnavibook);
        bottomNavigationView.setSelectedItemId(R.id.book);
        RoomAdapter adapter = new RoomAdapter(null, BookActivity.this);


        Intent i = getIntent();
        Bundle b = i.getExtras();



        if (b == null) {
            b = null;
        }
        else {
            String type = b.getString("typeRoom");
            long idRoom = b.getLong("idRoom");
            if (tvmaphong.getText().equals("null")) {
                tvloai.setText(type);
            } else
                tvloai.setText("null");

            if (tvmaphong.getText().equals("null"))
                tvmaphong.setText(String.valueOf(idRoom));
        }



        btndat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long myID = getMyID(email);
                String ngaydat = edtDate1.getText().toString();
                String ngaytra = edtDate2.getText().toString();
                int songuoi = Integer.parseInt(edtsoluong.getText().toString());
                String chinhanhdat = spncn.getSelectedItem().toString();
                String loaiphongdat=tvloai.getText().toString();
                long roomid= Long.parseLong(tvmaphong.getText().toString());
                insertRoom(ngaydat, ngaytra, songuoi, chinhanhdat, loaiphongdat, roomid, myID);
                convertStatus(roomid);
                startActivity(new Intent(BookActivity.this,lichsudatphong.class));
            }
        });




        // SetData();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), TrangChuActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.book:
                        return true;
                    case R.id.acc:
                        startActivity(new Intent(getApplicationContext(), AccountActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

        edtDate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog1();

            }
        });
        edtDate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog2();
            }
        });
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), TrangChuActivity.class));
            }
        });


        String[] chinhanh = new String[]{"Chi nhánh quận 1", "Chi nhánh quận 3"};
        ArrayAdapter adaptercn = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, chinhanh);
        spncn.setAdapter(adaptercn);


    }


    public void convertStatus(long idRoom){
        SQLiteDatabase database = db.helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.COT_STATUS, 1);

        database.update(DBHelper.TEN_BANG_PHONG, contentValues, DBHelper.COT_ROOMID + " = ? ", new String[]{String.valueOf(idRoom)});
    }

    private void openDialog1() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String thismonth = null;
                if (month + 1 < 10) {
                    thismonth = "0" + String.valueOf(month + 1);
                }
                edtDate1.setText(String.valueOf(year) + "-" + thismonth + "-" + String.valueOf(day));
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    private void openDialog2() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String thismonth = null;
                if (month + 1 < 10) {
                    thismonth = "0" + String.valueOf(month + 1);
                }
                edtDate2.setText(String.valueOf(year) + "-" + thismonth + "-" + String.valueOf(day));
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

    }



    public void insertRoom(String ngaydat, String ngaytra, int soluong,String chinhanh, String loaiphong, long room_id, long userbook){

        SQLiteDatabase database = db.helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.COT_NGAYDAT, ngaydat);
        contentValues.put(DBHelper.COT_NGAYTRA, ngaytra);
        contentValues.put(DBHelper.COT_SOLUONG, soluong);
        contentValues.put(DBHelper.COT_CHINHANHDAT, chinhanh);

        contentValues.put(DBHelper.COT_LOAIPHONGDAT, loaiphong);
        contentValues.put(DBHelper.COT_ROOM_ID, room_id);
        contentValues.put(DBHelper.COT_USER_ID_BOOK, userbook);

        database.insert(DBHelper.BANG_DAT_PHONG, null, contentValues);

    }

    public long getMyID(String email){
        SQLiteDatabase database = db.helper.getReadableDatabase();
        Cursor cursor = database.query(DBHelper.TEN_BANG_USER, null, null , null, null, null, null);

        long id = 0;
        while (cursor.moveToNext()){
            if(cursor.getString(2).equals(email))
                id = cursor.getLong(0);
        }

        return  id;
    }


}