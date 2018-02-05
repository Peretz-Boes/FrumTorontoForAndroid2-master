package com.boes.peretz.frumtoronto;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by alanrabinowitz on 2018-02-01.
 */

public class VerificationDialogFragment extends DialogFragment {

    public interface NoticeDialogListener{
        void onDialogPositiveClick(DialogFragment dialogFragment);
        void onDialogNegativeClick(DialogFragment dialogFragment);
    }

    NoticeDialogListener noticeDialogListener;
    StringBuilder captchaText=new StringBuilder();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.verification_dialog_layout,null);
        alertDialogBuilder.setView(view);
        alertDialogBuilder.setTitle(R.string.verification_dialog_title);
        final TextView textView=view.findViewById(R.id.captcha_text_text_view);
        final EditText editText=view.findViewById(R.id.numbers_input_edit_text);
        Random random=new Random();
        for (int i=0;i<4;i++){
            int captchaNumber=random.nextInt(10);
            captchaText.append(String.valueOf(captchaNumber));
        }
        textView.setText(captchaText);
        alertDialogBuilder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (editText.getText().toString().equalsIgnoreCase(textView.getText().toString())){
                    noticeDialogListener.onDialogPositiveClick(VerificationDialogFragment.this);
                }else {
                    Toast.makeText(getContext(), R.string.numbers_not_accurate_for_solving_captcha_error_message,Toast.LENGTH_LONG).show();
                }
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                noticeDialogListener.onDialogNegativeClick(VerificationDialogFragment.this);
            }
        });
        return alertDialogBuilder.create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            noticeDialogListener=(NoticeDialogListener)activity;
        }catch (ClassCastException classCastException){
            throw new ClassCastException(activity.toString()+" needs to implement NoticeDialogListener");
        }
    }
}


