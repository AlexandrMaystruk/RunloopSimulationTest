package com.gmail.maystruks.runloopsimulationtest.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.gmail.maystruks.runloopsimulationtest.News;
import com.gmail.maystruks.runloopsimulationtest.NewsTitleSingleton;
import com.gmail.maystruks.runloopsimulationtest.R;
import com.gmail.maystruks.runloopsimulationtest.activity.NewsActivity;
import java.util.List;


public class RecyclerNewsListAdapter extends RecyclerView.Adapter<RecyclerNewsListAdapter.MyViewHolder> {

    private List<News> newsList;
    private Context context;


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvDatePublication;
        CardView mCardView;


        public MyViewHolder(View v) {
            super(v);
            tvTitle = v.findViewById(R.id.tv_news_title);
            tvDatePublication = v.findViewById(R.id.tv_news_date_publication);
            mCardView = v.findViewById(R.id.card_view_hike);

        }
    }


    public RecyclerNewsListAdapter(Context context, List<News> newsList) {
        this.context = context;
        this.newsList = newsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {


        holder.tvTitle.setText(newsList.get(position).getTitle());
        holder.tvDatePublication.setText(newsList.get(position).getDate());
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NewsActivity.startActivity(context, newsList.get(position).getDescription());
                NewsTitleSingleton.getINSTANCE().setTitle(newsList.get(position).getTitle());

            }
        });

    }


    public void updateList(List<News> newsList) {

        if(this.newsList.size() != newsList.size()){
            this.newsList.clear();
            this.newsList.addAll(newsList);
            notifyDataSetChanged();
        }
    }


    @Override
    public int getItemCount() {
        return newsList.size();
    }

}