package exportation.test;

import exportation.model.da.UserDa;
import exportation.model.entity.User;

import java.util.List;

public class UserDaTest {
    public static void main(String[] args) {
        try {
            testSaveUser();
            testFindByUsername();
            testEditUser();
            testDeleteUser();
            testFindAllUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testSaveUser() throws Exception {
        UserDa userDa = new UserDa();

        User user = User.builder()
                .username("testuser")
                .password("testpass")
                .enabled(true)
                .build();

        try {
            User savedUser = userDa.save(user);
            System.out.println("Saved user: " + savedUser);
        } finally {
            userDa.close();
        }
    }

    public static void testEditUser() throws Exception {
        UserDa userDa = new UserDa();

        String usernameToFind = "testuser";

        try {
            User foundUser = userDa.findByUsername(usernameToFind);
            if (foundUser != null) {
                System.out.println("Found user to edit: " + foundUser);

                // Modify the user
                foundUser.setPassword("newpassword");
                foundUser.setEnabled(false);

                User editedUser = userDa.edit(foundUser);
                System.out.println("Edited user: " + editedUser);
            } else {
                System.out.println("User with username '" + usernameToFind + "' not found.");
            }
        } finally {
            userDa.close();
        }
    }

    public static void testDeleteUser() throws Exception {
        UserDa userDa = new UserDa();

        String usernameToDelete = "testuser";

        try {
            User foundUser = userDa.findByUsername(usernameToDelete);
            if (foundUser != null) {
                System.out.println("Found user to delete: " + foundUser);

                User deletedUser = userDa.remove(foundUser.getId());
                System.out.println("Deleted user: " + deletedUser);
            } else {
                System.out.println("User with username '" + usernameToDelete + "' not found.");
            }
        } finally {
            userDa.close();
        }
    }

    public static void testFindAllUsers() throws Exception {
        UserDa userDa = new UserDa();

        try {
            List<User> userList = userDa.findAll();
            System.out.println("All users:");
            for (User user : userList) {
                System.out.println(user);
            }
        } finally {
            userDa.close();
        }
    }

    public static void testFindByUsername() throws Exception {
        UserDa userDa = new UserDa();

        String usernameToFind = "testuser";

        try {
            User foundUser = userDa.findByUsername(usernameToFind);
            if (foundUser != null) {
                System.out.println("Found user by username '" + usernameToFind + "': " + foundUser);
            } else {
                System.out.println("User with username '" + usernameToFind + "' not found.");
            }
        } finally {
            userDa.close();
        }
    }
}
