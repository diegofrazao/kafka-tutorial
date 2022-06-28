# Tutorial do Kafka (com docker)

### Para esse tutorial será necessário a instalação de algumas ferramentas, tais como:
- [Docker](https://docs.docker.com/get-docker/);
- IDE (
    [Eclipse](https://www.eclipse.org/downloads/), 
    [Intellij](https://www.jetbrains.com/pt-br/idea/download/#section=windows), 
    [VScode](https://code.visualstudio.com/download));

### Configuração do projeto:

#### Obter o código do projeto

```shell
git clone https://github.com/diegofrazao/kafka-tutorial.git
```

#### Rodar o docker desktop
Abra o seu [Docker](https://docs.docker.com/get-docker/) (pré-requisito deste tutorial) e deixe-o aberto.

#### Subir as imagens do Kafka no docker
Abra a pasta onde está localizado o arquivo *“docker-compose.yml”* (no projeto está na raiz).

Execute o comando e espere finalizar as configurações:
```shell
docker-compose up -d
```

#### Abrir o projeto
Abra o projeto na sua IDE preferida e faça o build o projeto.

### Executando o projeto

Execute a classe **Consumer** (src/main/java/com/example/kafkahello/Consumer.java) em um terminal da IDE e o deixe aberto.

Agora execute a classe **Producer** (src/main/java/com/example/kafkahello/Producer.java) em outro terminal.

> Neste ponto as mensagens geradas pelo **Producer** serão imprimidas no terminal do producer e as mensgaens lidas pelo **Consumer** serão imprimidas no terminal do consumer.

### Alterando as mensagens enviadas pelo producer

```
String message = String.format( **sua_mensagem_aqui** );
System.out.println(message);
producer.send(new ProducerRecord<Number, String>(topic, i, **message**));
```

Faça as alterações no código nos lugares indicados por "**".

Com isso você conseguirá mudar a mensagem que desejar.
