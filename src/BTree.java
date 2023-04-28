public class BTree implements Definitions{
    private No raiz;

    public BTree(){
        this.raiz = null;
    }

    public No getRaiz() {
        return raiz;
    }

    //metodo para retornar endereço da folha;
    public No navegarAteFolha(int info){
        return raiz;
    }

    //metodo para buscar o pai do elemento informado
    public No localizaPai(No folha, int info){
        return raiz;
    }

    public void split(No folha, No pai){
        No cx1 = new No();
        No cx2 = new No();
        int pos;
        //realiza o split do vetor info, obtendo o inicio até a raiz, jogando em um vetor auxiliar tamanho N
        for(int i=0; i<N; i++){
            cx1.setvInfo(i,folha.getvInfo(i));
            cx1.setvPos(i, folha.getvPos(i));
            cx1.setvLig(i,folha.getvLig(i));
        }
        cx1.setvLig(N,folha.getvLig(N));
        cx1.setTl(N);

        //realiza o split do vetor info, obtendo o a pos raiz+1 até o final, jogando em um vetor auxiliar tamanho N
        for(int i=N+1; i<N*2+1; i++){
            cx2.setvInfo(i-N-1,folha.getvInfo(i));
            cx2.setvPos(i-N-1,folha.getvPos(i));
            cx2.setvLig(i-N-1, folha.getvLig(i));
        }
        cx2.setvLig(N,folha.getvLig(N*2+1));
        cx2.setTl(N);

        if(pai == folha){
            folha.setvInfo(0, folha.getvInfo(N));//obtem o cara do meio
            folha.setvPos(0, folha.getvPos(N));

            folha.setvLig(0, cx1);
            folha.setvLig(1, cx2);

            folha.setTl(1);
        }else{
            pos = pai.procurarPosicao(folha.getvInfo(N));
            pai.remanejar(pos);
            pai.setvInfo(pos, folha.getvInfo(N));
            pai.setvPos(pos, folha.getvPos(N));
            pai.setTl(pai.getTl()+1);
            pai.setvLig(pos, cx1);
            pai.setvLig(pos+1, cx2);

            if(pai.getTl()>N*2){//verifica se o pai passou do limite do Vetor, dando split nele se necessario
                folha = pai;
                pai = localizaPai(folha,folha.getvInfo(N));
                split(folha, pai);
            }
        }
    }

    public void inserir(int info, int posArq){
        No folha, pai;
        int pos;
        if(raiz == null)
            raiz = new No(info, posArq);
        else{
            folha = navegarAteFolha(info);
            pos = folha.procurarPosicao(info);
            folha.remanejar(pos);
            folha.setvInfo(pos, info);
            folha.setvPos(pos, posArq);
            folha.setTl(folha.getTl()+1);

            if(folha.getTl()>2*N){
                pai = localizaPai(folha, info);
                split(folha, pai);
            }
        }
    }
}
