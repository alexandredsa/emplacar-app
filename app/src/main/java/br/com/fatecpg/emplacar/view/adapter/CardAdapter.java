package br.com.fatecpg.emplacar.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.meetic.marypopup.MaryPopup;

import java.util.List;

import br.com.fatecpg.emplacar.R;
import br.com.fatecpg.emplacar.domain.Reward;

/**
 * Created by alexandre on 02/11/16.
 */
public class CardAdapter extends RecyclerView.Adapter<CardRecyclerViewHolder> {
    private final List<Reward> cards;
    private final Activity mActivity;

    public CardAdapter(List<Reward> cards, Activity mActivity) {
        this.cards = cards;
        this.mActivity = mActivity;
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
        holder.picture.setTag(reward);
        holder.picture.setOnClickListener(v -> expandCard(v));
    }



    private void expandCard(View v) {
        MaryPopup marypopup = MaryPopup.with(mActivity)
                .cancellable(true)
                .center(true)
                .draggable(true)
                .fadeOutDragging(true)
                .blackOverlayColor(Color.parseColor("#DD444444"))
                .backgroundColor(Color.parseColor("#EFF4F5"))
                .content(buildView((ImageView) v))
                .from(v);

        marypopup.show();
    }

    private View buildView(ImageView v) {
        LayoutInflater vi = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = vi.inflate(R.layout.item_card_zoom, null);
        ImageView pic = (ImageView) view.findViewById(R.id.picture);
        Reward r = (Reward) v.getTag();
        pic.setImageResource(r.getImgResource());
        return view;
    }

    @Override
    public int getItemCount() {
        return this.cards.size();
    }
}
