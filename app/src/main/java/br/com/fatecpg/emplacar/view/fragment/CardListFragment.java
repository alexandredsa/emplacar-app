package br.com.fatecpg.emplacar.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.fatecpg.emplacar.R;
import br.com.fatecpg.emplacar.cards.RewardSetFactory;
import br.com.fatecpg.emplacar.domain.entity.Reward;
import br.com.fatecpg.emplacar.view.adapter.CardAdapter;

/**
 * Created by alexandre on 02/11/16.
 */

public class CardListFragment extends Fragment {
    private List<Reward> rewards;
    GridLayoutManager lLayout;
    RecyclerView rView;
    Activity mActivity;


    public CardListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_card_list, container, false);
        lLayout = new GridLayoutManager(mActivity, 3);

        rView = (RecyclerView) v.findViewById(R.id.recycler_item);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);
        CardAdapter rcAdapter = new CardAdapter(this.rewards, mActivity);
        rView.setAdapter(rcAdapter);
        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    @Override
    public void onStop() {
        super.onStop();
        setAsNotNew();
    }

    private void setAsNotNew() {
        for (Reward reward : rewards) {
            reward.setNew(false);
            reward.save();
        }
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
