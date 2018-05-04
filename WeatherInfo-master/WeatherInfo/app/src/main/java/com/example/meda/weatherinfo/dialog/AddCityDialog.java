package com.example.meda.weatherinfo.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import com.example.meda.weatherinfo.R;

/**
 * Created by meda on 4/28/16.
 */
public class AddCityDialog extends DialogFragment {

    EditText etCity;


    public AddCityDialog newInstance() {
        AddCityDialog fragment = new AddCityDialog();
        return fragment;
    }

    public interface AddCityDialogListener{
        public abstract void onAddCityCreated(String cityName);
    }

    private AddCityDialogListener cityListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        final View editView = layoutInflater.inflate(R.layout.add_city_dialog_fragment, null);


        return new AlertDialog.Builder(getActivity())
                .setIcon(R.drawable.ic_action_add)
                .setTitle(R.string.addCity)
                .setView(editView)
                .setPositiveButton(R.string.enter,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                            etCity = (EditText) editView.findViewById(R.id.etCity);
                            cityListener.onAddCityCreated(etCity.getText().toString());
                            }
                        }
                )
                .setNegativeButton(R.string.cancel,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                            }
                        }
                )
                .create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try{
            this.cityListener = (AddCityDialogListener)activity;
        }catch(final ClassCastException e){
            throw new ClassCastException(activity.toString() +
                    activity.getString(R.string.cityDialog) ); //courtesy of John Morales
        }
    }


}
