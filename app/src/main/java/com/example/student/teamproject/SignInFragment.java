package com.example.student.teamproject;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignInFragment extends Fragment {
    private static final String TAG = "SignInFragment";

    private OnFragmentInteractionListener mListener;
    private List<NotesModel> notesList;

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
                    (AppCompatActivity) getActivity(), view, getString(R.string.signing_in));

        } catch (NullPointerException e) {
            Log.e(TAG, "Cannot get activity. @onViewCreated(..)");
        }

        SqliteDbUtils db = new SqliteDbUtils(getContext());

        db.addItem("date", "title1", "desc", true);
        db.addItem("date", "title2", "desc", true);
        db.addItem("date", "title3", "desc", true);
        db.addItem("date", "title4", "desc", true);

//        db.deleteItem("date", "title1");
//        db.deleteItem("date", "title2");
        db.deleteItem("date", "title3");
        db.deleteItem("date", "title4");

        notesList = db.getList();

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
            Button getListBut =
                    (Button) getActivity().findViewById(R.id.sign_in_get_list_button_id);
            Button eyeButton = (Button) getActivity().findViewById(R.id.sign_in_eye_button_id);
            final EditText emailInput =
                    (EditText) getActivity().findViewById(R.id.sign_in_email_input_id);
            final EditText passwordInput =
                    (EditText) getActivity().findViewById(R.id.sign_in_password_input_id);
            Button signInButton = (Button) getActivity().findViewById(R.id.sign_in_login_button_id);
            Button signupButton =
                    (Button) getActivity().findViewById(R.id.sign_in_sign_up_button_id);


            final List<NotesModel> fList = notesList;
            getListBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("list", fList.toString());
                    Log.d("list.size", "" + fList.size());
                }
            });

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

            signInButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String email = emailInput.getText().toString();
                    String password = passwordInput.getText().toString();

                    signIn(email, password);
                }
            });

            signupButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Fragment fragment = new SignUpFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.contentContainer, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            });
        } catch (NullPointerException exception) {
            Log.e(TAG, "Some button is null. @setButtons()");
            exception.printStackTrace();
        }
    }

    private void signIn(final String email, final String password) {
        if (email.equals("root") && password.equals("foobar")) {
            Toast.makeText(getContext(), R.string.correct_login_data, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(
                    getContext(), R.string.incorrect_login_data, Toast.LENGTH_LONG).show();
        }

        String apiUrl = getString(R.string.calendar_api_link1);

        if (getContext() != null) {
            Object queue = Volley.newRequestQueue(getContext());

            StringRequest postRequest = new StringRequest(Request.Method.POST, apiUrl,

                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                Log.d(TAG, "UserResponse: " + response);
//                                Gson gson = new GsonBuilder().create();
//                                UserModel feed = gson.fromJson(response, UserModel.class);
//
//                                if (feed.data != null) {
//                                    UserModel.id = feed.data.id
//                                    UserModel.name = "${feed.data.first_name} ${feed.data.last_name}"
//                                    UserModel.email = feed.data.email
//                                }

                            } catch (Exception exception) {
                                Log.e(TAG, "@onResponse(..)");
                                exception.printStackTrace();
                            }
                        }

                    },

                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            String errorText = "Błąd połączenia z serwerem.";
                            Toast.makeText(getContext(), errorText, Toast.LENGTH_LONG).show();

                            Log.e(TAG, error.toString());
//                            String body = "";
//                            //get status code here
//                            String statusCode = String.valueOf(error.networkResponse.statusCode);
//                            //get response body and parse with appropriate encoding
//
//                            if(error.networkResponse.data!=null) {
//                                try {
//                                    body = new String(
//                                            error.networkResponse.data,"UTF-8");
//                                } catch (UnsupportedEncodingException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//
//                            Log.d(TAG, "Body: " + body + " @onErrorResponse(..)");
                        }
                    }) {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();

                    params.put("login", email);
                    params.put("passwd", password);

                    Log.d(TAG, "Params: " + params + " @getParams()");

                    return params;
                }
            };
            ((RequestQueue) queue).add(postRequest);

        } else {
            Log.e(TAG, "Response: context is null. @signIn(..)");
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
