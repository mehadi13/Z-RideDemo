package com.example.mehadihossain.z_ridedemo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.mehadihossain.z_ridedemo.fragment.BasketFragment;
import com.example.mehadihossain.z_ridedemo.fragment.InformationFragment;
import com.example.mehadihossain.z_ridedemo.fragment.ViewDetailsFragment;
import com.example.mehadihossain.z_ridedemo.fragment.HeaderFragment;
import com.example.mehadihossain.z_ridedemo.fragment.MenuFragment;
import com.example.mehadihossain.z_ridedemo.fragment.OperationsFragment;
import com.example.mehadihossain.z_ridedemo.fragment.PaymentModuleFragment;
import com.example.mehadihossain.z_ridedemo.fragment.PickAndDropFragment;
import com.example.mehadihossain.z_ridedemo.fragment.PlaceListFragment;
import com.example.mehadihossain.z_ridedemo.fragment.RideRequestFragment;
import com.example.mehadihossain.z_ridedemo.fragment.ServiceSelectionFragment;
import com.example.mehadihossain.z_ridedemo.fragment.user.LogInFragment;
import com.example.mehadihossain.z_ridedemo.util.CalculateWorkingDay;

public class MainActivity extends AppCompatActivity implements BasketFragment.OnBasketFragmentListener,
        ServiceSelectionFragment.OnServiceSelectionFragmentListener,PickAndDropFragment.OnPickAndDropFragmentListener,
        PlaceListFragment.OnPlaceListFragmentInteractionListener,ViewDetailsFragment.OnViewDetailsFragmentListener,
        MenuFragment.OnFragmentInteractionListener,HeaderFragment.OnHeaderFragmentListener,
        OperationsFragment.OnOperationFragmentListener,RideRequestFragment.OnRideRequestFragmentListener,
        InformationFragment.OnInformationFragmentInteractionListener,
        LogInFragment.OnLogInFragmentInteractionListener{

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment activeFragment;
    private boolean isSignIn = false;
    private Menu menu;
    private View headerFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        headerFragment = findViewById(R.id.header_fragment);

        activeFragment = new PlaceListFragment();
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.body_container,activeFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onPlaceListFragmentInteraction() {
      removeAndAddFragment(activeFragment,new ServiceSelectionFragment());
    }

    @Override
    public void onPickAndDropFragmentInteraction() {
      removeAndAddFragment(activeFragment,new BasketFragment());
    }

    @Override
    public void OnServiceSelectionFragmentInteraction() {
        removeAndAddFragment(activeFragment,new OperationsFragment());
    }

    @Override
    public void OnBasketFragmentInteraction() {
       removeAndAddFragment(activeFragment,new PaymentModuleFragment());
    }

    @Override
    public void onHeaderFragmentInteractionHome() {
        removeAndAddFragment(activeFragment,new PlaceListFragment());
        setMenuItemVisibility(0,true);
        setMenuItemVisibility(1,true);
        setMenuItemVisibility(2,false);
    }

    @Override
    public void onHeaderFragmentInteractionBack() {

    }

    @Override
    public void OnOperationFragmentInteraction(String title) {
        if(title.equals("new")) {
            removeAndAddFragment(activeFragment,new RideRequestFragment());
        }else {
            removeAndAddFragment(activeFragment, new ViewDetailsFragment());
        }
    }

    @Override
    public void OnRideRequestFragmentInteraction(String title) {
            PickAndDropFragment pickAndDropFragment = new PickAndDropFragment();
            pickAndDropFragment.setTitle(title);
            removeAndAddFragment(activeFragment, pickAndDropFragment);
    }

    @Override
    public void onViewDetailsFragmentInteraction() {
        removeAndAddFragment(activeFragment, new InformationFragment());
    }

    @Override
    public void onInformationFragmentInteraction() {

    }

    @Override
    public void onLogInFragmentInteraction() {

    }

    private void removeAndAddFragment(Fragment toRemove,Fragment toAdd){
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(toRemove);
        fragmentTransaction.add(R.id.body_container,toAdd);
        fragmentTransaction.commit();
        activeFragment = toAdd;

        if(activeFragment instanceof PlaceListFragment){
            headerFragment.setVisibility(View.GONE);
        }else if(headerFragment.getVisibility()==View.GONE){
            headerFragment.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(!isSignIn) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.usermenu, menu);
            this.menu = menu;
            setMenuItemVisibility(2,false);
            return true;
        }else {
            setMenuItemVisibility(0,false);
            setMenuItemVisibility(1,false);
            return false;
        }
    }

    void setMenuItemVisibility(int index,boolean visible){
        menu.getItem(index).setVisible(visible);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.siginin:
                Toast.makeText(this, "Sign in selected", Toast.LENGTH_SHORT)
                        .show();
                removeAndAddFragment(activeFragment,new LogInFragment());
                setMenuItemVisibility(0,false);
                setMenuItemVisibility(1,false);
                break;
            // action with ID action_settings was selected
            case R.id.signup:
                Toast.makeText(this, "Sign up selected", Toast.LENGTH_SHORT)
                        .show();
                break;
            default:
                break;
        }
        return true;
    }
}
