#!/usr/bin/env bash

hadoop fs -rm -r /user/hadoop-twq/hive-course/douban
hadoop fs -mkdir -p /user/hadoop-twq/hive-course/douban
hadoop fs -mkdir -p /user/hadoop-twq/hive-course/douban/links

hadoop fs -chmod -R 777 /user/hadoop-twq/hive-course/douban

$HIVE_HOME/bin/beeline -u jdbc:hive2://slave1-dev:10000 -n hadoop-twq -f ./sql/douban_init.sql 
