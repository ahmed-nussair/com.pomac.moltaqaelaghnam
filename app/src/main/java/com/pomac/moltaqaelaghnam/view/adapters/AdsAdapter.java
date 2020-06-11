package com.pomac.moltaqaelaghnam.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pomac.moltaqaelaghnam.R;
import com.pomac.moltaqaelaghnam.view.uimodels.AdItem;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class AdsAdapter extends RecyclerView.Adapter<AdsAdapter.AdsViewHolder> {

    private Context context;
    private List<AdItem> adItems;

    public AdsAdapter(Context context, List<AdItem> adItems) {
        this.context = context;
        this.adItems = adItems;
    }

    @NonNull
    @Override
    public AdsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ad_item_layout, parent, false);
        return new AdsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdsViewHolder holder, int position) {
        holder.adTitleTextView.setText(adItems.get(position).getTitle());
        holder.phoneTextView.setText(adItems.get(position).getPhone());
        holder.areaTextView.setText(adItems.get(position).getArea());

        Picasso.get()
                .load(adItems.get(position).getImagePath())
                .into(holder.adImageView);


        try {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
            calendar.setTime(Objects.requireNonNull(sdf.parse(adItems.get(position).getAdDateTime())));
            holder.adCreationTimeTextView.setText(getCreationTimeText(calendar.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return adItems.size();
    }

    private String getCreationTimeText(Date date) {
        String result = "";

        final String hour = "ساعة";
        final String hours = "ساعات";
        final String second = "ثانية";
        final String seconds = "ثواني";
        final String minute = "دقيقة";
        final String minutes = "دقائق";
        final String day = "يوم";
        final String days = "أيام";
        final String week = "أسبوع";
        final String weeks = "أسابيع";
        final String month = "شهر";
        final String months = "شهور";
        final String year = "سنة";
        final String years = "سنين";
        final String since = "منذ";

        Date now = new Date();

        long duration = now.getTime() - date.getTime();

        duration /= 1000;

        result = duration > 1 ? duration + " " + seconds : duration + " " + second;

        if (duration > 60) {
            duration /= 60;
            result = duration > 1 ? duration + " " + minutes : duration + " " + minute;

            if (duration > 60) {
                duration /= 60;
                result = duration > 1 ? duration + " " + hours : duration + " " + hour;

                if (duration > 24) {
                    duration /= 24;
                    result = duration > 1 ? duration + " " + days : duration + " " + day;

                    if (duration > 7) {
                        duration /= 7;
                        result = duration > 1 ? duration + " " + weeks : duration + " " + week;

                        if (duration > 4) {
                            duration /= 4;
                            result = duration > 1 ? duration + " " + months : duration + " " + month;

                            if (duration > 12) {
                                duration /= 12;
                                result = duration > 1 ? duration + " " + years : duration + " " + year;
                            }
                        }
                    }
                }
            }
        }

        return since + " " + result;
    }

    static class AdsViewHolder extends RecyclerView.ViewHolder {

        ImageView adImageView;
        TextView adTitleTextView;
        TextView phoneTextView;
        TextView areaTextView;
        TextView adCreationTimeTextView;

        public AdsViewHolder(@NonNull View itemView) {
            super(itemView);
            adImageView = itemView.findViewById(R.id.adImageView);
            adTitleTextView = itemView.findViewById(R.id.adTitleTextView);
            phoneTextView = itemView.findViewById(R.id.phoneTextView);
            areaTextView = itemView.findViewById(R.id.areaTextView);
            adCreationTimeTextView = itemView.findViewById(R.id.adCreationTimeTextView);
        }
    }
}
