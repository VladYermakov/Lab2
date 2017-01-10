package com.yermakov.xplatform.lesson7.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.yermakov.xplatform.lesson7.R;
import com.yermakov.xplatform.lesson7.helpers.FirebaseDatabaseHelper;

public class NewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        final EditText questionView = (EditText) findViewById(R.id.question);

        final EditText[] variantViews = new EditText[4];

        final CheckBox[] checkBoxes = new CheckBox[4];

        variantViews[0] = (EditText) findViewById(R.id.variant1);
        variantViews[1] = (EditText) findViewById(R.id.variant2);
        variantViews[2] = (EditText) findViewById(R.id.variant3);
        variantViews[3] = (EditText) findViewById(R.id.variant4);

        checkBoxes[0] = (CheckBox) findViewById(R.id.cb1);
        checkBoxes[1] = (CheckBox) findViewById(R.id.cb2);
        checkBoxes[2] = (CheckBox) findViewById(R.id.cb3);
        checkBoxes[3] = (CheckBox) findViewById(R.id.cb4);

        Button buttonSave = (Button) findViewById(R.id.button_send);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String question = questionView.getText().toString();

                String[] variants = new String[4];
                Boolean[] answers = new Boolean[4];

                for (int i = 0; i < 4; ++i) {
                    variants[i] = variantViews[i].getText().toString();
                    answers[i] = checkBoxes[i].isChecked();
                }


                FirebaseDatabaseHelper.addTest(question, variants, answers);

                questionView.setText(null);

                for (int i = 0; i < 4; ++i) {
                    variantViews[i].setText(null);
                    checkBoxes[i].setChecked(false);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
