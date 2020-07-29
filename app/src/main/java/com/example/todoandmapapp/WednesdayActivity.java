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

public class WednesdayActivity extends AppCompatActivity {

    private ArrayList<String> itemswed;
    private ArrayAdapter<String> itemsAdapterwed;
    private ListView lvItemswed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wednesday);

        lvItemswed = (ListView) findViewById(R.id.lvItemswed);
        itemswed = new ArrayList<String>();

        readItems();

        itemsAdapterwed = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, itemswed);
        lvItemswed.setAdapter(itemsAdapterwed);

        setupListViewListener();
    }

    private void setupListViewListener() {
        lvItemswed.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter, View item, int pos, long id) {
                        itemswed.remove(pos);
                        itemsAdapterwed.notifyDataSetChanged();
                        writeItems();
                        return true;
                    }

                });
    }

    public void onAddItem(View v) {
        EditText etNewItem = (EditText) findViewById(R.id.etNewItemwed);
        String itemText = etNewItem.getText().toString();
        itemsAdapterwed.add(itemText);
        etNewItem.setText("");
        writeItems();
    }

    private void readItems() {
        File filesDir = getFilesDir();
        File todowedFile = new File(filesDir, "todowed.txt");
        try {
            itemswed = new ArrayList<String>(FileUtils.readLines(todowedFile));
        } catch (IOException e) {
            itemswed = new ArrayList<String>();
        }
    }

    private void writeItems() {
        File filesDir = getFilesDir();
        File todowedFile = new File(filesDir, "todowed.txt");
        try {
            FileUtils.writeLines(todowedFile, itemswed);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}