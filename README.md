# Jegykassza

A 3 megvalósított user story a következő:

###Fejlesztőként szeretném, hogy a felhasználók hozzáférjenek a jelenleg elérhető bérletekhez/jegyekhez azért, hogy tudjanak vásárolni.
Az elérhető jegyek bérletek per pillanat az elérhető zónákra vonatkoznak.
Az ErvenyessegZona osztályban szereplő helyekre lehet jegyet/bérletet venni. A bérletek egyelőre egységesen 1000 a jegyek 200 ft-ba kerülnek.
A lekérés a Berlet / Jegy Controller osztály getAll metodusain keresztül érhető el amely visszaadja az összes elérhető helységet, és az árat.


###Fejlesztőként szeretném, hogy a bérlet és jegy vásárlási adatok rendelkezésre álljanak, amiből statisztika készíthető a járatok optimalizálására.
###Fejlesztőként szeretném, hogy a felhasználók különböző fizetési módozatok (bankkártya, PayPal.. stb) közül választhassanak, hogy az általuk preferált módon tudjanak fizetni.
A fizetés a következő képpen zajlik:

A vásárló kiválaszt egy terméket mondjuk: Bérlet, Országos.
Ezt beküldi a BerletController post végpontjára. Ekkor létrejön egy Bérlet tétel amely még nem aktív, a fizetés státusza PENDING.
A válaszban érkező azonosítóval a fizetési módnak megfelelő helyre kell behívni - PaymentController -  payByCreditCard, payByPaypal, payByCash.
Ekkor megtörténik a fizetés, létrejön egy fizetési tétel, és aktiválódik a bérlet.

A payment controller read all metódusa visszaadja az összes fizetést amelyekből statisztika készíthető.

