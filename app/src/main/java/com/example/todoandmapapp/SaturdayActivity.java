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

public class SaturdayActivity extends AppCompatActivity {

    private ArrayList<String> itemssat;
    private ArrayAdapter<String> itemsAdaptersat;
    private ListView lvItemssat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saturday);

        lvItemssat = (ListView) findViewById(R.id.lvItemssat);
        itemssat = new ArrayList<String>();

        readItems();

        itemsAdaptersat = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, itemssat);
        lvItemssat.setAdapter(itemsAdaptersat);

        setupListViewListener();
    }

    private void setupListViewListener() {
        lvItemssat.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter, View item, int pos, long id) {
                        itemssat.remove(pos);
                        itemsAdaptersat.notifyDataSetChanged();
                        writeItems();
                        return true;
                    }

                });
    }

    public void onAddItem(View v) {
        EditText etNewItem = (EditText) findViewById(R.id.etNewItemsat);
        String itemText = etNewItem.getText().toString();
        itemsAdaptersat.add(itemText);
        etNewItem.setText("");
        writeItems();
    }

    private void readItems() {
        File filesDir = getFilesDir();
        File todosatFile = new File(filesDir, "todosat.txt");
        try {
            itemssat = new ArrayList<String>(FileUtils.readLines(todosatFile));
        } catch (IOException e) {
            itemssat = new ArrayList<String>();
        }
    }

    private void writeItems() {
        File filesDir = getFilesDir();
        File todosatFile = new File(filesDir, "todosat.txt");
        try {
            FileUtils.writeLines(todosatFile, itemssat);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}