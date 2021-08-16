package co.zw.gotour.server.Util;

import com.google.firebase.auth.UserRecord;

import co.zw.gotour.server.Model.User;

public class FirebaseUserMapper {

   public static User mapToUser(UserRecord firebaseUser) {
        User user = new User();

        user.setEmail(firebaseUser.getEmail());
        user.setFirebaseId(firebaseUser.getUid());
        // TODO: Map userRecord.getDisplayName() -> user.firstname and user.lastname

        return user;

    }
}
