package com.example.student.teamproject;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class SignUpFragment extends Fragment {
    private static final String TAG = "SignUpFragment";

    private OnFragmentInteractionListener mListener;

    // default constructor
    public SignUpFragment() { }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container,false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        try {
            View fragmentSignInView = (View) getActivity().findViewById(R.id.fragment_sign_in_id);

            if (fragmentSignInView != null) {
                KeyboardUtils.setupKeyboardVisibility(fragmentSignInView, getActivity());
            }

        } catch (NullPointerException exception) {
            Log.e(TAG, "Fragment view is null. @onViewCreated(..)");
            exception.printStackTrace();
        }

        try {
            TopBarUtils.setTopBar(
                    (AppCompatActivity) getActivity(), view, getString(R.string.registration));

        } catch (NullPointerException e) {
            Log.e(TAG, "Cannot get activity. @onViewCreated(..)");
        }

        setButtons();
    }

    @Override
    public void onDetach() {
        super.onDetach();

        try {
            FragmentActivity activity = getActivity();

            if (activity != null) {
                KeyboardUtils.hideSoftKeyboard(getActivity());
            }
        } catch (NullPointerException exception) {
            Log.e(TAG, "Cannot get activity. @onDetach(..)");
            exception.printStackTrace();
        }

        mListener = null;
    }

    private void setButtons() {
        try {
            Button signUpBut = (Button) getActivity().findViewById(R.id.sign_up_button);

            signUpBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        } catch (NullPointerException exception) {
            Log.e(TAG, "Some button is null. @setButtons()");
            exception.printStackTrace();
        }
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
