package ee.murdvee.veebipood.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private double price;
    private boolean active;
    private int stock;

    //@ManyToMany -> private List<Ingredients> Ingredients
    //@OneToMany -> private List<Ingredients> Ingredients
    //@ManyToOne -> tooted jagavad seda kategooriad
    //OneToOne -> tooted ei jaga seda kategooriad

    @ManyToOne
    private Category category; //automaatselt voorvotmega (@Id v2ljaga) siia tabelisse
    //  Panen andmebaasi, aga ei määra väärtust:
    //  double -> 0
    //  boolean -> false
    //  int -> 0

    //  Panen andmebaasi, aga ei määra väärtust:
    //  Double -> null
    //  Boolean -> null
    //  Integer -> null
}
