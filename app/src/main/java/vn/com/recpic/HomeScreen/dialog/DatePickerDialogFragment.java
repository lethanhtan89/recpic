package vn.com.recpic.HomeScreen.dialog;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.DatePicker;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import vn.com.recpic.HomeScreen.fragment.AddExpenseFragment;

/**
 * Created by Administrator on 2/9/2017.
 */

public class DatePickerDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    String date;
    //DateDialogFragmentListener listener;
    public DatePickerDialogFragment (String date){
        this.date = date;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        month += 1;
        String monthString = month < 10 ? "0" + month : "" + month;
        String dayString = dayOfMonth < 10 ? "0" + dayOfMonth: "" + dayOfMonth;
        date = year + "/" + monthString + "/" + dayString;
//        Intent intent = new Intent();
//        intent.putExtra("date", date);
//        getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
    }
}
