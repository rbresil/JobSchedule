package com.test.jobschedule;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootCompleteReceiver extends BroadcastReceiver {

    private final String TAG = "BootCompleteReceiver";
    private final int JOB_BOOT_COMPLETED = 100;
    private ComponentName jobBootCompletedName;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            Log.d(TAG, "TEST_BR ACTION_BOOT_COMPLETED received");

            jobBootCompletedName = new ComponentName(context, BootCompletedService.class);

            JobInfo jobInfo = new JobInfo.Builder(JOB_BOOT_COMPLETED, jobBootCompletedName)
                    .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                    //.setMinimumLatency(1000)
                    .build();

            JobScheduler scheduler = (JobScheduler)
                    context.getSystemService(context.JOB_SCHEDULER_SERVICE);

            int result = scheduler.schedule(jobInfo);
            if (result == JobScheduler.RESULT_SUCCESS) {
                JobActivity.showToast(context,
                        "BootCompleteReceiver.onReceive");

                Log.d(TAG, "TEST_BR BootCompleteReceiver scheduled successfully!");
            }
        }
    }
}
