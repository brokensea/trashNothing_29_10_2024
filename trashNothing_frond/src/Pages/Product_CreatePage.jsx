import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import './css/Product_CreatePage.css';

const ZUSTAND_ENUM = ["NEU", "WIE_NEU", "GEBRAUCHSSPUREN"]; 
const KATEGORIE_ENUM = ["KLEIDUNG", "MOEBEL", "SPIELZEUG"];
export default function Product_CreatePage() {
    const navigate = useNavigate(); 
    const [lieferung, setLieferung] = useState(false);
    const [titel, setTitel] = useState('');
    const [beschreibung, setBeschreibung] = useState('');
    const [anzahl, setAnzahl] = useState('');
    const [preis, setPreis] = useState('');
    const [zustand, setZustand] = useState(ZUSTAND_ENUM[0]);
    const [marke, setMarke] = useState('');
    const [kategorie, setKategorie] = useState(KATEGORIE_ENUM[0]);
    const [plz, setPlz] = useState('');
    const [ort, setOrt] = useState('');
    const [strasse, setStrasse] = useState('');
    const [name, setName] = useState('');
    const [telefonnummer, setTelefonnummer] = useState('');
    const benutzerId = localStorage.getItem('benutzerId');
    useEffect(() => {
        const fetchUserDetails = async () => {
            try {
                const response = await fetch(`http://localhost:8080/api/v1/benutzerDetail/${benutzerId}`, {
                    headers: {
                        'Authorization': 'Bearer ' + localStorage.getItem('token'),
                    }
                });

                if (response.ok) {
                    const data = await response.json();
                   
                    setPlz(data.plz);
                    setOrt(data.ort);
                    setStrasse(data.addressStrasse);
                    setName(data.name);
                    setTelefonnummer(data.handynummer);
                } else {
                    console.error("Fehler beim Abrufen der Benutzerdaten.");
                }
            } catch (error) {
                console.error("Es ist ein Fehler aufgetreten:", error);
            }
        };
        if (benutzerId) {
            fetchUserDetails();
        }
    }, [benutzerId]);

    const handleCreateProduct = async (event) => { 
        event.preventDefault(); 
        const productData = {
            lieferung,
            titel,
            beschreibung,
            marke,
            anzahl: parseInt(anzahl, 10), 
            preis: parseFloat(preis), 
            zustand: zustand.trim().toUpperCase(),
            kategorie: kategorie.trim().toUpperCase(),
            benutzerId: parseInt(benutzerId, 10)
        };
        console.log("Product data:", productData);
    
        try {
            const response = await fetch('http://localhost:8080/api/v1/product', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + localStorage.getItem('token'),
                },
                body: JSON.stringify(productData)
            });
    
            if (response.ok) {
                console.log('Produkt erfolgreich erstellt!');
                toast.success("Produkt erfolgreich erstellt!");
                setTimeout(() => {
                    navigate('/marktplatz');
                }, 1300); 
            
            } else {
                const errorData = await response.json(); 
                console.error('Fehler beim Erstellen des Produkts:', errorData);
                toast.error("Fehler beim Erstellen des Produkts: " + (errorData.message || errorData.error));
            }
        } catch (error) {
            toast.error("Es ist ein Fehler aufgetreten.");
            console.error('Error:', error);
        }
    };

    
    return (
        <div className="form-container">
            <form className="form" onSubmit={handleCreateProduct}>
                <div className="form-group">
                <label>Lieferung:</label>
                <div className="lieferung-options">
                    <label>
                        <input
                            type="radio"
                            name="lieferung"
                            value="ja"
                            checked={lieferung}
                            onChange={() => setLieferung(true)}
                        />{" "}
                        Ja
                    </label>
                    <label>
                        <input
                            type="radio"
                            name="lieferung"
                            value="nein"
                            checked={!lieferung}
                            onChange={() => setLieferung(false)}
                        />{" "}
                        Nein
                    </label>
                </div>
                </div>
                <div className="form-group">
                    <label>Titel der Anzeige:</label>
                    <input type="text" value={titel} onChange={(e) => setTitel(e.target.value)} />
                </div>
                <div className="form-group">
                    <label>Beschreibung:</label>
                    <textarea value={beschreibung} onChange={(e) => setBeschreibung(e.target.value)}></textarea>
                </div>
                <div className="form-group">
                    <label>Anzahl:</label>
                    <input type="number" value={anzahl} onChange={(e) => setAnzahl(e.target.value)} />
                </div>
                <div className="form-group">
                    <label>Preis:</label>
                    <div className="price-input">
                        <input type="text" value={preis} onChange={(e) => setPreis(e.target.value)} /> EUR
                    </div>
                </div>
                <div className="form-group">
                    <label>Zustand:</label>
                    <select value={zustand} onChange={(e) => setZustand(e.target.value)}>
                    {ZUSTAND_ENUM.map((option) => (
                            <option key={option} value={option}>{option}</option>
                        ))}
                    </select>
                </div>
                <div className="form-group">
                    <label>Marke:</label>
                    <textarea value={marke} onChange={(e) => setMarke(e.target.value)}></textarea>
                </div>
                <div className="form-group">
                    <label>Kategorie:</label>
                    <select value={kategorie} onChange={(e) => setKategorie(e.target.value)}>
                    {KATEGORIE_ENUM.map((option) => (
                            <option key={option} value={option}>{option}</option>
                        ))}
                    </select>
                </div>
                <div className='space_div'></div>
                <div className="form-group">
                <label>PLZ/Ort*:</label>
                <div className="plz-ort-container">
                    <input
                        type="text"
                        placeholder="PLZ"
                        value={plz}
                        onChange={(e) => setPlz(e.target.value)}
                    />
                    <input
                        type="text"
                        placeholder="Ort"
                        value={ort}
                        onChange={(e) => setOrt(e.target.value)}
                    />
                </div>
                </div>
                
                <div className="form-group">
                    <label>Straße/Nr.*:</label>
                    <input type="text" value={strasse} onChange={(e) => setStrasse(e.target.value)} />
                </div>
                <div className="form-group">
                    <label>Name*:</label>
                    <input type="text" value={name} onChange={(e) => setName(e.target.value)} />
                </div>
                <div className="form-group">
                    <label>Telefonnummer*:</label>
                    <input type="text" value={telefonnummer} onChange={(e) => setTelefonnummer(e.target.value)} />
                </div>
                <button type="submit" className="submit-button">Produkt einstellen</button>
                <ToastContainer />
            </form>
        </div>
    );
}
