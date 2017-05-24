package com.example.mehadihossain.z_ridedemo.fragment.body.useraccount;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.example.mehadihossain.z_ridedemo.R;

public class LogInFragment extends Fragment implements View.OnClickListener {
    private OnLogInFragmentInteractionListener mListener;
    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    //private UserLoginTask mAuthTask = null;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private Button signInButton;
    SharedPreferences.Editor spEditor;


    public LogInFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_log_in, container, false);
        signInButton = (Button) view.findViewById(R.id.sign_in_button);
        mPasswordView = (EditText) view.findViewById(R.id.password);
        return  view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        signInButton.setOnClickListener(this);
        SharedPreferences sharedPreference = getActivity().getSharedPreferences("z-ride-signinfo",Context.MODE_PRIVATE);
        spEditor = sharedPreference.edit();
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.sign_in_button){
            if((mPasswordView.getText().toString()).equals("12345"))
            spEditor.putString("sign_info","yes");
            spEditor.commit();
            mListener.onLogInFragmentInteraction(true);
        }else {
            mListener.onLogInFragmentInteraction(false);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnLogInFragmentInteractionListener) {
            mListener = (OnLogInFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnLogInFragmentInteractionListener {
        // TODO: Update argument type and name
        void onLogInFragmentInteraction(boolean successful);
    }
}
