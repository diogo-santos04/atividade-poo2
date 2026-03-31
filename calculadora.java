import java.awt.BorderLayout; [cite: 1]
import java.awt.Font; [cite: 2]
import java.awt.GridLayout; [cite: 4]
import java.awt.event.ActionEvent; [cite: 5]
import javax.swing.JButton; [cite: 7]
import javax.swing.JFrame; [cite: 8]
import javax.swing.JPanel; [cite: 10]
import javax.swing.JTextField; [cite: 11]

public class CalculadoraVisual {
    // Variáveis para armazenar os dados do cálculo
    private static double primeiroNumero = 0;
    private static String operacao = "";
    private static boolean novaOperacao = true;

    public static void main(String[] args) {
        // 1. Configuração do Frame (janela)
        JFrame frame = new JFrame("Calculadora Java"); [cite: 17]
        frame.setSize(300, 400); [cite: 18]
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); [cite: 19]
        frame.setLayout(new BorderLayout()); [cite: 21]

        // 2. Visor da calculadora
        JTextField visor = new JTextField(); [cite: 23]
        visor.setFont(new Font("Arial", Font.BOLD, 24)); [cite: 24]
        visor.setEditable(false); [cite: 25]
        visor.setHorizontalAlignment(JTextField.RIGHT); [cite: 26]
        frame.add(visor, BorderLayout.NORTH); [cite: 28]

        // 3. Painel de botões
        JPanel painelBotoes = new JPanel(); [cite: 30]
        painelBotoes.setLayout(new GridLayout(5, 4, 5, 5)); [cite: 31]

        // 4. Texto dos botões (corrigido conforme sua lista)
        String[] textosBotoes = {
            "7", "8", "9", "/", [cite: 35]
            "4", "5", "6", "*", [cite: 36, 37]
            "1", "2", "3", "-", [cite: 39, 41, 42, 43]
            "0", ".", "=", "+", [cite: 44, 45, 47, 48]
            "C" [cite: 49]
        };

        // 5. Laço para criar e dar funcionalidade aos botões
        for (String texto : textosBotoes) { [cite: 52]
            JButton botao = new JButton(texto); [cite: 53]
            
            botao.addActionListener((ActionEvent e) -> { [cite: 54]
                if (texto.equals("C")) { [cite: 55]
                    visor.setText(""); [cite: 57]
                    primeiroNumero = 0;
                    operacao = "";
                } 
                else if (texto.equals("=")) { [cite: 58]
                    double segundoNumero = Double.parseDouble(visor.getText());
                    double resultado = calcular(primeiroNumero, segundoNumero, operacao);
                    visor.setText(String.valueOf(resultado));
                    novaOperacao = true;
                } 
                else if (texto.matches("[/\\-*+]")) { // Se for um operador
                    primeiroNumero = Double.parseDouble(visor.getText());
                    operacao = texto;
                    novaOperacao = true;
                } 
                else { // Se for número ou ponto
                    if (novaOperacao) {
                        visor.setText(texto);
                        novaOperacao = false;
                    } else {
                        visor.setText(visor.getText() + texto); [cite: 65]
                    }
                }
            });
            
            painelBotoes.add(botao); [cite: 69]
        }

        frame.add(painelBotoes, BorderLayout.CENTER); [cite: 71]
        frame.setVisible(true); [cite: 75]
    }

    // Método auxiliar para realizar as contas
    private static double calcular(double n1, double n2, String op) {
        switch (op) {
            case "+": return n1 + n2;
            case "-": return n1 - n2;
            case "*": return n1 * n2;
            case "/": return n2 != 0 ? n1 / n2 : 0;
            default: return n2;
        }
    }
}
