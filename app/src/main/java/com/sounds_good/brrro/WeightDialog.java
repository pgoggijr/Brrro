package com.sounds_good.brrro;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;

/**
 * Created by Peter on 5/12/2015.
 */
public class WeightDialog extends DialogFragment {
    private String weight;
    private Editable weightEditable;

    public interface WeightDialogListener {
        void onDialogPositiveClick(WeightDialog Dialog);
        void onDialogNegativeClick(WeightDialog Dialog);
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
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /* invoke soft keyboard */
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LinearLayout layout = new LinearLayout(getActivity());
        LinearLayout.LayoutParams layoutParams;
        EditText weightView = new EditText(getActivity());
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        float density = getActivity().getResources().getDisplayMetrics().density;

        /* set up edit text view & its layout */
        layout.setOrientation(LinearLayout.VERTICAL);
        layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        /* use dp values instead of pixels */
        layoutParams.setMargins(
                (int) density * 100,0,
                (int) density * 100,0);

        weightView.setText(weight);
        weightView.setInputType(InputType.TYPE_CLASS_NUMBER);
        weightView.selectAll();
        weightView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        weightView.setGravity(Gravity.CENTER);
        weightEditable = weightView.getText();
        layout.addView(weightView, layoutParams);

        /* set up builder */
        builder.setMessage(R.string.dialog_change_weight)
            .setView(layout)
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
