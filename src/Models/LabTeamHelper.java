package Models;

/**
 * Created by swebo_000 on 2016-04-08.
 */
public class LabTeamHelper {
    public String userA;
    public String userB;
    public String userAmail;
    public String userBmail;
    public String courseName;
    public String courseCode;
    public int teamLabID;


    public LabTeamHelper(String userA, String userB, String userAmail, String userBmail, String courseName, String courseCode, int teamLabID) {
        this.userA = userA;
        this.userB = userB;
        this.userAmail = userAmail;
        this.userBmail = userBmail;
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.teamLabID = teamLabID;
    }

    public String getUserA() {
        return userA;
    }

    public void setUserA(String userA) {
        this.userA = userA;

    }

    public String getUserB() {
        return userB;
    }

    public void setUserB(String userB) {
        this.userB = userB;
    }

    public String getUserAmail() {
        return userAmail;
    }

    public void setUserAmail(String userAmail) {
        this.userAmail = userAmail;
    }

    public String getUserBmail() {
        return userBmail;
    }

    public void setUserBmail(String userBmail) {
        this.userBmail = userBmail;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public int getTeamLabID() {
        return teamLabID;
    }

    public void setTeamLabID(int teamLabID) {
        this.teamLabID = teamLabID;
    }
}
