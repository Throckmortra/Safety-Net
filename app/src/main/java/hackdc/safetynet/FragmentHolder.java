package hackdc.safetynet;

/**
 * Created by eakorcovelos on 9/26/15.
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;


public class FragmentHolder extends FragmentActivity implements CalendarFragment.OnFragmentInteractionListener{

    private ProgressDialog mProgressDialog;
    private String mButtonID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.fragment_holder);
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        mButtonID = intent.getStringExtra("buttonID");
        selectItem(Integer.parseInt(mButtonID));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void selectItem(int position) {
        Fragment fragment;

        switch(position){
            case 0: {
                // show list of past and current clinician visits
                //TODO:what happens if they have no clinician?
                fragment = new CalendarFragment();
                break;
            }
            case 1: {
                // pull up the progress of the individual
                fragment = new CalendarFragment();
                break;
            }
            case 2: {
                //show grid view of
                fragment = new GridFragment();
                break;
            }
            case 3: {
                fragment = new FormFragment();
                break;
            }
            case 4: {
                fragment = new ReportListFragment();
                break;
            }
            default: {
                fragment = null;
                break;
            }
        }

        replaceFragment(fragment);
    }

    public void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_view, fragment)
                .commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container_view);
        fragment.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
