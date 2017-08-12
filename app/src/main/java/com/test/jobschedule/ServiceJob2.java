package com.test.jobschedule;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

public class ServiceJob2 extends JobService {
    private final String TAG = "ServiceJob2";

    @Override
    // True : if your service needs to process the work (on a separate thread).
    // False: if there's no more work to be done for this job.
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG, "TEST_BR onStartJob");

        JobActivity.showToast(getApplicationContext(), "Starting Service Job2: onStartJob()");

        return false;
    }

    @Override
    // True : to indicate to the JobManager whether you'd like to reschedule this job based on
    //        the retry criteria provided at job creation-time.
    // False: to drop the job. Regardless of the value returned, your job must stop executing.
    public boolean onStopJob(JobParameters params) {
        Log.d(TAG, "TEST_BR onStopJob");

        JobActivity.showToast(getApplicationContext(), "Finishing Service Job2: onStopJob()");

        return false;
    }
}
