package database;

import org.flywaydb.core.Flyway;

public class FlywayMigrationUtil {
    public static void runMigrations() {
        // Solo se usa para poblar la db en el primer arranque.
        Flyway flyway = Flyway.configure()
                .dataSource("jdbc:mysql://localhost:3306/aad_tr2", "cristian_ramos", "12345")
                .baselineOnMigrate(true)
                .load();

        flyway.migrate();
    }
}