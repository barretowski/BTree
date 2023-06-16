# BTree

![image](https://github.com/barretowski/BTree/assets/69700545/b242c8bf-4524-46c1-b68b-816bd0b50e0c)

# Teste de mesa - Inserção
![btree_insercao](https://github.com/barretowski/BTree/assets/69700545/10f4e5d6-0e28-4b5d-a7be-cb9a7e94262b)

# Exclusão BTree
-> 1º Caso: O elemento está em um nó, sendo assim só remove-lo e dps remanejar ao contrario.
-> 2º Caso: O elemento está em um "pai", então é necessario localizar um substituto
	- 1º Elemento = Anda no nó 0, dps anda TL até o final
	- 2º Elemento = Andea no nó 1, dps pos 0 até o final
	- Verifica se o primeiro elemento tem irmãos e é > N, se for menor, pega o elemento da direita.
	
-> 3º Caso: O elemento é uma folha, porem após a remoção, o nivel fica < N, sendo assim:
	- Localizar o pai da folha
	- Localiza as irmãs do pai (pai-1 e pai+1)
	- Verifica qual das irmãs tem a quantidade de elementos > N
	- Obtem a irmã do pai disponivel e desce para a folha
	- Obtem o nó filho da irmã mais proximo (esquerda ou direita) e sobe ele para a posição do pai.

-> 4º Caso: ambas as paginas contem <= N, sendo assim nenhuma pode realizar a troca
	- É necessario fazer a fusão das paginas
  - Obtem a folha da esquerda e desloca para a posição removida.
  - Localiza a irmã da esquerda do elemento folha
  - Seleciona o pai e desce para a folha, fazendo a concatenação dela com as 2 filhas.
  - Verifica se o pai está >=N, caso não esteja: 
  - Realiza a concatenação da pagina do pai com a sua irmã

# Teste de mesa - exclusão
![Teste de mesa - exclusão](https://github.com/barretowski/BTree/assets/69700545/87dcc52b-35c7-4f9b-a9b8-9f5b54c43196)
