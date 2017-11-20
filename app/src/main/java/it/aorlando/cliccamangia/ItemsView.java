package it.aorlando.cliccamangia;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import it.aorlando.cliccamangia.Model.Item;

/**
 * Created by fagottino on 27/07/17.
 */


public class ItemsView extends DialogFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //return super.onCreateDialog(savedInstanceState);
        return new AlertDialog.Builder(getActivity())
                // Set Dialog Icon
                //.setIcon(android.support.v7.appcompat.R.drawable.androidhappy)
                .setView(R.layout.item_display)

                // Positive button
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something else
                    }
                }).create();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.item_display, container, false);
        txtItemName = (TextView) view.findViewById(R.id.txtItemName);

        return view;
    }

    public void setData(Item pItem) {
    }
}
