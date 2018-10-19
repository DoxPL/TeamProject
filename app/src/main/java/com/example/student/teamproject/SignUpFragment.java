package com.example.student.teamproject;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class SignUpFragment extends Fragment {
    private static final String TAG = "SignUpFragment";
    private static final String EMAIL_ERR = "emailErr";
    private static final String PASSWORD_ERR = "passwordErr";
    private static final String PASSWORD_REP_ERR = "passwordRepErr";

    private Bundle errors = new Bundle();

    private OnFragmentInteractionListener mListener;

    // default constructor
    public SignUpFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        try {
            View fragmentSignInView = (View) getActivity().findViewById(R.id.fragment_sign_up_id);

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
            Button eyeButton = (Button) getActivity().findViewById(R.id.sign_up_eye_button);
            final EditText emailInput =
                    (EditText) getActivity().findViewById(R.id.sign_up_email_input);
            final EditText passwordInput =
                    (EditText) getActivity().findViewById(R.id.sign_up_password_input);
            final EditText passwordRepInput =
                    (EditText) getActivity().findViewById(R.id.sign_up_password_rep_input);
            final TextView emailErrView =
                    (TextView) getActivity().findViewById(R.id.sign_up_email_error_id);
            final TextView passwordErrView =
                    (TextView) getActivity().findViewById(R.id.sign_up_password_error_id);
            final TextView passwordRepErrView =
                    (TextView) getActivity().findViewById(R.id.sign_up_password_rep_error_id);

            eyeButton.setOnClickListener(new View.OnClickListener() {
                boolean isClicked = false;

                @Override
                public void onClick(View view) {
                    if (!isClicked) {
                        passwordInput.setTransformationMethod(null);
                        isClicked = true;
                    } else {
                        passwordInput.setTransformationMethod(new PasswordTransformationMethod());
                        isClicked = false;
                    }

                    passwordInput.setSelection(passwordInput.getText().length());
                }
            });

            signUpBut.setOnClickListener(new View.OnClickListener() {
                String email;
                String password;
                String passwordRep;

                @Override
                public void onClick(View view) {
                    emailErrView.setVisibility(View.GONE);
                    passwordErrView.setVisibility(View.GONE);
                    passwordRepErrView.setVisibility(View.GONE);

                    email = emailInput.getText().toString().toLowerCase();
                    password = passwordInput.getText().toString();
                    passwordRep = passwordRepInput.getText().toString();

                    userDataValidation(email, password, passwordRep);
                }
            });
        } catch (NullPointerException exception) {
            Log.e(TAG, "Some button is null. @setButtons()");
            exception.printStackTrace();
        }
    }

    private boolean userDataValidation(String email, String password, String passwordRep) {
        Pattern emailPattern =
                Pattern.compile("\\A[\\w\\-.+]+@[a-z\\d\\-]+(\\.[a-z\\d\\-]+)*\\.[a-z]+\\z");

//        bartek.andrzej@ss3-tg.com.dsd
//        daniel.galion94@gmail.com

        boolean isEmailCorrect = emailPattern.matcher(email).matches();
        boolean isPasswordCorrect = password.length() >= 6 && !password.trim().isEmpty();
        boolean isPasswordRepCorrect = password.equals(passwordRep);

        errors.clear();

        if (!isEmailCorrect) errors.putString(EMAIL_ERR, getString(R.string.incorrect_email));
        if (!isPasswordCorrect)
            errors.putString(PASSWORD_ERR, getString(R.string.incorrect_password));
        if (!isPasswordCorrect)
            errors.putString(PASSWORD_REP_ERR, getString(R.string.incorrect_password_rep));

        onErrorViewChange();

        return isEmailCorrect && isPasswordCorrect && isPasswordRepCorrect;
    }

    private void onErrorViewChange() {
        try {
            TextView emailErrView =
                    (TextView) getActivity().findViewById(R.id.sign_up_email_error_id);
            TextView passwordErrView =
                    (TextView) getActivity().findViewById(R.id.sign_up_password_error_id);
            TextView passwordRepErrView =
                    (TextView) getActivity().findViewById(R.id.sign_up_password_rep_error_id);


            if (errors.get(EMAIL_ERR) != null) {
                emailErrView.setVisibility(View.VISIBLE);
                emailErrView.setText((String) errors.get(EMAIL_ERR));
            }

            if (errors.get(PASSWORD_ERR) != null) {
                passwordErrView.setVisibility(View.VISIBLE);
                passwordErrView.setText((String) errors.get(PASSWORD_ERR));
            }

            if (errors.get(PASSWORD_REP_ERR) != null) {
                passwordRepErrView.setVisibility(View.VISIBLE);
                passwordRepErrView.setText((String) errors.get(PASSWORD_REP_ERR));
            }
        } catch (NullPointerException exception) {
            Log.e(TAG, "Some View or errors Bundle causes NPE @onErrorViewChange()");
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
