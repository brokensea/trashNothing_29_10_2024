import React, { useState } from 'react';

export default function Product_CreatePage() {
    const [titel, setTitel] = useState('');
    const [beschreibung, setBeschreibung] = useState('');
    const [anzahl, setAnzahl] = useState('');
    const [preis, setPreis] = useState('');
    const [zustand, setZustand] = useState('');
    const [marke, setMarke] = useState('');
    const [lieferung, setLieferung] = useState(false);
    const [imgUrl, setImgUrl] = useState('');
    const [deleteUrl, setDeleteUrl] = useState('');
    const [kategorie, setKategorie] = useState('');
    const [benutzerId, setBenutzerId] = useState('');
    const handleCreateProduct = async () => {
        const productData = {
            titel,
            beschreibung,
            anzahl,
            preis: parseFloat(preis),
            zustand,
            marke,
            lieferung,
            imgUrl,
            deleteUrl,
            kategorie,
            benutzerId: parseInt(benutzerId),
            anzeigentyp
        };
        try {
            const response = await fetch('/api/v1/product', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(productData)
            });

            if (response.ok) {
                alert('Produkt erfolgreich erstellt!');
            } else {
                alert('Fehler beim Erstellen des Produkts.');
            }
        } catch (error) {
            console.error('Error:', error);
            alert('Ein Fehler ist aufgetreten.');
        }
    };
        return (
            <div className="product-page-container" style={{ backgroundColor: '#FFFFFF', padding: '20px' }}>
                <h2>Neues Produkt erstellen</h2>

                <div>
                    <label>Titel</label>
                    <input
                        type="text"
                        value={titel}
                        onChange={(e) => setTitel(e.target.value)}
                        placeholder="Title"
                        className="product-input"
                    />
                </div>

                <div>
                    <label>Beschreibung</label>
                    <textarea
                        value={beschreibung}
                        onChange={(e) => setBeschreibung(e.target.value)}
                        placeholder="Beschreibung"
                        className="product-input"
                    />
                </div>

                <div>
                    <label>Anzahl</label>
                    <input
                        type="number"
                        value={anzahl}
                        onChange={(e) => setAnzahl(e.target.value)}
                        placeholder="Anzahl"
                        className="product-input"
                    />
                </div>

                <div>
                    <label>Preis</label>
                    <input
                        type="number"
                        step="0.01"
                        value={preis}
                        onChange={(e) => setPreis(e.target.value)}
                        placeholder="Preis"
                        className="product-input"
                    />
                </div>

                <div>
                    <label>Zustand</label>
                    <input
                        type="text"
                        value={zustand}
                        onChange={(e) => setZustand(e.target.value)}
                        placeholder="Zustand"
                        className="product-input"
                    />
                </div>

                <div>
                    <label>Marke</label>
                    <input
                        type="text"
                        value={marke}
                        onChange={(e) => setMarke(e.target.value)}
                        placeholder="Marke"
                        className="product-input"
                    />
                </div>

                <div>
                    <label>Lieferung</label>
                    <input
                        type="checkbox"
                        checked={lieferung}
                        onChange={(e) => setLieferung(e.target.checked)}
                    /> Ja
                </div>

                <div>
                    <label>Bild</label>
                    <input
                        type="text"
                        value={imgUrl}
                        onChange={(e) => setImgUrl(e.target.value)}
                        placeholder="Bild"
                        className="product-input"
                    />
                </div>

                <div>
                    <label>Kategorie</label>
                    <select
                        value={kategorie}
                        onChange={(e) => setKategorie(e.target.value)}
                        className="product-input"
                    >
                        <option value="">Wählen Sie eine Kategorie</option>
                 
                        <option value="ELEKTRONIK">Elektronik</option>
                        <option value="MOEBEL">Möbel</option>
                        <option value="KLEIDUNG">Kleidung</option>
                    </select>
                </div>

                <div>
                    <label>Benutzer ID</label>
                    <input
                        type="number"
                        value={benutzerId}
                        onChange={(e) => setBenutzerId(e.target.value)}
                        placeholder="Benutzer ID"
                        className="product-input"
                    />
                </div>

                <button onClick={handleCreateProduct} className="create-product-button">
                    Produkt erstellen
                </button>
            </div>
        );
    };
