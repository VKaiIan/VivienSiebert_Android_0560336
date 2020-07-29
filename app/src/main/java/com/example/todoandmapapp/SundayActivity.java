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

public class SundayActivity extends AppCompatActivity {

    private ArrayList<String> itemssun;
    private ArrayAdapter<String> itemsAdaptersun;
    private ListView lvItemssun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sunday);

        lvItemssun = (ListView) findViewById(R.id.lvItemssun);
        itemssun = new ArrayList<String>();

        readItems();

        itemsAdaptersun = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, itemssun);
        lvItemssun.setAdapter(itemsAdaptersun);

        setupListViewListener();
    }

    private void setupListViewListener() {
        lvItemssun.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter, View item, int pos, long id) {
                        itemssun.remove(pos);
                        itemsAdaptersun.notifyDataSetChanged();
                        writeItems();
                        return true;
                    }

                });
    }

    public void onAddItem(View v) {
        EditText etNewItem = (EditText) findViewById(R.id.etNewItemsun);
        String itemText = etNewItem.getText().toString();
        itemsAdaptersun.add(itemText);
        etNewItem.setText("");
        writeItems();
    }

    private void readItems() {
        File filesDir = getFilesDir();
        File todosunFile = new File(filesDir, "todosun.txt");
        try {
            itemssun = new ArrayList<String>(FileUtils.readLines(todosunFile));
        } catch (IOException e) {
            itemssun = new ArrayList<String>();
        }
    }

    private void writeItems() {
        File filesDir = getFilesDir();
        File todosunFile = new File(filesDir, "todosun.txt");
        try {
            FileUtils.writeLines(todosunFile, itemssun);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}