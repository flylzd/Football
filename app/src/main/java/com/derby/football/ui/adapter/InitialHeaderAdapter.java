package com.derby.football.ui.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.derby.football.R;
import com.eowise.recyclerview.stickyheaders.StickyHeadersAdapter;

import java.util.List;

public class InitialHeaderAdapter  implements StickyHeadersAdapter<InitialHeaderAdapter.ViewHolder> {

    private List<String> items;

    public InitialHeaderAdapter() {
//        this.items = items;
    }

    public InitialHeaderAdapter(List<String> items) {
//        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.letter_header, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder headerViewHolder, int position) {
//        headerViewHolder.letter.setText(items.get(position));
                headerViewHolder.letter.setText("Header");

    }

    @Override
    public long getHeaderId(int position) {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView letter;

        public ViewHolder(View itemView) {
            super(itemView);
            letter = (TextView) itemView.findViewById(R.id.letter);
        }
    }
}
