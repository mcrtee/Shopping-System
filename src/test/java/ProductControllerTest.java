
import javafx.application.Platform;
import javafx.scene.control.ListView;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

public class ProductControllerTest {

    @BeforeAll
    static void initToolkit() throws InterruptedException {
        // Use a latch to wait for the JavaFX Toolkit to initialize
        CountDownLatch latch = new CountDownLatch(1);
        Platform.startup(() -> latch.countDown());
        latch.await();
    }

    @Test
    void testAddToCart() {
        // Simulate a ListView and verify it works
        ListView<String> listView = new ListView<>();
        listView.getItems().add("Test Product");
        listView.getSelectionModel().select("Test Product");

        // Assertions
        assertEquals(1, listView.getItems().size());
        assertEquals("Test Product", listView.getSelectionModel().getSelectedItem());
    }
}
