package com.example.detlef.fall2what.gamelogic;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.detlef.fall2what.ConnectionActivity;
import com.example.detlef.fall2what.R;

import java.util.ArrayList;
import java.util.Set;

import static android.support.v4.app.ActivityCompat.startActivityForResult;

/**
 * Created by Detlef on 12-Jan-16.
 */


public class BluetoothConnector
{

    private BroadcastReceiver mReceiver;
    private BluetoothAdapter mBluetoothAdapter;
    private ConnectionActivity connectionActivity;
    private Set<BluetoothDevice> pairedDevices;
    ListView lv;
    private final static int REQUEST_ENABLE_BT = 1;

    public BluetoothConnector(ConnectionActivity connectionActivity)
    {
        this.connectionActivity = connectionActivity;
        this.lv = connectionActivity.listView;
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null)
            Log.e("hardware error", "il dispositivo non possiede un sensore bluetooth");
        else
            Log.e("success", "Bluetooth supported");
    }



    public void on()
    {
        if (!mBluetoothAdapter.isEnabled()) {
            Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            connectionActivity.startActivityForResult(turnOn, 0);
            Toast.makeText(connectionActivity.getApplicationContext(),"Turned on",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(connectionActivity.getApplicationContext(),"Already on", Toast.LENGTH_LONG).show();
        }
    }

    public void off()
    {
        mBluetoothAdapter.disable();
        Toast.makeText(connectionActivity.getApplicationContext(),"Turned off" ,Toast.LENGTH_LONG).show();
    }

    public void visible()
    {
        Intent getVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        connectionActivity.startActivityForResult(getVisible, 0);
    }

    public void list(){
        pairedDevices = mBluetoothAdapter.getBondedDevices();
        ArrayList list = new ArrayList();

        for(BluetoothDevice bt : pairedDevices)
            Toast.makeText(connectionActivity.getApplicationContext(),bt.getName(),Toast.LENGTH_SHORT).show();

        /*final ArrayAdapter adapter = new ArrayAdapter(connectionActivity.getApplicationContext(),android.R.layout.simple_list_item_1, list);
        lv.setAdapter(adapter);*/


        IntentFilter filter = new IntentFilter();

        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);

         mReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();

                if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
                    //discovery starts, we can show progress dialog or perform other tasks
                } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                    //discovery finishes, dismis progress dialog
                } else if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                    //bluetooth device found
                    BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    Toast.makeText(connectionActivity.getApplicationContext(),"Found device " + device.getName(),Toast.LENGTH_SHORT).show();
                }
            }
        };

        connectionActivity.registerReceiver(mReceiver, filter);
        mBluetoothAdapter.startDiscovery();

    }


    public void disconnect(){

            connectionActivity.unregisterReceiver(mReceiver);
        
    }




}
