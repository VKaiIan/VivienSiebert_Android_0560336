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

public class FridayActivity extends AppCompatActivity {

    private ArrayList<String> itemsfri;
    private ArrayAdapter<String> itemsAdapterfri;
    private ListView lvItemsfri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friday);

        lvItemsfri = (ListView) findViewById(R.id.lvItemsfri);
        itemsfri = new ArrayList<String>();

        readItems();

        itemsAdapterfri = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, itemsfri);
        lvItemsfri.setAdapter(itemsAdapterfri);

        setupListViewListener();
    }

    private void setupListViewListener() {
        lvItemsfri.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter, View item, int pos, long id) {
                        itemsfri.remove(pos);
                        itemsAdapterfri.notifyDataSetChanged();
                        writeItems();
                        return true;
                    }

                });
    }

    public void onAddItem(View v) {
        EditText etNewItem = (EditText) findViewById(R.id.etNewItemfri);
        String itemText = etNewItem.getText().toString();
        itemsAdapterfri.add(itemText);
        etNewItem.setText("");
        writeItems();
    }

    private void readItems() {
        File filesDir = getFilesDir();
        File todofriFile = new File(filesDir, "todofri.txt");
        try {
            itemsfri = new ArrayList<String>(FileUtils.readLines(todofriFile));
        } catch (IOException e) {
            itemsfri = new ArrayList<String>();
        }
    }

    private void writeItems() {
        File filesDir = getFilesDir();
        File todofriFile = new File(filesDir, "todofri.txt");
        try {
            FileUtils.writeLines(todofriFile, itemsfri);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}