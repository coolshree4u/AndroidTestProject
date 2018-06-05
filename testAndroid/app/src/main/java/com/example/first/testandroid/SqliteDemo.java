package com.example.first.testandroid;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SqliteDemo extends AppCompatActivity {

    MyDBHelper myDBHelper;

    private EditText etId;

    private EditText etFirstName;
    private EditText etLastName;
    private EditText etAdress;
    private EditText etSalary;

    private Button btnInsert;
    private Button btnUpdate;
    private Button btnDelete;
    private Button btnShowAll;


    private TextView tvFinalData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_demo);
        myDBHelper=new MyDBHelper(SqliteDemo.this);
        init();

    }


    public void init() {

        etId = (EditText) findViewById(R.id.etEmail);
        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etAdress = (EditText) findViewById(R.id.etAddress);
        etSalary = (EditText) findViewById(R.id.etSalary);

        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnShowAll = (Button) findViewById(R.id.btnAll);

        btnInsert.setOnClickListener(mydbListner);
        btnUpdate.setOnClickListener(mydbListner);
        btnDelete.setOnClickListener(mydbListner);
        btnShowAll.setOnClickListener(mydbListner);


        tvFinalData = (TextView) findViewById(R.id.tvResult);
    }

    private View.OnClickListener mydbListner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnInsert:
                    System.out.println(Integer.parseInt(getValue(etId))+getValue(etFirstName)+getValue(etLastName)+getValue(etAdress)+Double.parseDouble(getValue(etSalary)));
                    long result=myDBHelper.insert(Integer.parseInt(getValue(etId)),getValue(etFirstName),getValue(etLastName),getValue(etAdress),Double.parseDouble(getValue(etSalary)));


                    if (result==-1){
                        Toast.makeText(SqliteDemo.this,"Some error Occured...",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(SqliteDemo.this,"Data inserted successfully, ID: "+result,Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.btnUpdate:
                    long resultUpdate=myDBHelper.update(Integer.parseInt(getValue(etId)),getValue(etFirstName),getValue(etLastName),getValue(etAdress),Double.parseDouble(getValue(etSalary)));

                    if (resultUpdate==0){
                        Toast.makeText(SqliteDemo.this,"Some error Occured...",Toast.LENGTH_SHORT).show();
                    }else if(resultUpdate==1){
                        Toast.makeText(SqliteDemo.this,"Data updated successfully, ID:"+resultUpdate,Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(SqliteDemo.this,"Multiple records updated ",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.btnDelete:
                    long resultDelete=myDBHelper.delete(Integer.parseInt(getValue(etId)));


                    if (resultDelete==0){
                        Toast.makeText(SqliteDemo.this,"Some error Occured...",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(SqliteDemo.this,"Data Deleted successfully",Toast.LENGTH_SHORT).show();
                    }
                    break;

                case R.id.btnAll:

                    StringBuffer finalData=new StringBuffer();
                    Cursor cursor=myDBHelper.getAllRecords();

                    for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
                        finalData.append(cursor.getInt(cursor.getColumnIndex(MyDBHelper.ID)));
                        finalData.append(" - ");
                        finalData.append(cursor.getString(cursor.getColumnIndex(MyDBHelper.FIRST_NAME)));
                        finalData.append(" - ");
                        finalData.append(cursor.getString(cursor.getColumnIndex(MyDBHelper.LAST_NAME)));
                        finalData.append(" - ");
                        finalData.append(cursor.getString(cursor.getColumnIndex(MyDBHelper.ADDRESS)));
                        finalData.append(" - ");
                        finalData.append(cursor.getDouble(cursor.getColumnIndex(MyDBHelper.SALARY)));
                        finalData.append("\n");
                    }

                    tvFinalData.setText(finalData.toString());
                    break;

            }

        }
    };

    private String getValue(EditText et) {

        return et.getText().toString().trim();
    }

    @Override
    protected void onStart() {
        super.onStart();
        myDBHelper.openDb();
    }

    @Override
    protected void onStop() {
        super.onStop();
        myDBHelper.closeDB();
    }
}
