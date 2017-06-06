package com.breadwallet.presenter.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;

import com.breadwallet.R;
import com.breadwallet.presenter.activities.GenericInfoDialogActivity;

public class GenericInfoDialogFragment extends DialogFragment {
    public static final String KEY_MESSAGE_ID = "id";
    public static final String KEY_MESSAGE_STRING = "str";
    public static final String KEY_FINISH_ACTIVITY_ON_DISMISS = "finish";
    public static final String KEY_OPEN_URL_ON_ACCEPT = "openurl";
    public static final String KEY_POSITIVE_BUTTON_MSG_ID = "posmsg";
    public static final String KEY_POSITIVE_BUTTON_MSG_STRING = "posmsgstr";
    public static final String KEY_NEGATIVE_BUTTON_MSG_STRING = "nevmsgstr";
    public static final String KEY_CANCELLABLE = "cancellable";
    public static final String KEY_DONT_DISMISS_ON_ACCEPT = "nodismiss";
    public static final String KEY_TITLE_ID = "titleid";
    public static final String KEY_TITLE_STRING = "titlestring";

    public static class Builder {
        Bundle args = new Bundle();

        public Builder message(int messageResId) {
            args.putInt(KEY_MESSAGE_ID, messageResId);
            return this;
        }

        public Builder message(String message) {
            args.putString(KEY_MESSAGE_STRING, message);
            return this;
        }

        public Builder title(int titleResId) {
            args.putInt(KEY_TITLE_ID, titleResId);
            return this;
        }

        public Builder title(String title) {
            args.putString(KEY_TITLE_STRING, title);
            return this;
        }

        public Builder positiveButtonMessage(int messageResId) {
            args.putInt(KEY_POSITIVE_BUTTON_MSG_ID, messageResId);
            return this;
        }

        public Builder positiveButtonMessage(String message) {
            args.putString(KEY_POSITIVE_BUTTON_MSG_STRING, message);
            return this;
        }

        public Builder negativeButtonMessage(String message) {
            args.putString(KEY_NEGATIVE_BUTTON_MSG_STRING, message);
            return this;
        }

        public Builder openUrlOnAccept(String url) {
            args.putString(KEY_OPEN_URL_ON_ACCEPT, url);
            return this;
        }

        public Builder finishActivityOnDismiss() {
            args.putBoolean(KEY_FINISH_ACTIVITY_ON_DISMISS, true);
            return this;
        }

        public Builder cancellable(boolean isCancellable) {
            args.putBoolean(KEY_CANCELLABLE, isCancellable);
            return this;
        }

        public Builder dontDismissOnAccept() {
            args.putBoolean(KEY_DONT_DISMISS_ON_ACCEPT, true);
            return this;
        }

        public Builder withArgs(Bundle args) {
            this.args.putAll(args);
            return this;
        }

        public void show(FragmentManager manager, String tag) {
            build().show(manager, tag);
        }

        public void showAllowingStateLoss(FragmentManager manager, String tag) {
            Fragment frag = build();
            FragmentTransaction ft = manager.beginTransaction();
            ft.add(frag, tag);
            ft.commitAllowingStateLoss();
        }

        public int show(FragmentTransaction transaction, String tag) {
            return build().show(transaction, tag);
        }

        public GenericInfoDialogFragment build() {
            return new GenericInfoDialogFragment().withArgs(args);
        }

        @Deprecated
        /**
         * Don't use this - it tends to do really weird things.
         * @param context
         */
        public void launchAsNewActivity(Context context) {
            Intent launchIntent = new Intent(context, GenericInfoDialogActivity.class);
            launchIntent.putExtras(args);
            launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(launchIntent);
        }
    }

    private class ButtonOnClickListener implements DialogInterface.OnClickListener, View.OnClickListener {
        private String urlToOpen;

        public ButtonOnClickListener(String urlToOpen) {
            this.urlToOpen = urlToOpen;
        }

        private void openUrl() {
            if (urlToOpen != null) {
                Uri uri = Uri.parse(urlToOpen);
                // open uri
            }
        }

        @Override
        public void onClick(DialogInterface dialog, int which) {
            openUrl();
        }

        @Override
        public void onClick(View v) {
            openUrl();
        }

    }

