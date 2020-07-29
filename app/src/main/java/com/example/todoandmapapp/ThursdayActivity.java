package com.example.todoandmapapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ThursdayActivity extends AppCompatActivity {

    private ArrayList<String> itemsthu;
    private ArrayAdapter<String> itemsAdapterthu;
    private ListView lvItemsthu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thursday);

        lvItemsthu = (ListView) findViewById(R.id.lvItemsthu);
        itemsthu = new ArrayList<String>();

        readItems();

        itemsAdapterthu = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, itemsthu);
        lvItemsthu.setAdapter(itemsAdapterthu);

        setupListViewListener();
    }

    private void setupListViewListener() {
        lvItemsthu.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter, View item, int pos, long id) {
                        itemsthu.remove(pos);
                        itemsAdapterthu.notifyDataSetChanged();
                        writeItems();
                        return true;
                    }

                });
    }

    public void onAddItem(View v) {
        EditText etNewItem = (EditText) findViewById(R.id.etNewItemthu);
        String itemText = etNewItem.getText().toString();
        itemsAdapterthu.add(itemText);
        etNewItem.setText("");
        writeItems();
    }

    private void readItems() {
        File filesDir = getFilesDir();
        File todothuFile = new File(filesDir, "todothu.txt");
        try {
            itemsthu = new ArrayList<String>(FileUtils.readLines(todothuFile));
        } catch (IOException e) {
            itemsthu = new ArrayList<String>();
        }
    }

    private void writeItems() {
        File filesDir = getFilesDir();
        File todothuFile = new File(filesDir, "todothu.txt");
        try {
            FileUtils.writeLines(todothuFile, itemsthu);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}