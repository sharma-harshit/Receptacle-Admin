package com.harshit.sharma.receptacleadmin;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Comment;

public class MainActivity extends AppCompatActivity
{
    TextView username;
    TextView requestId;
    TextView area;
    TextView status;
    ImageView imageView;

    DatabaseReference mRef;
    ChildEventListener childEventListener;
    ListView listView;
    ArrayAdapter adapter;
    ValueEventListener vel;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        username = (TextView) findViewById(R.id.tusername);
        requestId = (TextView) findViewById(R.id.trequestId);
        area = (TextView) findViewById(R.id.tarea);
        status = (TextView) findViewById(R.id.tstatus);
        imageView = (ImageView) findViewById(R.id.img);

        mRef = FirebaseDatabase.getInstance().getReference().child("requests").child("Vishu");

        vel =new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                Log.d("Child Added", "onChildAdded:" + dataSnapshot.getKey());
                packet p = dataSnapshot.getValue(packet.class);
                if(!p.userEmail.equals(""))
                {
                    username.setText("User : "+p.userEmail);
                    requestId.setText("Request id : "+p.requestId);
                    area.setText("Area : "+p.area);
                    status.setText("Status : "+p.status);

                    byte[] decodedByteArray = Base64.decode(p.picture, Base64.NO_WRAP);
                    imageView.setImageBitmap(BitmapFactory.decodeByteArray(decodedByteArray, 0,decodedByteArray.length));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        mRef.addListenerForSingleValueEvent(vel);
    }

    void refresh()
    {
        adapter.notifyDataSetChanged();
    }
    public void onPause()
    {
        super.onPause();
        if(childEventListener!=null)
            mRef.removeEventListener(vel);
    }
    public void onResume()
    {
        super.onResume();
        if(childEventListener!=null)
            mRef.removeEventListener(vel);
    }
}