    private GenericInfoDialogFragment withArgs(Bundle args) {
        Bundle currentArgs = getOrCreateArguments();
        currentArgs.putAll(args);
        return this;
    }

    private Bundle getOrCreateArguments() {
        Bundle args = getArguments();
        if (args == null) {
            args = new Bundle();
            setArguments(args);
        }
        return args;
    }

    public static void show(int messageResId, FragmentManager fm) {
        Bundle args = new Bundle();
        args.putInt(KEY_MESSAGE_ID, messageResId);
        doShow(fm, args);
    }

    public static void show(String message, FragmentManager fm) {
        Bundle args = new Bundle();
        args.putString(KEY_MESSAGE_STRING, message);
        doShow(fm, args);
    }

    public static void show(String message, FragmentManager fm, boolean finishActivityOnDismiss) {
        Bundle args = new Bundle();
        args.putString(KEY_MESSAGE_STRING, message);
        args.putBoolean(KEY_FINISH_ACTIVITY_ON_DISMISS, finishActivityOnDismiss);
        doShow(fm, args);
    }

    public static void show(int messageResId, FragmentManager fm, boolean finishActivityOnDismiss) {
        Bundle args = new Bundle();
        args.putInt(KEY_MESSAGE_ID, messageResId);
        args.putBoolean(KEY_FINISH_ACTIVITY_ON_DISMISS, finishActivityOnDismiss);
        doShow(fm, args);
    }

    private static void doShow(FragmentManager fm, Bundle args) {
        DialogFragment frag = new GenericInfoDialogFragment();
        frag.setArguments(args);
        frag.show(fm, null);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setCancelable(getArguments().getBoolean(KEY_CANCELLABLE));
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final Bundle args = getArguments();

        String title;
        if (args.containsKey(KEY_TITLE_ID)) {
            title = getString(args.getInt(KEY_TITLE_ID));
            builder.setTitle(title);
        } else if (args.containsKey(KEY_TITLE_STRING)) {
            title = args.getString(KEY_TITLE_STRING);
            builder.setTitle(title);
        }

        String msg;
        if (args.containsKey(KEY_MESSAGE_ID)) {
            msg = getString(args.getInt(KEY_MESSAGE_ID));
        } else if (args.containsKey(KEY_MESSAGE_STRING)) {
            msg = args.getString(KEY_MESSAGE_STRING);
        } else {
            msg = "";
        }
        builder.setMessage(msg);

        String negativeMsg;
        if (args.containsKey(KEY_NEGATIVE_BUTTON_MSG_STRING)) {
            negativeMsg = args.getString(KEY_NEGATIVE_BUTTON_MSG_STRING);
            builder.setNegativeButton(negativeMsg, null);
        }

        final String urlToOpenOnAccept = getUrlToOpenOnAccept();
        String positiveMsg;
        if (args.containsKey(KEY_POSITIVE_BUTTON_MSG_ID)) {
            positiveMsg = getString(args.getInt(KEY_POSITIVE_BUTTON_MSG_ID));
        } else if (args.containsKey(KEY_POSITIVE_BUTTON_MSG_STRING)) {
            positiveMsg = args.getString(KEY_POSITIVE_BUTTON_MSG_STRING);
        } else {
            positiveMsg = getString(R.string.OK);
        }

        builder.setPositiveButton(positiveMsg, dontDimissOnAccept() || urlToOpenOnAccept == null ? null
                : new ButtonOnClickListener(urlToOpenOnAccept));

        AlertDialog dialog = builder.create();

        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();

        if (dontDimissOnAccept()) {
            Button b = ((AlertDialog) getDialog()).getButton(DialogInterface.BUTTON_POSITIVE);
            b.setOnClickListener(new ButtonOnClickListener(getUrlToOpenOnAccept()));
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (getArguments().getBoolean(KEY_FINISH_ACTIVITY_ON_DISMISS)) {
            Activity a = getActivity();
            if (a != null) {
                a.finish();
            }
        }
    }

    private boolean dontDimissOnAccept() {
        return getArguments().getBoolean(KEY_DONT_DISMISS_ON_ACCEPT);
    }

    private String getUrlToOpenOnAccept() {
        return getArguments().getString(KEY_OPEN_URL_ON_ACCEPT);
    }
}
