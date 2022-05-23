# KSQL Array UDFs

# Examples

To write UDFs/UDAFs ([details](https://docs.confluent.io/current/ksql/docs/developer-guide/udf.html)), you can use
the following examples as a starting point. Simply [fork](https://github.com/miguno/ksql-udf-examples/fork) this
repository and add or modify the examples as you see fit. Of course, you can also contribute examples to this
repository by sending a [pull request](https://github.com/miguno/ksql-udf-examples/pulls).

| Example                                  | Type      | Description                                           |
| ---------------------------------------- | --------- | ----------------------------------------------------- |
| [`MULTIPLY(col1, col2)`][1] ([tests][2]) | [UDF][5]  | Multiplies two numbers                                |
| [`SUM_NULL(col1)`][3] ([tests][4])       | [UDAF][5] | Sums values in a colum, treating `null` as `0` (zero) |

[1]: src/main/java/com/gschmutz/ksql/udf/array/udf/MultiplyUdf.java
[2]: src/test/java/com/miguno/ksql/udfdemo/udf/MultiplyUdfTest.java
[3]: src/main/java/com/gschmutz/ksql/udf/array/udaf/SumUdaf.java
[4]: src/test/java/com/miguno/ksql/udfdemo/udaf/SumUdafTest.java
[5]: https://docs.confluent.io/current/ksql/docs/developer-guide/udf.html

To unit-test the UDFs/UDAFs:

```bash
$ mvn clean test
```

To package the UDFs/UDAFs ([details](https://docs.confluent.io/current/ksql/docs/developer-guide/implement-a-udf.html#build-the-udf-package)):

```bash
# Create a standalone jar ("fat jar")
$ mvn clean package

# >>> Creates target/ksql-udf-demo-1.0-jar-with-dependencies.jar
```

To deploy the packaged UDFs/UDAFs to KSQL servers, refer to the
[KSQL documentation on UDF/UDAF](https://docs.confluent.io/current/ksql/docs/developer-guide/udf.html#deploying).
You can verify that the UDFs/UDAFs are available for use by running `SHOW FUNCTIONS`, and show the details of
any specific function with `DESCRIBE FUNCTION <name>`.

To use the UDFs/UDAFs in KSQL ([details]()):

```sql
DROP STREAM test1_s;
CREATE STREAM IF NOT EXISTS test1_s 
  (id INTEGER, status STRING)
  WITH (kafka_topic='test1', partitions=8,
        value_format='AVRO');

SELECT id,collect_list(status) from test1_s GROUP BY id EMIT CHANGES;

SELECT id,array_lag(collect_list(status),1) from test1_s GROUP BY id EMIT CHANGES;


```


<a name="Learn"></a>

# Want to Learn More?

* Head over to the [KSQL documentation](https://docs.confluent.io/current/ksql/).
* [Mitch Seymour](http://blog.mitchseymour.com/)'s talk [The Exciting Frontier of Custom KSQL Functions](https://kafka-summit.org/sessions/exciting-frontier-custom-ksql-functions/) ([slides](http://blog.mitchseymour.com/presentations/kafka-summit-london-2019/slides/#/)), Kafka Summit London 2019, which includes UDF usage for machine learning as well as a POC for writing UDFs in non-JVM languages like Ruby, Python


<a name="License"></a>

# License

See [LICENSE](LICENSE) for licensing information.
