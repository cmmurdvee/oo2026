# Coding Conventions — Spring Boot (murdvee)

## Stack
- **Java 21**, Spring Boot 4.x
- **Maven** build tool
- **PostgreSQL** (prod), H2 (test)
- **Lombok** for boilerplate
- **Jakarta Persistence (JPA)** for ORM

---

## Package Structure

```
ee.murdvee.[project]/
├── entity/       — JPA entiteedid ja enum-id
├── dto/          — Java Record DTO-d
├── repository/   — JpaRepository liidesed
├── service/      — äriloogika (@Service) — kui aega on
├── controller/   — REST kontrollerid (@RestController)
└── exception/    — ApiExceptionHandler + ErrorMessage (kui vaja)
```

> **Märkus:** Selles projektis (proovikontrolltoo) jäi `service/` kiht ajanappuse tõttu tegemata.
> Äriloogika on otse kontrolleris. Täielikus lahenduses kuulub loogika `service/` kihi alla.

---

## Entities

```java
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class MyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;          // primitiivid/stringid otse, ilma @Column-ita
    private double price;         // double (mitte Double) kui null pole vajalik
    private int count;            // int kui null pole vajalik

    @ManyToOne
    private OtherEntity other;    // ilma @JoinColumn-ita

    @OneToMany(cascade = CascadeType.ALL)
    private List<ChildEntity> children = new ArrayList<>();
}
```

- `Long id` — alati `Long`, mitte `long`
- Ei kasutata `@Column` annotatsioone
- Enum-id eraldi failis samas `entity/` paketis
- Entity meetodid (nt `getTotalPoints()`) lubatud kui loogika on lihtne

---

## DTOs — Java Records

```java
public record MyDto(
        String name,
        MyEnumType type
) {
}
```

- Alati **Java Record**, mitte klass
- Nimed: `[Entity]SaveDto`, `[Entity]RentalDto`, `[Entity]ResponseDto`

---

## Repositories

```java
public interface MyRepository extends JpaRepository<MyEntity, Long> {
    // Spring Data meetodid nimetuse järgi:
    List<MyEntity> findByFieldName(Type value);
    // kommentaar SQL-na: // SELECT * FROM my_entity WHERE field_name = value
}
```

---

## Controllers

```java
@RestController
@RequiredArgsConstructor          // mitte @AllArgsConstructor kontrolleris
public class MyController {

    private final MyRepository myRepository;   // final + RequiredArgsConstructor

    @GetMapping("entities")
    public List<MyEntity> findAll() {
        return myRepository.findAll();
    }

    @PostMapping("entities")
    public MyEntity save(@RequestBody MyDto dto) {
        MyEntity entity = new MyEntity();
        entity.setName(dto.name());
        return myRepository.save(entity);
    }

    @PatchMapping("entities/field")
    public MyEntity updateField(@RequestParam Long id, @RequestParam String value) {
        MyEntity entity = myRepository.findById(id).orElseThrow();
        entity.setField(value);
        return myRepository.save(entity);
    }

    @DeleteMapping("entities/{id}")
    public void delete(@PathVariable Long id) {
        myRepository.deleteById(id);
    }
}
```

- Ei kasutata `@RequestMapping` klassi tasemel
- URL-id: **lühike, väiketähtedega, kebab-case** (`start-rental`, `films/available`)
- `findById(...).orElseThrow()` — alati nii, mitte `get()`

---

## Service kiht (kui kasutatakse)

```java
@Service
@AllArgsConstructor               // service-is @AllArgsConstructor (mitte final + RequiredArgs)
public class MyService {

    private MyRepository myRepository;

    public void validate(MyEntity entity) {
        if (entity.getName() == null || entity.getName().isBlank()) {
            throw new RuntimeException("Nimi ei tohi olla tühi");
        }
    }

    public MyEntity doSomething(Long id, ...) {
        MyEntity entity = myRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ei leitud ID-ga " + id));
        // äriloogika...
        return myRepository.save(entity);
    }
}
```

- Validatsioon service kihis, mitte kontrolleris
- Erandid: `RuntimeException` eesti keeles

---

## Exception Handling

```java
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleException(RuntimeException ex) {
        ErrorMessage msg = new ErrorMessage();
        msg.setMessage(ex.getMessage());
        msg.setStatus(HttpStatus.BAD_REQUEST.value());
        msg.setTimestamp(new Date());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
    }
}

@Data
public class ErrorMessage {
    private String message;
    private Date timestamp;
    private int status;
}
```

---

## application.properties

```properties
spring.application.name=myproject
server.port=8080

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.datasource.url=jdbc:postgresql://localhost:5432/myproject
spring.datasource.username=postgres
spring.datasource.password=root
```

---

## Kommentaarid

- Kommentaarid on **eesti keeles**
- Kommenteeritakse äriloogikat, mitte ilmselget koodi
- SQL-ekvivalendid repositooriumis: `// SELECT * FROM ...`

---

## Üldreeglid

- Ei kasutata `Optional` — ainult `orElseThrow()`
- Ei kasutata `@Transactional` tavapärastes CRUD operatsioonides
- `double` primitiiv rahaliste väärtuste jaoks (mitte `BigDecimal`)
- Enum-id SUURTÄHTEDEGA: `NEW`, `REGULAR`, `OLD`
- `switch` avaldised uue stiili järgi (`case X -> ...`)