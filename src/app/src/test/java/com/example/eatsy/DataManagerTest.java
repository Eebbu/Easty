package com.example.eatsy.datamanagement;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class contains unit tests for the DataManager class.
 * It tests the singleton instance and the generation of unique IDs.
 * @author Boxuan Lin
 */
public class DataManagerTest {

    /**
     * Tests if the DataManager class follows the singleton pattern.
     * It ensures that two calls to getDataInstance() return the same instance.
     */
    @Test
    public void testSingletonInstance() {
        DataManager instance1 = DataManager.getDataInstance();
        DataManager instance2 = DataManager.getDataInstance();
        assertSame("Both instances should be the same", instance1, instance2);
    }

    /**
     * Tests the generateTimestampBasedId() method of the DataManager class.
     * It ensures that the generated IDs are not null and are unique.
     */
    @Test
    public void testGenerateTimestampBasedId() {
        String id1 = DataManager.generateTimestampBasedId();
        String id2 = DataManager.generateTimestampBasedId();

        assertNotNull("Generated ID should not be null", id1);
        assertNotNull("Generated ID should not be null", id2);
        assertNotEquals("Generated IDs should be unique", id1, id2);
    }
}
