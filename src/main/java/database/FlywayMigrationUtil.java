package database;

import org.flywaydb.core.Flyway;

import java.sql.SQLException;

public class FlywayMigrationUtil {
    public static void runMigrations() throws SQLException {
        // Solo se usa para poblar la db en el primer arranque.
        Flyway flyway = Flyway.configure()
                .dataSource("jdbc:mysql://localhost:3306/aad_tr2", "cristian_ramos", "12345")
                .baselineOnMigrate(true)
                .load();

        flyway.migrate();
        flyway.getConfiguration().getDataSource().getConnection().close();
    }
}