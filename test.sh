#!/bin/bash

# # test complex query i

# for i in {1..14}
# do
#     sed -e 's/ldbc.snb.interactive.LdbcQuery'"${i}"'_enable=false/ldbc.snb.interactive.LdbcQuery'"${i}"'_enable=true/g' benchmark.properties.copy
#     sed -e 's/ldbc.snb.interactive.LdbcQuery'"${i}"'_enable=false/ldbc.snb.interactive.LdbcQuery'"${i}"'_enable=true/g' benchmark.properties.copy > benchmark.properties
#     ./benchmark.sh > ./output/query${i}.log
# done

# query 1, count 100
sed -e 's/ldbc.snb.interactive.LdbcQuery1_enable=false/ldbc.snb.interactive.LdbcQuery1_enable=true/g' \
    -e 's/operation_count=2/operation_count=100/g' \
    benchmark.properties.copy

sed -e 's/ldbc.snb.interactive.LdbcQuery1_enable=false/ldbc.snb.interactive.LdbcQuery1_enable=true/g' \
    -e 's/operation_count=2/operation_count=100/g' \
    benchmark.properties.copy > benchmark.properties

./benchmark.sh > ./output/query1.log

# query 2, count 100
sed -e 's/ldbc.snb.interactive.LdbcQuery2_enable=false/ldbc.snb.interactive.LdbcQuery2_enable=true/g' \
    -e 's/operation_count=2/operation_count=100/g' \
    benchmark.properties.copy

sed -e 's/ldbc.snb.interactive.LdbcQuery2_enable=false/ldbc.snb.interactive.LdbcQuery2_enable=true/g' \
    -e 's/operation_count=2/operation_count=100/g' \
    benchmark.properties.copy > benchmark.properties
    
./benchmark.sh > ./output/query2.log

# query 3, count 10
sed -e 's/ldbc.snb.interactive.LdbcQuery3_enable=false/ldbc.snb.interactive.LdbcQuery3_enable=true/g' \
    -e 's/operation_count=2/operation_count=10/g' \
    benchmark.properties.copy

sed -e 's/ldbc.snb.interactive.LdbcQuery3_enable=false/ldbc.snb.interactive.LdbcQuery3_enable=true/g' \
    -e 's/operation_count=2/operation_count=10/g' \
    benchmark.properties.copy > benchmark.properties
    
./benchmark.sh > ./output/query3.log

# query 4, count 100
sed -e 's/ldbc.snb.interactive.LdbcQuery4_enable=false/ldbc.snb.interactive.LdbcQuery4_enable=true/g' \
    -e 's/operation_count=2/operation_count=100/g' \
    benchmark.properties.copy

sed -e 's/ldbc.snb.interactive.LdbcQuery4_enable=false/ldbc.snb.interactive.LdbcQuery4_enable=true/g' \
    -e 's/operation_count=2/operation_count=100/g' \
    benchmark.properties.copy > benchmark.properties
    
./benchmark.sh > ./output/query4.log

# query 5, count 10
sed -e 's/ldbc.snb.interactive.LdbcQuery5_enable=false/ldbc.snb.interactive.LdbcQuery5_enable=true/g' \
    -e 's/operation_count=2/operation_count=10/g' \
    benchmark.properties.copy

sed -e 's/ldbc.snb.interactive.LdbcQuery5_enable=false/ldbc.snb.interactive.LdbcQuery5_enable=true/g' \
    -e 's/operation_count=2/operation_count=10/g' \
    benchmark.properties.copy > benchmark.properties
    
./benchmark.sh > ./output/query5.log

# query 6, count 50
sed -e 's/ldbc.snb.interactive.LdbcQuery6_enable=false/ldbc.snb.interactive.LdbcQuery6_enable=true/g' \
    -e 's/operation_count=2/operation_count=50/g' \
    benchmark.properties.copy

sed -e 's/ldbc.snb.interactive.LdbcQuery6_enable=false/ldbc.snb.interactive.LdbcQuery6_enable=true/g' \
    -e 's/operation_count=2/operation_count=50/g' \
    benchmark.properties.copy > benchmark.properties
    
./benchmark.sh > ./output/query6.log

# query 7, count 100
sed -e 's/ldbc.snb.interactive.LdbcQuery7_enable=false/ldbc.snb.interactive.LdbcQuery7_enable=true/g' \
    -e 's/operation_count=2/operation_count=100/g' \
    benchmark.properties.copy

