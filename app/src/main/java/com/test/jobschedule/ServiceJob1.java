package com.test.jobschedule;

import android.app.job.JobService;
import android.app.job.JobParameters;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;

public class ServiceJob1 extends JobService {

    private final String TAG = "ServiceJob1";
    private ServiceJob1AsyncTask job1AsyncTask = new ServiceJob1AsyncTask();
    private boolean jobRunning = false;

    private class ServiceJob1AsyncTask extends AsyncTask<JobParameters, Void, JobParameters[]> {

        @Override
        protected void onPreExecute(){
            JobActivity.showToast(getApplicationContext(), "Start Job1 A11");
        }

        @Override
        protected JobParameters[] doInBackground(JobParameters... params) {

            Log.d(TAG, "TEST_BR doInBackground starting");

            int i;
            for (i=0; i < 20 && jobRunning; i++) {
                SystemClock.sleep(1000);
            }

            Log.d(TAG, "TEST_BR doInBackground finishing: i = " + i);

            return params;
        }

        protected void onPostExecute(JobParameters[] result) {
            // If we finished the job we can finish this service now.
            if (jobRunning) {
                // Call this to inform the JobManager you've finished executing.
                // This can be called from any thread.
                Log.d(TAG, "TEST_BR onPostExecute: calling jobFinished()");
                jobFinished(result[0], false);
                jobRunning = false;

                JobActivity.showToast(getApplicationContext(),
                        "Finishing Service Job1: onPostExecute()");
            }
        }
    }

    @Override
    // True : if your service needs to process the work (on a separate thread).
    // False: if there's no more work to be done for this job.
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG, "TEST_BR Service Job1: onStartJob()");

        job1AsyncTask.execute(params);
        jobRunning = true;

        return true;
    }

    @Override
    // True : to indicate to the JobManager whether you'd like to reschedule this job based on
    //        the retry criteria provided at job creation-time.
    // False: to drop the job. Regardless of the value returned, your job must stop executing.
    public boolean onStopJob(JobParameters params) {
        Log.d(TAG, "TEST_BR onStopJob");
        jobRunning = false;

        JobActivity.showToast(getApplicationContext(),
                "Finishing Service Job1: onStopJob()");

        return false;
    }
}
