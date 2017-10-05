# Master 1 ICE, GLa - Validation et Vérification - Labs
## BOUVERON Maxime - RAILLARD Pol-Hervé

### Tests Unitaires

**Q.1b** En effet, si un test utilise d'autres opérations, nous ne pouvons pas détecter d'où peut venir un éventuel soucis. Pour parer à ce problème, nous pouvons tester toutes les opérations externes utilisées par un test, dans ce cas si, `getX()` et `getY()`

**Q.1c** Pour ces tests, getX et setX peuvent être réunis dans un même tests setGetX. En executant les tests nous pouvons nous rendre compte que tous les tests ne passent pas : setGetY ne passe pas car dans le code de la classe MyPoint, il y a `x = newY;` au lieu de `y = newY;` dans le setter de y.
Pour le tests concernant le `Double.NaN`, dans les commentaires de la classe il est écrit qu'un setter ne doit rien effectuer si il reçoit Double.NaN, hors, d'après les tests, celui-ci set Double.NaN et le renvoie.

**Q.1d** Il y a plusieurs cas à tester pour celui-ci. Pour le point qui égal à `null`, la javadoc précise qu'il doit renvoyer le point (0,0), chose qu'il ne fait pas et qui fait donc planter le test.

**Q.1e** L'opération `@Before setUp()` s'executant avant chaque tests, on peut donc créer un nouveau `MyPoint` dans celui-ci et y accèder depuis les tests.

**Q.1f** La méthode `horizontalSimmetry()` ne fonctionne pas avec des arguments corrects car le `setY()` n'est pas fonctionnel. Avec un argument `null` cependant, la bonne exception est lancée.

### Couverture de code

**Q.2a** En executant les tests avec rapport de couverture de tests, IntelliJ nous donne le pourcentage de classes, de méthodes et de ligne couvertes. Pour les méthodes, ce qui nous intéresse le plus, 56% sont déjà couverts (9/16). Le pourcentage de lignes couvertes est de 28% on peut donc rajouter des tests simples pour les méthodes restantes.
Après avoir rajouté quelques tests, on est à une couverture de lignes de 65% et de 93% des méthodes (15/16).

**Q.2b** Si toutes les instructions sont couvertes par les tests, le code n'e st pas pour autant sûr : en effet, la qualité des tests rentre en compte et les tests unitaires ne testent pas exemple pas les interractions entre les différentes classes (ce qui ne s'applique pas ici).

**Q.2c** Le test provoquant une exception, il est nécessaire de rajouter `@Test(expected = IllegalArgumentException.class)` au début du test afin de prendre en compte celle-ci. De nombreux cas sont possibles et il est quasiment impossible de tous les vérifier, il faut donc en tester un maximum afin d'avoir une certaine certitude.

### Test d'intégration pour Java avec Mockito

**Q.3a** Il est impossible de tester un nombre aléatoire, en effet, celui-ci pouvant prendre n'importe quelle valeur, ce n'est pas prédictible et donc difficilement testable. Avec un framework de mock il sera donc possible de définir la valeur aléatoire et donc tester si la méthode fait bien l'action demandée.

**Q.3b** On peut générer un faux nombre aléatoire, en utilisant cette ligne : `when(random1.nextInt()).thenReturn(2);`, ceci va remplacer la valeur de retour de la méthode `.nextInt()` par la valeur passée en paramètre de `thenReturn()`, permettant ainsi de vérifier si c'est bien la valeur qui est affectée au point.

**Q.3c** On sait que la méthode `translate(ITranslation)` utilise l'objet ITranslation en appellant `translation.getTx()` et `translation.getTy()`, on peut donc utiliser Mockito pour faire en sorte que ces méthodes renvoient la valeur souhaitée avec un faux objet ITranslation : `when(this.translation.getTx()).thenReturn(1);` 

