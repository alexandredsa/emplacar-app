package br.com.fatecpg.emplacar.domain.entity;

import com.orm.SugarRecord;

import br.com.fatecpg.emplacar.cards.CardType;

/**
 * Created by alexandre on 01/11/16.
 */
public class Reward extends SugarRecord{
    int imgResource;
    boolean isNew;
    String cardType;

    public Reward() {
    }


    public Reward(boolean isNew, int imgResource) {
        this.isNew = isNew;
        this.imgResource = imgResource;
    }

    public Reward(int imgResource, CardType cardType) {
        this.imgResource = imgResource;
        this.cardType = cardType.toString();
    }

    public Reward(int imgResource, boolean isNew, String cardType) {
        this.imgResource = imgResource;
        this.isNew = isNew;
        this.cardType = cardType;
    }

    public Reward(boolean isNew) {
        this.isNew = isNew;
    }

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

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType.toString();
    }
}
