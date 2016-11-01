package br.com.fatecpg.emplacar.view.user.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

public abstract class ApplicationPreferences {
    private SharedPreferences prefs;
    private Gson gson;

    public ApplicationPreferences(Context mContext) {
        this.gson = new Gson();
        this.prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
    }

    protected void storeObject(String key, Object obj) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, obj.toString());
        editor.commit();
    }

    protected <T extends Object> T retrieveObject(String key, Class<T> clazz) {
        String json = prefs.getString(key, null);
        if (json == null)
            return null;

        return clazz.cast(gson.fromJson(json, clazz));
    }

}
