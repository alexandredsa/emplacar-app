package br.com.fatecpg.emplacar.domain;

import com.orm.SugarRecord;

import br.com.fatecpg.emplacar.cards.CardType;

/**
 * Created by alexandre on 01/11/16.
 */
public class Reward extends SugarRecord{
    private int imgResource;
    private boolean isNew;
    private String cardType;

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public int getImgResource() {
        return imgResource;
    }

    public void setImgResource(int imgResource) {
        this.imgResource = imgResource;
    }

    public CardType getCardType() {
        return CardType.valueOf(cardType);
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType.toString();
    }
}
