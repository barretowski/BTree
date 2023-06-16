public class Main {
    public static void main(String[] args) {
        BTree bTree = new BTree();
        bTree.inserir(3, 0);
        bTree.inserir(4, 0);
        bTree.inserir(8, 0);
        bTree.inserir(9, 0);
        bTree.inserir(10, 0);
        bTree.inserir(11, 0);
        bTree.inserir(13, 0);
        bTree.inserir(17, 0);
        bTree.inserir(20, 0);
        bTree.inserir(25, 0);
        bTree.inserir(28, 0);
        bTree.inserir(30, 0);
        bTree.inserir(55, 0);
        bTree.inserir(52, 0);
        bTree.inserir(48, 0);
        bTree.inserir(45, 0);
        bTree.inserir(43, 0);
        bTree.inserir(40, 0);
        bTree.inserir(36, 0);
        bTree.inserir(33, 0);
        bTree.inserir(50, 0);

        /*bTree.inserir(20, 0);
        bTree.inserir(10, 0);
        bTree.inserir(30, 0);
        bTree.inserir(40, 0);
        bTree.inserir(15, 0);
        bTree.inserir(35, 0);
        bTree.inserir(50, 0);
        bTree.inserir(25, 0);
        bTree.inserir(9, 0);
        bTree.inserir(5, 0);
        bTree.inserir(13, 0);
        bTree.inserir(21, 0);
        bTree.inserir(22, 0);
        bTree.inserir(23, 0);
        bTree.inserir(26, 0);
        bTree.inserir(27, 0);
        bTree.inserir(31, 0);*/

        System.out.println("-> Arvore-B");
        bTree.in_ordem(bTree.getRaiz());

        bTree.excluir(50);
        bTree.excluir(52);
        bTree.excluir(55);
        bTree.excluir(45);
        bTree.excluir(43);
        bTree.excluir(36);
        bTree.excluir(28);


        System.out.println("\n\n-> Arvore-B após exclusão");
        bTree.in_ordem(bTree.getRaiz());
    }
}