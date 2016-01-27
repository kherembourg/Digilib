package com.kherembourg.digilib.calendar.dagger;

import com.kherembourg.digilib.calendar.ui.activities.CalendarActivity;
import com.kherembourg.digilib.calendar.ui.fragments.CalendarFragment;
import com.kherembourg.digilib.transversal.dagger.DaggerHelper;
import com.kherembourg.digilib.transversal.dagger.DigilibComponent;

import dagger.Component;

@CalendarScope
@Component(
        modules = {CalendarModule.class},
        dependencies = {DigilibComponent.class}
)
public interface CalendarComponent {

    final class Initializer {
        public static CalendarComponent init() {
            return DaggerCalendarComponent.builder()
                    .digilibComponent(DaggerHelper.component())
                    .calendarModule(new CalendarModule())
                    .build();
        }
    }

    void inject(CalendarActivity calendarActivity);
    void inject(CalendarFragment calendarFragment);
}
