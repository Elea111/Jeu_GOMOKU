projet R304

## NOTE 12

## CORRECTION
Je ne vois nulle part le projet porte sur Go ou Gomoku, il faut lire le code ou le diagramma d'architecture. Meme le nom du projet ne le dit pas ! Super pour tester.

Si les consignes ne sont pas assez claires vous avez eu de nombreux TD plus discord pour poser des questions !

Il y a très peu de tests.

Il est décevant qu'en 2e année vous ne soyez pas capable de prendre un id facultatif en début de commande ! C'était à faire avant se lancer dans des choses compliquées comme une intercace  IJeuPlateau qui n'était pas du tout demandée ni IMode qui peut se comprendre mais était tout à fait facultative. Dans l'industrie, les clients apprécient très peu qu'on considère une partie du cahier des charges comme facultative surtout pour faire autre chose et quand cela n'a rien d'insurmontable.

Il aurait éyé appréciable de mettre les noms complets des membres pour faciliter la saisie des notes.

Vous imposez une talle de plateau minimal à 5 alors que j'avais dit 3 pour le gomoku. Vous me compliquez la tâche et une nouvelle consigne non respectée. Pire la taille est vérifié dans le code par des nombres magiques !

A quoi sert removePiece sur un Gomoku. Par ailleurs il n'y aucune pièce mais des pierres.

Que fait la méthode compteAlignement dans Botminimax ? La notion d'alignement ne concerne pas que minimax ! Elle contient par ailleurs la nombre d'alignements comme un nombre magique !

Membres du groupe :
Faiza 203, Nathan 203, Rayane 203, Eléa 203

INTRODUCTION :

Nous avons réussi à créer deux modes : un mode interactif, où l'interface est faite pour jouer normalement, avec des messages interactifs qui guident l'utilisateur, le plateau qui reste toujours affiché et des indications à chaque étape ; l'autre mode est un mode GTP, avec les messages d'erreur ou de validation formels du protocole GTP et où il faut entrer les commandes GTP, cela dans le but de pouvoir voir où sont les potentiels problèmes au cours d'une partie. Pour basculer entre les deux modes, il suffit à n'importe quel moment de la partie d'entrer "switch" pour passer en mode GTP ou en mode partie interactive (un message s'affiche alors pour indiquer le mode dans lequel nous nous trouvons). De plus, nous avons un botNaif ainsi qu'un botMinimax qui peut recevoir une profondeur allant jusqu'à 5, après quoi le code devient très lent. Nous avons réalisé tout cela en essayant de respecter au maximum les principes SOLID vus en cours. Il n’est cependant pas possible de mettre des numéros avant une commande en mode GTP.



DIAGRAMME D'ARCHITECTURE :
//www.plantuml.com/plantuml/png/dPJVwjCm5CVlynI7_kvaaquGL4QP8im9bLrufnDYUwPnJLAQ18JX4_a8tkt9NDTaRO-Rz9ViPac-vvFpvvokZEMDTR9YZDMyEF1l27N5BN873r-T4GgDEJAXB3PxNY1itgDxYrilMIwXlyeoLbJS6F2tx5VF_8437XmPG2TTQwaF8sLzzaSW4G_VrmvTGryUQt9T8bNqJwAnGgjMOww7EHTgw8Li2RSSBhIKN9Mu_hZjZVM_-qtxD2-is8_Iq3b_2_n6srme8VdFuT47VWQ4_JidlPkz62vh_kaXJLyyIzDPoSrtB7VpBt1DbL_Cuy9-cj4mMm2GB19uSZPy_hBzpUgA2xMRPv1qaIq-gqKIpMYJ_hgm3giMuyi8zp3G8uB_6OHQ1ADNa9n-5mroYwS_oIG4AHz1J0x6Jz4baZ0k8PAkw-6_bI2J1gohFZokVFd0v6x0f9j8m5CHeRTY4IInCGYIrnX4o78yWbIR8aY3tYuZQNrY5DTqfAqZ9fao9ZfFZ6AS3oXoV3y6x31qXa8UdsQZ3tduxw-ydh4LgjB9wYy0



TESTS UNITAIRES :
  Tests de base sur le plateau :
       - Création du plateau : On vérifie que le plateau est bien généré à la bonne taille.
       - Placement et récupération des pièces : On s'assure que les pièces peuvent être posées et récupérées correctement sur le plateau.
       - Réinitialisation du plateau : On teste que le plateau est bien vidé quand on en a besoin.

  Tests des parties contre les bots :

       - Partie avec le Bot Minimax : On simule une partie entre un humain et le Bot Minimax. Le bot réfléchit à ses coups en fonction d’une profondeur donnée.

       - Partie avec le Bot Naïf : On simule une partie entre un humain et le Bot Naïf, qui joue de manière aléatoire.

Résultats :
  Tous les tests passent sans problème. On a pu vérifier que le plateau fonctionne bien (création, placement des pièces, réinitialisation) et que les interactions avec les bots se déroulent correctement.

Ces tests couvrent les scénarios principaux (création du plateau, gestion des coups, parties contre les bots). Il y a encore des cas qu’on pourrait tester, comme des scénarios très spécifiques ou des tests de performances pour le bot Minimax avec une grande profondeur.
    
Je ne comprends pas ce que veut dire "Bot Minimax Noir (X), entrez votre coup (ex: A1) :" !


BILAN DU PROJET : 

Le projet avait plutôt bien commencé. En effet, nous avons fait le maximum pour respecter les principes SOLID, mais au fur et à mesure que les tâches s'accumulaient, il devenait de plus en plus compliqué de faire en sorte que la totalité du code soit SOLID.
Mais cela nous a aussi permis de mieux appréhender ces principes, tout en gardant un code fonctionnel. Nous avons réussi à faire en sorte qu'une partie soit jouable, que le botMinimax soit fonctionnel avec un système de profondeur. Nous avons cependant trouvé les consignes assez vagues, notamment en ce qui concernait la deuxième partie du projet (la partie où il faut passer en mode non-GTP, et plus récemment, l'explication du bot minimax avec sa notion de profondeur).
