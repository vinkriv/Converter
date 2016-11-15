package com.example.a10017404.converter;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Spinner s1;
    Spinner s2;
    Spinner s3;
    Spinner s4;
    EditText et1;
    EditText et2;
    EditText et3;
    EditText et4;
    ArrayList<String> portlist;
    double val;
    double newval;
    final String VAL = "";
    final String NEWVAL = "";


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putDouble(VAL,val);
        outState.putDouble(NEWVAL,newval);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState!=null){
            val=savedInstanceState.getDouble(VAL);
            newval=savedInstanceState.getDouble(NEWVAL);

        }
        s1 = (Spinner) findViewById(R.id.spinner1);
        s2 = (Spinner) findViewById(R.id.spinner2);
        s3 = (Spinner) findViewById(R.id.spinner3);
        s4 = (Spinner) findViewById(R.id.spinner4);
        et1 = (EditText) findViewById(R.id.editText1);
        et2 = (EditText) findViewById(R.id.editText2);
        et3 = (EditText) findViewById(R.id.editText3);
        et4 = (EditText) findViewById(R.id.editText4);
        portlist = new ArrayList<>();
        portlist.add("Galleon");
        portlist.add("Sickle");
        portlist.add("Knut");
        portlist.add("Dollar");
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, portlist);
            s1.setAdapter(arrayAdapter);
            s2.setAdapter(arrayAdapter);
        }
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            et1.setText(String.valueOf(val));
            et2.setText(String.valueOf(newval));
            et1.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {


                }


                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {


                }


                @Override
                public void afterTextChanged(Editable s) {
                    convert();
                }
            });
            s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    convert();
                }


                @Override
                public void onNothingSelected(AdapterView<?> parent) {


                }
            });
            s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    convert();
                }


                @Override
                public void onNothingSelected(AdapterView<?> parent) {


                }
            });
        }
    }


    public void convert() {
        if (!(et1.getText().toString().equals(""))) {
            val = Double.parseDouble(et1.getText().toString());
        } else val = 0;
        if (s1.getSelectedItem().toString().equals(s2.getSelectedItem().toString())) {
            newval = val;
        } else if (s1.getSelectedItem().toString().equals("Galleon")) {
            if (s2.getSelectedItem().toString().equals("Sickle")) {
                newval = val * 17;
            } else if (s2.getSelectedItem().toString().equals("Knut")) {
                newval = val * 29 * 17;
            } else if (s2.getSelectedItem().toString().equals("Dollar")) {
                newval = val * 7.35;
            }
        } else if (s1.getSelectedItem().toString().equals("Sickle")) {
            if (s2.getSelectedItem().toString().equals("Knut")) {
                newval = val * 29;
            } else if (s2.getSelectedItem().toString().equals("Galleon")) {
                newval = val / 17;
            } else if (s2.getSelectedItem().toString().equals("Dollar")) {
                newval = (val / 17) * 7.35;
            }
        } else if (s1.getSelectedItem().toString().equals("Knut")) {
            if (s2.getSelectedItem().toString().equals("Galleon")) {
                newval = (val / 29) / 17;
            } else if (s2.getSelectedItem().toString().equals("Sickle")) {
                newval = val / 29;
            } else if (s2.getSelectedItem().toString().equals("Dollar")) {
                newval = ((val / 29) / 17) * 7.35;
            }
        } else if (s1.getSelectedItem().toString().equals("Dollar")) {
            if (s2.getSelectedItem().toString().equals("Galleon")) {
                newval = val / 7.35;
            } else if (s2.getSelectedItem().toString().equals("Sickle")) {
                newval = (val / 7.35) * 17;
            } else if (s2.getSelectedItem().toString().equals("Knut")) {
                newval = (val / 7.35) * 17 * 29;
            }
        }
        newval=Math.floor(newval*10000) / 10000;
        et2.setText(String.valueOf(newval));
    }
}