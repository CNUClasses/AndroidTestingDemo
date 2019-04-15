package com.royalldesigns.testingdemo;

import android.content.Context;
import android.util.Log;
import android.widget.Switch;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class SwitchStatePersistence {
    private String destinationFile;
    private Context context;

    public SwitchStatePersistence(Context context, String destinationFile) {
        this.destinationFile = destinationFile;
        this.context = context;
    }

    public String saveState(Switch switchToSave) {
        File stateFile = new File(context.getFilesDir(), destinationFile);
        try {
            PrintStream out = new PrintStream(new FileOutputStream(stateFile));
            out.print(switchToSave.isChecked());
            out.close();
        } catch (FileNotFoundException e) {
            Log.e("SwitchStatePersistence", "Error writing to file.");
        }
        return stateFile.getAbsolutePath();
    }
}
