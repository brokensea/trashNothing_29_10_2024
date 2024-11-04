import React, { useState, useEffect } from "react";
import "./css/Marktplatz.css";

const ZUSTAND = ["NEU", "WIE_NEU", "GEBRAUCHSSPUREN"];
const KATEGORIE = ["KLEIDUNG", "MOEBEL", "SPIELZEUG"];

export default function WishListPage() {
  const [searchTerm, setSearchTerm] = useState("");
  const [allProducts, setAllProducts] = useState([]);
  const [products, setProducts] = useState([]);
  const [selectedZustand, setSelectedZustand] = useState([]);
  const [selectedKategorie, setSelectedKategorie] = useState([]);
  const [deliveryOption, setDeliveryOption] = useState(false);
  const [pickupOption, setPickupOption] = useState(false);

  const fetchProducts = async () => {
    const benutzerId = localStorage.getItem("benutzerId");
    if (!benutzerId) {
      console.error("Benutzer ID is not found in localStorage");
      return;
    }
    try {
      const response = await fetch(
        `http://localhost:8080/api/v1/product/AddToWishlist/user/${benutzerId}`,
        {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        }
      );
      if (!response.ok) {
        throw new Error("Network response was not ok");
      }
      const data = await response.json();

      setAllProducts(data || []);
      setProducts(data || []);
    } catch (error) {
      console.error("Error fetching products:", error);
    }
  };

  useEffect(() => {
    fetchProducts();
  }, []);

  const handleZustandChange = (zustand) => {
    setSelectedZustand((prev) =>
      prev.includes(zustand)
        ? prev.filter((item) => item !== zustand)
        : [...prev, zustand]
    );
  };

  const handleKategorieChange = (kategorie) => {
    setSelectedKategorie((prev) =>
      prev.includes(kategorie)
        ? prev.filter((item) => item !== kategorie)
        : [...prev, kategorie]
    );
  };
  const handleSearch = async () => {
    let url = "http://localhost:8080/api/v1/produkte/search";

    if (searchTerm && selectedKategorie.length > 0) {
      url += `?title=${encodeURIComponent(
        searchTerm
      )}&kategorie=${encodeURIComponent(selectedKategorie.join(","))}`;
    } else if (searchTerm) {
      url += `/searchByTitle?title=${encodeURIComponent(searchTerm)}`;
    } else if (selectedKategorie.length > 0) {
      url += `/searchByKategorie?kategorie=${encodeURIComponent(
        selectedKategorie.join(",")
      )}`;
    }

    try {
      const response = await fetch(url, {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
      });
      if (!response.ok) {
        throw new Error("Network response was not ok");
      }
      const data = await response.json();
      setProducts(data);
    } catch (error) {
      console.error("Error fetching products:", error);
    }
  };

  const applyFilters = () => {
    const filteredProducts = allProducts.filter(
      (product) =>
        (selectedZustand.length === 0 ||
          selectedZustand.includes(product.zustand)) &&
        (selectedKategorie.length === 0 ||
          selectedKategorie.includes(product.kategorie)) &&
        (!deliveryOption || product.lieferung) &&
        (!pickupOption || !product.lieferung)
    );
    setProducts(filteredProducts);
  };

  return (
    <>
      <section className="background_section_m">
        <h1 className="main_heading">Diese Artikel hättest du gerne</h1>
        
        <div className="search_area">
          <input
            type="text"
            placeholder="Suche nach Produkt..."
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
            className="search_input"
          />
          <button className="post_button" onClick={handleSearch}>
            Produkt einstellen
          </button>
        </div>
      </section>

      <div className="product_listing_container">
        <aside className="sidebar">
          <h3>Kategorien</h3>
          <div className="checkbox_list">
            {KATEGORIE.map((kategorie, index) => (
              <label key={index}>
                <input
                  type="checkbox"
                  checked={selectedKategorie.includes(kategorie)}
                  onChange={() => handleKategorieChange(kategorie)}
                />
                {kategorie}
              </label>
            ))}
          </div>

          <h3>Zustand</h3>
          <div className="checkbox_list">
            {ZUSTAND.map((zustand, index) => (
              <label key={index}>
                <input
                  type="checkbox"
                  checked={selectedZustand.includes(zustand)}
                  onChange={() => handleZustandChange(zustand)}
                />
                {zustand}
              </label>
            ))}
          </div>
          <h3>DeliveryOptions </h3>
          <label>
            <input
              type="checkbox"
              checked={deliveryOption}
              onChange={() => setDeliveryOption(!deliveryOption)}
            />
            Lieferung möglich
          </label>
          <label>
            <input
              type="checkbox"
              checked={pickupOption}
              onChange={() => setPickupOption(!pickupOption)}
            />
            Abholung
          </label>

          <button className="apply_button" onClick={applyFilters}>
            Anwenden
          </button>
          <button
            className="reset_button"
            onClick={() => setProducts(allProducts)}
          >
            Reset
          </button>
        </aside>

        <div className="product_listings">
          {products.length > 0 ? (
           products.map((product, index) => (
            <div key={product.id || index} className="product_card">
                <img
                  src={product.imgUrl}
                  alt={product.titel}
                  className="product_image"
                />
                <div className="product_info">
                  <h4 className="product_price">
                    {product.preis.toFixed(2)} EUR
                  </h4>
                  <p className="product_title">{product.titel}</p>
                  <p className="product_details">{product.beschreibung}</p>
                  <p className="product_location">
                    {product.lieferung ? "Lieferung möglich" : "Abholung"}
                  </p>
                </div>
                <div className="product_actions">
                  <button className="details_button">Details</button>
                  <label className="wishlist_label">
                    <input type="checkbox" /> Auf die Wunschliste
                  </label>
                </div>
              </div>
            ))
          ) : (
            <p className="no_products_message">Keine Produkte gefunden.</p>
          )}
        </div>
      </div>
    </>
  );
}
