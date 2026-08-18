# PageFlip — L'IA et sa répercussion sur l'homme

Application Android (Kotlin) qui affiche ton livre PDF page par page.
**Un tap sur la moitié droite de l'écran** tourne la page vers l'avant, avec une
vraie animation de tournage de page (rotation façon livre). **Un tap sur la
moitié gauche** revient à la page précédente, avec la même animation en sens
inverse.

## Comment ça marche

- `app/src/main/assets/pages/` contient les 60 pages du livre, déjà converties
  en images PNG (une image = une page).
- `PageAdapter.kt` charge ces images dans un `ViewPager2`.
- `FlipPageTransformer.kt` est le cœur de l'animation : il fait pivoter
  chaque page autour de son bord vertical (comme une vraie page de livre qui
  se soulève), avec un léger assombrissement pour donner de la profondeur.
- `MainActivity.kt` pose deux zones invisibles (gauche/droite) par-dessus le
  `ViewPager2` : un tap dedans appelle `viewPager.setCurrentItem(page, true)`,
  ce qui déclenche l'animation de la page qui tourne.

## Ouvrir le projet dans Android Studio

1. Télécharge et dézippe `PageFlip.zip`.
2. Ouvre **Android Studio** → **File > Open...** → sélectionne le dossier
   `pageflip` (celui qui contient `settings.gradle.kts`).
3. Android Studio va détecter qu'il manque le Gradle Wrapper : clique sur
   **"OK"**/**"Create Gradle Wrapper"** s'il te le propose, ou laisse le
   projet se synchroniser avec le Gradle intégré à l'IDE (première ouverture
   un peu plus longue, c'est normal).
4. S'il te demande un `local.properties` avec le chemin du SDK Android,
   laisse-le faire automatiquement (c'est le cas la plupart du temps).
5. Branche un téléphone Android (mode débogage USB activé) ou lance un
   émulateur, puis clique sur **Run ▶**.

## Remplacer les images des pages

Les images viennent d'un export de ton PDF en PNG (150 dpi). Si tu modifies
le livre, régénère les images et remets-les dans
`app/src/main/assets/pages/` en respectant le nom `page-01.png`,
`page-02.png`, etc. (l'app les trie par nom, donc garde le zéro devant les
numéros à un chiffre).

## Personnalisation rapide

- **Sens des pages inversé (BD/manga, droite → gauche) :** dans
  `MainActivity.kt`, inverse simplement les listeners `tapNext`/`tapPrev`.
- **Vitesse/allure de l'animation :** `ViewPager2` utilise l'animation de
  scroll standard ; pour un flip plus lent ou plus marqué, ajuste les valeurs
  dans `FlipPageTransformer.kt` (par ex. l'angle `90f` ou l'assombrissement).
- **Va-et-vient au doigt (swipe) :** le swipe horizontal classique du
  `ViewPager2` fonctionne aussi en plus du tap, avec la même animation.
