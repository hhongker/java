package domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hhr
 * @date 2020-07-02 11:19
 * @description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Student2 {
    private Integer id;
    private String name;
    private String sex;
    private Float chinese;
    private Float math;
    private Float english;

    @Override
    public String toString() {
        return "Student2{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", chinese=" + chinese +
                ", math=" + math +
                ", english=" + english +
                '}';
    }
}
