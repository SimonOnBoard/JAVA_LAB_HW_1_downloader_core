package tasks;


import javax.imageio.ImageReader;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ImageLoader {
    public static Connection connection;
    public String filePath;
    private ImageLoader(Connection connection) {
        this.connection = connection;
    }

    public static ImageLoader create(String url, String username, String password) {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
        return new ImageLoader(connection);
    }

    public void load(String url, String path) {
        try {
            URL website = new URL(url);
            ReadableByteChannel rbc;
            rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream(this.getFileName(url, path));
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
            rbc.close();
            FileSaver.saveInfo(connection,filePath);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private String getFileName(String url, String path) {
        String[] splited = url.split("/");
        this.filePath = path + '/' + url.substring(url.lastIndexOf("/")+1);
        return filePath;
    }
}
