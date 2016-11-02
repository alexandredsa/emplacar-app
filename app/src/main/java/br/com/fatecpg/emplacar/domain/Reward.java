package br.com.fatecpg.emplacar.domain;

import com.orm.SugarRecord;

import br.com.fatecpg.emplacar.cards.CardType;

/**
 * Created by alexandre on 01/11/16.
 */
public class Reward extends SugarRecord{
    private int imgResource;
    private boolean isNew;
    private CardType cardType;

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
}
