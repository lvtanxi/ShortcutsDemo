package com.qiangyu.test.coupondisplayviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list);
        BaseAdapter arrayAdapter = new ArrayAdapter(this,R.layout.item_list,R.id.name,
                Arrays.asList("美食劵","活动劵","优惠劵","团购劵","外卖劵"));
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ShortcutsActvity.startShortcutsActvity(MainActivity.this);
            }
        });
    }
}
