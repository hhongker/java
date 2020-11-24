package domain;

public class Student {

    private int stId;
    private String stName;
    private int stAge;
    private SchoolCard schoolCard;

    public Student() {
    }

    public Student(String stName, int stAge, SchoolCard schoolCard) {
        this.stName = stName;
        this.stAge = stAge;
        this.schoolCard = schoolCard;
    }

    public Student(int stId, String stName, int stAge, SchoolCard schoolCard) {
        this.stId = stId;
        this.stName = stName;
        this.stAge = stAge;
        this.schoolCard = schoolCard;
    }

    public int getStId() {
        return stId;
    }

    public void setStId(int stId) {
        this.stId = stId;
    }

    public String getStName() {
        return stName;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }

    public int getStAge() {
        return stAge;
    }

    public void setStAge(int stAge) {
        this.stAge = stAge;
    }

    public SchoolCard getSchoolCard() {
        return schoolCard;
    }

    public void setSchoolCard(SchoolCard schoolCard) {
        this.schoolCard = schoolCard;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stId=" + stId +
                ", stName='" + stName + '\'' +
                ", stAge=" + stAge +
                ", schoolCard=" + schoolCard +
                '}';
    }
}
