package org.jala.university.application.service;

import java.util.UUID;

public interface SessionService {

    void setCurrentUserId(UUID userId);

    UUID getCurrentUserId();

    void clearSession();
}
