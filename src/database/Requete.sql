
CREATE TABLE utilisateurs (
                              id SERIAL PRIMARY KEY,
                              name VARCHAR(255) NOT NULL,
                              age INT NOT NULL
);

-- Création de la table consommations
CREATE TABLE consommations (
                               id SERIAL PRIMARY KEY,
                               utilisateur_id INT ,
                               startDate date,
                               endDate date,
                              consommationType VARCHAR(50) ,
                               quantite int ,
                               consomationImpact DOUBLE PRECISION,
                               FOREIGN KEY (utilisateur_id) REFERENCES utilisateurs(id) ON DELETE CASCADE
);


