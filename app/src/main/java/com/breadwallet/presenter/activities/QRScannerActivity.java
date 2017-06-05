package com.breadwallet.presenter.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.breadwallet.presenter.activities.util.BRActivity;
import com.breadwallet.R;
import com.dlazaro66.qrcodereaderview.QRCodeReaderView;

public class QRScannerActivity extends BRActivity implements QRCodeReaderView.OnQRCodeReadListener {

    private QRCodeReaderView qrCodeReaderView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_qrscanner);
        initQRCodeReaderView();
    }

    private void initQRCodeReaderView() {
        qrCodeReaderView = (QRCodeReaderView) findViewById(R.id.qrdecoderview);
        qrCodeReaderView.setAutofocusInterval(2000L);
        qrCodeReaderView.setOnQRCodeReadListener(this);
        qrCodeReaderView.setBackCamera();
        qrCodeReaderView.startCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (qrCodeReaderView != null) {
            qrCodeReaderView.startCamera();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (qrCodeReaderView != null) {
            qrCodeReaderView.stopCamera();
        }
    }

    @Override
    public void onQRCodeRead(final String text, PointF[] points) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result", text);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}
