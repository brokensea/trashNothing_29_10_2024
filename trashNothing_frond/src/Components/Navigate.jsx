import { Link, Outlet } from 'react-router-dom';
export default function Navigate() {
    return (
        <div>
            <header className="bg-gray-800 text-white p-4">
                <nav className="space-x-4">
                    <Link to="/" className="hover:underline">Homepage</Link>
                    <Link to="/signup" className="hover:underline">Registrieren</Link>
                    <Link to="/login" className="hover:underline">Login</Link> {/* 添加登录链接 */}
                    <Link to="/marktplatz" className="hover:underline">Marktplatz</Link>
                    <Link to="/createproduct" className="hover:underline">Product Create</Link>
                    <Link to="/soldproduct" className="hover:underline">Product Sold</Link>
                    <Link to="/detailsproduct" className="hover:underline">Wishlist</Link>
                    <Link to="/wischlist" className="hover:underline">Product Details</Link>
                </nav>
            </header>
            
        </div>
    );
}