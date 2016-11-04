package br.com.fatecpg.emplacar.cards;

import java.util.List;

import br.com.fatecpg.emplacar.domain.entity.Reward;

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
        return Reward.find(Reward.class, "card_type = ?", "TRAFFIC_TICKET");
    }

    private List<Reward> SignCards() {
        return Reward.find(Reward.class, "card_type = ?", "SIGN");
    }
}
