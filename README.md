# Randomizer

Applicazione JavaFX per la gestione di elenchi, cronologia estrazioni, password e testa o croce.

## Requisiti
- Java 17
- Maven
- Windows (la configurazione JavaFX del progetto usa il classifier `win`)

## Avvio
Dalla cartella del progetto:

```powershell
mvn clean package -DskipTests
mvn javafx:run
```

Se stai usando Eclipse, importa il progetto come **Maven Project** e avvia `application.Main`.

## Note
Le risorse sono caricate dal percorso `src` come root del classpath, quindi immagini, CSS e FXML devono restare nei percorsi attuali.
