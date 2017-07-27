package com.aliakseipilko.metarutils.demo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;

import com.aliakseipilko.metarutils.MetarDecodeException;
import com.aliakseipilko.metarutils.MetarUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.test_one_btn)
    Button builtInTestBtn;
    @BindView(R.id.test_two_btn)
    Button USTestCaseBtn;
    @BindView(R.id.metar_et)
    EditText customMetarET;
    @BindView(R.id.decode_metar_btn)
    Button decodeCustomMetarBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.decode_metar_btn)
    public void decodeCustomMetar() {
        String metar = customMetarET.getText().toString().trim();
        if (metar.length() < 1) {
            customMetarET.setError("Enter a METAR");
        } else {
            decodeMetar(metar);
        }
    }

    @OnClick(R.id.test_one_btn)
    public void decodeSetUKMetar() {
        final String metar = getResources().getString(R.string.uk_metar);
        new AlertDialog.Builder(this)
                .setTitle("METAR Test")
                .setMessage("Decoding this METAR:\n" + metar)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        decodeMetar(metar);
                    }
                })
                .setNegativeButton("No", null)
                .show();

    }

    @OnClick(R.id.test_two_btn)
    public void decodeSetUSMetar() {
        final String metar = getString(R.string.us_metar);
        new AlertDialog.Builder(this)
                .setTitle("METAR Test")
                .setMessage("Decoding this METAR:\n" + metar)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        decodeMetar(metar);
                    }
                })
                .setNegativeButton("No", null)
                .show();

    }

    @OnClick(R.id.test_three_btn)
    public void decodeSetUKMilMetar() {
        final String metar = getString(R.string.uk_mil_metar);
        new AlertDialog.Builder(this)
                .setTitle("METAR Test")
                .setMessage("Decoding this METAR:\n" + metar)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        decodeMetar(metar);
                    }
                })
                .setNegativeButton("No", null)
                .show();

    }

    @OnClick(R.id.test_four_btn)
    public void decodeSetRUSMetar() {
        final String metar = getString(R.string.rus_metar);
        new AlertDialog.Builder(this)
                .setTitle("METAR Test")
                .setMessage("Decoding this METAR:\n" + metar)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        decodeMetar(metar);
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    public void decodeMetar(String metar) {
        MetarUtils metarUtils = new MetarUtils();
        String decode = "";
        try {
            decode = MetarUtils.decodeMetarToString(metar);
        } catch (MetarDecodeException e) {
            e.printStackTrace();
        }
        displayResult(decode);
    }

    public void displayResult(String result) {
        new AlertDialog.Builder(this)
                .setTitle("Result")
                .setMessage(result)
                .setCancelable(true)
                .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .show();
    }
}
