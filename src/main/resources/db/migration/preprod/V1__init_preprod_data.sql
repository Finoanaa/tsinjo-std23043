-- Donors
INSERT INTO donor (email, full_name) VALUES
                                         ('alice@hei.school', 'Alice Rakoto'),
                                         ('bob@hei.school', 'Bob Randria');

-- Beneficiaries
INSERT INTO beneficiary (email, full_name) VALUES
                                               ('zoe@hei.school', 'Zoe Raharisoa'),
                                               ('luc@hei.school', 'Luc Andriam');

-- Payments
INSERT INTO payment (id, payment_date, amount, payment_method, status) VALUES
                                                                           ('pay-preprod-1', '2025-08-01 09:30:00', 5000, 'Orange Money', 'SUCCEEDED'),
                                                                           ('pay-preprod-2', '2025-08-02 10:00:00', 10000, 'Carte bancaire', 'SUCCEEDED'),
                                                                           ('pay-preprod-3', '2025-08-03 11:00:00', 8000, 'Airtel Money', 'SUCCEEDED');

-- Donations
INSERT INTO donation (donor_id, payment_id) VALUES
                                                (1, 'pay-preprod-1'),
                                                (2, 'pay-preprod-2');

-- Helps
INSERT INTO help (beneficiary_id, payment_id, description) VALUES
    (1, 'pay-preprod-3', 'Accident de moto survenu à Ambatonakanga');
