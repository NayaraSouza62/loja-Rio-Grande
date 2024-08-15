## Objetivo
O banco de dados `calhas` gerencia informações de usuários, clientes e ordens de serviços.
 
## Considerações
Todas as tabelas devem manter a integridade referencial para que o sistema funcione da melhor forma.

## Estrutura completa do banco de dados (MySql)
 -- criação do banco de dados--
 create database calhas;
 
 -- tabela criação de usuários--
  CREATE TABLE `tbusuarios` (
  `iduser` int NOT NULL AUTO_INCREMENT,
  `usuario` varchar(30) NOT NULL,
  `fone` varchar(15) DEFAULT NULL,
  `login` varchar(15) NOT NULL,
  `senha` varchar(15) NOT NULL,
  `perfil` varchar(10) NOT NULL,
  PRIMARY KEY (`iduser`),
  UNIQUE KEY `login` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ;


 -- tabela criação de clientes, contendo um idcli para servir de FOREIGN KEY --
 CREATE TABLE `tbclientes` (
  `idcli` int NOT NULL AUTO_INCREMENT,
  `nomecli` varchar(40) NOT NULL,
  `fone` varchar(15) NOT NULL,
  `endcli` varchar(50) NOT NULL,
  `cpf` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`idcli`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- tabela criação de OS --
 CREATE TABLE `tbos` (
  `os` int NOT NULL AUTO_INCREMENT,
  `data_os` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `situacao` varchar(50) NOT NULL,
  `material` varchar(20) DEFAULT NULL,
  `valor` decimal(8,2) NOT NULL,
  `vendedor` varchar(20) DEFAULT NULL,
  `formapto` varchar(20) DEFAULT NULL,
  `idcli` int NOT NULL,
  PRIMARY KEY (`os`),
  KEY `idcli` (`idcli`),
  CONSTRAINT `tbos_ibfk_1` FOREIGN KEY (`idcli`) REFERENCES `tbclientes` (`idcli`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
