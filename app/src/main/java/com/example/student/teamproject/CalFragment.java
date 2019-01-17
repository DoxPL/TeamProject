package com.example.student.teamproject;

import android.app.AlertDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CalFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "CalFragment";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    CalendarView calView;
    boolean alertActive;

    private OnFragmentInteractionListener mListener;

    public CalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CalFragment newInstance(String param1, String param2) {
        CalFragment fragment = new CalFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cal, container, false);
        final Context context = getContext();
        calView = (CalendarView) view.findViewById(R.id.calendar);
        setToday();
        final SqliteDbUtils dbUtils = new SqliteDbUtils(context);
        calView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, final int year, final int month, final int dayOfMonth) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
                View dialogView = inflater.inflate(R.layout.note_dialog, null);
                dialogBuilder.setView(dialogView);
                final AlertDialog dialog = dialogBuilder.create();
                alertActive = false;
                TextView tvDay = (TextView) dialogView.findViewById(R.id.tvDay);
                final EditText etTitle = (EditText) dialogView.findViewById(R.id.etTitle);
                final EditText etDesc = (EditText) dialogView.findViewById(R.id.etDescr);
                Switch swAlert = (Switch) dialogView.findViewById(R.id.swAlert);
                Button btnSave = (Button) dialogView.findViewById(R.id.btnSave);
                final TimePicker timePicker = (TimePicker) dialogView.findViewById(R.id.timePicker);
                timePicker.setIs24HourView(true);
                timePicker.setVisibility(View.GONE);

                swAlert.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked) {
                            alertActive = true;
                            timePicker.setVisibility(View.VISIBLE);
                        }
                        else {
                            alertActive = false;
                            timePicker.setVisibility(View.GONE);
                        }
                    }
                });

                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Alert currentAlert = new Alert();
                        currentAlert.setName(etTitle.getText().toString());
                        currentAlert.setDescription(!etDesc.getText().toString().equals("")
                                ? etDesc.getText().toString() : getActivity().getResources().getString(R.string.noDescr));
                        currentAlert.setYear(year);
                        currentAlert.setMonth(month);
                        currentAlert.setDay(dayOfMonth);
                        currentAlert.setHour(timePicker.getHour());
                        currentAlert.setMinute(timePicker.getMinute());

                        if(formValidation(etTitle.getText().toString()))
                        {
                            dbUtils.addItem(DateUtilities.getFullDate(currentAlert), currentAlert.getName(), currentAlert.getDescription(), alertActive);
                            Notification.handle(getContext(), currentAlert);
                            dialog.dismiss();
                        }
                        else {
                            Toast.makeText(context, getResources().getString(R.string.incorrect_title), Toast.LENGTH_LONG).show();
                        }
                    }
                });

                String date = dayOfMonth + "." + (month + 1) + "." + year;
                tvDay.setText(tvDay.getText() + date);
                dialog.show();
            }
        });

        return view;
    }

    private void setToday()
    {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(year, month, day);
        long millis = calendar.getTimeInMillis();
        calView.setDate(millis, true, true);
    }

    private boolean formValidation(String title)
    {
        if(title.matches(".{3,}"))
            return true;
        return false;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        try {
            TopBarUtils.setTopBar(
                    (AppCompatActivity) getActivity(), view, getString(R.string.calendar) );

        } catch (NullPointerException e) {
            Log.e(TAG, "Cannot get activity. @onViewCreated(..)");
        }
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        } */
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}

//    private void setTopBar(View view) {
//        AppCompatActivity activity = (AppCompatActivity) getActivity();
//
//        try {
//            ActionBar actionBar = activity.getSupportActionBar();
//
//            actionBar.setTitle(R.string.calendar);
//        } catch (NullPointerException exception) {
//            Log.e(TAG, "Cannot get actionBar. @setTopBar(..)");
//        }
//    }
