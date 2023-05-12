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
        No folha=raiz;
        int pos;
        while(folha.getvLig(0)!=null){
            pos = folha.procurarPosicao(info);//procura a melhor posição atual
            folha = folha.getvLig(pos);
        }
        return folha;
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
    public void excluir(int info){
        No no = localizaNo(info);
        No subE, subD, folha;
        int pos;
        if(no!=null){
            pos = no.procurarPosicao(info);
            if(no.getvLig(0)!=null){ // não é folha
                subE=localizarSubE(no, pos);
                subD=localizarSubD(no, pos);
                if(subE.getTl()>N && subD.getTl()<=N){//sub da esquerda
                    no.setvInfo(pos, subE.getvInfo(subE.getTl()-1));
                    no.setvPos(pos,subE.getvPos(subE.getTl()-1));
                    folha = subE;
                    pos = subE.getTl()-1;
                }else{//sub da direita
                    no.setvInfo(pos, subD.getvInfo(0));
                    no.setvPos(pos, subD.getvPos(0));
                    folha = subD;;
                    pos = 0;
                }
            }else
                folha = no;

            //remover da folha
            folha.remanejarExclusao(pos);
            folha.setTl(folha.getTl()-1);

            if(folha==raiz && folha.getTl()==0)
                raiz = null;
            else if(folha!=raiz && folha.getTl()<N){
                redistr_concat(folha);
            }

        }
    }

    private No localizarSubE(No no, int pos)
    {
        no=no.getvLig(pos);
        while(no.getvLig(0)!=null)
            no=no.getvLig(no.getTl());
        return no;
    }

    private No localizarSubD(No no, int pos)
    {
        no=no.getvLig(pos);
        while(no.getvLig(0)!=null)
            no=no.getvLig(0);
        return no;
    }

    private No localizaNo(int info) {
        No no = raiz;
        boolean achou = false;
        int pos;
        while(no!=null && !achou){
            pos = no.procurarPosicao(info);
            if(pos<no.getTl() && no.getvInfo(pos)==info)
                achou = true;
            else
                no = no.getvLig(pos);
        }
        return no;
    }

    public void redistr_concat(No folha){
        No pai = localizaPai(folha, folha.getvInfo(0));
        int posPai = pai.procurarPosicao(folha.getvInfo(0));
        No irmaE, irmaD;
        if(posPai>0)
            irmaE = pai.getvLig(posPai-1);
        else
            irmaE = null;

        if(posPai<pai.getTl())
            irmaD = pai.getvLig(posPai+1);
        else
            irmaD = null;

        if(irmaE!=null && irmaE.getTl()>N){//redistribuição com a irmã da esquerda
            folha.remanejar(0);
            folha.setvInfo(0, pai.getvInfo(posPai-1));
            folha.setvPos(0, pai.getvPos(posPai-1));
            folha.setTl(folha.getTl()+1);
            pai.setvInfo(posPai-1,irmaE.getvInfo(irmaE.getTl()-1));
            //continua...
        }else if(irmaD!=null && irmaD.getTl()>N){//redistribuição com a irmã da direita

        }else{//concatenação
            if(irmaE!=null){//concatenação com a irmã da esquerda
                //desce o pai e concatena com a irmã da esquerda...
                irmaE.setvInfo(irmaE.getTl(), pai.getvInfo(posPai-1));
                irmaE.setvPos(irmaE.getTl(), pai.getvPos(posPai-1));
                irmaE.setTl(irmaE.getTl()+1);

                //exclui a antiga posição do pai
                pai.remanejarExclusao(posPai-1);
                pai.setTl(pai.getTl()-1);

                //percorre toda a folha da direita para trazer os elementos restantes para a irma da esquerda
                for(int i=0; i<folha.getTl(); i++){
                    irmaE.setvInfo(irmaE.getTl(), folha.getvInfo(i));
                    irmaE.setvPos(irmaE.getTl(), folha.getvInfo(i));

                    //pega as ligações
                    irmaE.setvLig(irmaE.getTl(), folha.getvLig(i));
                    irmaE.setTl(irmaE.getTl()+1);
                }
                irmaE.setvLig(irmaE.getTl(), folha.getvLig(folha.getTl()));
                pai.setvLig(posPai-1, irmaE);

            }else{//concatenação com a irmã da direita
                //desce o pai e concatena com a irma da direita
                irmaD.setvInfo(irmaD.getTl(), pai.getvInfo(posPai+1));
                irmaD.setvPos(irmaD.getTl(), pai.getvInfo(posPai+1));
                irmaD.setTl(irmaD.getTl()+1);

                //exclui a antiga posicao do pai
                pai.remanejarExclusao(posPai+1);
                pai.setTl(pai.getTl()+1);

                //percorre a folha da esquerda e insere na irma da direita
                for(int i=0; i<folha.getTl();i++){

                }
            }
            if(pai==raiz && folha.getTl()==0){
                if(irmaE!=null)
                    raiz = irmaE;
                else
                    raiz = irmaD;
            }else if(folha.getTl()<N){
                folha = pai;
                redistr_concat(folha);
            }
        }



    }

}
