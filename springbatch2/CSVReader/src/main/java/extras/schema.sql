CREATE TABLE transactions (
	id int NOT NULL auto_increment,
	date varchar(256) NOT NULL,
    country varchar(256) NOT NULL,
    area varchar(256) NOT NULL,
    number varchar(256) NOT NULL,
    ttl_area varchar(256) NOT NULL,
    avg_area varchar(256) NOT NULL,
    ttl_trans_amt varchar(256) NOT NULL,
    min_trans_amt varchar(256) NOT NULL,
    max_trans_amt varchar(256) NOT NULL,
    unit_price_min varchar(256) NOT NULL,
    unit_price_max varchar(256) NOT NULL,
    unit_price_med varchar(256) NOT NULL,
    unit_price_avg varchar(256) NOT NULL,
    unit_price_std varchar(256) NOT NULL,
    month varchar(256) NOT NULL,
    year varchar(256) NOT NULL,
    index varchar(256) NOT NULL,
    PRIMARY KEY (id)
);

Load data local infile "C:\\Users\\Sam\\Desktop\\springbatch2\\src\\main\\java\\com\\smoothstack\\springbatch2\\input\\transactions.csv"
into table transaction_pool.transactions
FIELDS TERMINATED BY ',' ENCLOSED BY '' ESCAPED BY '\\'
LINES TERMINATED BY '\n' STARTING BY '';