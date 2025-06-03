# CarRepairsSystem-MiniProject

**CarRepairsSystem-MiniProject** to aplikacja konsolowa stworzona w celu zarządzania warsztatem samochodowym. Umożliwia rejestrację klientów, przeglądanie dostępnych usług oraz umawianiem wizyt.

## 🎯 Cel projektu

Celem projektu jest stworzenie prostego systemu do zarządzania naprawami samochodowymi, który pozwala na:

- Rejestrację i logowanie klientów
- Przeglądanie dostępnych usług warsztatu
- Umawianie wizyt na naprawy
- Zarządzanie harmonogramem przez pracowników
- Przeglądanie historii napraw

Projekt został zrealizowany w ramach nauki technologii Java oraz praktyki w tworzeniu warstw aplikacji.

## 🛠️ Technologie

W projekcie wykorzystano następujące technologie:

- **Java 17** – główny język programowania
- **Maven** – system zarządzania zależnościami i budowania projektu
- **Lombok** – redukcja kodu boilerplate

## 📁 Struktura projektu

```
CarRepairsSystem-MiniProject/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── szylica/
│   │   │           └── carrepairssystem/
│   │   │               ├── controllers/
│   │   │               ├── models/
│   │   │               ├── repositories/
│   │   │               └── services/
│   │   └── resources/
│   │       ├── templates/
│   │       └── application.properties
├── pom.xml
└── README.md
```

## 🚀 Uruchamianie projektu

Aby uruchomić aplikację lokalnie:

1. Upewnij się, że masz zainstalowane **Java 17** i **Maven**.
2. Sklonuj repozytorium:

   ```bash
   git clone https://github.com/szylica/CarRepairsSystem-MiniProject.git
   ```

3. Przejdź do katalogu projektu:

   ```bash
   cd CarRepairsSystem-MiniProject
   ```

4. Uruchom aplikację za pomocą Mavena:

   ```bash
   mvn spring-boot:run
   ```

5. Otwórz przeglądarkę i przejdź do `http://localhost:8080`

## 📌 Funkcjonalności

- Rejestracja i logowanie klientów
- Przeglądanie dostępnych usług
- Umawianie wizyt na naprawy
- Przeglądanie historii napraw

## 🤝 Współpraca

Projekt jest otwarty na sugestie i współpracę. Jeśli masz pomysły na ulepszenia lub chcesz zgłosić błędy, proszę otwórz [issue](https://github.com/szylica/CarRepairsSystem-MiniProject/issues) lub stwórz [pull request](https://github.com/szylica/CarRepairsSystem-MiniProject/pulls).

## 📄 Licencja

Ten projekt jest objęty licencją MIT – zobacz plik [LICENSE](LICENSE) po więcej informacji.
