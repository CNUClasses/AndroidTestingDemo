package com.royalldesigns.testingdemo;

import android.widget.Toast;

public class Toaster {
    private static final String BASE_MESSAGE = "User selected: ";
    private static final String PAID = "PAID";
    private static final String DUE = "DUE";

    public Toaster () {

    }

    public void toastIt(Toast toast, boolean state) {
        String status = (state) ? PAID : DUE;
        String toastMessage = BASE_MESSAGE + status;
        toast.setText(toastMessage);
    }

}
