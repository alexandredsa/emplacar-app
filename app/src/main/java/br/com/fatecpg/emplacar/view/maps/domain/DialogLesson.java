package br.com.fatecpg.emplacar.view.maps.domain;

import android.content.Context;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by alexa on 11/09/2016.
 */
public class DialogLesson {
    private String title;
    private String message;
    private String confirmText;

    public DialogLesson(String title, String message, String confirmText) {
        this.title = title;
        this.message = message;
        this.confirmText = confirmText;
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

    public SweetAlertDialog buildDialog(Context mContext, SweetAlertDialog.OnSweetClickListener clickListener) {
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(mContext);
        sweetAlertDialog.setCancelable(false);
        sweetAlertDialog.setTitleText(this.title)
                .setContentText(this.message)
                .setConfirmText(confirmText).setConfirmClickListener(clickListener);

        return sweetAlertDialog;
    }

}
