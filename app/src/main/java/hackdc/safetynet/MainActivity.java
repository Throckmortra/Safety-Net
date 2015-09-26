package hackdc.safetynet;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
<<<<<<< HEAD
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
=======
>>>>>>> 3313324be8aa18fa7ea6a2e58230afe956439bbe
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
<<<<<<< HEAD
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
=======
import android.widget.TextView;
import android.widget.Toast;

>>>>>>> 3313324be8aa18fa7ea6a2e58230afe956439bbe


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private Context mContext;
    private FloatingActionButton fab;
    private String mActivityTitle;
    private ActionBarDrawerToggle mDrawerToggle;
    private ArrayAdapter<String> mArrayAdapter;
    private String [] fakeData;
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static final String TAG = "MainActivity";
<<<<<<< HEAD
    private LinearLayout progress, nextAppt, rankToday;
    private RelativeLayout rl;
=======
>>>>>>> 3313324be8aa18fa7ea6a2e58230afe956439bbe

    private BroadcastReceiver mRegistrationBroadcastReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = getApplicationContext();

//TODO: WORK ON CLICK FOR ADD EPISODE REPORT
//        fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(TabActivity.this, OrangeTabsActivity.class);
//                intent.putExtra("buttonID", 1 + "");
//                startActivity(intent);
//            }
//        });
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        progress = (LinearLayout) findViewById(R.id.progressBar);
        nextAppt = (LinearLayout) findViewById(R.id.nextApptBar);
        rankToday = (LinearLayout) findViewById(R.id.rankTodayBar);
        rl = (RelativeLayout) findViewById(R.id.overallBox);

        progress.setOnClickListener(this);
        nextAppt.setOnClickListener(this);
        rankToday.setOnClickListener(this);

        mActivityTitle = getTitle().toString();
        addDrawerItems();
        setupDrawer();

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("nav bar click", position + "");
                if (position == 0) {
//                    Intent intent = new Intent(TabActivity.this, OrangeTabsActivity.class);
//                    intent.putExtra("buttonID", 0 + "");
//                    startActivity(intent);
                } else if (position == 1) { //NOTIFICATIONS

                } else if (position == 2) { //ADD FRIENDS

                } else if (position == 3) { //GROUPS
                } else if (position == 5) {
//                    logout();
                }
            }
        });

        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                R.string.drawer_open,
                R.string.drawer_close) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        startGcm();

<<<<<<< HEAD
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.nextApptBar){
            Intent intent = new Intent(MainActivity.this, FragmentHolder.class);
            intent.putExtra("buttonID", 0 + "");
            startActivity(intent);
        }
        if(v.getId() == R.id.progressBar){
            Intent intent = new Intent(MainActivity.this, FragmentHolder.class);
            intent.putExtra("buttonID", 1 + "");
            startActivity(intent);
        }
        if(v.getId() == R.id.rankTodayBar){
            Intent intent = new Intent(MainActivity.this, FragmentHolder.class);
            intent.putExtra("buttonID", 2 + "");
            startActivity(intent);
        }
=======


>>>>>>> 3313324be8aa18fa7ea6a2e58230afe956439bbe
    }

    private void startGcm() {

        Log.d("ayyy", "lmao");
        Intent intent = new Intent(this, RegistrationIntentService.class);
        startService(intent);
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d("ayyy", "lmao");
                Toast.makeText(getApplicationContext(), "recieved shit", Toast.LENGTH_LONG).show();
                SharedPreferences sharedPreferences =
                        PreferenceManager.getDefaultSharedPreferences(context);
                boolean sentToken = sharedPreferences
                        .getBoolean(QuickStartPreferences.SENT_TOKEN_TO_SERVER, false);
                if (sentToken) {
                    Toast.makeText(getApplicationContext(), "sent token", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "toekn error", Toast.LENGTH_LONG).show();

                }
            }
        };

        // Start IntentService to register this application with GCM.

    }

    private void addDrawerItems() {
        String[] navItems = { "Dashboard", "Resources", "Settings", "Sign Out" };
        mArrayAdapter = new ArrayAdapter<String>(this, R.layout.drawer_list_item, R.id.label, navItems);
        mDrawerList.setAdapter(mArrayAdapter);
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
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

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            Log.d("drawer click", item.toString());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
