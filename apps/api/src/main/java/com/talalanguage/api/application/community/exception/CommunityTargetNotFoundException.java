package com.talalanguage.api.application.community.exception;

public class CommunityTargetNotFoundException extends RuntimeException {

    public CommunityTargetNotFoundException(String targetType, String targetId) {
        super("Community target not found: " + targetType + " " + targetId);
    }
}
