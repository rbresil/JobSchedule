package com.test.jobschedule;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.JobIntentService;
import android.util.Log;


public class SimpleJobIntentService extends JobIntentService {
    private final String TAG = "SimpleJobIntentService";

    static final int JOB_ID = 1000;

    // Get the main thread UI:
    // Handler handler = new Handler(Looper.getMainLooper());
    // If the handler itself is created from the main (UI) thread the argument
    // can be omitted for brevity:
    Handler mHandler = new Handler();

    public SimpleJobIntentService() {
    }

    /**
     * Convenience method for enqueuing work in to this service.
     */
    static void enqueueWork(Context context, Intent work) {
        enqueueWork(context, SimpleJobIntentService.class, JOB_ID, work);
    }

    @Override
    protected void onHandleWork(Intent intent) {
        Log.d(TAG, "TEST_BR onHandleWork() enter. Thread name is: " +  Thread.currentThread().getName());

        String text = "onHandleWork() enter";
        String label = intent.getStringExtra("LabelTeste");
        if (label != null) {
            text = text + ":" + label;
        }

        Thread.currentThread().getName();

        sendToast("onHandleWork()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "TEST_BR onDestroy() enter");
    }

    private void sendToast(final CharSequence text) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                JobActivity.showToast(getApplicationContext(), text);
            }
        });
    }
}
