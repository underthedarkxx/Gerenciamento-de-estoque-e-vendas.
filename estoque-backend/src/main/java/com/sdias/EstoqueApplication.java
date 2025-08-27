package com.sdias;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal que inicia a aplicação Spring Boot.
 * É o ponto de entrada de todo o projeto.
 */
@SpringBootApplication // Esta anotação combina três outras: @Configuration, @EnableAutoConfiguration e @ComponentScan
public class EstoqueApplication {

    /**
     * O método main é o ponto de partida que o Java executa.
     * A linha SpringApplication.run() inicializa o Spring, cria o servidor
     * e prepara toda a sua aplicação para receber requisições.
     * @param args Argumentos de linha de comando (geralmente não usados aqui).
     */
    public static void main(String[] args) {
        SpringApplication.run(EstoqueApplication.class, args);
    }
}
