package com.kherembourg.digilib.calendar.presenters;

import com.kherembourg.digilib.transversal.presenters.Presenter;

public interface CalendarPresenter extends Presenter<CalendarView> {

    void loadItems(boolean refresh);
}
