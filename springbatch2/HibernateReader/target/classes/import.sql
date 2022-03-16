Load data local infile "C:\\Users\\Sam\\Desktop\\springbatch2\\src\\main\\java\\com\\smoothstack\\springbatch2\\input\\transactions.csv"
into table transaction_pool.transactions
FIELDS TERMINATED BY ',' ENCLOSED BY '' ESCAPED BY '\\'
LINES TERMINATED BY '\n' STARTING BY '';