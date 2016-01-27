package com.kherembourg.digilib.calendar.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kherembourg.digilib.R;
import com.kherembourg.digilib.calendar.dagger.CalendarComponent;
import com.kherembourg.digilib.calendar.presenters.CalendarPresenter;
import com.kherembourg.digilib.calendar.presenters.CalendarView;
import com.kherembourg.digilib.calendar.ui.adapters.CalendarAdapter;
import com.kherembourg.digilib.trakt.model.EpisodeListResponse;
import com.kherembourg.digilib.transversal.presenters.Presenter;
import com.kherembourg.digilib.transversal.ui.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CalendarFragment extends BaseFragment implements CalendarView {

    @Inject
    CalendarPresenter calend;

    @Bind(R.id.recycler_view_calendar)
    RecyclerView recyclerView;

    private CalendarAdapter calendarAdapter;

    public static Fragment newInstance() {
        return new CalendarFragment();
    }

    //region FRAGMENT LIFECYCLE

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = getView();
        if(view == null) {
            view = inflater.inflate(R.layout.fragment_calendar, container, false);
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        calendarAdapter = new CalendarAdapter(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(calendarAdapter);

        calend.loadItems(false);
    }

    @Override
    public void displayItems(final List<EpisodeListResponse> episodes) {
        calendarAdapter.add(episodes);
    }

    @Override
    protected void injectComponent() {
        CalendarComponent.Initializer.init().inject(this);
    }

    @Override
    protected Presenter getPresenter() {
        return calend;
    }

    //endregion FRAGMENT LIFECYCLE
}
