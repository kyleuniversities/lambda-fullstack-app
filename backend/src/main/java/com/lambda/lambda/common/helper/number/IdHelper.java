package com.lambda.lambda.common.helper.number;

import com.lambda.lambda.common.utility.number.id.MockIdGenerator;

public class IdHelper {
    /**
     * Creates a mock id
     */
    public static String nextMockId() {
        return MockIdGenerator.newInstance().nextId();
    }

    /**
     * Private Constructor to prevent instantiation
     */
    private IdHelper() {
        super();
    }
}
