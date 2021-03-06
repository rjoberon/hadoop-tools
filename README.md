hadoop-tools
============

Some tools for Hadoop

## BatchRename 

[de.l3s.hadoop.BatchRename](src/main/java/de/l3s/hadoop/BatchRename.java)
renames files in HDFS *very fast*. Renaming 100 files with `hdfs dfs
-mv` takes four minutes while this tool requires less than four
seconds!

Usage:

```shell
git clone git@github.com:rjoberon/hadoop-tools.git
cd hadoop-tools
mvn install
mvn exec:java -Dexec.mainClass="de.l3s.hadoop.BatchRename" -Dexec.args="hdfs://host:port"< FILE
```

where `host:port` points to your namenode (the default is
`master.ib:8020`) and `FILE` contains the files to be renamed and
their new name (space separated, one file per line). E.g.:

```
old_name_1 new_name_1
old_name_2 new_name_2
old_name_3 new_name_3
```

