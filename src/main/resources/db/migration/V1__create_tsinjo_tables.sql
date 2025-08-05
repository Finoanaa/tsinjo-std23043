-- Donor : donateur
CREATE TABLE donor (
                       id SERIAL PRIMARY KEY,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       full_name VARCHAR(255) NOT NULL
);

-- Beneficiary : bénéficiaire
CREATE TABLE beneficiary (
                             id SERIAL PRIMARY KEY,
                             email VARCHAR(255) NOT NULL UNIQUE,
                             full_name VARCHAR(255) NOT NULL
);

-- Payment : paiement
CREATE TABLE payment (
                         id VARCHAR(100) PRIMARY KEY, -- id reçu de Vola, souvent un UUID
                         payment_date TIMESTAMP NOT NULL,
                         amount INT NOT NULL, -- montant en Ar (unité entière)
                         payment_method VARCHAR(100) NOT NULL,
                         status VARCHAR(20) NOT NULL -- VERIFYING, SUCCEEDED, FAILED
);

-- Donation : don
CREATE TABLE donation (
                          id SERIAL PRIMARY KEY,
                          donor_id INT NOT NULL REFERENCES donor(id) ON DELETE CASCADE,
                          payment_id VARCHAR(100) NOT NULL REFERENCES payment(id) ON DELETE CASCADE
);

-- Help : aide
CREATE TABLE help (
                      id SERIAL PRIMARY KEY,
                      beneficiary_id INT NOT NULL REFERENCES beneficiary(id) ON DELETE CASCADE,
                      payment_id VARCHAR(100) NOT NULL REFERENCES payment(id) ON DELETE CASCADE,
                      description TEXT NOT NULL -- description de l'accident couvert
);
