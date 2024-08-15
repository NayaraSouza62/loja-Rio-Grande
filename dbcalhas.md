## Objetivo
O banco de dados `calhas` gerencia informações de usuários, clientes e ordens de serviços.
 
## Considerações
Todas as tabelas devem manter a integridade referencial para que o sistema funcione da melhor forma.

## Estrutura completa do banco de dados (MySql)
-- Criação do banco de dados
CREATE DATABASE calhas;

-- Tabela criação de usuários
CREATE TABLE `tbusuarios` (
  `iduser` INT NOT NULL AUTO_INCREMENT,
  `usuario` VARCHAR(30) NOT NULL,
  `fone` VARCHAR(15) DEFAULT NULL,
  `login` VARCHAR(15) NOT NULL,
  `senha` VARCHAR(15) NOT NULL,
  `perfil` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`iduser`),
  UNIQUE KEY `login` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Tabela criação de clientes, contendo um idcli para servir de FOREIGN KEY
CREATE TABLE `tbclientes` (
  `idcli` INT NOT NULL AUTO_INCREMENT,
  `nomecli` VARCHAR(40) NOT NULL,
  `fone` VARCHAR(15) NOT NULL,
  `endcli` VARCHAR(50) NOT NULL,
  `cpf` VARCHAR(11) DEFAULT NULL,
  PRIMARY KEY (`idcli`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAU


