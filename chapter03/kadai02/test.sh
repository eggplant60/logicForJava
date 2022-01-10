#!/bin/bash

EXE="java -Dfile.encoding=SHIFT-JIS src/BusinessDayMulti.java"
NKF="nkf -Lu"

function unit {
  echo "\$ java BusinessDayMulti $1 $2 $3 $4"
  $EXE $1 $2 $3 $4 | $NKF
  echo ''
}

echo "--- 実行例 ---"
unit 2022 2 4 209   # OK
unit 2022 2 29 100  # OK
unit 2022 9 1 -209  # OK

# echo "--- 2024/5/2周りのテスト ----"
# unit 2024 5 2 1   # OK
# unit 2024 5 3 1   # OK
# unit 2024 5 6 1   # OK
# unit 2024 5 5 0   # OK
# unit 2024 5 6 0   # OK
# unit 2024 5 7 -1  # OK
# unit 2024 5 3 -1  # OK
