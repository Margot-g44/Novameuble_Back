# Novameuble_Back

## Aperçu

Application Spring Boot (Java 21) connectée à une base PostgreSQL Neon avec gestion de schéma par Flyway et scripts SQL versionnés.

## Changements réalisés
- Connexion à Neon activée (SSL requis) via la datasource Spring.
- Dépendances Maven mises à jour:
  - Ajout du pilote JDBC PostgreSQL: `org.postgresql:postgresql` (scope runtime).
  - Ajout de Flyway: `org.flywaydb:flyway-core`.
  - Ajout du support PostgreSQL 17+: `org.flywaydb:flyway-database-postgresql:11.7.2`.
- Configuration Spring actualisée (`application.properties`):
  - URL JDBC remplacée par l’URL Neon (avec `sslmode=require` et `channelBinding=require`).
  - Identifiants `username`/`password` mis à jour pour Neon.
  - Pool Hikari ajusté: `spring.datasource.hikari.maximum-pool-size=5`.
  - JPA configuré pour laisser Flyway piloter le schéma: `spring.jpa.hibernate.ddl-auto=none`.
  - Suppression des anciennes propriétés `javax.persistence.schema-generation.*` devenues obsolètes.
- Migrations Flyway:
  - Correction du chemin: création de `src/main/resources/db/migration` (au lieu de `src/main/migrations`).
  - Ajout de `V1__init.sql` (création de la table `utilisateur`).
  - Application de la migration V1 au démarrage (journal Flyway validé).
- Exécution et dépannage:
  - Attribution des permissions d’exécution à `mvnw` (macOS): `chmod +x mvnw`.
  - Libération du port `8080` lorsqu’il était occupé, puis redémarrage.
  - Démarrage confirmé: Tomcat sur `http://localhost:8080`.

## Prérequis
- Java 21
- Maven Wrapper (fourni: `./mvnw`)

## Configuration

Utilisez des variables d’environnement pour éviter de committer des secrets:

```bash
export JDBC_URL="jdbc:postgresql://<host>/<database>?sslmode=require&channelBinding=require"
export DB_USER="<utilisateur>"
export DB_PASSWORD="<mot_de_passe>"
```

Dans `src/main/resources/application.properties`:
```properties
spring.application.name=Adaction
spring.datasource.url=${JDBC_URL}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.hikari.maximum-pool-size=5

# Laisser Flyway piloter le schéma
spring.jpa.hibernate.ddl-auto=none
```

Notes:
- Neon nécessite `sslmode=require` et souvent `channelBinding=require`.
- Hibernate détecte automatiquement PostgreSQL, il est inutile de fixer `hibernate.dialect`.

## Migrations Flyway

- Emplacement: `src/main/resources/db/migration`
- Nommage: `V{num}__{description}.sql` (ex: `V2__ajout_table_projet.sql`)

Exemple existant (`V1__init.sql`):
```sql
CREATE TABLE IF NOT EXISTS utilisateur (
  id SERIAL PRIMARY KEY,
  email VARCHAR(255) UNIQUE NOT NULL,
  nom VARCHAR(100) NOT NULL,
  created_at TIMESTAMPTZ DEFAULT NOW()
);
```

Ajouter une nouvelle migration (ex: table `projet`):
```sql
CREATE TABLE IF NOT EXISTS projet (
  id SERIAL PRIMARY KEY,
  nom VARCHAR(150) NOT NULL,
  description TEXT,
  created_at TIMESTAMPTZ DEFAULT NOW()
);
```

Au démarrage, Flyway validera et appliquera les migrations sur la base Neon.

## Commandes utiles

Compilation et lancement:
```bash
./mvnw spring-boot:run
```

Première exécution (si `mvnw` non exécutable):
```bash
chmod +x mvnw
./mvnw spring-boot:run
```

Libérer le port 8080 en cas de conflit:
```bash
lsof -i :8080 -sTCP:LISTEN
kill <PID>
```

## Dépannage

- Avertissement: `spring.jpa.open-in-view is enabled by default`
  - Optionnel: désactiver via `spring.jpa.open-in-view=false` si non nécessaire.

- Aucun script détecté par Flyway
  - Vérifier le chemin exact `src/main/resources/db/migration` et le nommage `V...__....sql`.

- Erreurs SSL côté Neon
  - Vérifier `sslmode=require` et `channelBinding=require` dans l’URL JDBC.

## Accès

Application disponible sur: `http://localhost:8080`