sed -e 's/ldbc.snb.interactive.LdbcQuery7_enable=false/ldbc.snb.interactive.LdbcQuery7_enable=true/g' \
    -e 's/operation_count=2/operation_count=100/g' \
    benchmark.properties.copy > benchmark.properties
    
./benchmark.sh > ./output/query7.log

# query 8, count 100
sed -e 's/ldbc.snb.interactive.LdbcQuery8_enable=false/ldbc.snb.interactive.LdbcQuery8_enable=true/g' \
    -e 's/operation_count=2/operation_count=100/g' \
    benchmark.properties.copy

sed -e 's/ldbc.snb.interactive.LdbcQuery8_enable=false/ldbc.snb.interactive.LdbcQuery8_enable=true/g' \
    -e 's/operation_count=2/operation_count=100/g' \
    benchmark.properties.copy > benchmark.properties
    
./benchmark.sh > ./output/query8.log

# query 9, count 50
sed -e 's/ldbc.snb.interactive.LdbcQuery9_enable=false/ldbc.snb.interactive.LdbcQuery9_enable=true/g' \
    -e 's/operation_count=2/operation_count=50/g' \
    benchmark.properties.copy

sed -e 's/ldbc.snb.interactive.LdbcQuery9_enable=false/ldbc.snb.interactive.LdbcQuery9_enable=true/g' \
    -e 's/operation_count=2/operation_count=50/g' \
    benchmark.properties.copy > benchmark.properties
    
./benchmark.sh > ./output/query9.log

# query 10, count 100
sed -e 's/ldbc.snb.interactive.LdbcQuery10_enable=false/ldbc.snb.interactive.LdbcQuery10_enable=true/g' \
    -e 's/operation_count=2/operation_count=100/g' \
    benchmark.properties.copy

sed -e 's/ldbc.snb.interactive.LdbcQuery10_enable=false/ldbc.snb.interactive.LdbcQuery10_enable=true/g' \
    -e 's/operation_count=2/operation_count=100/g' \
    benchmark.properties.copy > benchmark.properties
    
./benchmark.sh > ./output/query10.log

# query 11, count 100
sed -e 's/ldbc.snb.interactive.LdbcQuery11_enable=false/ldbc.snb.interactive.LdbcQuery11_enable=true/g' \
    -e 's/operation_count=2/operation_count=100/g' \
    benchmark.properties.copy

sed -e 's/ldbc.snb.interactive.LdbcQuery11_enable=false/ldbc.snb.interactive.LdbcQuery11_enable=true/g' \
    -e 's/operation_count=2/operation_count=100/g' \
    benchmark.properties.copy > benchmark.properties
    
./benchmark.sh > ./output/query11.log

# query 12, count 10
sed -e 's/ldbc.snb.interactive.LdbcQuery12_enable=false/ldbc.snb.interactive.LdbcQuery12_enable=true/g' \
    -e 's/operation_count=2/operation_count=10/g' \
    benchmark.properties.copy

sed -e 's/ldbc.snb.interactive.LdbcQuery12_enable=false/ldbc.snb.interactive.LdbcQuery12_enable=true/g' \
    -e 's/operation_count=2/operation_count=10/g' \
    benchmark.properties.copy > benchmark.properties
    
./benchmark.sh > ./output/query12.log

# query 13, count 100
sed -e 's/ldbc.snb.interactive.LdbcQuery13_enable=false/ldbc.snb.interactive.LdbcQuery13_enable=true/g' \
    -e 's/operation_count=2/operation_count=100/g' \
    benchmark.properties.copy

sed -e 's/ldbc.snb.interactive.LdbcQuery13_enable=false/ldbc.snb.interactive.LdbcQuery13_enable=true/g' \
    -e 's/operation_count=2/operation_count=100/g' \
    benchmark.properties.copy > benchmark.properties
    
./benchmark.sh > ./output/query13.log

# query 14, count 100
sed -e 's/ldbc.snb.interactive.LdbcQuery14_enable=false/ldbc.snb.interactive.LdbcQuery14_enable=true/g' \
    -e 's/operation_count=2/operation_count=100/g' \
    benchmark.properties.copy

sed -e 's/ldbc.snb.interactive.LdbcQuery14_enable=false/ldbc.snb.interactive.LdbcQuery14_enable=true/g' \
    -e 's/operation_count=2/operation_count=100/g' \
    benchmark.properties.copy > benchmark.properties
    
./benchmark.sh > ./output/query14.log