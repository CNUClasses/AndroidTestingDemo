package com.royalldesigns.testingdemo;

import android.widget.Toast;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import androidx.test.filters.SmallTest;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
@SmallTest
public class ToasterTest {

    @Mock
    private Toast toast;

    @Test
    public void populatesToastWithPaidStateMessage() {
        Toaster toaster = new Toaster();
        toaster.toastIt(toast, true);

        verify(toast, times(1)).setText("User selected: PAID");
    }

    @Test
    public void populatesToastWithDueStateMessage() {
        Toaster toaster = new Toaster();
        toaster.toastIt(toast, false);

        verify(toast, times(1)).setText("User selected: DUE");
    }
}
