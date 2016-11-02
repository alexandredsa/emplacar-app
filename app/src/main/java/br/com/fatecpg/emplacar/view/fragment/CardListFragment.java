package br.com.fatecpg.emplacar.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;

import br.com.fatecpg.emplacar.R;
import br.com.fatecpg.emplacar.cards.RewardSetFactory;
import br.com.fatecpg.emplacar.domain.Reward;
import br.com.fatecpg.emplacar.view.adapter.CardAdapter;

/**
 * Created by alexandre on 02/11/16.
 */

public class CardListFragment extends Fragment {
    private List<Reward> rewards;
    GridLayoutManager lLayout;
    RecyclerView rView;
    Context mContext;


    public CardListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_card_list, container, false);
        lLayout = new GridLayoutManager(mContext, 3);

        rView = (RecyclerView) v.findViewById(R.id.recycler_item);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);
        CardAdapter rcAdapter = new CardAdapter(this.rewards, mContext);
        rView.setAdapter(rcAdapter);
        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    public void onStop() {
        super.onStop();
        setAsNotNew();
    }

    private void setAsNotNew() {
        for(Reward reward : rewards){
            reward.setNew(false);
            reward.save();
        }
    }

    private List<Reward> mock() {
        return Arrays.asList(new Reward(false, R.drawable.ic_ticket_02),
                new Reward(true, R.drawable.ic_ticket_01),
                new Reward(true, R.drawable.ic_ticket_03));
    }

    public static Fragment newInstance(int i) {
        CardListFragment cardListFragment = new CardListFragment();
        cardListFragment.rewards = new RewardSetFactory(i).create();
        return cardListFragment;
    }

    public static String getTitle(int position) {
        switch (position) {
            case 0:
                return "PLACAS";
            case 1:
                return "MULTAS";
        }

        throw new UnsupportedOperationException("Reward tab Invalid index");
    }
}
