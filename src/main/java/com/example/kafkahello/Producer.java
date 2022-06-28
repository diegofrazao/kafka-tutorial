package com.example.kafkahello;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.Properties;

//@SpringBootApplication
public class Producer {

    public static void main(String[] args) throws Exception {
//        SpringApplication.run(Producer.class, args);

        String clientId = "my-producer";

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:29092"); //conexao com o kafka
        props.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer"); //tipo da chave da mensagem
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer"); //tipo do valor da mensagem
        props.put("acks", "all");
        props.put("client.id", clientId);

        KafkaProducer<Number, String> producer = new KafkaProducer<Number, String>(props);

        int numOfRecords = 10; //numero de mensagens que esta criando
        String topic = "numbers";

        //Example - Formatted string as message and messages are sent with 300ms delay (3 messages / second)
        try {
            for (int i = 0; i < numOfRecords; i++) {
                String message = String.format("Producer %s has sent message %s at %s", clientId, i, new Date());

                System.out.println(message);

                producer.send(new ProducerRecord<Number, String>(topic, i, Integer.toString(i)));
                Thread.sleep(300);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }
}
