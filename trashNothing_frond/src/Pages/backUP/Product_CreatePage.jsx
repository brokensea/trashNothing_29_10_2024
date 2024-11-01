import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import './css/Product_CreatePage.css';
import axios from 'axios';

const ZUSTAND_ENUM = ["NEU", "WIE_NEU", "GEBRAUCHSSPUREN"]; 
const KATEGORIE_ENUM = ["KLEIDUNG", "MOEBEL", "SPIELZEUG"];
export default function Product_CreatePage() {
    const navigate = useNavigate(); 
    const [selectedImage, setSelectedImage] = useState(null);
    const [previewUrl, setPreviewUrl] = useState(null);
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
    const [imgUrl, setImgUrl] = useState('');

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
    
    const handleImageChange = (event) => {
        const file = event.target.files[0];
        if (file) {
            setSelectedImage(file);
           
            setPreviewUrl(URL.createObjectURL(file));
        }
    };
    const handleCreateProduct = async (event) => { 
        event.preventDefault();
    
        if (!selectedImage) {
            toast.error("Bitte wählen Sie ein Bild aus.");
            return;
        }
        if (!titel || !beschreibung || !anzahl || !preis || !marke || !kategorie) {
            toast.error("Bitte füllen Sie alle erforderlichen Felder aus.");
            return;
        }
        if (!anzahl || parseInt(anzahl, 10) <= 0) {
            toast.error("Bitte geben Sie eine gültige Anzahl ein.");
            return;
        }
    
        // Здесь объявляем FormData
        const formData = new FormData();
        formData.append("imgUrl", selectedImage);
        formData.append("lieferung", lieferung);
        formData.append("titel", titel);
        formData.append("beschreibung", beschreibung);
        formData.append("marke", marke);
        formData.append("anzahl", parseInt(anzahl, 10));
        formData.append("preis", parseFloat(preis));
        formData.append("zustand", zustand.trim().toUpperCase());
        formData.append("kategorie", kategorie.trim().toUpperCase());
        formData.append("benutzerId", parseInt(benutzerId, 10));
    
        try {
            // Отправка данных на сервер
            const uploadResponse = await axios.post('http://localhost:8080/api/v1/product/withImage', formData, {
                headers: {
                    'Content-Type': 'multipart/form-data',
                    'Authorization': 'Bearer ' + localStorage.getItem('token'),
                }
            });
    
            if (uploadResponse.status === 200) {
                console.log('Produkt erfolgreich erstellt!');
                toast.success("Produkt erfolgreich erstellt!");
                setTimeout(() => {
                    navigate('/marktplatz');
                }, 1300); 
            } else {
                console.error('Fehler beim Erstellen des Produkts:', uploadResponse.data);
                toast.error("Fehler beim Erstellen des Produkts: " + (uploadResponse.data.message || uploadResponse.data.error));
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
                <div>
            <div className="form-group">
                <label>Bilder hochladen:</label>
                <input type="file" accept="image/*" onChange={handleImageChange} />
          
            </div>
            
            {previewUrl && (
                <div className="image-preview">
                    <img src={previewUrl} alt="preview" width="100" height="100" />
                </div>
            )}
            
            <input type="hidden" value={imgUrl} />
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