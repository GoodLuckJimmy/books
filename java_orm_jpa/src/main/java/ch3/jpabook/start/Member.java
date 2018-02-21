package ch3.jpabook.start;

import javax.persistence.*;

@Entity
// @org.hibernate.annotations.DynamicUpdate // 수정된 데이터만 사용해서 동적으로 update sql 생성
@Table(name="MEMBER")
public class Member {

    @Id
    @Column(name="ID")
    private String id;

    @Column(name="NAME")
    private String username;

    private Integer age;
    
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    
}
