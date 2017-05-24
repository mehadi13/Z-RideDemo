package com.example.mehadihossain.z_ridedemo.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.mehadihossain.z_ridedemo.R;
import com.example.mehadihossain.z_ridedemo.fragment.body.BasketFragment;
import com.example.mehadihossain.z_ridedemo.fragment.body.InformationFragment;
import com.example.mehadihossain.z_ridedemo.fragment.body.ViewDetailsFragment;
import com.example.mehadihossain.z_ridedemo.fragment.HeaderFragment;
import com.example.mehadihossain.z_ridedemo.fragment.MenuFragment;
import com.example.mehadihossain.z_ridedemo.fragment.body.OperationsFragment;
import com.example.mehadihossain.z_ridedemo.fragment.body.PaymentModuleFragment;
import com.example.mehadihossain.z_ridedemo.fragment.body.PickAndDropFragment;
import com.example.mehadihossain.z_ridedemo.fragment.body.PlaceListFragment;
import com.example.mehadihossain.z_ridedemo.fragment.body.RideRequestFragment;
import com.example.mehadihossain.z_ridedemo.fragment.body.ServiceSelectionFragment;
import com.example.mehadihossain.z_ridedemo.fragment.body.useraccount.LogInFragment;

public class MainActivity extends AppCompatActivity implements BasketFragment.OnBasketFragmentListener,
        ServiceSelectionFragment.OnServiceSelectionFragmentListener,PickAndDropFragment.OnPickAndDropFragmentListener,
        PlaceListFragment.OnPlaceListFragmentInteractionListener,ViewDetailsFragment.OnViewDetailsFragmentListener,
        MenuFragment.OnFragmentInteractionListener,HeaderFragment.OnHeaderFragmentListener,
        OperationsFragment.OnOperationFragmentListener,RideRequestFragment.OnRideRequestFragmentListener,
        InformationFragment.OnInformationFragmentInteractionListener,
        LogInFragment.OnLogInFragmentInteractionListener{
    /*
    Main activity has 3 section.
    1. Header ->  It contains two button, (a)Home & (b)Back. This sections visibility depends on the active fragment of Body section.
                  It invisible for place list & visible for other fragment
    2. Menu -> Contains logo
    3. Body -> fragment transaction take place here
     */

    //heading componant
    private Menu menu;
    private View headerFragment;

    // variable for fragment tracaction
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment activeFragment;

    //to check user state
    private SharedPreferences sharedPreferences;
    private boolean isSignIn = false;
    private String SIGNIN_DEFAULT = "N/A";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //check if a user signed in or not
        sharedPreferences = getSharedPreferences("z-ride-signinfo", Context.MODE_PRIVATE);
        String sign_in = sharedPreferences.getString("sign_info",SIGNIN_DEFAULT);
        if(!sign_in.equals(SIGNIN_DEFAULT)){
            isSignIn = true;
        }

        //create a view obj of header section
        headerFragment = findViewById(R.id.header_fragment);

        //at init state show place list
        activeFragment = new PlaceListFragment();
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.body_container,activeFragment,"placelist");
        fragmentTransaction.addToBackStack("placelist");
        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onPlaceListFragmentInteraction() {
      removeAndAddFragment(new ServiceSelectionFragment(),"service");
    }

    @Override
    public void onPickAndDropFragmentInteraction() {
      removeAndAddFragment(new PaymentModuleFragment(),"payment");
    }

    @Override
    public void OnServiceSelectionFragmentInteraction() {
        if(isSignIn) {
            removeAndAddFragment(new OperationsFragment(), "operation");
        }else{
            Toast.makeText(this,"Please sign in",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void OnBasketFragmentInteraction() {
       //removeAndAddFragment(activeFragment,new PaymentModuleFragment(),"payment");
    }

    @Override
    public void onHeaderFragmentInteractionHome() {
        removeAndAddFragment(new PlaceListFragment(),"placelist");
    }

    @Override
    public void onHeaderFragmentInteractionBack() {
        backPressed();
    }

    private void backPressed(){
        int index = fragmentManager.getBackStackEntryCount();
        if(index>1) {
            fragmentManager.popBackStack();
            index = index - 2;
            FragmentManager.BackStackEntry backEntry = fragmentManager.getBackStackEntryAt(index);
            String tag = backEntry.getName();
            Toast.makeText(this, Integer.toString(index) + "\n" + tag, Toast.LENGTH_SHORT).show();
            if (tag.equals("placelist")) {
                headerFragment.setVisibility(View.GONE);
            } else if (headerFragment.getVisibility() == View.GONE) {
                headerFragment.setVisibility(View.VISIBLE);
            }
            signInfoCheck();
        }else{
            finish();
        }
    }
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        backPressed();
    }

    @Override
    public void OnOperationFragmentInteraction(String title) {
        if(title.equals("new")) {
            removeAndAddFragment(new RideRequestFragment(),"request");
        }else {
            removeAndAddFragment(new ViewDetailsFragment(),"viewdetails");
        }
    }

    @Override
    public void OnRideRequestFragmentInteraction(String title) {
            PickAndDropFragment pickAndDropFragment = new PickAndDropFragment();
            pickAndDropFragment.setTitle(title);
            removeAndAddFragment(pickAndDropFragment,"pickdrop");
    }

    @Override
    public void onViewDetailsFragmentInteraction() {
        removeAndAddFragment(new InformationFragment(),"info");
    }

    @Override
    public void onInformationFragmentInteraction() {

    }

    @Override
    public void onLogInFragmentInteraction(boolean successful) {
        if(successful){
            backPressed();
            isSignIn = true;
            signInfoCheck();
        }else {
            signInfoCheck();
        }
    }

    //fragment transaction
    private void removeAndAddFragment(Fragment toAdd,String tag){
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.body_container,toAdd,tag);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();
        activeFragment = toAdd;
        setHeaderVisibility();
    }

    private void setHeaderVisibility(){
        //setup header fragment visibility
        if(activeFragment instanceof PlaceListFragment){
            headerFragment.setVisibility(View.GONE);
        }else if(headerFragment.getVisibility()==View.GONE){
            headerFragment.setVisibility(View.VISIBLE);
        }

        //setup menu option visibility
        if(activeFragment instanceof LogInFragment){
            setMenuItemVisibility(0,false);
            setMenuItemVisibility(1,false);
            setMenuItemVisibility(2,false);
        }else{
            signInfoCheck();
        }
    }

    // Menu functionality
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.usermenu, menu);
        this.menu = menu;
        signInfoCheck();
        return true;
    }

    private void signInfoCheck(){
        if(!isSignIn) {
            setMenuItemVisibility(0,true);
            setMenuItemVisibility(1,true);
            setMenuItemVisibility(2,false);
        }else {
            setMenuItemVisibility(0,false);
            setMenuItemVisibility(1,false);
            setMenuItemVisibility(2,true);
        }
    }
    void setMenuItemVisibility(int index,boolean visible){
        menu.getItem(index).setVisible(visible);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID sign in was selected
            case R.id.siginin:
                Toast.makeText(this, "Sign in selected", Toast.LENGTH_SHORT)
                        .show();
                removeAndAddFragment(new LogInFragment(),"login");
                break;
            // action with ID sign up was selected
            case R.id.signup:
                Toast.makeText(this, "Sign up selected", Toast.LENGTH_SHORT)
                        .show();
                break;
            // action with ID sign out was selected
            case R.id.signout:
                Toast.makeText(this, "Sign out selected", Toast.LENGTH_SHORT)
                        .show();
                removeAndAddFragment(new PlaceListFragment(),"placelist");
                SharedPreferences.Editor spEditor = sharedPreferences.edit();
                spEditor.putString("sign_info",SIGNIN_DEFAULT);
                spEditor.commit();
                isSignIn=false;
                signInfoCheck();
                break;
            default:
                break;
        }
        return true;
    }
}
