package edu.metroState.ics372GroupProject3.dataCollector;

import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;

public class CreateActivity extends AppCompatActivity {
    final String TAG = "create Activity Log";
    Button submitButton;
    Button cancelButton;
    EditText siteID;
    EditText readingType;
    EditText readingUnit;
    EditText readingID;
    EditText readingValue;
    EditText readingDate;
    ProgramState createData;

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
        readingDate = findViewById(R.id.readingDateField);
        cancelButton = findViewById(R.id.cancel);
        submitButton = findViewById(R.id.submitButton);

        /*
        Display the current study ID and Name to the user
         */
        final Study currentStudy = createData.getCurrentProgramRecord().get(0);
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
                Intent cancelIntent =  new Intent(CreateActivity.this, MainActivity.class);
                startActivity(cancelIntent);
            }
        });

        /**
         * submit the reading data to the activity that
         * will handle it
         */
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent submitReadingIntent = new Intent(getApplicationContext(), DisplayActivity.class);
                submitReadingIntent.putExtra("the created item", "new reading");
                /**
                 * call the item factory to create item
                 */
                //Site cSite = new Site(siteID.getText().toString());
                createData.setCurrentProgramSite(siteID.getText().toString());
                String id = siteID.getText().toString();
                String type =  readingType.getText().toString();
                String unit = readingUnit.getText().toString();
                String rId = readingID.getText().toString();
                double value = Double.parseDouble(readingValue.getText().toString());
                Long date = Long.parseLong(readingDate.getText().toString());
                try {
                    createData.createSite(id);
                    createData.createReading(id, type, unit,rId, value, date);
                    Log.i(TAG, createData.getCurrentProgramSite().toString());
                    Log.i(TAG, createData.getCurrentProgramStudy().toString());
//                    AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
//                            builder.setTitle("Confirmation")
//                            .setMessage("Reading Created! Do you want to create another one?")
//                            .setCancelable(true)
//                            .setNegativeButton("Cancel", public );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
