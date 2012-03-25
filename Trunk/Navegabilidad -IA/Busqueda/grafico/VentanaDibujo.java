/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Ventana.java
 *
 * Created on 22/03/2012, 22:01:18
 */

package grafico;

import java.awt.Color;
import java.awt.geom.Line2D;
import java.io.FileWriter;
import java.io.PrintWriter;

import utils.MathAux;

/**
 *
 * @author FABIAN
 */
public class VentanaDibujo extends javax.swing.JFrame {
	 
	
	// Variables declaration - do not modify//GEN-BEGIN:variables
    private BotonPanelDibujo btnExportar;
    private BotonPanelDibujo btnLimpiar;
    private javax.swing.JPanel jPanel1;
    public PanelDibujo panelDibujo;
    private javax.swing.JSplitPane jSplitPane1;
    // End of variables declaration//GEN-END:variables

    /** Creates new form Ventana */
    public VentanaDibujo() {
        initComponents();
        addListeners();	
        this.setTitle("Navegabilidad");
    	this.setLocationRelativeTo(null);
    	this.setResizable(false);
    	this.setVisible(true);
        //this.setSize(640, 480);
    }

    private void addListeners() {
		MouseAdaptador mouseMove = new MouseAdaptador();
		panelDibujo.addMouseMotionListener(mouseMove);
		panelDibujo.addMouseListener(mouseMove);
		btnExportar.addMouseListener(mouseMove);
		btnLimpiar.addMouseListener(mouseMove);
    	
    	KeyAdaptador keyAdaptor = new KeyAdaptador();
    	this.addKeyListener(keyAdaptor);
    	panelDibujo.addKeyListener(keyAdaptor);
		
	}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        btnExportar = new BotonPanelDibujo(this,BotonPanelDibujo.ACC_EXPORTAR);
        btnLimpiar = new BotonPanelDibujo(this,BotonPanelDibujo.ACC_LIMPIAR);
        //panelDibujo = new javax.swing.JPanel();
        panelDibujo = new PanelDibujo(MathAux.ANCHO,MathAux.ALTO,Color.WHITE);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnExportar.setText("Exportar");
        btnLimpiar.setText("Limpiar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLimpiar)
                    .addContainerGap())
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(btnExportar)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(btnExportar)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnLimpiar)
                    .addContainerGap(246, Short.MAX_VALUE))
            );

        jSplitPane1.setLeftComponent(jPanel1);

        panelDibujo.setPreferredSize(new java.awt.Dimension(MathAux.ANCHO, MathAux.ALTO));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(panelDibujo);
        panelDibujo.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 293, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 298, Short.MAX_VALUE)
        );

        jSplitPane1.setRightComponent(panelDibujo);

        getContentPane().add(jSplitPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaDibujo().setVisible(true);
            }
        });
    }
    
    public void imprimirLineas()
    {
    	for (int i = 0; i < panelDibujo.lineas.size(); i++) {
    		Line2D linea = (Line2D) panelDibujo.lineas.get(i);
			System.out.println("P1: ("+linea.getX1()+", "+linea.getY1()+")\tP2: ("+linea.getX2()+", "+linea.getY2()+")");
		}
    	exportarATxt();
    }
    
    
    //Exporta a un .txt con la siguienta notaci�n: "(x1,y2);(x2,y2)"
    public void exportarATxt()
    {
    	FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(".\\output.txt");
            pw = new PrintWriter(fichero);

            for (int i = 0; i < panelDibujo.lineas.size(); i++) {
        		Line2D linea = (Line2D) panelDibujo.lineas.get(i);
        		 pw.println("("+linea.getX1()+","+linea.getY1()+");("+linea.getX2()+","+linea.getY2()+")");
    		}

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
    }
    

}
