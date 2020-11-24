package domain;

public class SchoolCard {

    private int scId;
    private float scMoney;
    private Student scStudent;

    public SchoolCard() {
    }

    public SchoolCard(float scMoney, Student scStudent) {
        this.scMoney = scMoney;
        this.scStudent = scStudent;
    }

    public SchoolCard(int scId, float scMoney, Student scStudent) {
        this.scId = scId;
        this.scMoney = scMoney;
        this.scStudent = scStudent;
    }

    public int getScId() {
        return scId;
    }

    public void setScId(int scId) {
        this.scId = scId;
    }

    public float getScMoney() {
        return scMoney;
    }

    public void setScMoney(float scMoney) {
        this.scMoney = scMoney;
    }

    public Student getScStudent() {
        return scStudent;
    }

    public void setScStudent(Student scStudent) {
        this.scStudent = scStudent;
    }

    @Override
    public String toString() {
        return "SchoolCard{" +
                "scId=" + scId +
                ", scMoney=" + scMoney +
                ", scStudent=" + scStudent +
                '}';
    }
}
