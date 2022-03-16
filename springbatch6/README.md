## Instructions for running SpringBatch6Application

### Configure the CLI arguments as
* inFile = com/smoothstack/springbatch6/input/transactions.csv
* outFile = com/smoothstack/springbatch6/output/outFile.csv

You'll notice several lines logged to the console as `skipping county: Saare maakond` 
noting that a record has been skipped per assignment. If `outFile.csv` has not been generated and the service is
still running, then stopping the service should generate `outFile.csv`
