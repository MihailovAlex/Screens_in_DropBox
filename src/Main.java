import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws AWTException, IOException {

        String ACCESS_TOKEN = "Access token"; //скрыт в целях конфиденциальности
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/Apps/Screen_shot_folder").build();
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

        InputStream in;
        ByteArrayOutputStream os;
        BufferedImage image;

        while (true) {
            try {
                sleep(5000);  // каждые 5 сек
                Rectangle area = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
                image = new Robot().createScreenCapture(area);
                os = new ByteArrayOutputStream();
                ImageIO.write(image, "jpg", os);
                in = new ByteArrayInputStream(os.toByteArray());
                MyThread myThread = new MyThread(client, in);
                myThread.start();
            } catch (Exception ex) {
                System.out.println(ex.fillInStackTrace());
            }

        }
    }
}
