package com.example.weather.service;

import org.springframework.stereotype.Service;

@Service
public class OfflineToggleService {
    private boolean offline = false;

    public boolean isOffline() {
        return offline;
    }

    public void setOffline(boolean offline) {
        this.offline = offline;
    }
}
