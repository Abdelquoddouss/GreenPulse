
CREATE TABLE utilisateurs (
                              id SERIAL PRIMARY KEY,
                              name VARCHAR(255) NOT NULL,
                              age INT NOT NULL
);

CREATE TABLE consommations (
                               id SERIAL PRIMARY KEY ,
                               utilisateur_id INT  ,
                               startDate date,
                               endDate date,
                              consommationType VARCHAR(50) ,
                               quantite int ,
                               consomationImpact DOUBLE PRECISION,
                               FOREIGN KEY (utilisateur_id) REFERENCES utilisateurs(id) ON DELETE CASCADE
);


CREATE TABLE transport (
                           id SERIAL PRIMARY KEY,
                           consommation_id INT ,
                           distance_parcourue DOUBLE PRECISION,
                           type_vehicule VARCHAR(50) ,
                           FOREIGN KEY (consommation_id) REFERENCES consommations(id) ON DELETE CASCADE
);

CREATE TABLE logement (
                          id SERIAL PRIMARY KEY,
                          consommation_id INT ,
                          consommation_energie DOUBLE PRECISION,
                          type_energie VARCHAR(50) ,
                          FOREIGN KEY (consommation_id) REFERENCES consommations(id) ON DELETE CASCADE
);

CREATE TABLE alimentation (
                              id SERIAL PRIMARY KEY,
                              consommation_id INT ,
                              type_aliment VARCHAR(50) ,
                              poids DOUBLE PRECISION,
                              FOREIGN KEY (consommation_id) REFERENCES consommations(id) ON DELETE CASCADE
);


CREATE TYPE consommation_type AS ENUM ('transport', 'logement', 'alimentation');
