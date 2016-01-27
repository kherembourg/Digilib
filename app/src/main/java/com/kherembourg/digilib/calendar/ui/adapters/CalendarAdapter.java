package com.kherembourg.digilib.calendar.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kherembourg.digilib.R;
import com.kherembourg.digilib.trakt.model.EpisodeListResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.CalendarHolder>{

    private final Context context;
    private final SimpleDateFormat serverDateFormat;
    private final SimpleDateFormat appDateFormat;
    private List<EpisodeListResponse> episodesList;

    public CalendarAdapter(Context applicationContext) {
        context = applicationContext;
        episodesList = new ArrayList<>();
        serverDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
        appDateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
    }

    @Override
    public CalendarHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CalendarHolder(LayoutInflater.from(context).inflate(R.layout.cell_calendar, parent, false));
    }

    @Override
    public void onBindViewHolder(final CalendarHolder holder, int position) {
        final EpisodeListResponse episode = episodesList.get(position);
        holder.titleView.setText(String.format(Locale.getDefault(), "%s S%dE%d",
                episode.getShow().getTitle(), episode.getEpisode().getSeason(), episode.getEpisode().getNumber()));

        try {
            Date serverDate = serverDateFormat.parse(episode.getFirstAired());
            holder.dateView.setText(appDateFormat.format(serverDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Toast.makeText(context, String.format("Clicked on %s", holder.titleView.getText()), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return episodesList.size();
    }

    @Override
    public int getItemViewType(final int position) {
        return super.getItemViewType(position);
    }

    public void add(List<EpisodeListResponse> episodes) {
        episodesList.addAll(episodes);
        notifyDataSetChanged();
    }

    public class CalendarHolder extends RecyclerView.ViewHolder {

        private final ImageView showImage;
        private final TextView titleView;
        private final TextView dateView;

        public CalendarHolder(View itemView) {
            super(itemView);
            showImage = (ImageView) itemView.findViewById(R.id.image_show);
            titleView = (TextView) itemView.findViewById(R.id.text_view_title);
            dateView = (TextView) itemView.findViewById(R.id.text_view_date);
        }
    }
}
