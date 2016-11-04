package br.com.fatecpg.emplacar.domain.builder;

import br.com.fatecpg.emplacar.cards.CardType;
import br.com.fatecpg.emplacar.domain.entity.Reward;

/**
 * Created by alexandre on 03/11/16.
 */
public final class RewardBuilder {
    int imgResource;
    String cardType;
    boolean isNew;
    private Long id = null;

    private RewardBuilder() {
    }

    public static RewardBuilder aReward() {
        return new RewardBuilder();
    }

    public RewardBuilder imgResource(int imgResource) {
        this.imgResource = imgResource;
        return this;
    }

    public RewardBuilder cardType(CardType cardType) {
        this.cardType = cardType.toString();
        return this;
    }

    public RewardBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public RewardBuilder isNew(boolean isNew) {
        this.isNew = isNew;
        return this;
    }

    public Reward build() {
        Reward reward = new Reward(isNew);
        reward.setImgResource(imgResource);
        reward.setCardType(cardType);
        reward.setId(id);
        return reward;
    }
}
