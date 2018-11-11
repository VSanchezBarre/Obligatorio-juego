package paqueteInterfaz;

import PackageJuego.Jugador;
import PackageJuego.Sistema;
import java.util.ArrayList;
import javax.swing.JList;

public class Registrar extends javax.swing.JFrame {

    Sistema sistema;
    ArrayList<Jugador> listaJugadores;
    ArrayList<String> listaAlias;

    public void setSistema(Sistema sistema) {
        this.sistema = sistema;
    }

    public Sistema getSistema() {
        return sistema;
    }

    public Registrar(Sistema sistema) {
        this.sistema = sistema;
        this.listaJugadores = sistema.getListaJugadores();
        this.listaAlias = crearListaAlias(sistema);
        this.jlistJugadores = new JList(listaAlias.toArray());
        initComponents();
    }

    public ArrayList<String> crearListaAlias(Sistema sistema) {
        ArrayList<String> listaAlias = new ArrayList<>();
        for (int i = 0; i < listaJugadores.size(); i++) {
            String alias = listaJugadores.get(i).getAlias();
            listaAlias.add(alias);
        }

        return listaAlias;
    }

    public Registrar() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jlistJugadores = new javax.swing.JList<>();
        textoNombre = new javax.swing.JTextField();
        textoAlias = new javax.swing.JTextField();
        textoEdad = new javax.swing.JTextField();
        labelNombre = new javax.swing.JLabel();
        labelAlias = new javax.swing.JLabel();
        labelEdad = new javax.swing.JLabel();
        botonEnter = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        titulo = new javax.swing.JTextPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        tituloLista = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jlistJugadores.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jlistJugadores);

        textoNombre.setText("Nombre");
        textoNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoNombreActionPerformed(evt);
            }
        });

        textoAlias.setText("Alias");

        textoEdad.setText("Edad");

        labelNombre.setText("Nombre");

        labelAlias.setText("Alias");

        labelEdad.setText("Edad");

        botonEnter.setText("ENTER");
        botonEnter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEnterActionPerformed(evt);
            }
        });

        titulo.setText("Registrar jugadores");
        jScrollPane3.setViewportView(titulo);

        tituloLista.setText("Lista de jugadores");
        jScrollPane5.setViewportView(tituloLista);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(313, 313, 313)
                        .addComponent(botonEnter))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(277, 277, 277)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(139, 139, 139)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelEdad)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textoEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelAlias)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textoAlias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelNombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 238, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane5)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE))
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelNombre))
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textoAlias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelAlias))))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelEdad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(botonEnter)
                .addGap(169, 169, 169))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textoNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoNombreActionPerformed

    }//GEN-LAST:event_textoNombreActionPerformed

    private void botonEnterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEnterActionPerformed

        boolean salir = false;
        while (!salir) {
            if (textoNombre.getText().equals("") || textoAlias.getText().equals("") || textoNombre.getText().equals("")) {
                System.out.println("Debe ingresar todos los valores ");
            } else {
                Jugador unJugador = new Jugador();
                unJugador.setNombre(textoNombre.getText());
                unJugador.setAlias((textoAlias).getText());
                unJugador.setEdad(Integer.parseInt(textoEdad.getText()));
                if (!listaAlias.contains(textoAlias.getText())) {
                    sistema.getListaJugadores().add(unJugador);
                    System.out.println("Jugador agregado con exito");
                    salir = true;

                } else {
                    System.out.println("Este alias ya esta ingresado, ingrese denuevo");
                }
            }
        }
    }//GEN-LAST:event_botonEnterActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registrar().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonEnter;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JList<String> jlistJugadores;
    private javax.swing.JLabel labelAlias;
    private javax.swing.JLabel labelEdad;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JTextField textoAlias;
    private javax.swing.JTextField textoEdad;
    private javax.swing.JTextField textoNombre;
    private javax.swing.JTextPane titulo;
    private javax.swing.JTextPane tituloLista;
    // End of variables declaration//GEN-END:variables
}
