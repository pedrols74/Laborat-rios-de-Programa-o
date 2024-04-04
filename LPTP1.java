package estga.poo.lptp1;

import java.io.*;
import java.util.*;

public class LPTP1 {
    
    public static void main(String[] args) {
        try {
            FileInputStream fileIn = new FileInputStream("circulo_coimbra.dat");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            // Lê o objeto VotosCirculoEleitoral do arquivo
            VotosCirculoEleitoral votosCirculoEleitoral = (VotosCirculoEleitoral) objectIn.readObject();

            // Fecha o fluxo de entrada
            objectIn.close();
            fileIn.close();

            // Imprime os resultados
            System.out.println("Nome do Círculo Eleitoral: " + votosCirculoEleitoral.getNomeCirculo());
            System.out.println("Resultados por Concelho:");

            // Itera sobre os resultados por concelho
            for (Map.Entry<String, VotosConcelho> entry : votosCirculoEleitoral.getVotosPorConcelho().entrySet()) {
                String concelho = entry.getKey();
                VotosConcelho votosConcelho = entry.getValue();

                System.out.println("Concelho: " + concelho);
                System.out.println("Resultados por Partido:");

                // Itera sobre os resultados por partido dentro do concelho
                for (Map.Entry<String, Integer> partidoEntry : votosConcelho.getVotosPorPartido().entrySet()) {
                    String partido = partidoEntry.getKey();
                    int votos = partidoEntry.getValue();

                    System.out.println("Partido: " + partido + ", Votos: " + votos);
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    
class VotosCirculoEleitoral implements Serializable {
    private String nomeCirculo;
    private Map<String, VotosConcelho> votosPorConcelho;

    public VotosCirculoEleitoral(String nomeCirculo) {
        this.nomeCirculo = nomeCirculo;
        this.votosPorConcelho = new HashMap<>();
    }

    public void adicionarVotosConcelho(String concelho, VotosConcelho dadosVotosConcelho) {
        votosPorConcelho.put(concelho, dadosVotosConcelho);
    }

    public String getNomeCirculo() {
        return nomeCirculo;
    }

    public Map<String, VotosConcelho> getVotosPorConcelho() {
        return votosPorConcelho;
    }
}

class VotosConcelho implements Serializable {
    private String concelho;
    private Map<String, Integer> votosPorPartido;

    public VotosConcelho(String concelho) {
        this.concelho = concelho;
        this.votosPorPartido = new HashMap<>();
    }

    public void adicionarVotos(String partido, int votos) {
        votosPorPartido.put(partido, votos);
    }

    public String getConcelho() {
        return concelho;
    }

    public Map<String, Integer> getVotosPorPartido() {
        return votosPorPartido;
    }
}
}
