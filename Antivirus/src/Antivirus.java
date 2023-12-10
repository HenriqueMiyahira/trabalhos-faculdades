import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Antivirus {

    public static void main(String[] args) {
    	
        String diretorio = "C:\\Users\\henri\\Desktop\\virus";

        List<String> arquivosAchados = new ArrayList<>();

        buscarArquivos(diretorio, arquivosAchados);

        gerarResultado(arquivosAchados);

    }

    private static void buscarArquivos(String diretorio, List<String> arquivosAchados) {
        File pasta = new File(diretorio);
        File[] arquivos = pasta.listFiles();

        if (arquivos != null) {
            for (File arquivo : arquivos) {
                if (arquivo.isFile() && (arquivo.getName().endsWith(".exe") || arquivo.getName().endsWith(".bat"))) {
                	arquivosAchados.add(arquivo.getAbsolutePath());
                } else if (arquivo.isDirectory()) {

                    buscarArquivos(arquivo.getAbsolutePath(), arquivosAchados);
                }
            }
        }
    }

    private static void gerarResultado(List<String> arquivosAchados) {
        if (arquivosAchados.isEmpty()) {
            System.out.println("Nenhum arquivo malicioso encontrado.");
            return;
        }

        System.out.println("Arquivos maliciosos encontrados:");

        for (String caminho : arquivosAchados) {
            System.out.println(caminho);
        }

        arquivosAchados(arquivosAchados);
    }

    private static void arquivosAchados(List<String> arquivosAchados) {
        try (FileWriter writer = new FileWriter("resultado_análise_antivirus.txt")) {
            for (String caminho : arquivosAchados) {
                writer.write(caminho + "\n");
            }
            System.out.println("Relatório salvo em resultado_análise_antivirus.txt");
        } catch (IOException e) {
            System.err.println("Erro ao salvar o resultado.");
            e.printStackTrace();
        }
    }
}
