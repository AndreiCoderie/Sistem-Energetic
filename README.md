# Sistem-Energetic
Retea complexa de curent cu mai multe entitati.

Pentru a citi datele din fisierul de in m-am folosit de o abordare
asemanatoare cu cea de la Tema1. 
Am o clasa de Input in care sunt definite tipurilor campurilor
in care vor fi stocate bucati din date.
In clasa InputLoader are un camp de timp string acre va fi pus path si o metoda,
read data in care citesc efectiv datele folosindu-ma de proprietatile
oferite de jsonParser si intoarce un obiect de tip Input.
 Mai departe, in clasa main voi creea un obiect 
de tip InputLoader care primeste ca argument in costructor args[0] ( path dat de voi
in clasa Test)
creez si un obiect de timp input prin intermediu instantei inputLoader si al clase 
readData.
Creez liste pentru distribuitori, consumatori dar si un map in care voi pune
Integer ( numarul rundei curente) si un array de schimbari lunare ( newCons si costsChanges).
Evident si aceste clase sunt creeate de mai devreme pentru a face citirea.
Pun elementele din input in aceste 2 liste si map, pe care le voi prelucra mai departe.


---------------- ALG PENTRU CELE IMPLEMENTATE


Creez un map in care la valoare pun preturile din luna respectiva, iar ca si cheie
luna respectiva, dupa care aleg cel mai mic pret din luna respectiva. Indexul acestuia
il pastrez intrun array, punandu-l mereu pe pozitia 0 in acel array. (indexul pentru
cel mai mic pret din arrayul de preturi din mapa va fi egal si cu indexu distribuitorului
(intrucat se face cate un pret pentru fiecare distribuitori, arrayul de preturi
fiind mereu egal cu arrayul de distribuitori ca lungime).
Dupa ce am salvat indecele cu cea mai mica rata, dau remove din distribuitori contractele
care s-au incheiat.
Dupa aceea adaug consumatori noi daca este lista de noi consumatori din cadrul mapului
de schimbari nu este nulla.
Dupa care parcurg lista de distribuitori si in cazul in care se ajunge la distribuitorul
care are rata cea mai mica se fac contracte noi, daca este cazul * Daca consumatorul
nu are deja un contract, daca distribuitorul nu este in faliment, etc*. Dupa care consumatorul
isi plateste rata, aici adaugand o metoda care calculeza bugetul consumatorului dupa luna curenta
unde se fac verificari daca isi permite, daca nu. Se verifica daca consumatorul si-a permis
sa o plateasca sau nu si in functie de raspuns se calculeaza si bugetul distribuitorului, din
nou, cu metode noi. * Aici se calculeaza bugetul pe luna respectiva, fara a se scadea si infrastructura
,aceea scanzandu-se la final *. Se verifica daca consumatorul este in faliment, iar daca da
contractul cu acel consumator este reziliat.
Daca distribuitorul n-are indicele celui cu cea mai mica rata, se fac aceleasi calcule, mai putin
cea cu noile contracte. Dupa ce a trecut de acest bracket if-else, se scade si infrastructura
din bugetul consumatorului si se verifica daca acestea este bankrup, eliminandu-se toate contractele
in cazul afirmativ. 
Dupa asta se reinitilaizasa contractStatusul ( nr de contracte ) al fiecarui distribuitor,
si se pastreaza ultimul pret din contractului unui consumator.

Dupa care se face scrierea in fisier, fac cate un LinkedHashMap pentru consumatori
si distribuitori * pentru a se pastra ordinea * pe care le pun in cate un jsonArray.
La final mai fac un linked map pentru final output in care pun cele doua jsonArrayuri.
Ma folosesc de ObjectMapper pentru a avea un format de tip json.

UPDATE ETAPA 2:

M-am folosit de factory design in partea de citire a datelor * consumatori si distribuitori *, folosind efectiv clasa de factory
doar pentru a dauga noi consumatorii, consumatorii respectiv distribuitorii. Singleteon pattern
este folosit in cadrul factory cu metoda getInstance.

Toata functionalitata din etapa 1 s-a pastrat. S-au adaugat producatorii si si strategia
de alegere a producatorilor pentru Distrib. Am folosit Strategy pattern aici, facand o interfanta comuna
pentru clase, cat si clasele respective unde este implementata logica fiecarui pattern de algere
a producatorilor care se bazeaza in special pe sortari.
