package com.test.jobschedule;

import android.content.Context;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.ComponentName;

import android.support.v7.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.util.Log;

import android.app.job.JobInfo;
import android.app.job.JobInfo.Builder;
import android.app.job.JobScheduler;

import android.net.Uri;

import static android.app.job.JobInfo.TriggerContentUri.FLAG_NOTIFY_FOR_DESCENDANTS;


public class JobActivity extends AppCompatActivity {
    private final String TAG = "JobActivity";

    private final int JOB1_ID = 1;
    private final int JOB2_ID = 2;

    private ComponentName job1Name;
    private ComponentName job2Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);

        final Button job1 = (Button) findViewById(R.id.button_job1);
        job1.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
//                CharSequence text = "Start Job1 A6";
//                int duration = Toast.LENGTH_SHORT;
//
//                Toast toast = Toast.makeText(getApplicationContext(), text, duration);
//                toast.show();
                showToast(getApplicationContext(), "Start Job1 A1");
                createServiceJob1();
            }
        });

        final Button job2 = (Button) findViewById(R.id.button_job2);
        job2.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
               // createServiceJob2();
            }
        });
    }

    private void createServiceJob1() {
        job1Name = new ComponentName(getApplication(), ServiceJob1.class);
        JobInfo jobInfo = new JobInfo.Builder(JOB1_ID, job1Name)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .build();

        JobScheduler scheduler = (JobScheduler)
                getApplication().getSystemService(getApplicationContext().JOB_SCHEDULER_SERVICE);
        int result = scheduler.schedule(jobInfo);
        if (result == JobScheduler.RESULT_SUCCESS) {
            Log.d(TAG, "TEST_BR Job1 scheduled successfully!");
        }
    }

    public static void showToast(Context context, CharSequence text) {
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
