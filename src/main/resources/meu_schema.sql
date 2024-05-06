CREATE TABLE CLIENT (
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    NAME VARCHAR(100)
);

CREATE TABLE PRODUCT(
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    DESCRIPTION VARCHAR(100),
    UNIT_PRICE NUMERIC(20,2)
);

CREATE TABLE DEMAND(
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    CLIENT_ID INTEGER REFERENCES CLIENT(ID),
    DATA_DEMAND TIMESTAMP,
    TOTAL NUMERIC(20,2)
);

CREATE TABLE ITEM_ORDER(
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    DEMAND_ID INTEGER REFERENCES DEMAND(ID),
    PRODUCT_ID INTEGER REFERENCES PRODUCT(ID),
    QUANTITY INTEGER
);