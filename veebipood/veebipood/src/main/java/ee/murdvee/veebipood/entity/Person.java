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

    // {CascadeType.DETACH, CascadeType.REMOVE, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
    // CascadeType --> CascadeType.REMOVE   kui kustutatakse Person, siis kustutatakse ka Address
    // CascadeType.PERSIST    kui lisatakse Person ja temaga antakse kaasa Address mida pole andmebaasis
    //                         siis ta lisatakse andmebaasi kui uus kirje Address tabelisse
    // Cascade.MERGE         kui muudetakse Personit ja Person küljes olevat Addressi siis
    //                          muutub nii Personi sisu kui ka Addressi sisu

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
}
