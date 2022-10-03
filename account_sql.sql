CREATE DATABACE atm_clients;

CREATE TABLE card_details (
    oner_name varchar(100) not null,
    nic_number varchar(12) not null,
    account_number varchar(10) PRIMARY KEY not null,
    card_number varchar(10) not null,
    card_pin varchar(4) not null,
    balance decimal(35,2) not null,
    card_status varchar(10) not null
    );

INSERT INTO card_details (oner_name, nic_number, account_number, card_number, card_pin, balance, card_status) VALUES 
    ("H.K. Damith","199812345670","1234567890","50","1111",473642.00,"valid"),
    ("J.L. Thilini","199812345671","1234567891","51","2222",256343.20,"valid"),
    ("H. Vishaka","199812345672","1234567892","52","3333",35328.24,"invalid"),
    ("P.W. Kumara","199812345673","1234567893","53","4444",24964.17,"valid"),
    ("I.P. Indika","199812345674","1234567894","54","5555",24735.41,"valid");

CREATE TABLE branch (
    branch_id varchar(6) not null PRIMARY KEY,
    branch_town varchar(50) not null,
    manager varchar(50) not null
);

INSERT INTO branch (branch_id, branch_town, manager) VALUES
    ("LB-001","Galle","S. Sampath"),
    ("LB-002","Labuduwa","Y. Rusith"),
    ("LB-003","Matara","E. Asitha"),
    ("LB-004","Kamburupitiya","D. Heshan");

CREATE TABLE atm(
    atm_id varchar(6) not null ,
    branch_id varchar(6) not null,
    balance decimal(35,2) not null,
    FOREIGN KEY(branch_id) REFERENCES branch(branch_id),
    PRIMARY KEY(atm_id, branch_id)
);

INSERT INTO atm (atm_id, branch_id, balance) VALUES 
    ("LA-001", "LB-001", 50000000.00),
    ("LA-002", "LB-001", 30000000.00),
    ("LA-003", "LB-001", 25000000.00),

    ("LA-001", "LB-002", 10000000.00),

    ("LA-001", "LB-003", 50000000.00),
    ("LA-002", "LB-003", 30000000.00),
    ("LA-003", "LB-003", 40000000.00),
    ("LA-004", "LB-003", 25000000.00),

    ("LA-001", "LB-004", 10000000.00),
    ("LA-002", "LB-004", 25000000.00);






