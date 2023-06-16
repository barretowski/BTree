//Created by: Paulo Henrique Barreto
//UNOESTE-FIPP -> RA: 261811215
public class No implements Definitions{

    private int vInfo[];
    private int vPos[];
    private No vLig[];
    int tl;

    public No(){
        this.vInfo = new int[N*2+1];
        this.vPos = new int[N*2+1];
        this.vLig = new No[N*2+2];
    };
    public No(int info, int posArq){
        vInfo = new int[2*N+1];
        vPos = new int[2*N+1];
        vLig = new No[2*N+2];

        vInfo[0] = info;
        vPos[0] = posArq;
        tl = 1;
    }
    public int getvInfo(int p) {
        return vInfo[p];
    }

    public void setvInfo(int p, int info) {
        this.vInfo[p] = info;
    }

    public int getvPos(int p) {
        return vPos[p];
    }

    public void setvPos(int p, int info) {
        this.vPos[p] = info;
    }

    public No getvLig(int p) {
        return vLig[p];
    }

    public void setvLig(int p, No lig) {
        this.vLig[p] = lig;
    }

    public int getTl() {
        return tl;
    }

    public void setTl(int tl) {
        this.tl = tl;
    }

    public int procurarPosicao(int info){
        //busca melhor posição para inserção dentro do vetor
        int i=0;
        while(i<tl && info > vInfo[i]){
            i++;
        }

        return i;
    }

    public void remanejar(int pos){
        vLig[tl+1] = vLig[tl];
        for(int i = tl; i>pos; i--){
            vInfo[i] = vInfo[i-1];
            vPos[i] = vPos[i-1];
            vLig[i] = vLig[i-1];
        }
    }

    public void remanejarExclusao(int pos) {
        for(int i=pos; i<tl-1; i++){
            vInfo[i] = vInfo[i+1];
            vPos[i] = vPos[i+1];
            vLig[i] = vLig[i+1];
        }
    }
}
