package ee.murdvee.veebipood.entity;

//import javax.persistence.* - vana
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Person { //User on hoivatud PostQre tasandil
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String firstName;
    @Column(unique = true) //andmebaasis peab olema unikaalne
    private String email;
    private String password;
    @Column(unique = true)
    private String personalCode;
}
