package com.harshit.sharma.receptacleadmin;

/**
 * Created by HARSHIT on 3/24/2018.
 */

public class adapterPacket {
    static int id;
    int requestId;
    String userEmail;
    String area;
    String status;
    adapterPacket()
    {
        userEmail = "";
        area = "";
    }
    adapterPacket(packet p)
    {
        this.requestId = id;
        id++;
        this.userEmail = p.userEmail;
        this.area =p.area;
        this.status=p.status;
    }
}
