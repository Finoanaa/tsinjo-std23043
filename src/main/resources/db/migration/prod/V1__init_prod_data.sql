-- Donors
INSERT INTO donor (email, full_name) VALUES
                                         ('charles@hei.school', 'Charles Rabe'),
                                         ('dina@hei.school', 'Dina Rasoa');

-- Beneficiaries
INSERT INTO beneficiary (email, full_name) VALUES
                                               ('hery@hei.school', 'Hery Raja'),
                                               ('miora@hei.school', 'Miora Lala');

-- Payments
INSERT INTO payment (id, payment_date, amount, payment_method, status) VALUES
                                                                           ('pay-prod-1', '2025-08-01 15:00:00', 15000, 'Orange Money', 'SUCCEEDED'),
                                                                           ('pay-prod-2', '2025-08-02 16:30:00', 20000, 'Carte bancaire', 'SUCCEEDED'),
                                                                           ('pay-prod-3', '2025-08-04 08:15:00', 18000, 'Airtel Money', 'SUCCEEDED');

-- Donations
INSERT INTO donation (donor_id, payment_id) VALUES
                                                (1, 'pay-prod-1'),
                                                (2, 'pay-prod-2');

-- Helps
INSERT INTO help (beneficiary_id, payment_id, description) VALUES
    (2, 'pay-prod-3', 'Chute grave lors d’un événement sportif à HEI');
