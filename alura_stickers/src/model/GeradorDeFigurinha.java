package model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.io.InputStream;
import java.net.URL;

public class GeradorDeFigurinha {
    public void cria(InputStream inputStream, String nomeArquivo) throws IOException {

        // leitura da imagem
        // InputStream inputStream = new FileInputStream(new File("entrada/image.jpg"));
        // inputStream = new URL("https://m.media-amazon.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@.jpg").openStream();
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        // criar nova imagem em memória e com transparência e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        // copiar a imagem original pra novo imagem (em memória)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        // configurar a fonte
        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 120);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(fonte);

        // escrever uma frase na nova imagem
        graphics.drawString("TOPZERA", 350, novaAltura - 80);

        // escrever a nova imagem em um arquivo
        ImageIO.write(novaImagem, "png", new File("saida/" + nomeArquivo));
    }
}
