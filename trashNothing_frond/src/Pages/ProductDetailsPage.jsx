import React, { useState, useEffect } from "react";
import './css/ProductDetailsPage.css';
import { useParams } from "react-router-dom";
export default function ProductDetailsPage() {
    const { productId } = useParams(); 
    const [product, setProduct] = useState(null);
    const [loading, setLoading] = useState(true);
    

    const fetchProductDetails = async () => {
        try {
            const token = localStorage.getItem('token');
            const response = await fetch(`http://localhost:8080/api/v1/produkte/${productId}`, {
                headers: {
                    Authorization: "Bearer " + localStorage.getItem("token"),
                }
            });
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            const data = await response.json();
            setProduct(data);
        } catch (error) {
            console.error("Error fetching product details:", error);
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchProductDetails();
    }, [productId]);

    if (loading) {
        return <p>Loading...</p>;
    }

    if (!product) {
        return <p>Produkt nicht gefunden.</p>;
    }

    const handleEdit = () => {
        console.log("Bearbeiten Button clicked");
    };

    const handleSold = () => {
        console.log("Verkauft Button clicked");
    };

    const handleWishlist = () => {
        console.log("Auf die Wunschliste Button clicked");
    };

    return (
        <section className="background_section_details">
        <div className="product-details">
            <div className="content">
                <div className="product-image">
                    <img src={product.imgUrl} alt={product.titel} />
                    <div className="product-buttons">
                        <button onClick={handleEdit} className="edit-button">Bearbeiten</button>
                        <button onClick={handleSold} className="sold-button">Verkauft</button>
                    </div>
                </div>
                <div className="product-info">
                    <h1>{product.titel}</h1>
                    <p className="product-price">{product.preis.toFixed(2)} EUR</p>
                    <p>Zustand: {product.zustand}</p>
                    <p>Marke: {product.marke}</p>
                    <p>Lieferung: {product.lieferung ? "Ja" : "Nein"}</p>
                    <p>Anzahl: {product.anzahl} stk.</p>
                    <button onClick={handleWishlist} className="wishlist-button">Auf die Wunschliste ❤️</button>
                    <h2>Produktbeschreibung</h2>
                    <p className="product-description">{product.beschreibung}</p>
                </div>
            </div>
            </div>
            </section>
    );
}