package ee.murdvee.decathlon.service;

import org.springframework.stereotype.Service;

@Service
public class DecathlonScoringService {

    // IAAF ametlikud punktivalemi parameetrid
    public int calculatePoints(String discipline, double performance) {
        return switch (discipline.toLowerCase()) {
            case "100m"          -> track(25.4347,  18.0,   1.81, performance);
            case "400m"          -> track(1.53775,  82.0,   1.81, performance);
            case "1500m"         -> track(0.03768,  480.0,  1.85, performance);
            case "110m tõkked"   -> track(5.74352,  28.5,   1.92, performance);
            case "kaugushüpe"    -> field(0.14354,  220.0,  1.40, performance * 100);
            case "kõrgushüpe"    -> field(0.8465,   75.0,   1.42, performance * 100);
            case "teivashüpe"    -> field(0.2797,   100.0,  1.35, performance * 100);
            case "kuulitõuge"    -> field(51.39,    1.5,    1.05, performance);
            case "kettaheit"     -> field(12.91,    4.0,    1.10, performance);
            case "odavise"       -> field(10.14,    7.0,    1.08, performance);
            default -> throw new RuntimeException(
                "Tundmatu spordiala: '" + discipline + "'. Lubatud spordialad: " +
                "100m, 400m, 1500m, 110m tõkked, kaugushüpe, kõrgushüpe, teivashüpe, kuulitõuge, kettaheit, odavise"
            );
        };
    }

    private int track(double A, double B, double C, double perf) {
        if (perf >= B) return 0;
        return (int) (A * Math.pow(B - perf, C));
    }

    private int field(double A, double B, double C, double perf) {
        if (perf <= B) return 0;
        return (int) (A * Math.pow(perf - B, C));
    }
}
