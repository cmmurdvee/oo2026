package ee.murdvee.decathlon.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Athlete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String country;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Result> results = new ArrayList<>();

    public int getTotalPoints() {
        return results.stream().mapToInt(Result::getPoints).sum();
    }
}
