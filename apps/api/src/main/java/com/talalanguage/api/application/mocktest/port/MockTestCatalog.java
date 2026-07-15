package com.talalanguage.api.application.mocktest.port;

import com.talalanguage.api.application.mocktest.model.MockTestDefinition;
import java.util.Optional;

public interface MockTestCatalog {

    MockTestDefinition current();

    Optional<MockTestDefinition> findById(String id);
}
