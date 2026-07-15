package com.talalanguage.api.application.mocktest;

import com.talalanguage.api.application.mocktest.model.CurrentMockTestQuestionView;
import com.talalanguage.api.application.mocktest.model.CurrentMockTestView;
import com.talalanguage.api.application.mocktest.port.MockTestCatalog;
import org.springframework.stereotype.Service;

@Service
public class GetCurrentMockTestUseCase {

    private final MockTestCatalog mockTestCatalog;

    public GetCurrentMockTestUseCase(MockTestCatalog mockTestCatalog) {
        this.mockTestCatalog = mockTestCatalog;
    }

    public CurrentMockTestView execute() {
        var current = mockTestCatalog.current();
        return new CurrentMockTestView(
                current.id(),
                current.title(),
                current.questions().stream()
                        .map(question -> new CurrentMockTestQuestionView(
                                question.id(),
                                question.question(),
                                question.options()
                        ))
                        .toList()
        );
    }
}
