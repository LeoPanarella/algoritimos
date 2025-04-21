import java.util.LinkedList;

class Entrada {
    String chave;
    String valor;

    public Entrada(String chave, String valor) {
        this.chave = chave;
        this.valor = valor;
    }

    @Override
    public String toString() {
        return chave + " => " + valor;
    }
}

public class TabelaHash {
    private LinkedList<Entrada>[] tabela;
    private int tamanho;

    public TabelaHash(int tamanho) {
        this.tamanho = tamanho;
        tabela = new LinkedList[tamanho];
        for (int i = 0; i < tamanho; i++) {
            tabela[i] = new LinkedList<>();
        }
    }

    private int hash(String chave) {
        int hashCode = 0;
        for (char c : chave.toCharArray()) {
            hashCode += c;
        }
        return hashCode % tamanho;
    }

    public void inserir(String chave, String valor) {
        int indice = hash(chave);
        for (Entrada e : tabela[indice]) {
            if (e.chave.equals(chave)) {
                e.valor = valor;
                return;
            }
        }
        tabela[indice].add(new Entrada(chave, valor));
    }

    public String buscar(String chave) {
        int indice = hash(chave);
        for (Entrada e : tabela[indice]) {
            if (e.chave.equals(chave)) {
                return e.valor;
            }
        }
        return null;
    }

    public void remover(String chave) {
        int indice = hash(chave);
        tabela[indice].removeIf(e -> e.chave.equals(chave));
    }

    public void mostrarTabela() {
        for (int i = 0; i < tamanho; i++) {
            System.out.print("Índice " + i + ": ");
            for (Entrada e : tabela[i]) {
                System.out.print(e + " | ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        TabelaHash tabela = new TabelaHash(10);

        tabela.inserir("maçã", "fruta vermelha");
        tabela.inserir("banana", "fruta amarela");
        tabela.inserir("carro", "veículo");
        tabela.inserir("cachorro", "animal");
        tabela.inserir("gato", "animal");

        tabela.mostrarTabela();

        System.out.println("\nBuscar chave 'banana': " + tabela.buscar("banana"));

        tabela.remover("banana");

        System.out.println("\nApós remover 'banana':");
        tabela.mostrarTabela();
    }
}

