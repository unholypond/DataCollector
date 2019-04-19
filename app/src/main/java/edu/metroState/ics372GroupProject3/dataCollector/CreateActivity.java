package edu.metroState.ics372GroupProject3.dataCollector;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Date;

public class CreateActivity extends AppCompatActivity {
    final String TAG = "create Activity Log";
    Button submitButton;
    Button cancelButton;
    EditText siteID;
    EditText readingType;
    EditText readingUnit;
    EditText readingID;
    EditText readingValue;
    ProgramState createData;
    AlertDialog.Builder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        createData = (ProgramState)getApplication();
        Log.i(TAG, Arrays.toString(createData.getCurrentProgramRecord().toArray()));
        TextView studyId = findViewById(R.id.currentStudyID);
        TextView studyName = findViewById(R.id.currentStudyName);
        siteID = findViewById(R.id.siteIdField);
        readingType = findViewById(R.id.readingTypeField);
        readingUnit = findViewById(R.id.readingUnitField);
        readingID = findViewById(R.id.readingIDField);
        readingValue = findViewById(R.id.readingValueField);
        cancelButton = findViewById(R.id.cancel);
        submitButton = findViewById(R.id.submitButton);
        builder = new AlertDialog.Builder(this,R.style.Dialog_NoActionBar_);
        Study currentStudy;

        /*
        Display the current study ID and Name to the user
         */
        currentStudy = createData.getCurrentProgramRecord().get(0);
        createData.setCurrentProgramStudy(currentStudy);
        Log.i(TAG, currentStudy.toString());
        studyId.setText(currentStudy.getStudyID());
        studyName.setText(currentStudy.getStudyName());

        /*
         * cancel create reading action and
         * return to the main activity
         */
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancelIntent =  new Intent(getApplicationContext(), MainActivity.class);
                startActivity(cancelIntent);
            }
        });

        /*
         * submit the reading data to the activity that
         * will handle it
         */
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*
                 * call the item factory to create item
                 */
                createData.setCurrentProgramSite(siteID.getText().toString());
                String id = siteID.getText().toString();
                String type =  readingType.getText().toString();
                String unit = readingUnit.getText().toString();
                String rId = readingID.getText().toString();
                double value = Double.parseDouble(readingValue.getText().toString());
                Date date = new Date();
                Long myDate = date.getTime();
                try {
                    createData.createSite(id);
                    createData.createReading(id, type, unit,rId, value, myDate);
                    Log.i(TAG, createData.getCurrentProgramStudy().toString());
                    builder.setTitle(R.string.createDialog_title)
                            .setMessage(R.string.createDialog_message)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    siteID.setText("");
                                    readingType.setText("");
                                    readingUnit.setText("");
                                    readingID.setText("");
                                    readingValue.setText("");
                                    siteID.requestFocus();
                                    Toast.makeText(getApplicationContext(),"Create new reading selected",
                                    Toast.LENGTH_LONG).show();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //  Action for 'NO' Button
                                    Intent displayIntent =  new Intent(getApplicationContext(),
                                            DisplayActivity.class);
                                    startActivity(displayIntent);
                                    dialog.cancel();
                                }
                            }).create().show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
