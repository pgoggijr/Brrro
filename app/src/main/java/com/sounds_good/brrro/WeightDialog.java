package com.sounds_good.brrro;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Peter on 5/12/2015.
 */
public class WeightDialog extends DialogFragment {
    private String weight;
    private Editable weightEditable;

    public interface WeightDialogListener {
        void onDialogPositiveClick(DialogFragment Dialog);
        void onDialogNegativeClick(DialogFragment Dialog);
    }

    WeightDialogListener mListener;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (WeightDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement WeightDialogListener");
        }
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        EditText weightView = new EditText(getActivity());
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        weightView.setText(weight);
        weightView.setInputType(InputType.TYPE_CLASS_NUMBER);
        weightView.selectAll();
        weightEditable = weightView.getText();

        /* set up builder */
        builder.setMessage(R.string.dialog_change_weight)
            .setView(weightView)
            .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    mListener.onDialogPositiveClick(WeightDialog.this);
                }
            })
            .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    mListener.onDialogNegativeClick(WeightDialog.this);
                }
            });
        return builder.create();
    }

    public String getWeight() {
        return weightEditable.toString();
    }
    public void setWeight(String weight) {
        this.weight = weight;
    }
}
