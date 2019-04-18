package utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import org.apache.commons.lang3.RandomStringUtils;

/**
 *
 * @author ihebc_000
 */
public class UploadImage {

    private static UploadImage instance;

    private final String CrLf = "\r\n";

    public static UploadImage getInstance() {

        if (instance == null) {
            instance = new UploadImage();
        }
        return instance;
    }

    public void upload(String image, String name) {

        URLConnection conn = null;
        OutputStream os = null;
        InputStream is = null;

        try {
            URL url = new URL("http://localhost/upload.php");
            conn = url.openConnection();
            conn.setDoOutput(true);

            String postData = "";

            FileInputStream imgIs = new FileInputStream(image);
            byte[] imgData = new byte[imgIs.available()];
            imgIs.read(imgData);

            String message1 = "";
            message1 += "-----------------------------4664151417711" + CrLf;
            message1 += "Content-Disposition: form-data; name=\"uploadedfile\"; filename=" + name + ""
                    + CrLf;
            message1 += "Content-Type: image/jpeg" + CrLf;
            message1 += CrLf;

            // the image is sent between the messages in the multipart message.
            String message2 = "";
            message2 += CrLf + "-----------------------------4664151417711--"
                    + CrLf;

            conn.setRequestProperty("Content-Type",
                    "multipart/form-data; boundary=---------------------------4664151417711");
            // might not need to specify the content-length when sending chunked
            // data.
            conn.setRequestProperty("Content-Length", String.valueOf((message1
                    .length() + message2.length() + imgData.length)));

            os = conn.getOutputStream();

            os.write(message1.getBytes());

            // SEND THE IMAGE
            int index = 0;
            int size = 1024;
            do {
                if ((index + size) > imgData.length) {
                    size = imgData.length - index;
                }
                os.write(imgData, index, size);
                index += size;
            } while (index < imgData.length);

            os.write(message2.getBytes());
            os.flush();

            is = conn.getInputStream();

            char buff = 512;
            int len;
            byte[] data = new byte[buff];
            do {
                len = is.read(data);

            } while (len > 0);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                os.close();
            } catch (Exception e) {
            }
            try {
                is.close();
            } catch (Exception e) {
            }
            try {

            } catch (Exception e) {
            }
        }
    }

}
