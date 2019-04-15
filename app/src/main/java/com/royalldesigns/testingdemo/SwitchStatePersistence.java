package com.royalldesigns.testingdemo;

import android.content.Context;
import android.util.Log;
import android.widget.Switch;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;

public class SwitchStatePersistence {
    private String destinationFile;
    private Context context;
    private static final String LOG_TAG = "SwitchStatePersistence";

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
            Log.e(LOG_TAG, "Error writing to file.");
        }
        return stateFile.getAbsolutePath();
    }

    public boolean getLastState() {
        File persistenceFile = new File(context.getFilesDir(), destinationFile);
        if (persistenceFile.exists()) {
            try {
                String result = new String(Files.readAllBytes(persistenceFile.toPath()));
                return Boolean.valueOf(result);
            } catch (IOException e) {
                Log.e(LOG_TAG, "Error reading from persistence file.");
            }
        }
        return false;
    }
}
