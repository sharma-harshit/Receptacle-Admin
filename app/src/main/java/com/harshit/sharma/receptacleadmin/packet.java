package  com.harshit.sharma.receptacleadmin;

/*
 Created by HARSHIT on 3/24/2018.
*/

import java.util.List;

public class packet
{
    static int id;
    int requestId;
    String userEmail;
    String area;
    String picture;
    String status;
    packet() {
        requestId = id;
        id++;
        userEmail = "";
        area = "";
    }

    packet(String userEmail, String area, byte[] picture)
    {
        requestId = id;
        id++;
        this.userEmail = userEmail;
        this.area = area;
        this.picture=picture.toString();

        this.status="To be reviewed.";
    }
}

