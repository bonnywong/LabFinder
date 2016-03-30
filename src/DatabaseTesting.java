import Models.UserEntity;
import Persist.JPAStore;

/**
 * TODO: Finish
 * Created by swebo_000 on 2016-03-28.
 */
public class DatabaseTesting {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        JPAStore db = new JPAStore();
        UserEntity user = new UserEntity();
        user.setName("Bonny Wong");
        user.setComments("Hello.");
        user.setFacebookId(12345);
        user.setProgram("Computer Science");
        user.setMaster("Computational Biology");
        db.persistUser(user);
    }
}
