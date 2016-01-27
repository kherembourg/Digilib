package com.kherembourg.digilib.transversal.dagger;

import com.kherembourg.digilib.transversal.Digilib;

public class DaggerHelper {

    private static DigilibComponent digilibComponent;

    /**
     * Init the dagger object graph with production modules <br/><br/>
     * <p/>
     * Must be called in onCreate of the Application
     */
    public static synchronized void init(Digilib application) {
        digilibComponent = DaggerDigilibComponent.builder()
                .digilibModule(new DigilibModule(application))
                .build();
        digilibComponent.inject(application);
    }

    public static DigilibComponent component() {
        return digilibComponent;
    }

    private DaggerHelper() {
        // do nothing, static class.
    }

}
