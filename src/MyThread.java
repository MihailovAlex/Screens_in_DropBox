import com.dropbox.core.v2.DbxClientV2;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyThread extends Thread {
    DbxClientV2 client;
    InputStream in;
    SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy_HH:mm:ss");

    public MyThread(DbxClientV2 client, InputStream in) {
        this.client = client;
        this.in = in;
    }

    public void run() {
        try {
            client.files().uploadBuilder("/"+formatter.format(new Date())+".jpg").uploadAndFinish(in);
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
