package co.zw.gotour.server.Util;

import com.google.firebase.auth.UserRecord;

import co.zw.gotour.server.Model.User;

public class FirebaseUserMapper {

    public static User mapToUser(UserRecord firebaseUser) {
        User user = new User();

        String[] displayName = firebaseUser.getDisplayName().split(" ");
        String firstname = displayName[0];
        String lastname = displayName[1];

        user.setEmail(firebaseUser.getEmail());
        user.setFirebaseId(firebaseUser.getUid());
        user.setFirstname(firstname);
        user.setLastname(lastname);
        return user;

    }
}
