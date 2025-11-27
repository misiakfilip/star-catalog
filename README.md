# Star Catalog Project (Java)

## Project Description

This project implements a Java application for managing a star catalog. The main class, `Gwiazda`, represents individual stars with detailed astronomical parameters. The application allows adding, removing, and searching stars while maintaining consistency in catalog naming.

Each star has the following properties:

* **Name** – a unique identifier consisting of 3 uppercase letters followed by 4 digits.
* **Catalog Name** – a designation using Greek letters (alpha, beta, gamma, etc.) followed by the constellation name. Names are automatically assigned based on the order stars are added to a constellation.
* **Declination** – celestial coordinate ranging from 0° to 90° (Northern Hemisphere) or 0° to -90° (Southern Hemisphere), expressed in degrees, minutes, and seconds.
* **Right Ascension** – celestial coordinate ranging from 00h to 24h, expressed in hours, minutes, and seconds.
* **Apparent Magnitude** – brightness of the star in magnitudes, ranging from -26.74 (Sun) to 15.00.
* **Absolute Magnitude** – intrinsic brightness calculated using the formula:
  `M = m - 5 * log10(r) + 5`
  where `m` is apparent magnitude and `r` is distance in parsecs (1 parsec = 3.26 light years).
* **Distance** – distance from Earth in light years.
* **Constellation** – the constellation where the star is located.
* **Hemisphere** – Northern or Southern hemisphere.
* **Temperature** – in degrees Celsius (minimum 2000°C, no maximum).
* **Mass** – in solar masses (0.1 – 50).

The application ensures that catalog names remain consistent when stars are removed (e.g., deleting "beta" updates subsequent names to fill the gap).

## Features

* Add new stars with validation for all fields.
* Remove stars and automatically update catalog names.
* Display all stars in the catalog.
* Search stars by:

  * Constellation
  * Distance from Earth
  * Temperature range
  * Apparent magnitude range
  * Hemisphere
  * Potential supernova candidates (mass > 1.44 solar masses)
* All data are stored in object files for persistence.

## Project Structure

```
|   build.xml
|   listaGwiazd.ser
|   listaGwiazd.txt
|   manifest.mf
|
+---build
|   \---classes
|       \---gwizady
|               Gwizady$Deklinacja.class
|               Gwizady$Gwiazda.class
|               Gwizady$Rektascensja.class
|               Gwizady.class
|
+---nbproject
|   ... NetBeans project configuration files ...
|
+---src
|   \---gwizady
|           Gwizady.java
|
\---test
```

## Technologies

* Java (tested with NetBeans IDE)
* Object serialization for data persistence (`.ser` files)

## How to Run

1. Open the project in NetBeans.
2. Build the project using **Clean and Build**.
3. Run the main class `Gwizady`.
4. Use the console-based menu to add, remove, search, or display stars.

## Author

Filip Misiak

## License

This project is licensed under the MIT License.
