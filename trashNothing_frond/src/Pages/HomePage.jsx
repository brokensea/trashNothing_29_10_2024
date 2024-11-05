import './css/Homepage.css';
import { Link, Outlet, useNavigate } from 'react-router-dom';

export default function HomePage() {
    return (
        <div className="homepage">
            <section className="upper_section">
                <article className='upper_article'>
                    <h1>Hilf mit die Umwelt zu schützen</h1>
                    <p>
                        Abfälle bedrohen Vögel, Delfine und Co. Mehr als zehn Millionen Tonnen Abfälle gelangen jährlich in die  Ozeane. Sie kosten Abertausende Meerestiere das Leben. Seevögel verwechseln Plastik mit natürlicher Nahrung, Delfine verfangen sich in alten Fischernetzen. Hilf mit Müll zu reduzieren und trashnothing.
                    </p>
                    <Link to="/marktplatz">
                            <button className='button_starteJetzt'>Starte jetzt!</button>
                        </Link>
                </article>
            </section>
            
            <section className="Lebe_eCommerce">
                <h2>Lebe eCommerce mal anders</h2>
                <article className="article_left">
                    <h3>Verkaufen statt wegwerfen</h3>
                    <p>
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Risus faucibus egestas neque, quis nunc in turpis cursus eget.
                    </p>
                </article>
                <article className="article_mid">
                    <h3>Verschenke und Schütze</h3>
                    <p>
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Risus faucibus egestas neque, quis nunc in turpis cursus eget.
                    </p>
                </article>
                <article className="article_right">
                    <h3>Der Umwelt zuliebe</h3>
                    <p>
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Risus faucibus egestas neque, quis nunc in turpis cursus eget.
                    </p>
                </article>
            </section>

            <section className="purple_section">
                <article className="ohne_limits_purple">
                    <h3>Ohne Limits</h3>
                    <p>
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Risus faucibus egestas neque, quis nunc in turpis cursus eget.
                    </p>
                    <button className="zur_Doku">Zur Dokumentation</button>
                </article>
                <article className="Community_purple">
                    <h3>Kenn deine Community</h3>
                    <p>
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Risus faucibus egestas neque, quis nunc in turpis cursus eget.
                    </p>
                    <button className="zur_Doku">Zur Dokumentation</button>
                </article>
            </section>

            <section className="last_Section">
                <h2>Von echten Menschen unterstützt</h2>
                <p>
                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Risus faucibus egestas neque, quis nunc in turpis cursus eget.
                </p>
            </section>
        </div>
    );
}