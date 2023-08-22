package lgl.lotion.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "USER_MST")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String USER_ID;
    private String NICKNAME;
    private String USER_PASSWORD;
    private String PROFILE_URL;
    private String TEL;
    private String MOBILE;
    private String EMAIL;
    private String USE_YN;
    private Date PASSWORD_UPDATE_TIME;
    private Date LAST_ACCESS_DATE;
    private String CREATE_USER;
    private Date CREATE_TIME;
    private String UPDATE_USER;
    private Date UPDATE_TIME;
}
