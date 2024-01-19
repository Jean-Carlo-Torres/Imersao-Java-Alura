import service.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        // fazer uma conexão HTTP e buscar os dados do JSON
        String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
        var client = HttpClient.newHttpClient();
        URI endereco = URI.create(url);
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();

        // extrair só os dados que interessam (titulo, poster, classificação)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        System.out.println(listaDeFilmes.size());
        System.out.println(listaDeFilmes.get(0));

        // exibir e manipular os dados
        int contador = 1;
        for (Map<String, String> filme : listaDeFilmes) {
            System.out.println(contador++ + "# FILME");
            System.out.println("TITULO: " + filme.get("title"));
            System.out.println("POSTER: " + filme.get("image"));

            // Exibir estrelas com base na nota do filme
            double imDbRating = Double.parseDouble(filme.get("imDbRating"));
            String estrelas = converteNotaParaEstrelas(imDbRating);

            System.out.print("AVALIAÇÃO:  ");
            System.out.println(colorirTexto(estrelas, Cor.AMARELO));
            System.out.println();
        }
    }

    // Função para converter a nota do filme em estrelas
    private static String converteNotaParaEstrelas(double nota) {
        if (nota >= 1 && nota <= 2.9) {
            return "★";
        } else if (nota >= 3 && nota <= 4.9) {
            return "★★";
        } else if (nota >= 5 && nota <= 6.9) {
            return "★★★";
        } else if (nota >= 7 && nota <= 8.9) {
            return "★★★★";
        } else if (nota >= 9 && nota <= 10) {
            return "★★★★★";
        } else {
            return "Nota inválida";
        }
    }

    public static String colorirTexto(String texto, Cor cor) {
        return cor.getCodigo() + texto + Cor.RESET.getCodigo();
    }

    // Enum para definir cores
    public enum Cor {
        RESET("\u001B[0m"),
        AMARELO("\u001B[33m");

        private final String codigo;

        Cor(String codigo) {
            this.codigo = codigo;
        }

        public String getCodigo() {
            return codigo;
        }
    }
}