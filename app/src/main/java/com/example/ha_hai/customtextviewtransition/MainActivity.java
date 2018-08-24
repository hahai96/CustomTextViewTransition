package com.example.ha_hai.customtextviewtransition;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "AAA";

    private View mSharedElement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_scene);
        findViewById(R.id.container).setOnClickListener(this);
        mSharedElement = findViewById(R.id.hello_world);
    }

    @Override
    public void onClick(View v) {

        Log.d(TAG, "1");
        startActivity(new Intent(this, ChildActivity.class), ActivityOptions.makeSceneTransitionAnimation(
                this, mSharedElement, mSharedElement.getTransitionName()).toBundle());
        Log.d(TAG, "2");
    }

    public static class ChildActivity extends Activity implements View.OnClickListener {
        private static final String TAG = "ChildActivity";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.end_scene);
            findViewById(R.id.container).setOnClickListener(this);
            Log.d(TAG, "3");
            getWindow().setEnterTransition(TransitionUtils.makeEnterTransition());
            getWindow().setSharedElementEnterTransition(TransitionUtils.makeSharedElementEnterTransition(this));
            setEnterSharedElementCallback(new EnterSharedElementCallback(this));
            Log.d(TAG, "4");
        }

        @Override
        public void onClick(View v) {
            finishAfterTransition();
        }
    }
}
