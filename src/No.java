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
        this.vInfo[posArq] = info;
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
        return 1;
    }

    public void remanejar(int pos){

    }
}
