package com.example.student.teamproject;

import android.app.AlertDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DatePickerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DatePickerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DatePickerFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    DatePicker datePicker;
    boolean alertActive = false;
    private OnFragmentInteractionListener mListener;

    public DatePickerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DatePickerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DatePickerFragment newInstance(String param1, String param2) {
        DatePickerFragment fragment = new DatePickerFragment();
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
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_date_picker, container, false);

        final Context context = getContext();
        final SqliteDbUtils dbUtils = new SqliteDbUtils(context);
        datePicker = (DatePicker) view.findViewById(R.id.datePicker);
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        long millis = calendar.getTimeInMillis();
        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, final int year, final int monthOfYear, final int dayOfMonth) {
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
                        if (isChecked) {
                            alertActive = true;
                            timePicker.setVisibility(View.VISIBLE);
                        } else {
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
                        currentAlert.setMonth(monthOfYear);
                        currentAlert.setDay(dayOfMonth);
                        currentAlert.setHour(timePicker.getHour());
                        currentAlert.setMinute(timePicker.getMinute());

                        if (formValidation(etTitle.getText().toString())) {
                            dbUtils.addItem(DateUtilities.getDate(currentAlert), currentAlert.getName(), currentAlert.getDescription(), alertActive);
                            Notification.handle(getContext(), currentAlert);
                            //Toast.makeText(context, "" + currentAlert.getRequestCode(), Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        } else {
                            Toast.makeText(context, getResources().getString(R.string.incorrect_title), Toast.LENGTH_LONG).show();
                        }
                    }
                });
                String date = dayOfMonth + "." + (monthOfYear + 1) + "." + year;
                tvDay.setText(tvDay.getText() + date);
                dialog.show();
            }
        });
        return view;
    }

    private boolean formValidation(String title)
    {
        if(title.matches(".{3,}"))
            return true;
        return false;
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
        /*
        if (context instanceof OnFragmentInteractionListener) {
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
