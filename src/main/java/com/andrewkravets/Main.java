package com.andrewkravets;

import com.andrewkravets.model.Post;
import com.andrewkravets.model.User;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * User: andrey.kravets
 * Date: 1/10/14 12:08 PM
 */
public class Main {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        //create and set custom date format for our dates in POJOs
        DateFormat customDateFormat = new SimpleDateFormat("yyyy/dd/MM, HH:mm:ss");
        mapper.setDateFormat(customDateFormat);

        Writer writer = new StringWriter();
        User user = generateUser();
        System.out.println("User before JSON serialization: " + user);

        mapper.writeValue(writer, user);
        System.out.println("Serialized to JSON User: " +writer.toString());

        User restoredUser = mapper.readValue(writer.toString(), User.class);

        System.out.println("Restored from JSON User: "+restoredUser);

    }

    private static User generateUser() {
        User user = new User();
        user.setLogin("testUser");
        user.setPassword("testPassword");
        user.setRegistrationDate(new Date());
        user.setPosts(new ArrayList<Post>());

        for (int i = 0; i < 3; i++) {
            Post post = new Post();
            post.setTitle("New post title " + i);
            post.setText("New post text " + i);
            post.setPublicationDate(new Date());
            user.getPosts().add(post);
        }

        return user;
    }


}
