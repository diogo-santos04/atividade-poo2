import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculadora {
    private JFrame frame;
    private JTextField visor;
    private double valorAtual = 0;
    private String operador = "";
    private boolean iniciandoNovoNumero = true;

    Run | Debug
    public static void main(String[] args) {
        new Calculadora().iniciar();
    }

    public void iniciar() {
        frame = new JFrame(title: "Calculadora Java");
        frame.setSize(width: 300, height: 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        visor = new JTextField(text: "0");
        visor.setFont(new Font(name: "Arial", Font.BOLD, size: 24));
        visor.setEditable(b: false);
        visor.setHorizontalAlignment(JTextField.RIGHT);
        frame.add(visor, BorderLayout.NORTH);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(rows: 5, cols: 4, hgap: 5, vgap: 5));

        String[] textosBotoes = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C"
        };

        for (String texto : textosBotoes) {
            JButton botao = new JButton(texto);
            botao.addActionListener(e -> processarClique(texto));
            painelBotoes.add(botao);
        }

        frame.add(painelBotoes, BorderLayout.CENTER);
        frame.setVisible(b: true);
    }

    private void processarClique(String texto) {
        if ("0123456789.".contains(texto)) {
            if (iniciandoNovoNumero) {
                visor.setText(texto);
                iniciandoNovoNumero = false;
            } else {
                visor.setText(visor.getText() + texto);
            }
        } else if ("C".equals(texto)) {
            visor.setText(t: "0");
            valorAtual = 0;
            operador = "";
            iniciandoNovoNumero = true;
        } else if ("=".equals(texto)) {
            calcular();
            operador = "";
            iniciandoNovoNumero = true;
        } else {
            if (!iniciandoNovoNumero) {
                calcular();
            }
            operador = texto;
            valorAtual = Double.parseDouble(visor.getText());
            iniciandoNovoNumero = true;
        }
    }

    private void calcular() {
        if (operador.isEmpty()) return;
        double segundoValor = Double.parseDouble(visor.getText());
        double resultado = 0;

        switch (operador) {
            case "+":
                resultado = valorAtual + segundoValor;
                break;
            case "-":
                resultado = valorAtual - segundoValor;
                break;
            case "*":
                resultado = valorAtual * segundoValor;
                break;
            case "/":
                if (segundoValor != 0) {
                    resultado = valorAtual / segundoValor;
                } else {
                    visor.setText(t: "Erro");
                    iniciandoNovoNumero = true;
                    return;
                }
                break;
        }

        if (resultado == (long) resultado) {
            visor.setText(String.format(format: "%d", (long) resultado));
        } else {
            visor.setText(String.valueOf(resultado));
        }
        valorAtual = resultado;
    }
}
