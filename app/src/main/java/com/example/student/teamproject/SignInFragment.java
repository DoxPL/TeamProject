package com.example.student.teamproject;

import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class SignInFragment extends Fragment {
    private static final String TAG = "SignInFragment";

    private OnFragmentInteractionListener mListener;

    // default constructor
    public SignInFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        View fragmentSignInView = (View) getActivity().findViewById(R.id.fragment_sign_in_id);
        KeyboardUtils.setupKeyboardVisibility(fragmentSignInView, getActivity());

        try {
            TopBarUtils.setTopBar(
                    (AppCompatActivity) getActivity(), view, getString(R.string.signing_in));

        } catch (NullPointerException e) {
            Log.e(TAG, "Cannot get activity. @onViewCreated(..)");
        }

        Button getListBut = (Button) getActivity().findViewById(R.id.sign_in_get_list_button);
        SqliteDbUtils db = new SqliteDbUtils(getContext());
        List<NotesModel> list;

        db.addItem("date", "title1", "desc", true);
        db.addItem("date", "title2", "desc", true);
        db.addItem("date", "title3", "desc", true);
        db.addItem("date", "title4", "desc", true);

//        db.deleteItem("date", "title1");
//        db.deleteItem("date", "title2");
        db.deleteItem("date", "title3");
        db.deleteItem("date", "title4");

        list = db.getList();

        final List<NotesModel> fList = list;
        getListBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("list", fList.toString());
                Log.d("list.size", "" + fList.size());
            }
        });
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
