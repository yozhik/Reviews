package com.ylet.sr.review;

import android.view.View;

import java.util.HashMap;
import java.util.Map;

public class ScreenSwitcher {
    private Map<String, View> screenActivator;
    private String visibleScreen;

    public ScreenSwitcher() {
        screenActivator = new HashMap<>();
    }

    public void addScreen(String screenName, View screen) {
        screen.setVisibility(View.GONE);
        screenActivator.put(screenName, screen);
    }

    public void showScreen(String screenName) {
        setVisibility(visibleScreen, View.GONE);
        setVisibility(screenName, View.VISIBLE);
        visibleScreen = screenName;
    }

    private void setVisibility(String screenName, int visibility) {
        if (screenName != null) {
            View view = screenActivator.get(screenName);
            view.setVisibility(visibility);
        }
    }
}
