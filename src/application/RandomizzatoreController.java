package application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class RandomizzatoreController {

    @FXML
    private Button btnCronEliminaTutto;

    @FXML
    private Button btnCronEliminaUltima;

    @FXML
    private Button btnCronEsporta;

    @FXML
    private Button btnCronImporta;

    @FXML
    private Button btnGEAdd;

    @FXML
    private Button btnGEElRandomizzato;
    
    @FXML
    private Button btnGEEliminaElemento;

    @FXML
    private Button btnGEElimina;

    @FXML
    private Button btnGEEsporta;

    @FXML
    private Button btnGEEstrazione;

    @FXML
    private Button btnGEImport;

    @FXML
    private Button btnGEModifica;

    @FXML
    private Button btnGENuovoEl;

    @FXML
    private Button btnGEsalva;

    @FXML
    private Button btnPswGenera;

    @FXML
    private Button btnTestaCroce;

    @FXML
    private ComboBox<String> cmbGEelenco;

    @FXML
    private ImageView imgTestaCroce;

    @FXML
    private Label lblTitle;

    @FXML
    private CheckBox radPswSp;

    @FXML
    private TextArea txtCronologia;

    @FXML
    private TextArea txtGEElenco;

    @FXML
    private TextField txtGENome;

    @FXML
    private TextField txtGENomeMod;

    @FXML
    private TextField txtGEnomeEl;

    @FXML
    private TextArea txtMessage;

    @FXML
    private TextField txtPswNCar;

    private ArchiviazioneEstrazioni archiviazioneEstrazioni;
    private ArchiviazioneElenchi archiviazioneElenchi;

    private ArrayList<Estrazione> estrazioni;
    private GestoreElenchi gestoreElenchi;

    void aggiornaCronologia() {
        txtCronologia.clear();
        for (Estrazione e : estrazioni) {
            txtCronologia.appendText(e.toString() + "\n\n");
        }
    }

    void aggiornaElenco() {
        txtGEElenco.clear();
        Elenco elenco = gestoreElenchi.getElenco(cmbGEelenco.getValue());
        if(elenco != null) {
            txtGEElenco.appendText(elenco.toString());
        }
        else {
            txtGEElenco.appendText("Elenco non trovato o non selezionato");
        }
    }

    @FXML
    void azioneCronEliminaTutto(ActionEvent event) {
    	estrazioni.clear();
        aggiornaCronologia();
    }

    @FXML
    void azioneCronEliminaUltima(ActionEvent event) {
        estrazioni.remove(estrazioni.size() - 1);
        aggiornaCronologia();
    }

    @FXML
    void azioneGEElimina(ActionEvent event) {
        if(gestoreElenchi.rimuoviElenco(cmbGEelenco.getValue())) {
            txtMessage.setText("Elenco rimosso correttamente");
            cmbGEelenco.getItems().clear();
            cmbGEelenco.getItems().addAll(gestoreElenchi.getNomi());
        }
        else {
            txtMessage.setText("Errore nella rimozione dell'elenco");
        }
    }

    @FXML
    void azioneCronEsporta(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.csv"));
        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            String filePath = file.getAbsolutePath();
            try {
                archiviazioneEstrazioni.exportEstrazioni(estrazioni, filePath);
                txtMessage.setText("Estrazioni esportate correttamente");
            } catch (IOException e) {
                txtMessage.setText("Errore nell'esportazione delle estrazioni: " + e.getMessage());
            }
        }
    }

    @FXML
    void azioneGEEsporta(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.csv"));
        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            String filePath = file.getAbsolutePath();
            try {
                archiviazioneElenchi.exportElenco(gestoreElenchi.getElenco(cmbGEelenco.getValue()), filePath);
                txtMessage.setText("Elenco esportato correttamente");
            } catch (IOException e) {
                txtMessage.setText("Errore nell'esportazione dell'elenco: " + e.getMessage());
            }
        }
    }

    @FXML
    void azioneCronImporta(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.csv"));
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            String filePath = file.getAbsolutePath();
            try {
                estrazioni = archiviazioneEstrazioni.importEstrazioni(filePath);
                aggiornaCronologia();
                txtMessage.setText("Estrazioni importate correttamente");
            } catch (Exception e) {
                txtMessage.setText("Errore nell'importazione delle estrazioni: " + e.getMessage());
            }
        }
    }

    @FXML
    void azioneGEImporta(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.csv"));
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            String filePath = file.getAbsolutePath();
            try {
                Elenco elenco = archiviazioneElenchi.importElenco(filePath);
                gestoreElenchi.aggiungiElenco(elenco);
                cmbGEelenco.getItems().clear();
                cmbGEelenco.getItems().addAll(gestoreElenchi.getNomi());
                aggiornaElenco();
                txtMessage.setText("Elenco importato correttamente");
            } catch (Exception e) {
                txtMessage.setText("Errore nell'importazione dell'elenco: " + e.getMessage());
            }
        }
    }

    @FXML
    void azioneGENuovoEl(ActionEvent event) {
        Elenco elenco = null;
        try {
            elenco = new Elenco(txtGEnomeEl.getText());
	        gestoreElenchi.aggiungiElenco(elenco);
	        cmbGEelenco.getItems().clear();
	        cmbGEelenco.getItems().addAll(gestoreElenchi.getNomi());
	        txtMessage.setText("Elenco creato correttamente");
	        txtGEnomeEl.clear();
	        aggiornaElenco();
        } catch (NomeNonValidoException e) {
            // TODO Auto-generated catch block
            txtMessage.setText("Errore nella creazione dell'elenco:"+e.getMessage());
        }
    }

    @FXML
    void azioneGEAdd(ActionEvent event) {
        Entita entita = null;
        try {
            entita = new Entita(txtGENome.getText());
	        gestoreElenchi.aggiungiEntitaAdElenco(cmbGEelenco.getValue(), entita);
	        txtMessage.setText("Entità aggiunta correttamente");
	        txtGENome.clear();
	        aggiornaElenco();
        } catch (NomeNonValidoException e) {
            // TODO Auto-generated catch block
           txtMessage.setText("Errore nella creazione dell'entità:"+e.getMessage());
        }
    }

    @FXML
    void azioneGEModifica(ActionEvent event) {
        try {
            if(gestoreElenchi.modificaEntitaInElenco(cmbGEelenco.getValue(), txtGENome.getText(), new Entita(txtGENomeMod.getText()))){
                txtMessage.setText("Entità modificata correttamente");
                txtGENomeMod.clear();
                txtGENome.clear();
            }
            else {
                txtMessage.setText("Errore nella modifica dell'entità");
            }
        } catch (NomeNonValidoException e) {
            // TODO Auto-generated catch block
            txtMessage.setText("Errore nella creazione dell'entità:"+e.getMessage());
        }
        aggiornaElenco();
    }
    
    @FXML
    void azioneGEEliminaElemento(ActionEvent event) {
        if(gestoreElenchi.rimuoviEntitaDaElenco(cmbGEelenco.getValue(), txtGENome.getText())){
            txtMessage.setText("Entità rimossa correttamente");
            txtGENome.clear();
        }
        else {
            txtMessage.setText("Errore nella rimozione dell'entità");
        }
        aggiornaElenco();
    }

   

    @FXML
    void azioneGEElRandomizzato(ActionEvent event) {
        Elenco elenco = gestoreElenchi.getElenco(cmbGEelenco.getValue());
        if(elenco != null) {
            estrazioni.add(new Estrazione("Elenco randomizzato", Randomizzatore.randomizeList(elenco.getElementi())));
            txtMessage.setText("Lista randomizzata");
        }
        else {
            txtMessage.setText("Elenco non trovato");
        }
        aggiornaCronologia();
    }

    @FXML
    void azioneGEEstrazione(ActionEvent event) {
        Elenco elenco = gestoreElenchi.getElenco(cmbGEelenco.getValue());
        if(elenco != null) {
            estrazioni.add(new Estrazione("Estrazione", Randomizzatore.estraiElemento(elenco.getElementi())));
            txtMessage.setText("Entità estratta");
        }
        else {
            txtMessage.setText("Elenco non trovato");
        }
        aggiornaCronologia();
    }

    @FXML
    void azioneLancioMoneta(ActionEvent event) {
        String risultato = Randomizzatore.testaOCroce();
        
        Timeline timeline = new Timeline();

        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), e -> {
            imgTestaCroce.setImage(new Image(getClass().getResource("/images/testaCroce/3.png").toString()));
        }));

        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(2), e -> {
            imgTestaCroce.setImage(new Image(getClass().getResource("/images/testaCroce/2.png").toString()));
        }));

        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(3), e -> {
            imgTestaCroce.setImage(new Image(getClass().getResource("/images/testaCroce/1.png").toString()));
        }));

        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(4), e -> {
            imgTestaCroce.setImage(new Image(getClass().getResource("/images/testaCroce/" + risultato + ".png").toString()));
            estrazioni.add(new Estrazione("Testa o Croce", risultato));
            aggiornaCronologia();
            txtMessage.setText("Estrazione effettuata");
        }));

        timeline.play();
    }

    @FXML
    void azionePswGenera(ActionEvent event) {
        try{
        String password = Randomizzatore.generatePassword(Integer.parseInt(txtPswNCar.getText()), radPswSp.isSelected());
        estrazioni.add(new Estrazione("Password", password));
        txtMessage.setText("Password generata correttamente");
        aggiornaCronologia();
        }catch(Exception e) {
            txtMessage.setText("Errore nella generazione della password:"+e.getMessage());
        }
    }

    @FXML
    void azioneRicaricaGBelenco(MouseEvent event) {
        aggiornaElenco();
    }

    @FXML
    void btnGESalva(ActionEvent event) {
        try {
            archiviazioneElenchi.salvaElenco(gestoreElenchi.getElenchi());
            archiviazioneEstrazioni.salvaEstrazione(estrazioni);
            txtMessage.setText("salvataggio avvenuto correttamente");
        } catch (Exception e) {
            txtMessage.setText("Errore nel salvataggio");
        }
    }

    @FXML
    void initialize() {
        assert btnCronEliminaTutto != null : "fx:id=\"btnCronEliminaTutto\" was not injected: check your FXML file 'Randomizzatore.fxml'.";
        assert btnCronEliminaUltima != null : "fx:id=\"btnCronEliminaUltima\" was not injected: check your FXML file 'Randomizzatore.fxml'.";
        assert btnCronEsporta != null : "fx:id=\"btnCronEsporta\" was not injected: check your FXML file 'Randomizzatore.fxml'.";
        assert btnCronImporta != null : "fx:id=\"btnCronImporta\" was not injected: check your FXML file 'Randomizzatore.fxml'.";
        assert btnGEAdd != null : "fx:id=\"btnGEAdd\" was not injected: check your FXML file 'Randomizzatore.fxml'.";
        assert btnGEElRandomizzato != null : "fx:id=\"btnGEElRandomizzato\" was not injected: check your FXML file 'Randomizzatore.fxml'.";
        assert btnGEElimina != null : "fx:id=\"btnGEElimina\" was not injected: check your FXML file 'Randomizzatore.fxml'.";
        assert btnGEEsporta != null : "fx:id=\"btnGEEsporta\" was not injected: check your FXML file 'Randomizzatore.fxml'.";
        assert btnGEEstrazione != null : "fx:id=\"btnGEEstrazione\" was not injected: check your FXML file 'Randomizzatore.fxml'.";
        assert btnGEImport != null : "fx:id=\"btnGEImport\" was not injected: check your FXML file 'Randomizzatore.fxml'.";
        assert btnGEModifica != null : "fx:id=\"btnGEModifica\" was not injected: check your FXML file 'Randomizzatore.fxml'.";
        assert btnGENuovoEl != null : "fx:id=\"btnGENuovoEl\" was not injected: check your FXML file 'Randomizzatore.fxml'.";
        assert btnGEsalva != null : "fx:id=\"btnGEsalva\" was not injected: check your FXML file 'Randomizzatore.fxml'.";
        assert btnPswGenera != null : "fx:id=\"btnPswGenera\" was not injected: check your FXML file 'Randomizzatore.fxml'.";
        assert btnTestaCroce != null : "fx:id=\"btnTestaCroce\" was not injected: check your FXML file 'Randomizzatore.fxml'.";
        assert cmbGEelenco != null : "fx:id=\"cmbGEelenco\" was not injected: check your FXML file 'Randomizzatore.fxml'.";
        assert imgTestaCroce != null : "fx:id=\"imgTestaCroce\" was not injected: check your FXML file 'Randomizzatore.fxml'.";
        assert lblTitle != null : "fx:id=\"lblTitle\" was not injected: check your FXML file 'Randomizzatore.fxml'.";
        assert radPswSp != null : "fx:id=\"radPswSp\" was not injected: check your FXML file 'Randomizzatore.fxml'.";
        assert txtGENome != null : "fx:id=\"txtGENome\" was not injected: check your FXML file 'Randomizzatore.fxml'.";
        assert txtGENomeMod != null : "fx:id=\"txtGENomeMod\" was not injected: check your FXML file 'Randomizzatore.fxml'.";
        assert txtGEnomeEl != null : "fx:id=\"txtGEnomeEl\" was not injected: check your FXML file 'Randomizzatore.fxml'.";
        assert txtMessage != null : "fx:id=\"txtMessage\" was not injected: check your FXML file 'Randomizzatore.fxml'.";
        assert txtPswNCar != null : "fx:id=\"txtPswNCar\" was not injected: check your FXML file 'Randomizzatore.fxml'.";

        archiviazioneElenchi = new ArchiviazioneElenchi();
        archiviazioneEstrazioni = new ArchiviazioneEstrazioni();
        gestoreElenchi = new GestoreElenchi();
        estrazioni = new ArrayList<Estrazione>();

        try {
            estrazioni = archiviazioneEstrazioni.leggiEstrazioni();
            gestoreElenchi.aggiungiTutti(archiviazioneElenchi.leggiElenchi());
        } catch (Exception e) {
            txtMessage.setText("Errore nel caricamento iniziale dei dati:"+e.getMessage());
        }
        aggiornaCronologia();
        aggiornaElenco();

       cmbGEelenco.getItems().addAll(gestoreElenchi.getNomi());
       cmbGEelenco.setOnAction(e -> aggiornaElenco());
    }

}
