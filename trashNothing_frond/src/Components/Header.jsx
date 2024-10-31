import { Link, Outlet } from 'react-router-dom';
import './Header.css';
export default function Header() {
    return (
        <div className="header-container">
            <Link to="/" className="logo">nothingtotrash</Link>
            <div className="nav-container">
                <Link to="/marktplatz">Marktplatz</Link>
                <Link to="/uber-uns">Über uns</Link>
            </div>
            <div className="right-container">
            <Link to="/login">
                    <button>Log in</button>
                </Link>
                <Link to="/registration">
                    <button className='register_button'>Registriere Dich</button>
                    </Link>
            </div>
        </div>
    );
}
