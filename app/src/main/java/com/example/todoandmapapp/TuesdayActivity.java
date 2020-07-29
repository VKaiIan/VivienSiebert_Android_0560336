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

public class TuesdayActivity extends AppCompatActivity {

    private ArrayList<String> itemstue;
    private ArrayAdapter<String> itemsAdaptertue;
    private ListView lvItemstue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuesday);

        lvItemstue = (ListView) findViewById(R.id.lvItemstue);
        itemstue = new ArrayList<String>();

        readItems();

        itemsAdaptertue = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, itemstue);
        lvItemstue.setAdapter(itemsAdaptertue);

        setupListViewListener();
    }

    private void setupListViewListener() {
        lvItemstue.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter, View item, int pos, long id) {
                        itemstue.remove(pos);
                        itemsAdaptertue.notifyDataSetChanged();
                        writeItems();
                        return true;
                    }

                });
    }

    public void onAddItem(View v) {
        EditText etNewItem = (EditText) findViewById(R.id.etNewItemtue);
        String itemText = etNewItem.getText().toString();
        itemsAdaptertue.add(itemText);
        etNewItem.setText("");
        writeItems();
    }

    private void readItems() {
        File filesDir = getFilesDir();
        File todotueFile = new File(filesDir, "todotue.txt");
        try {
            itemstue = new ArrayList<String>(FileUtils.readLines(todotueFile));
        } catch (IOException e) {
            itemstue = new ArrayList<String>();
        }
    }

    private void writeItems() {
        File filesDir = getFilesDir();
        File todotueFile = new File(filesDir, "todotue.txt");
        try {
            FileUtils.writeLines(todotueFile, itemstue);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}