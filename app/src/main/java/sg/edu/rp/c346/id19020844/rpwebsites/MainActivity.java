package sg.edu.rp.c346.id19020844.rpwebsites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    Spinner spnCat, spnSubCat;
    Button btnGo;

    ArrayList<String> alSubCategory;
    ArrayAdapter<String> aaSubCategory;

    String[][] sites = {
            {
                "https://www.rp.edu.sg",
                "https://www.rp.edu.sg/student-life",
            },
            {
                "https://www.rp.edu.sg/soi/full-time-diplomas/details/r47",
                "https://www.rp.edu.sg/soi/full-time-diplomas/detaild/r12",
            }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spnCat = findViewById(R.id.categorySpinner);
        spnSubCat = findViewById(R.id.subCategorySpinner);
        btnGo = findViewById(R.id.goButton);

        // initialise the ArrayList
        alSubCategory = new ArrayList<>();

        // create an ArrayAdapter using the default spinner layout and the ArrayList
        aaSubCategory = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, alSubCategory);

        // bind the ArrayAdpater to the spinner
        spnCat.setAdapter(aaSubCategory);

        spnCat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                aaSubCategory.clear();
                if (i == 0) {
                    String[] strSubCategory = getResources().getStringArray(R.array.category);
                    aaSubCategory.addAll(Arrays.asList(strSubCategory));
                }
                else {
                    String[] strSubCategory = getResources().getStringArray(R.array.category);
                    aaSubCategory.addAll(Arrays.asList(strSubCategory));
                }
                // Update the sub category spinner
                aaSubCategory.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String url = sites[spnCat.getSelectedItemPosition()][spnSubCat.getSelectedItemPosition()];

                Intent myIntent = new Intent(MainActivity.this, WebViewActivity.class);
                myIntent.putExtra("url", url);
                startActivity(myIntent);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        // get the data selected by the user
        int catPos = spnCat.getSelectedItemPosition();
        int subCatPos = spnSubCat.getSelectedItemPosition();

        // obtain the Default SharedPreferences object
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        // create a SharedPreferences Editor by calling edit()
        SharedPreferences.Editor prefEdit = prefs.edit();

        // write the keys and values into SharedPreferences via the Editor
        prefEdit.putInt("cat_position", catPos);
        prefEdit.putInt("subCat_position", subCatPos);

        // call commit() to save the changes made to the SharedPreferences
        prefEdit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // obtain the Default SharedPreferences object
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        // retrieve the saved data from the SharedPreferences
        int catPos = prefs.getInt("cat_position", 0);
        int subCatPos = prefs.getInt("subCat_position", 0);

        spnCat.setSelection(catPos);

        alSubCategory.clear();

        // obtain  the string array of the sub category
        if (catPos == 0) {
            String[] strSubCat = getResources().getStringArray(R.array.subCategory_rp);
            alSubCategory.addAll(Arrays.asList(strSubCat));
        }
        else if (catPos == 1) {
            String[] strSubCat = getResources().getStringArray(R.array.subCategory_soi);
            alSubCategory.addAll(Arrays.asList(strSubCat));
        }
        spnSubCat.setSelection(subCatPos);

        aaSubCategory.notifyDataSetChanged();
    }
}
