package com.example.student.teamproject;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class EventsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    RecyclerView recyclerView;
    AlertAdapter alertAdapter;
    List<Alert> alertList;

    public EventsFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static EventsFragment newInstance(String param1, String param2) {
        EventsFragment fragment = new EventsFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        alertList = new ArrayList<Alert>();
        //
        Alert test = new Alert();
        test.setName("Testowy alarm");
        test.setDescription("Opis alarmu");
        test.setYear(2018);
        test.setMonth(10);
        test.setDay(17);
        test.setHour(14);
        test.setMinute(30);
        test.setActive(true);

        Alert test1 = new Alert();
        test1.setName("Testowy alarm 2");
        test1.setDescription("Opis alarmu");
        test1.setYear(2018);
        test1.setMonth(10);
        test1.setDay(12);
        test1.setHour(10);
        test1.setMinute(11);
        test1.setActive(true);

        Alert test2 = new Alert();
        test2.setName("Testowy alarm");
        test2.setDescription("Opis alarmu");
        test2.setYear(2018);
        test2.setMonth(10);
        test2.setDay(17);
        test2.setHour(14);
        test2.setMinute(30);
        test2.setActive(true);
        //
        alertList.add(test1);
        alertList.add(test);
        alertList.add(test2);

        alertAdapter = new AlertAdapter(getContext(), alertList);
        recyclerView.setAdapter(alertAdapter);
        return view;
    }

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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
