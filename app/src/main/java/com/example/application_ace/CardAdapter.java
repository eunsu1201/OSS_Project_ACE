package com.example.application_ace;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    private final List<CardItem> cardItems;

    public CardAdapter(List<CardItem> cardItems) {
        this.cardItems = cardItems;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        holder.bind(cardItems.get(position));
    }

    @Override
    public int getItemCount() {
        return cardItems.size();
    }

    static class CardViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvCardContent;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCardContent = itemView.findViewById(R.id.tvCardContent);
        }

        public void bind(CardItem item) {
            tvCardContent.setText(item.getContent());
        }
    }
}
