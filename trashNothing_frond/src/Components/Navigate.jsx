import { Link, Outlet } from 'react-router-dom';
export default function Navigate() {
    return (
        <div className='navigate_box'>
            <header className="bg-gray-800 bg-white text-black p-4">
                <nav className="space-x-4">
                    <Link to="/" className="hover:underline">Homepage</Link>
                    <Link to="/registration" className="hover:underline">Registrieren</Link>
                    <Link to="/login" className="hover:underline">Login</Link> 
                    <Link to="/marktplatz" className="hover:underline">Marktplatz</Link>
                    <Link to="/createproduct" className="hover:underline">Product Create</Link>
                    <Link to="/soldproduct" className="hover:underline">Product Sold</Link>
<<<<<<< HEAD
                    <Link to="/detailsproduct" className="hover:underline">Product Details</Link>
                    <Link to="/wischlist" className="hover:underline">Wishlist</Link>
                    <Link to="/gekaufte" className="hover:underline">Gekauftlist</Link>
=======
                    <Link to="/wischlist" className="hover:underline">Wishlist</Link>
>>>>>>> 15423420d49cff45fc07f6e45c50327af0e344c0
                </nav>
            </header>

        </div>
    );
}