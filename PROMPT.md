Application.java:3: error: package org.springframework.boot does not exist
import org.springframework.boot.SpringApplication;
                               ^
Application.java:4: error: package org.springframework.boot.autoconfigure does not exist
import org.springframework.boot.autoconfigure.SpringBootApplication;
                                             ^
Application.java:6: error: cannot find symbol
@SpringBootApplication
 ^
  symbol: class SpringBootApplication
Application.java:9: error: cannot find symbol
        SpringApplication.run(Application.class, args);
        ^
  symbol:   variable SpringApplication
  location: class Application
4 errors

Me direcione sobre o erro gerado e o que pode ser feito para corrigir



o que você melhoraria no trecho selecionado?




realize essas sugestões no código





TaskStatus precisa de um dto para entrada e validação com @Valid. Como você criaria no mesmo diretório do TaskRequest.java?


crie, por favor