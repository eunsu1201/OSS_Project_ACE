package com.example.application_ace;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;

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
        private final TextView tvCardTitle;
        private final TextView tvCardContent;
        private final ImageView imgCard;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCardTitle = itemView.findViewById(R.id.tvCardTitle);
            tvCardContent = itemView.findViewById(R.id.tvCardContent);
            imgCard = itemView.findViewById(R.id.imgCard);
        }

        public void bind(CardItem item) {
            tvCardTitle.setText(item.getTitle());
            tvCardContent.setText(item.getContent());

            if ("재무상태표와 손익계산서의 관계".equals(item.getTitle())) {
                imgCard.setVisibility(View.VISIBLE);
                imgCard.setImageResource(R.drawable.financial_statement);
            } else {
                imgCard.setVisibility(View.GONE);
            }

            if ("거래".equals(item.getTitle()) && item.getContent().contains("일상&회계의 거래")) {
                imgCard.setVisibility(View.VISIBLE);
                imgCard.setImageResource(R.drawable.transaction);
            } else {
                imgCard.setVisibility(View.GONE);
            }

            if ("결산의 절차".equals(item.getTitle())) {
                imgCard.setVisibility(View.VISIBLE);
                imgCard.setImageResource(R.drawable.closing);
            } else {
                imgCard.setVisibility(View.GONE);
            }

        }
    }
}
