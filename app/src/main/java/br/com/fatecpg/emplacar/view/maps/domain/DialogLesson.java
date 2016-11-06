package br.com.fatecpg.emplacar.view.maps.domain;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;

import br.com.fatecpg.emplacar.R;

/**
 * Created by alexa on 11/09/2016.
 */
public class DialogLesson {
    private String title;
    private String message;
    private String confirmText;
    private int image = -1;

    public DialogLesson(String title, String message, String confirmText) {
        this.title = title;
        this.message = message;
        this.confirmText = confirmText;
    }

    public DialogLesson(String title, String message, String confirmText, int image) {
        this.title = title;
        this.message = message;
        this.confirmText = confirmText;
        this.image = image;
    }

    public DialogLesson() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getConfirmText() {
        return confirmText;
    }

    public void setConfirmText(String confirmText) {
        this.confirmText = confirmText;
    }

    public MaterialDialog buildDialog(Context mContext, MaterialDialog.OnClickListener clickListener) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(mContext)
                .title(this.title)
                .content(this.message)
                .positiveText(this.confirmText)
                .cancelable(false);
        if (image != -1)
            builder.iconRes(this.image).maxIconSizeRes(R.dimen.max_icon_size_dialog);

        return builder.show();
    }

}
