package com.example.kafkahello;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class Consumer {
    public static void main(String[] args) {

        String groupId = "my-consumer";

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:29092"); //conexao com o kafka
        props.put("group.id", groupId);
        props.put("key.deserializer", "org.apache.kafka.common.serialization.IntegerDeserializer"); //tipo da chave da mensagem
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer"); //tipo do valor da mensagem
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");

        String topics[] = {"numbers"};

        KafkaConsumer<Number, String> consumer = new KafkaConsumer<Number, String>(props);

        consumer.subscribe(Arrays.asList(topics));

        try {
            while(true) {
                ConsumerRecords<Number, String> records = consumer.poll(Duration.ofMillis(100));

                for (ConsumerRecord<Number, String> record : records) {
                    String message = String.format(
                            "offset= %d, key= %s, value= %s, partition= %s%n",
                            record.offset(),
                            record.key(),
                            record.value(),
                            record.partition());
                    System.out.println(message);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            consumer.close();
        }
    }
}
