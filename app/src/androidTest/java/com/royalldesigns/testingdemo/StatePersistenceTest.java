package com.royalldesigns.testingdemo;

import android.content.Context;
import android.widget.Switch;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import androidx.test.filters.MediumTest;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
@MediumTest
public class StatePersistenceTest {

    @Rule
    public TemporaryFolder temporaryLocation = new TemporaryFolder();
    @Mock
    private Switch mockSwitch;
    @Mock
    private Context context;

    @Test
    public void savesTrueSwitchStateToFileSystem() throws IOException {
        initMocks(this);
        when(mockSwitch.isChecked()).thenReturn(true);
        when(context.getFilesDir()).thenReturn(temporaryLocation.newFolder());

        SwitchStatePersistence persistence = new SwitchStatePersistence(context, "state.save");
        String savedPath = persistence.saveState(mockSwitch);

        String result = new String(Files.readAllBytes(Paths.get(savedPath)));

        assertTrue(result.equals("true"));
    }

    @Test
    public void savesFalseSwitchStateToFileSystem() throws IOException {
        initMocks(this);
        when(mockSwitch.isChecked()).thenReturn(false);
        when(context.getFilesDir()).thenReturn(temporaryLocation.newFolder());

        SwitchStatePersistence persistence = new SwitchStatePersistence(context, "state.save");
        String savedPath = persistence.saveState(mockSwitch);

        String result = new String(Files.readAllBytes(Paths.get(savedPath)));

        assertTrue(result.equals("false"));
    }

    @Test
    public void returnsFalseStateWhenFileDoesNotExist() throws IOException {
        initMocks(this);
        when(context.getFilesDir()).thenReturn(temporaryLocation.newFolder());

        SwitchStatePersistence persistence = new SwitchStatePersistence(context, "doesNotExist");

        assertFalse(persistence.getLastState());
    }

    @Test
    public void returnsFalseStateWhenItWasLastState() throws IOException {
        initMocks(this);
        when(mockSwitch.isChecked()).thenReturn(false);
        when(context.getFilesDir()).thenReturn(temporaryLocation.newFolder());

        SwitchStatePersistence persistence = new SwitchStatePersistence(context, "state.save");
        persistence.saveState(mockSwitch);

        assertFalse(persistence.getLastState());
    }

    @Test
    public void returnsTrueStateWhenItWasLastState() throws IOException {
        initMocks(this);
        when(mockSwitch.isChecked()).thenReturn(true);
        when(context.getFilesDir()).thenReturn(temporaryLocation.newFolder());

        SwitchStatePersistence persistence = new SwitchStatePersistence(context, "state.save");
        persistence.saveState(mockSwitch);

        assertTrue(persistence.getLastState());
    }

}
