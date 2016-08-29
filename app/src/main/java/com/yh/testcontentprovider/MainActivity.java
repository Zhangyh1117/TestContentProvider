package com.yh.testcontentprovider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button insertButton;
    private Button deleteButton;
    private Button updateButton;
    private Button queryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insertButton = (Button)findViewById(R.id.insert_button);
        deleteButton = (Button)findViewById(R.id.delete_button);
        updateButton = (Button)findViewById(R.id.update_button);
        queryButton = (Button)findViewById(R.id.query_button);

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insert();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete();
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
            }
        });

        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                query();
            }
        });
    }

    public void insert(){
        //访问内容提供者的步骤：
        //1.需要一个内容解析者
        ContentResolver contentResolver = getContentResolver();
        //使用 content:// + 授权路径
        Uri uri = Uri.parse("content://com.yh.contentprovider.DatabaseProveder/student");
        ContentValues contentValues = new ContentValues();
        contentValues.put("name","王五");
        contentValues.put("address","广东");
        contentResolver.insert(uri,contentValues);
        Log.d("ContentProvider", "insert");
    }

    public  void delete(){
        ContentResolver contentResolver = getContentResolver();
        //实现删除单行记录，如果要删除多行记录content://com.yh.contentprovider.DatabaseProveder/student
        Uri uri = Uri.parse("content://com.yh.contentprovider.DatabaseProveder/student/1");
        contentResolver.delete(uri,null,null);
        Log.d("ContentProvider", "delete");
    }

    public void update(){
        ContentResolver contentResolver= getContentResolver();

        Uri uri = Uri.parse("content://com.yh.contentprovider.DatabaseProveder/student/2");
        ContentValues contentValues = new ContentValues();
        contentValues.put("name","李四");
        contentValues.put("address","上海");
        contentResolver.update(uri,contentValues,null,null);
        Log.d("ContentProvider", "update");
    }

    public void query(){
        ContentResolver contentResolver= getContentResolver();

        //查询单条记录:content://com.yh.contentprovider.DatabaseProveder/student/2
        //查询多条记录:content://com.yh.contentprovider.DatabaseProveder/student
        Uri uri = Uri.parse("content://com.yh.contentprovider.DatabaseProveder/student/2");
        //select * from student where id = 2
        Cursor cursor = contentResolver.query(uri,null,null,null,null) ;
        while (cursor.moveToNext()){
            Log.d("ContentProvider", cursor.getString(cursor.getColumnIndex("name")));
        }
    }
}
