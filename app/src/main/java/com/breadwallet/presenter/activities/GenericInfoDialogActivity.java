package com.breadwallet.presenter.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.breadwallet.presenter.fragments.GenericInfoDialogFragment;

public class GenericInfoDialogActivity extends FragmentActivity {
    public static void launch(Context context, int messageResId) {
        Intent launchIntent = new Intent(context, GenericInfoDialogActivity.class);
        launchIntent.putExtra(GenericInfoDialogFragment.KEY_MESSAGE_ID, messageResId);
        if (!(context instanceof Activity)) {
            launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(launchIntent);
    }

    public static void launch(Context context, int titleResId, int messageResId) {
        Intent launchIntent = new Intent(context, GenericInfoDialogActivity.class);
        launchIntent.putExtra(GenericInfoDialogFragment.KEY_TITLE_ID, titleResId);
        launchIntent.putExtra(GenericInfoDialogFragment.KEY_MESSAGE_ID, messageResId);
        if (!(context instanceof Activity)) {
            launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(launchIntent);
    }

    public static void launch(Context context, String message) {
        Intent launchIntent = new Intent(context, GenericInfoDialogActivity.class);
        launchIntent.putExtra(GenericInfoDialogFragment.KEY_MESSAGE_STRING, message);
        if (!(context instanceof Activity)) {
            launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(launchIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            Bundle args = getIntent().getExtras();
            new GenericInfoDialogFragment.Builder().withArgs(args).finishActivityOnDismiss()
                    .show(getSupportFragmentManager(), null);
        }
    }
}
