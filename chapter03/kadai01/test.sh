#!/bin/bash

EXE="java -Dfile.encoding=SHIFT-JIS src/KoyomiMulti.java"
NKF="nkf -Lu"

function unit {
  echo "\$ java KoyomiMulti $1 $2 $3"
  $EXE $1 $2 $3 | $NKF
  echo ''
}

unit 1900 2 29
unit 2000 2 29
unit 2020 7 24
unit 2031 1 1
unit 2100 2 29

# echo "-- 追加分 --"
# unit 2000 3 1
# unit 2100 2 28
# unit 2100 3 1
# unit 2030 1 1
# unit 2022 1 9