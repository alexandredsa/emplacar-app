package br.com.fatecpg.emplacar.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import br.com.fatecpg.emplacar.R;

/**
 * Created by alexandre on 02/11/16.
 */
public class CardRecyclerViewHolder extends RecyclerView.ViewHolder {
    ImageView picture;
    View newCard;

    public CardRecyclerViewHolder(View itemView) {
        super(itemView);
        picture = (ImageView) itemView.findViewById(R.id.picture);
        newCard = itemView.findViewById(R.id.newCard);
    }
}
