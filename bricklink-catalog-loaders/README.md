## bricklink-catalog-loaders

This module is responsible for loading and processing catalog data from Bricklink. It contains utilities for
importing, transforming, or managing LEGO set/catalog information.

See [Bricklink LEGO Catalog Database Download](https://www.bricklink.com/catalogDownload.asp) for more details on
available data.

## Example Usage (Find Majisto!)

```java
BlinkMinifigureCatalogLoader loader = new BlinkMinifigureCatalogLoader();
Optional<BlinkMinifigure> minifig = loader.stream().filter(part -> "cas019".equals(part.getNumber())).findFirst();

assertTrue(minifig.isPresent());

assertEquals("Majisto Wizard",minifig.get().

getName());
```