package br.com.fatecpg.emplacar.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.fatecpg.emplacar.R;
import br.com.fatecpg.emplacar.domain.Reward;

/**
 * Created by alexandre on 02/11/16.
 */
public class CardAdapter extends RecyclerView.Adapter<CardRecyclerViewHolder> {
    private final List<Reward> cards;
    private final Context mContext;

    public CardAdapter(List<Reward> cards, Context mContext) {
        this.cards = cards;
        this.mContext = mContext;
    }

    @Override
    public CardRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_reward, null);
        CardRecyclerViewHolder rcv = new CardRecyclerViewHolder(layoutView);
        return rcv;

    }

    @Override
    public void onBindViewHolder(CardRecyclerViewHolder holder, int position) {
        final Reward reward = this.cards.get(position);
        holder.picture.setImageResource(reward.getImgResource());
        holder.newCard.setVisibility(reward.isNew() ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return this.cards.size();
    }
}
