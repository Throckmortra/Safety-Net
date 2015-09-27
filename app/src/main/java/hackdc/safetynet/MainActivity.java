package hackdc.safetynet;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.getpebble.android.kit.PebbleKit;
import com.getpebble.android.kit.util.PebbleDictionary;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import hackdc.safetynet.API.RestManager;
import hackdc.safetynet.API.RestRequests;
import hackdc.safetynet.API.models.BlankResponse;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final UUID PEBBLE_APP_UUID = UUID.fromString("e06e86a8-8c29-4f62-9640-6ef21e1f1480");
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private Context mContext;
    private FloatingActionButton fab;
    private String mActivityTitle;
    private ActionBarDrawerToggle mDrawerToggle;
    private ArrayAdapter<String> mArrayAdapter;
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static final String TAG = "MainActivity";
    private LinearLayout progress, nextAppt, rankToday;
    private RelativeLayout rl;
    private static TextView quote, rank;
    private String[] quotes = {"You're the greatest running buddy ever! -Carrie", "You're making great progress! -Jake", "I love you honey! -Mom"};

    private BroadcastReceiver mRegistrationBroadcastReceiver;

    Thread t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = getApplicationContext();

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FragmentHolder.class);
                intent.putExtra("buttonID", 3 + "");
                startActivity(intent);
            }
        });
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        quote = (TextView) findViewById(R.id.quoteBar);
        progress = (LinearLayout) findViewById(R.id.progressBar);
        nextAppt = (LinearLayout) findViewById(R.id.nextApptBar);
        rankToday = (LinearLayout) findViewById(R.id.rankTodayBar);
        rank = (TextView) findViewById(R.id.ranker);
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

        PebbleKit.registerReceivedDataHandler(this, new PebbleKit.PebbleDataReceiver(PEBBLE_APP_UUID) {

            @Override
            public void receiveData(final Context context, final int transactionId, final PebbleDictionary data) {
                Log.d("pebble data", "Received : " + data.getInteger(0));

                RestAdapter restAdapter = RestManager.getRestAdapter();
                RestRequests request = restAdapter.create(RestRequests.class);

                String message = "";
                String[] devices = {"ehwrqwezUrM:APA91bFctFHgH7q7DmUms_zHc7iDojBzBL_i9pHnz4d1nK5os1QKbxd-GO-3ssWuF7hekjEGSA6m7ibjCOxlNot3Jgx9uXkstAx0UwgRLbln_2sX3AzETO4zEDNEtpKaSBHLf0Gm5417",
                        "fAX0A9j3rpY:APA91bEqk_lsBg_6_yisr3X51aLIa-ZMI3Z_QtDnMnZL-yGht9jpUSqbT-sFvonIY4n_LpNRvaL0YkpfrLGLXo8tgt_GlyAoHn3XjRjxWyEu1SHercgieGkJK1PlO0Hk5lDtx-TLvKow",
                        "dJlV0rgg3-4:APA91bEhW4O_lIYBIaNKcyCMfd4ZXoZ3SdavpK2UiRxsJ94rz10DBCCFZt1S3QJYFHYzso60rtjvoObT8FthREt5GuuBLKRtGl5gKS7iqoYTMOfFOYEEnxjKhO4DbucSi8216F4PMAq6"};

                switch (data.getInteger(0).intValue()) {
                    case 0:
                        message = "help, i'm experiencing an episode";
                        HashMap body = new HashMap<>(2);
                        body.put("message", message);
                        body.put("devices", devices);

                        request.helpRequest(body, new Callback<BlankResponse>() {
                            @Override
                            public void success(BlankResponse blankResponse, Response response) {
                                Log.d("helpRequest", "success");
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                Log.d("helpRequest", "failed");
                            }
                        });

                        break;
                    case 1:
                        message = "select";
                        break;
                    case 2:
                        message = "down";
                        break;
                }


//                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                PebbleKit.sendAckToPebble(getApplicationContext(), transactionId);
            }
        });
        Random r = new Random();
        quote.setText(quotes[r.nextInt(3)]);
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
            intent.putExtra("buttonID", 4 + "");
            startActivity(intent);
        }
        if(v.getId() == R.id.rankTodayBar){
            Intent intent = new Intent(MainActivity.this, FragmentHolder.class);
            intent.putExtra("buttonID", 2 + "");
            startActivity(intent);
        }
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


    public static void setRank(String r) {
        rank.setText("Your rank today is: " + r);
    }

}
