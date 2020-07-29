package com.example.todoandmapapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import org.apache.commons.io.FileUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MondayActivity extends AppCompatActivity {

    private ArrayList<String> itemsmo;
    private ArrayAdapter<String> itemsAdaptermo;
    private ListView lvItemsmo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monday);

        lvItemsmo = (ListView) findViewById(R.id.lvItemsmo);
        itemsmo = new ArrayList<String>();

        readItems();

        itemsAdaptermo = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, itemsmo);
        lvItemsmo.setAdapter(itemsAdaptermo);

        setupListViewListener();
    }

    private void setupListViewListener() {
        lvItemsmo.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter, View item, int pos, long id) {
                        itemsmo.remove(pos);
                        itemsAdaptermo.notifyDataSetChanged();
                        writeItems();
                        return true;
                    }

                });
    }

    public void onAddItem(View v) {
        EditText etNewItem = (EditText) findViewById(R.id.etNewItemmo);
        String itemText = etNewItem.getText().toString();
        itemsAdaptermo.add(itemText);
        etNewItem.setText("");
        writeItems();
    }

    private void readItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            itemsmo = new ArrayList<String>(FileUtils.readLines(todoFile));
        } catch (IOException e) {
            itemsmo = new ArrayList<String>();
        }
    }

    private void writeItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            FileUtils.writeLines(todoFile, itemsmo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

