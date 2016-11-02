package br.com.fatecpg.emplacar.cards;

import java.util.List;

import br.com.fatecpg.emplacar.domain.Reward;

/**
 * Created by alexandre on 02/11/16.
 */

public class RewardSetFactory {
    private int index;

    public RewardSetFactory(int index) {
        this.index = index;
    }

    public List<Reward> create() {
        if (index == 0)
            return SignCards();
        else if (index == 1)
            return TrafficTicketCards();

        throw new UnsupportedOperationException("Reward tab Invalid index");
    }

    private List<Reward> TrafficTicketCards() {
        return null;
    }

    private List<Reward> SignCards() {
        return null;
    }
}
