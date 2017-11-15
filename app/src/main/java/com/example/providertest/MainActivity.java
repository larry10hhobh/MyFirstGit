package com.example.providertest;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private String newId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addData = (Button) findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 添加数据
                Uri uri = Uri.parse("content://com.example.databasetest.provider/book");
                ContentValues values = new ContentValues();
                values.put("name", "名侦探柯南");
                values.put("author", "青山");
                values.put("pages", 9980);
                values.put("price", 699.9);
                Uri newUri = getContentResolver().insert(uri, values);
                newId = newUri.getPathSegments().get(1);
            }
        });

        Button queryData = (Button) findViewById(R.id.query_data);
        queryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 查询数据
                Uri uri = Uri.parse("content://com.example.databasetest.provider/book");
                Cursor cursor = getContentResolver().query(uri, null,
                        null, null, null);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.d("MainActvity", "book name is " + name);
                        Log.d("MainActvity", "book name is " + author);
                        Log.d("MainActvity", "book pages is " + pages);
                        Log.d("MainActvity", "book price is " + price);
                    }
                    cursor.close();
                }
            }
        });

        Button updataData = (Button) findViewById(R.id.update_data);
        updataData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 更新数据
                Uri uri = Uri.parse("content://com.example.databasetest.provider/book/"+newId);
                ContentValues values = new ContentValues();
                values.put("name","海贼王");
                values.put("author","尾田");
                values.put("pages",1000);
                values.put("price",666.6);
                getContentResolver().update(uri,values,null,null);
            }
        });

        Button deleteData = (Button) findViewById(R.id.delete_data);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 删除数据
                Uri uri  = Uri.parse("content://com.example.databasetest.provider/book/"+newId);
                getContentResolver().delete(uri,null,null);
            }
        });
    }
}
