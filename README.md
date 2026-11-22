# Poweramp Glass Dark (skin v3)

Skin para Poweramp v3 basado en el tema oscuro nativo (`ActivityTheme_Black`), creado a partir del
módulo oficial `poweramp_skin_sample` de [maxmpz/powerampapi](https://github.com/maxmpz/powerampapi).

Solo cambia:
1. **La isla inferior (navbar + miniplayer)**: fondo oscuro translúcido + brillo superior + borde fino de 1dp.
2. **Popups, menús flotantes y diálogos**: fondo oscuro translúcido (`colorBgPrimary`).
   En Android 12+ los diálogos además desenfocan de verdad el fondo (`windowBlurBehind`).

Variantes (cada una es un skin propio en Poweramp → Ajustes → Tema):
- **Glass Dark**: oscuro plano al 92%, sin bordes.
- **Glass Dark · Translúcido**: 82%.
- **Glass Dark · AMOLED**: negro puro.
- **Glass Dark · Contorno**: como Glass Dark con una línea muy fina.

## Por qué no hay opciones dentro del skin
Poweramp (build 1026+) trata como **no verificado** a todo skin cuyo certificado no esté en su lista blanca:
las opciones solo viven en memoria (no se guardan), no hay página de opciones y el skin se deselecciona
a las 48 h. Para verificarlo hay que firmar con una clave de release fija y enviar el paquete + huella SHA-256
al desarrollador de Poweramp (ver `glass_skin/readme.md`, sección *Skin Verification*).

## Limitación (motor de skins)
El motor de skins de Poweramp **no expone desenfoque en tiempo real detrás de una vista** (backdrop blur).
Los únicos blur del SDK son los del fondo de carátula (`aaBlur*`) y `shadeBlur*`, que están comentados/no expuestos.
Por eso la isla inferior y los menús usan una aproximación (translucidez + brillo + borde); el blur real solo
es posible en los diálogos (son ventanas propias) mediante el blur de ventana de Android 12+.

## Compilar
- **GitHub Actions**: cada push genera el APK → pestaña *Actions* → artefacto `poweramp-glass-dark-apk`.
- **Local**: Android Studio / `./gradlew :glass_skin:assembleDebug` (JDK 17, Android SDK 36).
  APK en `glass_skin/build/outputs/apk/debug/`.
